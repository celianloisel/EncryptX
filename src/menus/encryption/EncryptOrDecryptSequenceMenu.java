package menus.encryption;

import menus.Menu;
import menus.MenuController;
import utils.encryption.caesar;
import utils.encryption.vigenere;
import utils.encryption.Polybius;
import utils.encryption.Enigma;
import menus.encryption.rc4.RC4Menu;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class EncryptOrDecryptSequenceMenu implements Menu {

    private final Scanner scanner;
    private final MenuController menuController;
    private List<String> encryptionSequence;
    private boolean isEncrypting;

    public EncryptOrDecryptSequenceMenu(MenuController menuController) {
        this.scanner = new Scanner(System.in);
        this.menuController = menuController;
        this.encryptionSequence = menuController.getEncryptionSequence();
    }

    @Override
    public void display() {
        System.out.println("\n\n==== Exécution de la Séquence de Chiffrement ====");
        System.out.println("Séquence d'algorithmes à appliquer : " + encryptionSequence);
        System.out.println("1. Exécuter la séquence sur un message");
        System.out.println("7. Retour");
        System.out.println("8. Quitter");
    }

    @Override
    public void handleInput() {
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                chooseMode();
                break;
            case 7:
                menuController.goBack();
                break;
            case 8:
                System.out.println("Merci d'avoir utilisé l'application. À bientôt !");
                menuController.stop();
                break;
            default:
                System.out.println("Option invalide, veuillez réessayer.");
                break;
        }
    }

    private void chooseMode() {
        int modeChoice = -1; // Initialisation avec une valeur invalide

        while (modeChoice != 1 && modeChoice != 2) {
            System.out.println("\nVoulez-vous chiffrer ou déchiffrer?");
            System.out.println("1. Chiffrer");
            System.out.println("2. Déchiffrer");
            System.out.print("Entrer 1 ou 2: ");

            if (scanner.hasNextInt()) { // Vérifie si l'entrée est un entier
                modeChoice = scanner.nextInt();
                scanner.nextLine(); // Consomme le reste de la ligne

                if (modeChoice == 1) {
                    isEncrypting = true;
                    System.out.println("Mode : Chiffrement");
                } else if (modeChoice == 2) {
                    isEncrypting = false;
                    System.out.println("Mode : Déchiffrement");
                } else {
                    System.out.println("Choix invalide, veuillez entrer 1 ou 2.");
                }
            } else {
                System.out.println("Entrée invalide, veuillez entrer un nombre (1 ou 2).");
                scanner.nextLine(); // Consomme l'entrée invalide
            }
        }

        executeSequence();
    }

    private void executeSequence() {
        if (menuController.getMessage() == null) {
            System.out.print("Entrez le message à traiter : ");
            String message = scanner.nextLine();
            menuController.setMessage(message);
        }

        String message = menuController.getMessage();

        for (String algorithm : encryptionSequence) {
            message = captureAlgorithmOutput(algorithm, message);
            System.out.println("Message après " + algorithm + " : " + message);
        }

        menuController.setMessage(message);
        System.out.println("Message final après séquence : " + message);
    }

    /**
     * Capture the output of an algorithm that uses System.out.print to return a result as a String.
     */
    private String captureAlgorithmOutput(String algorithm, String message) {
        try {
            switch (algorithm) {
                case "César":
                    System.out.println("Application de César...");
                    System.out.print("Entrez le décalage pour César : ");
                    int shift = scanner.nextInt();
                    scanner.nextLine();
                    return isEncrypting
                            ? caesar.cesarEncryption(message, shift)
                            : caesar.cesarDecryption(message, shift);

                case "Vigenère":
                    System.out.println("Application de Vigenère...");
                    System.out.print("Entrez la clé pour Vigenère : ");
                    String keyVigenere = scanner.nextLine();
                    return isEncrypting
                            ? vigenere.vigenereEncryption(message, keyVigenere)
                            : vigenere.vigenereDecryption(message, keyVigenere);

                case "Polybe":
                    System.out.println("Application de Polybe...");
                    return isEncrypting
                            ? Polybius.polybiusCipher(message)
                            : Polybius.polybiusDecipher(message);

                case "Enigma":
                    System.out.println("Application de Enigma...");
                    Enigma enigma = new Enigma();
                    return enigma.cipher(message);

                case "RC4":
                    System.out.println("Application de RC4...");
                    System.out.print("Entrez la clé pour RC4 : ");
                    RC4Menu rc4Menu = new RC4Menu(menuController);
                    rc4Menu.processEncryption();
                    return message;

                default:
                    System.out.println("Algorithme non supporté : " + algorithm);
                    return message;
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de l'application de l'algorithme : " + e.getMessage());
            return message;
        }
    }
}