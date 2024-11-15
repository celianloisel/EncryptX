package menus.encryption;

import menus.Menu;
import menus.MenuController;
import menus.MenuUtils;
import menus.encryption.rc4.RC4Menu;

import java.util.Scanner;

/**
 * The type Encrypt or decrypt menu.
 */
public class EncryptOrDecryptMenu implements Menu {

    private final Scanner scanner;
    private final MenuController menuController;
    private EncryptDecryptAlgorithmMenu algorithmMenu;

    /**
     * Instantiates a new Encrypt or decrypt menu.
     *
     * @param menuController the menu controller
     */
    public EncryptOrDecryptMenu(MenuController menuController) {
        this.scanner = new Scanner(System.in);
        this.menuController = menuController;

        // Algorithm menu selection according to the chosen encryption method
        String encryptionMethod = menuController.getEncryptionMethod();
        if ("RC4".equals(encryptionMethod)) {
            this.algorithmMenu = new RC4Menu(menuController);
        }
        // Other algorithms here if necessary
    }

    @Override
    public void display() {
        System.out.println("\n\n==== Choix d'utilisation  ====");
        System.out.println("1. Chiffrer");
        System.out.println("2. Déchiffrer");
        System.out.println("\n6. Aide");
        System.out.println("7. Retour");
        System.out.println("8. Quitter");
    }

    @Override
    public void handleInput() {
        int choice = MenuUtils.getValidatedIntInput(scanner);

        switch (choice) {
            case 1:
                System.out.println("Le chiffrement a été sélectionné");
                if (algorithmMenu != null) {
                    algorithmMenu.processEncryption();
                } else {
                    System.out.println("Algorithme de chiffrement non supporté.");
                }
                break;
            case 2:
                System.out.println("Le déchiffrement a été sélectionné.");
                if (algorithmMenu != null) {
                    algorithmMenu.processDecryption();
                } else {
                    System.out.println("Algorithme de déchiffrement non supporté.");
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