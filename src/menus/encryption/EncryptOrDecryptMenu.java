package menus.encryption;

import menus.Menu;
import menus.MenuController;
import menus.inputs.InputMessage;
import utils.encryption.rc4.RC4Encryption;
import menus.MenuUtils;
import utils.encryption.rc4.HexUtils;

import java.util.Scanner;

/**
 * The type Encrypt or decrypt menu.
 */
public class EncryptOrDecryptMenu implements Menu {

    private final Scanner scanner;
    private final MenuController menuController;

    /**
     * Instantiates a new Encrypt or decrypt menu.
     *
     * @param menuController the menu controller
     */
    public EncryptOrDecryptMenu(MenuController menuController) {
        this.scanner = new Scanner(System.in);
        this.menuController = menuController;
    }

    @Override
    public void display() {
        System.out.println("\n\n==== Choix d'utilisation  ====");
        System.out.println("1. Chiffrer");
        System.out.println("2. Déchiffrer");

        System.out.println("\n");
        System.out.println("6. Aide");
        System.out.println("7. Retour");
        System.out.println("8. Quitter");
    }

    @Override
    public void handleInput() {
        int choice = MenuUtils.getValidatedIntInput(scanner);

        switch (choice) {
            case 1:
                System.out.println("Le chiffrement a été sélectionné");
                processEncryption();
                break;
            case 2:
                System.out.println("Le déchiffrement a été sélectionné.");
                processDecryption();
                break;
            case 6:
                MenuUtils.displayHelp();
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

    private void processEncryption() {
        String encryptionMethod = menuController.getEncryptionMethod();

        if ("RC4".equals(encryptionMethod)) {
            System.out.print("Entrez la clé : ");
            String key = scanner.nextLine();
            RC4Encryption rc4 = new RC4Encryption(key);

            System.out.print("Entrez le texte à chiffrer : ");
            String plaintext = scanner.nextLine();
            byte[] encrypted = rc4.encryptDecrypt(plaintext.getBytes());

            // Encodes encrypted data in hexadecimal for display
            String encryptedText = HexUtils.bytesToHex(encrypted);
            System.out.println("Texte chiffré (RC4) : " + encryptedText);
        } else {
            // Other generic encryption logic or for other algorithms
            menuController.setCurrentMenu(new InputMessage(menuController));
        }
    }

    private void processDecryption() {
        String encryptionMethod = menuController.getEncryptionMethod();

        if ("RC4".equals(encryptionMethod)) {
            System.out.print("Entrez la clé : ");
            String key = scanner.nextLine();
            RC4Encryption rc4 = new RC4Encryption(key);

            System.out.print("Entrez le texte à déchiffrer (en hexadécimal) : ");
            String hexCiphertext = scanner.nextLine();

            // Convert ciphertext from hexadecimal to bytes
            byte[] ciphertext = HexUtils.hexToBytes(hexCiphertext);
            byte[] decrypted = rc4.encryptDecrypt(ciphertext);
            String decryptedText = new String(decrypted);

            System.out.println("Texte déchiffré (RC4) : " + decryptedText);
        } else {
            // Other generic decryption logic or for other algorithms
            menuController.setCurrentMenu(new InputMessage(menuController));
        }
    }
}