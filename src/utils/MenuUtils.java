package utils;

import java.util.Scanner;

/**
 * The type Menu utils.
 */
public class MenuUtils {

    /**
     * Demande à l'utilisateur de saisir un entier valide.
     * Affiche un message d'erreur et redemande en cas d'entrée invalide.
     *
     * @param scanner Le scanner pour lire l'entrée utilisateur
     * @return Un entier saisi par l'utilisateur
     */
    public static int getValidatedIntInput(Scanner scanner) {
        int choice;

        while (true) {
            System.out.print("Choisissez une option : ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
                return choice;
            } else {
                System.out.println("Entrée invalide. Veuillez entrer un nombre correspondant à une option.");
                scanner.nextLine();
            }
        }
    }

    /**
     * Display help.
     */
    public static void displayHelp() {
        System.out.println("\n\n==== AIDE ====");
        System.out.println("1-5 : Sélectionnez une option disponible pour accéder aux différents menus.\n(Si le nombre n'est pas présent dans les choix proposé alors cela ne fonctionnera pas.)");
        System.out.println("6 : Affiche ce menu d'aide.");
        System.out.println("7 : Reviens au menu précédent.");
        System.out.println("8 : Quitte l'application.");
    }
}
