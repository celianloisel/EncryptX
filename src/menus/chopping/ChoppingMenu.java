package menus.chopping;

import menus.Menu;
import menus.MenuController;
import menus.integrity.EnsuringIntegrity;
import menus.MenuUtils;

import java.util.Scanner;

/**
 * The type Chopping menu.
 */
public class ChoppingMenu implements Menu {

    private final Scanner scanner;
    private final MenuController menuController;

    /**
     * Instantiates a new Chopping menu.
     *
     * @param menuController the menu controller
     */
    public ChoppingMenu(MenuController menuController) {
        this.scanner = new Scanner(System.in);
        this.menuController = menuController;
    }

    @Override
    public void display() {
        System.out.println("\n\n==== MENU DE HACHAGE ====");
        System.out.println("1. MD5");
        System.out.println("2. SHA-256");
        System.out.println("6. Aide");
        System.out.println("7. Retour");
        System.out.println("8. Quitter");
    }

    @Override
    public void handleInput() {
        int choice = MenuUtils.getValidatedIntInput(scanner);

        menuController.clearHashAlgorithm();

        switch (choice) {
            case 1:
                System.out.println("Hachage avec MD5 sélectionné.");
                menuController.setHashAlgorithm("MD5");
                if (menuController.getMessage() != null) {
                    menuController.setCurrentMenu(new EnsuringIntegrity(menuController));
                } else {
                    menuController.setCurrentMenu(new menus.inputs.InputMessage(menuController));
                }
                break;
            case 2:
                System.out.println("Hachage avec SHA-256 sélectionné.");
                menuController.setHashAlgorithm("SHA-256");
                if (menuController.getMessage() != null) {
                    menuController.setCurrentMenu(new EnsuringIntegrity(menuController));
                } else {
                    menuController.setCurrentMenu(new menus.inputs.InputMessage(menuController));
                }
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