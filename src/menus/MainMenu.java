package menus;

import java.util.Scanner;

import menus.chopping.ChoppingMenu;
import menus.encryption.EncryptionMenu;
import menus.gnpa.GNPAMenu;
import utils.MenuUtils;

/**
 * The type Main menu.
 */
public class MainMenu implements Menu {

    private final Scanner scanner;
    private final MenuController menuController;

    /**
     * Instantiates a new Main menu.
     *
     * @param menuController the menu controller
     */
    public MainMenu(MenuController menuController) {
        this.scanner = new Scanner(System.in);
        this.menuController = menuController;
    }

    @Override
    public void display() {
        System.out.println("\n\n==== MENU PRINCIPAL ====");
        System.out.println("1. Chiffrement");
        System.out.println("2. Hachage");
        System.out.println("3. Steganographie");
        System.out.println("4. GNPA");
        System.out.println("5. La Totale");
        System.out.println("6. Aide");
        System.out.println("7. Retour");
        System.out.println("8. Quitter");
    }

    @Override
    public void handleInput() {
        int choice = MenuUtils.getValidatedIntInput(scanner);

        switch (choice) {
            case 1:
                System.out.println("Redirection vers le menu de Chiffrement...");
                menuController.setCurrentMenu(new EncryptionMenu(menuController));
                break;
            case 2:
                System.out.println("Redirection vers le menu de Hachage...");
                menuController.setCurrentMenu(new ChoppingMenu(menuController));
                // Hash menu
                break;
            case 3:
                System.out.println("Redirection vers le menu de Steganographie...");
                // Steganography menu
                break;
            case 4:
                System.out.println("Redirection vers le menu GNPA...");
                menuController.setCurrentMenu(new GNPAMenu(menuController));
                // GNPA menu
                break;
            case 5:
                System.out.println("Redirection vers le menu 'La Totale'...");
                // Full menu
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