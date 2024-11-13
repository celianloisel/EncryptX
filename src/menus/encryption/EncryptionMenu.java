package menus.encryption;

import menus.Menu;
import menus.MenuController;
import utils.MenuUtils;

import java.util.Scanner;

/**
 * The type Encryption menu.
 */
public class EncryptionMenu implements Menu {

    private final Scanner scanner;
    private final MenuController menuController;

    /**
     * Instantiates a new Encryption menu.
     *
     * @param menuController the menu controller
     */
    public EncryptionMenu(MenuController menuController) {
        this.scanner = new Scanner(System.in);
        this.menuController = menuController;
    }

    @Override
    public void display() {
        System.out.println("\n\n==== MENU DE CHIFFREMENT ====");
        System.out.println("\nChiffrement classique");
        System.out.println("1. César");
        System.out.println("2. Vigenère");
        System.out.println("3. Polybe");

        System.out.println("\nChiffrement moderne");
        System.out.println("4. Enigma");
        System.out.println("5. RC4");

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
                System.out.println("Chiffrement avec César sélectionné.");
                menuController.setCurrentMenu(new EncryptOrDecryptMenu(menuController));
                // Logique de chiffrement César
                break;
            case 2:
                System.out.println("Chiffrement avec Vigenère sélectionné.");
                menuController.setCurrentMenu(new EncryptOrDecryptMenu(menuController));
                // Logique de chiffrement Vigenère
                break;
            case 3:
                System.out.println("Chiffrement avec Polybe sélectionné.");
                menuController.setCurrentMenu(new EncryptOrDecryptMenu(menuController));
                // Logique de chiffrement Polybe
                break;
            case 4:
                System.out.println("Chiffrement avec Enigma sélectionné.");
                menuController.setCurrentMenu(new EncryptOrDecryptMenu(menuController));
                // Logique de chiffrement Enigma
                break;
            case 5:
                System.out.println("Chiffrement avec RC4 sélectionné.");
                menuController.setCurrentMenu(new EncryptOrDecryptMenu(menuController));
                // Logique de chiffrement RC4
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
}