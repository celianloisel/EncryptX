package menus.encryption;

import menus.Menu;
import menus.MenuController;
import menus.encryption.rc4.RC4Menu;
import utils.encryption.caesar;
import utils.encryption.vigenere;
import utils.encryption.Polybius;
import utils.encryption.Enigma;
import java.util.List;
import java.util.Scanner;

/**
 * The type Encrypt Or Decrypt Sequence Menu.
 */
public class EncryptOrDecryptSequenceMenu implements Menu {

    private final Scanner scanner;
    private final MenuController menuController;
    private List<String> encryptionSequence;

    /**
     * Instantiates a new Encrypt or decrypt sequence menu.
     *
     * @param menuController the menu controller
     */
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
                executeSequence();
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

    private void executeSequence() {
        // Checks if a message is already stored, otherwise requests the initial message
        if (menuController.getMessage() == null) {
            System.out.print("Entrez le message à chiffrer : ");
            String message = scanner.nextLine();
            menuController.setMessage(message);
        }

        String message = menuController.getMessage();

        // Execute each algorithm in sequence
        for (String algorithm : encryptionSequence) {
            switch (algorithm) {
                case "César":
                    System.out.println("Application de César...");
                    System.out.print("Entrez le décalage pour César : ");
                    int shift = scanner.nextInt();
                    scanner.nextLine(); // Consomme le retour à la ligne
                    caesar.cesarEncryption(message, shift);
                    break;
                case "Vigenère":
                    System.out.println("Application de Vigenère...");
                    System.out.print("Entrez la clé pour Vigenère : ");
                    String keyVigenere = scanner.nextLine();
                    vigenere.vigenereEncryption(message, keyVigenere);
                    break;
                case "Polybe":
                    System.out.println("Application de Polybe...");
                    message = Polybius.polybiusCipher(message);
                    break;
                case "Enigma":
                    System.out.println("Application de Enigma...");
                    message = Enigma.enigmaEncrypt(message);
                    break;
                case "RC4":
                    System.out.println("Application de RC4...");
                    RC4Menu rc4Menu = new RC4Menu(menuController);
                    rc4Menu.processEncryption();
                    break;
                default:
                    System.out.println("Algorithme non supporté : " + algorithm);
                    break;
            }
            System.out.println("Message après " + algorithm + " : " + message);
        }

        // Update final message in MenuController
        menuController.setMessage(message);
        System.out.println("Message final après séquence : " + message);
    }
}