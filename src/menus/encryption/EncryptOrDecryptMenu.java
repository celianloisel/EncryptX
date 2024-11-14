package menus.encryption;

import menus.Menu;
import menus.MenuController;
import utils.MenuUtils;

import java.util.Scanner;

/**
 * The type Encrypt Or Decrypt menu.
 */
public class EncryptOrDecryptMenu implements Menu {

    private final Scanner scanner;
    private final MenuController menuController;

    /**
     * Instantiates a new Encrypt Or Decrypt menu.
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
                menuController.setCurrentMenu(new menus.inputs.InputMessage(menuController));
                break;
            case 2:
                System.out.println("Le déchiffrement a été sélectionné.");
                menuController.setCurrentMenu(new menus.inputs.InputMessage(menuController));
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