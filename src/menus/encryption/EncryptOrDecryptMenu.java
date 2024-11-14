package menus.encryption;

import menus.Menu;
import menus.MenuController;
import menus.inputs.InputMessage;
import utils.encryption.RC4Encryption;
import menus.MenuUtils;

import java.util.Scanner;

public class EncryptOrDecryptMenu implements Menu {

    private final Scanner scanner;
    private final MenuController menuController;

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
            byte[] encrypted = rc4.encrypt(plaintext);
            System.out.println("Texte chiffré (RC4) : " + new String(encrypted));
        } else {
            // Autre logique de chiffrement générique ou pour d'autres algorithmes
            menuController.setCurrentMenu(new InputMessage(menuController));
        }
    }

    private void processDecryption() {
        String encryptionMethod = menuController.getEncryptionMethod();

        if ("RC4".equals(encryptionMethod)) {
            System.out.print("Entrez la clé : ");
            String key = scanner.nextLine();
            RC4Encryption rc4 = new RC4Encryption(key);

            System.out.print("Entrez le texte à déchiffrer : ");
            String ciphertext = scanner.nextLine();
            String decrypted = rc4.decrypt(ciphertext.getBytes());
            System.out.println("Texte déchiffré (RC4) : " + decrypted);
        } else {
            // Autre logique de déchiffrement générique ou pour d'autres algorithmes
            menuController.setCurrentMenu(new InputMessage(menuController));
        }
    }
}