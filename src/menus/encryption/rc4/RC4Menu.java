package menus.encryption.rc4;

import menus.MenuController;
import menus.encryption.EncryptDecryptAlgorithmMenu;
import utils.encryption.rc4.RC4Encryption;
import utils.encryption.rc4.HexUtils;

import java.util.Scanner;

/**
 * The type Rc 4 menu.
 */
public class RC4Menu implements EncryptDecryptAlgorithmMenu {

    private final Scanner scanner;
    private final MenuController menuController;

    /**
     * Instantiates a new Rc 4 menu.
     *
     * @param menuController the menu controller
     */
    public RC4Menu(MenuController menuController) {
        this.scanner = new Scanner(System.in);
        this.menuController = menuController;
    }

    @Override
    public void processEncryption() {
        System.out.print("Entrez la clé : ");
        String key = scanner.nextLine();
        RC4Encryption rc4 = new RC4Encryption(key);

        // Vérifie si un message est déjà défini
        String plaintext = menuController.getMessage();
        if (plaintext == null) {
            System.out.print("Entrez le texte à chiffrer : ");
            plaintext = scanner.nextLine();
            menuController.setMessage(plaintext); // Stocke le message dans le contrôleur pour une utilisation ultérieure
        }

        byte[] encrypted = rc4.encryptDecrypt(plaintext.getBytes());

        // Encodage des données chiffrées en hexadécimal pour affichage
        String encryptedText = HexUtils.bytesToHex(encrypted);
        System.out.println("Texte chiffré (RC4) : " + encryptedText);

        // Met à jour le message chiffré dans le contrôleur pour chaîner les algorithmes
        menuController.setMessage(encryptedText);
    }

    @Override
    public void processDecryption() {
        System.out.print("Entrez la clé : ");
        String key = scanner.nextLine();
        RC4Encryption rc4 = new RC4Encryption(key);

        // Vérifie si un message chiffré est déjà défini dans le contrôleur
        String hexCiphertext = menuController.getMessage();
        if (hexCiphertext == null) {
            System.out.print("Entrez le texte à déchiffrer (en hexadécimal) : ");
            hexCiphertext = scanner.nextLine();
            menuController.setMessage(hexCiphertext); // Stocke le message dans le contrôleur pour une utilisation ultérieure
        }

        // Convertit le texte chiffré en bytes à partir de l'hexadécimal
        byte[] ciphertext = HexUtils.hexToBytes(hexCiphertext);
        byte[] decrypted = rc4.encryptDecrypt(ciphertext);
        String decryptedText = new String(decrypted);

        System.out.println("Texte déchiffré (RC4) : " + decryptedText);

        // Met à jour le message déchiffré dans le contrôleur pour chaîner les algorithmes
        menuController.setMessage(decryptedText);
    }
}