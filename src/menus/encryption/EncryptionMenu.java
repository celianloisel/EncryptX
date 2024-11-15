package menus.encryption;

import menus.Menu;
import menus.MenuController;
import menus.MenuUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The type Encryption menu.
 */
public class EncryptionMenu implements Menu {

    private final Scanner scanner;
    private final MenuController menuController;

    /**
     * Instantiates a new Encryption menu.
     *
     * @param menuController the menu controller
     */
    public EncryptionMenu(MenuController menuController) {
        this.scanner = new Scanner(System.in);
        this.menuController = menuController;
    }

    @Override
    public void display() {
        System.out.println("\n\n==== MENU DE CHIFFREMENT ====");
        System.out.println("\nChiffrement classique");
        System.out.println("1. César");
        System.out.println("2. Vigenère");
        System.out.println("3. Polybe");

        System.out.println("\nChiffrement moderne");
        System.out.println("4. Enigma");
        System.out.println("5. RC4");

        System.out.println("\nEntrez une combinaison de chiffres pour définir la séquence de chiffrement (ex : 125 pour César, Vigenère, RC4).");
        System.out.println("6. Aide");
        System.out.println("7. Retour");
        System.out.println("8. Quitter");
    }

    @Override
    public void handleInput() {
        System.out.print("Entrez votre séquence de chiffrement : ");
        String input = scanner.nextLine();

        if (input.equals("6")) {
            MenuUtils.displayHelp();
        } else if (input.equals("7")) {
            menuController.goBack();
        } else if (input.equals("8")) {
            System.out.println("Merci d'avoir utilisé l'application. À bientôt !");
            menuController.stop();
        } else {
            List<String> encryptionMethods = new ArrayList<>();

            // Interpreting the encryption sequence
            for (char c : input.toCharArray()) {
                switch (c) {
                    case '1':
                        encryptionMethods.add("César");
                        break;
                    case '2':
                        encryptionMethods.add("Vigenère");
                        break;
                    case '3':
                        encryptionMethods.add("Polybe");
                        break;
                    case '4':
                        encryptionMethods.add("Enigma");
                        break;
                    case '5':
                        encryptionMethods.add("RC4");
                        break;
                    default:
                        System.out.println("Option invalide, veuillez réessayer.");
                        break;
                }
            }

            if (encryptionMethods.isEmpty()) {
                System.out.println("Aucun algorithme valide sélectionné. Veuillez réessayer.");
                return;
            }

            // Pass the algorithm sequence to the controller for sequential processing
            menuController.setEncryptionSequence(encryptionMethods);
            menuController.setCurrentMenu(new EncryptOrDecryptSequenceMenu(menuController));
        }
    }
}