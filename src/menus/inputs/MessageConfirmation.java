package menus.inputs;

import menus.MenuController;
import java.util.Scanner;

public class MessageConfirmation {
    private final Scanner scanner;
    private final MenuController menuController;

    public MessageConfirmation(MenuController menuController) {
        this.scanner = new Scanner(System.in);
        this.menuController = menuController;
    }

    public void confirmMessageSave() {
        System.out.println("Voulez-vous enregistrer le message avant de revenir au menu précédent ?");
        System.out.println("1. Oui");
        System.out.println("2. Non");

        String input = scanner.nextLine();

        switch (input) {
            case "1":
                System.out.println("Message sauvegardé.");
                // Le message est déjà enregistré dans `menuController`, donc on ne fait rien de plus ici
                menuController.goBack();
                break;
            case "2":
                System.out.println("Message non sauvegardé.");
                menuController.setMessage(null);  // Efface le message en cours
                menuController.goBack();
                break;
            default:
                System.out.println("Option invalide, veuillez choisir 1 ou 2.");
                confirmMessageSave();  // Redemande si l'entrée n'est pas valide
                break;
        }
    }
}