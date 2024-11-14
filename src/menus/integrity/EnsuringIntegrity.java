package menus.integrity;

import menus.Menu;
import menus.MenuController;
import menus.MenuUtils;
import menus.chopping.ChoppingMenu;
import utils.hashing.Sha_256;
import utils.hashing.MD5;

import java.util.Scanner;

public class EnsuringIntegrity implements Menu {

    private final Scanner scanner;
    private final MenuController menuController;

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

        if (menuController.getHashAlgorithm() != null) {
            System.out.println("Hachage sélectionné : " + menuController.getHashAlgorithm());

            String hashedMessage = null;
            switch (menuController.getHashAlgorithm()) {
                case "SHA-256":
                    hashedMessage = new Sha_256().sha256Hash(menuController.getMessage());
                    break;
                case "MD5":
                    hashedMessage = new MD5().MD5Hash(menuController.getMessage());
                    break;
                default:
                    System.out.println("Algorithme de hachage inconnu.");
                    break;
            }

            if (hashedMessage != null) {
                System.out.println("Message haché (" + menuController.getHashAlgorithm() + ") : " + hashedMessage);
            } else {
                System.out.println("Erreur lors du hachage du message.");
            }

            System.out.println("\n6. Aide");
            System.out.println("7. Retour");
            System.out.println("8. Quitter");
        } else {
            System.out.println("Voulez-vous garantir l'intégrité de votre message en le hachant ?");
            System.out.println("1. Oui");
            System.out.println("2. Non");
            System.out.println("\n6. Aide");
            System.out.println("7. Retour");
            System.out.println("8. Quitter");
        }
    }

    @Override
    public void handleInput() {
        if (menuController.getHashAlgorithm() != null) {
            String input = scanner.nextLine();
            switch (input) {
                case "6":
                    MenuUtils.displayHelp();
                    break;
                case "7":
                    menuController.goBack();
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
        } else {
            int choice = MenuUtils.getValidatedIntInput(scanner);

            switch (choice) {
                case 1:
                    System.out.println("Le Oui a été sélectionné");
                    menuController.setCurrentMenu(new ChoppingMenu(menuController));
                    break;
                case 2:
                    System.out.println("Le Non a été sélectionné.");
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
}