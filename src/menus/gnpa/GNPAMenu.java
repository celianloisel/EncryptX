package menus.gnpa;

import menus.Menu;
import menus.MenuController;
import utils.GNPA;
import utils.MenuUtils;

import java.util.Scanner;

/**
 * The type GNPAMenu menu.
 */
public class GNPAMenu implements Menu {

    private final Scanner scanner;
    private final MenuController menuController;

    /**
     * Instantiates a new GNPAMenu menu.
     *
     * @param menuController the menu controller
     */
    public GNPAMenu(MenuController menuController) {
        this.scanner = new Scanner(System.in);
        this.menuController = menuController;
    }

    @Override
    public void display() {
        System.out.println("\n\n==== Configuration GNPA ====");
        System.out.println("6. Aide");
        System.out.println("7. Retour");
        System.out.println("8. Quitter");
        System.out.print("\nVeuillez entrer votre graine ou choisissez une option (6, 7, 8) : ");
    }

    @Override
    public void handleInput() {
        String graine = scanner.nextLine();

        switch (graine) {
            case "6":
                MenuUtils.displayHelp();
                return;
            case "7":
                menuController.goBack();
                return;
            case "8":
                System.out.println("Merci d'avoir utilisé l'application. À bientôt !");
                menuController.stop();
                return;
            default:
                break;
        }

        System.out.print("\nEntrez le nombre de nombres aléatoires à générer : ");
        String numberInput = scanner.nextLine();
        try {
            int numberOfRandomlyGeneratedNumbers = Integer.parseInt(numberInput);
            if (numberOfRandomlyGeneratedNumbers > 0) {
                System.out.println("Lancement de GNPA avec la graine et le nombre de nombres spécifiés...");
                GNPA.runGNPA(graine, numberOfRandomlyGeneratedNumbers);
            } else {
                System.out.println("Veuillez entrer un nombre positif.");
                handleInput();
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrée invalide. Veuillez entrer un nombre entier.");
            handleInput();
        }
    }
}