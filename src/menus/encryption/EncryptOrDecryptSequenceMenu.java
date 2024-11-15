package menus.encryption;

import menus.Menu;
import menus.MenuController;
/*import menus.encryption.algorithms.CaesarMenu;
import menus.encryption.algorithms.VigenereMenu;
import menus.encryption.algorithms.PolybeMenu;
import menus.encryption.algorithms.EnigmaMenu;*/
import menus.encryption.rc4.RC4Menu;
import java.util.List;
import java.util.Scanner;

/**
 * The type Encrypt Or Decrypt Sequence Menu.
 */
public class EncryptOrDecryptSequenceMenu implements Menu {

    private final Scanner scanner;
    private final MenuController menuController;
    private List<String> encryptionSequence;

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
        scanner.nextLine(); // Consommer le retour à la ligne

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
        // Vérifie si un message est déjà stocké, sinon demande le message initial
        if (menuController.getMessage() == null) {
            System.out.print("Entrez le message à chiffrer : ");
            String message = scanner.nextLine();
            menuController.setMessage(message);
        }

        // Exécute chaque algorithme dans l'ordre de la séquence
        for (String algorithm : encryptionSequence) {
            switch (algorithm) {
/*                case "César":
                    System.out.println("Application de César...");
                    CaesarMenu caesarMenu = new CaesarMenu(menuController);
                    caesarMenu.processEncryption();
                    break;
                case "Vigenère":
                    System.out.println("Application de Vigenère...");
                    VigenereMenu vigenereMenu = new VigenereMenu(menuController);
                    vigenereMenu.processEncryption();
                    break;
                case "Polybe":
                    System.out.println("Application de Polybe...");
                    PolybeMenu polybeMenu = new PolybeMenu(menuController);
                    polybeMenu.processEncryption();
                    break;
                case "Enigma":
                    System.out.println("Application de Enigma...");
                    EnigmaMenu enigmaMenu = new EnigmaMenu(menuController);
                    enigmaMenu.processEncryption();
                    break;*/
                case "RC4":
                    System.out.println("Application de RC4...");
                    RC4Menu rc4Menu = new RC4Menu(menuController);
                    rc4Menu.processEncryption();
                    break;
                default:
                    System.out.println("Algorithme non supporté : " + algorithm);
                    break;
            }
        }

        // Affiche le message final après application de tous les algorithmes
        System.out.println("Message final après séquence : " + menuController.getMessage());
    }
}