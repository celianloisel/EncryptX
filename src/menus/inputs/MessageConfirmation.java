package menus.inputs;

import menus.MenuController;
import java.util.Scanner;

/**
 * The type Message confirmation.
 */
public class MessageConfirmation {
    private final Scanner scanner;
    private final MenuController menuController;

    /**
     * Instantiates a new Message confirmation.
     *
     * @param menuController the menu controller
     */
    public MessageConfirmation(MenuController menuController) {
        this.scanner = new Scanner(System.in);
        this.menuController = menuController;
    }

    /**
     * Confirm message save.
     */
    public void confirmMessageSave() {
        System.out.println("Voulez-vous enregistrer le message avant de revenir au menu précédent ?");
        System.out.println("1. Oui");
        System.out.println("2. Non");

        String input = scanner.nextLine();

        switch (input) {
            case "1":
                System.out.println("Message sauvegardé.");
                menuController.goBack();
                break;
            case "2":
                System.out.println("Message non sauvegardé.");
                menuController.setMessage(null);
                menuController.goBack();
                break;
            default:
                System.out.println("Option invalide, veuillez choisir 1 ou 2.");
                confirmMessageSave();
                break;
        }
    }
}