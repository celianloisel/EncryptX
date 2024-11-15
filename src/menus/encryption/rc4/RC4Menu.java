package menus.encryption.rc4;

import menus.encryption.EncryptDecryptAlgorithmMenu;
import utils.encryption.rc4.RC4Encryption;
import utils.encryption.rc4.HexUtils;

import java.util.Scanner;

/**
 * The type Rc 4 menu.
 */
public class RC4Menu implements EncryptDecryptAlgorithmMenu {

    private final Scanner scanner;

    /**
     * Instantiates a new Rc 4 menu.
     */
    public RC4Menu() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void processEncryption() {
        System.out.print("Entrez la clé : ");
        String key = scanner.nextLine();
        RC4Encryption rc4 = new RC4Encryption(key);

        System.out.print("Entrez le texte à chiffrer : ");
        String plaintext = scanner.nextLine();
        byte[] encrypted = rc4.encryptDecrypt(plaintext.getBytes());

        // Encodes encrypted data in hexadecimal for display
        String encryptedText = HexUtils.bytesToHex(encrypted);
        System.out.println("Texte chiffré (RC4) : " + encryptedText);
    }

    @Override
    public void processDecryption() {
        System.out.print("Entrez la clé : ");
        String key = scanner.nextLine();
        RC4Encryption rc4 = new RC4Encryption(key);

        System.out.print("Entrez le texte à déchiffrer (en hexadécimal) : ");
        String hexCiphertext = scanner.nextLine();

        // Convert ciphertext from hexadecimal to bytes
        byte[] ciphertext = HexUtils.hexToBytes(hexCiphertext);
        byte[] decrypted = rc4.encryptDecrypt(ciphertext);
        String decryptedText = new String(decrypted);

        System.out.println("Texte déchiffré (RC4) : " + decryptedText);
    }
}