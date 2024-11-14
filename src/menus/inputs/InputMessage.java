package menus.inputs;

import menus.Menu;
import menus.MenuController;
import menus.integrity.EnsuringIntegrity;
import menus.MenuUtils;

import java.util.Scanner;

/**
 * The type InputMessage menu.
 */
public class InputMessage implements Menu {

    private final Scanner scanner;
    private final MenuController menuController;

    /**
     * Instantiates a new InputMessage menu.
     *
     * @param menuController the menu controller
     */
    public InputMessage(MenuController menuController) {
        this.scanner = new Scanner(System.in);
        this.menuController = menuController;
    }

    @Override
    public void display() {
        System.out.println("\n\n==== Message ====");
        System.out.println("6. Aide");
        System.out.println("7. Retour");
        System.out.println("8. Quitter");

        if (menuController.getMessage() != null) {
            System.out.println("Le message actuel est : " + menuController.getMessage());
            System.out.println("Vous pouvez procéder au hachage ou choisir une autre option.");
        } else {
            System.out.print("\nEntrer votre message ou choisissez une option (6, 7, 8) : ");
        }
    }

    @Override
    public void handleInput() {
        String input = scanner.nextLine();

        if (menuController.getMessage() == null) {
            if (!input.equals("6") && !input.equals("7") && !input.equals("8")) {
                menuController.setMessage(input);
                System.out.println("Message reçu : " + input);
                menuController.setCurrentMenu(new EnsuringIntegrity(menuController));
                return;
            }
        }

        switch (input) {
            case "6":
                MenuUtils.displayHelp();
                break;
            case "7":
                new MessageConfirmation(menuController).confirmMessageSave();
                break;
            case "8":
                System.out.println("Merci d'avoir utilisé l'application. À bientôt !");
                menuController.stop();
                break;
            default:
                System.out.println("Option invalide, veuillez réessayer.");
                handleInput();
                break;
        }
    }
}