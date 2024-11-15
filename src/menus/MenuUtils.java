package menus;

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
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String BLUE = "\u001B[34m";
    public static final String WHITE = "\u001B[37m";

    public static void displayHelp() {

        System.out.println(RED + "\n\n==== AIDE ====" + RESET);
        System.out.println(GREEN + "Voici quelques numéros à taper si vous avez besoin de :" + RESET);
        System.out.println("7 : Revenir au menu précédent.");
        System.out.println("8 : Quitter l'application.");

        System.out.println(RED + "\n==== CHIFFREMENT ====" + RESET);

        System.out.println(RED + "- CÉSAR :" + RESET);
        System.out.println("  " + GREEN + "Fonctionnement :" + RESET + " Chaque lettre est remplacée par une autre lettre décalée d'un nombre fixe dans l'alphabet.");
        System.out.println("  " + WHITE + "Exemple :" + RESET + " Si on choisit un décalage de 3, 'A' devient 'D', 'B' devient 'E', etc.");
        System.out.println("  " + BLUE + "Limites :" + RESET + " Facile à casser, car il n'y a que 25 décalages possibles.");

        System.out.println(RED + "- VIGENÈRE :" + RESET);
        System.out.println("  " + GREEN + "Fonctionnement :" + RESET + " Utilise un mot-clé pour décaler les lettres. Chaque lettre du mot-clé modifie une lettre différente du message.");
        System.out.println("  " + WHITE + "Exemple :" + RESET + " Avec le mot-clé 'CLE', la première lettre est modifiée par 'C', la deuxième par 'L', etc.");
        System.out.println("  " + BLUE + "Limites :" + RESET + " Plus complexe que César, mais vulnérable si on connaît la longueur du mot-clé.");

        System.out.println(RED + "- POLYBE :" + RESET);
        System.out.println("  " + GREEN + "Fonctionnement :" + RESET + " Les lettres sont placées dans un tableau (ou grille) et représentées par leurs coordonnées (ligne, colonne).");
        System.out.println("  " + WHITE + "Exemple :" + RESET + " Avec un tableau 5x5, 'A' est '11', 'B' est '12', etc.");
        System.out.println("  " + BLUE + "Limites :" + RESET + " Simplifie l'encodage des messages dans des chiffres.");

        System.out.println(RED + "- ENIGMA :" + RESET);
        System.out.println("  " + GREEN + "Fonctionnement :" + RESET + " Une machine historique qui utilise des rotors pour transformer chaque lettre en une autre.");
        System.out.println("  " + WHITE + "Exemple :" + RESET + " Chaque rotor tourne après chaque lettre, ce qui change le codage pour chaque caractère.");
        System.out.println("  " + BLUE + "Limites :" + RESET + " Extrêmement complexe à casser sans connaître la configuration des rotors.");

        System.out.println(RED + "- RC4 :" + RESET);
        System.out.println("  " + GREEN + "Fonctionnement :" + RESET + " Crée une suite de nombres pseudo-aléatoires qui est utilisée pour chiffrer les données.");
        System.out.println("  " + WHITE + "Exemple :" + RESET + " Chaque caractère du texte est combiné avec un nombre aléatoire généré.");
        System.out.println("  " + BLUE + "Limites :" + RESET + " Découvert comme vulnérable, il est rarement utilisé aujourd'hui.");

        System.out.println(RED + "\n==== HACHAGE ====" + RESET);

        System.out.println(RED + "- MD5 :" + RESET);
        System.out.println("  " + GREEN + "Fonctionnement :" + RESET + " Transforme un texte en une empreinte de 32 caractères hexadécimaux.");
        System.out.println("  " + WHITE + "Exemple :" + RESET + " Le texte 'Bonjour' donne une empreinte unique, mais deux textes différents peuvent parfois donner la même empreinte (collision).");
        System.out.println("  " + BLUE + "Limites :" + RESET + " Obsolète pour des applications sensibles à cause de sa vulnérabilité aux collisions.");

        System.out.println(RED + "- SHA-256 :" + RESET);
        System.out.println("  " + GREEN + "Fonctionnement :" + RESET + " Crée une empreinte de 64 caractères hexadécimaux. Plus complexe et sécurisé que MD5.");
        System.out.println("  " + WHITE + "Exemple :" + RESET + " Le texte 'Bonjour' donne une empreinte unique et difficile à casser.");
        System.out.println("  " + BLUE + "Limites :" + RESET + " Protéger les mots de passe et garantir l'intégrité des fichiers.");

        System.out.println(RED + "\n==== STÉGANOGRAPHIE ====" + RESET);

        System.out.println(RED + "- STÉGANOGRAPHIE :" + RESET);
        System.out.println("  " + GREEN + "Fonctionnement :" + RESET + " Cacher des messages secrets dans des fichiers multimédias comme des images, des vidéos ou des sons.");
        System.out.println("  " + WHITE + "Exemple :" + RESET + " Les bits du message sont insérés dans les bits les moins significatifs d'une image.");
        System.out.println("  " + BLUE + "Limites :" + RESET + " Le message reste invisible à l'œil nu.");


        System.out.println(RED + "\n==== LA TOTALE ====" + RESET);

        System.out.println(RED + "- LA TOTALE :" + RESET);
        System.out.println("  " + GREEN + "Fonctionnement :" + RESET + " Combine plusieurs techniques pour protéger un message.");
        System.out.println("  " + GREEN + "Étapes :" + RESET);
        System.out.println("    1. Le message est chiffré pour le rendre illisible.");
        System.out.println("    2. Il est ensuite haché pour garantir qu'il n'est pas modifié.");
        System.out.println("    3. Ajout d'éléments de sécurité :");
        System.out.println("       " + WHITE + "- Exemple : Vecteur d’initialisation :" + RESET + " Une clé temporaire unique pour renforcer le chiffrement.");
        System.out.println("       " + WHITE + "- Exemple : Sel :" + RESET + " Rend chaque mot de passe unique.");
        System.out.println("       " + WHITE + "- Exemple : Poivre :" + RESET + " Une clé secrète ajoutée aux données.");
        System.out.println("       " + WHITE + "- Exemple : HMAC :" + RESET + " Une signature pour vérifier l'intégrité.");
        System.out.println("  " + BLUE + "Limites :" + RESET + " Complexité accrue et dépendance à une configuration correcte.");
        System.out.println("  " + GREEN + "Résultat :" + RESET + " Un fichier protégé et sécurisé.");

    }



}
