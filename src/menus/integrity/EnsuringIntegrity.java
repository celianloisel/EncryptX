package menus.integrity;

import menus.Menu;
import menus.MenuController;
import menus.chopping.ChoppingMenu;
import menus.MenuUtils;

import java.util.Scanner;

/**
 * The type Ensuring integrity.
 */
public class EnsuringIntegrity implements Menu {

    private final Scanner scanner;
    private final MenuController menuController;

    /**
     * Instantiates a new Ensuring integrity.
     *
     * @param menuController the menu controller
     */
    public EnsuringIntegrity(MenuController menuController) {
        this.scanner = new Scanner(System.in);
        this.menuController = menuController;
    }

    @Override
    public void display() {
        System.out.println("\n\n==== Menu d'intégrité ====");

        if (menuController.getMessage() != null) {
            System.out.println("Message actuel : " + menuController.getMessage());
        } else {
            System.out.println("Aucun message n'a été défini.");
        }

        System.out.println("Voulez-vous garantir l'intégrité de votre message en le hachant ?");
        System.out.println("1. Oui");
        System.out.println("2. Non");
        System.out.println("\n6. Aide");
        System.out.println("7. Retour");
        System.out.println("8. Quitter");
    }

    @Override
    public void handleInput() {
        int choice = MenuUtils.getValidatedIntInput(scanner);

        switch (choice) {
            case 1:
                System.out.println("Le Oui a été sélectionné");
                menuController.setCurrentMenu(new ChoppingMenu(menuController));
                break;
            case 2:
                System.out.println("Le Non a été sélectionné.");
                // Here we add message processing according to the user's needs
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