package utils.encryption;

import java.util.Scanner;

/**
 * The {@code vigenere} class provides methods for encrypting and decrypting messages using the Vigenère cipher.
 * The cipher uses a keyword to shift each letter of the message for encryption and decryption.
 *
 * <p>
 * This class supports user interaction to choose encryption or decryption, validates the input (lowercase letters only),
 * and performs the necessary operations using the provided key.
 * </p>
 *
 * <p>
 * Usage: Users can choose to encrypt or decrypt a message by providing the appropriate inputs.
 * </p>
 *
 * @author Your Name
 */
public class vigenere {

    /**
     * Prompts the user to choose between encryption and decryption.
     * Calls the appropriate function based on the user's choice.
     */
    public static void choose() {
        Scanner sc = new Scanner(System.in);

        // Ask the user to choose encryption or decryption
        while (true) {
            System.out.println("Do you want to encrypt or decrypt? (E/D): ");
            String choice = sc.nextLine().toUpperCase();
            if (choice.equals("E")) {
                checkMessageAndKeyRestriction(true); // Call encryption function
            } else if (choice.equals("D")) {
                checkMessageAndKeyRestriction(false); // Call decryption function
            } else {
                // If the input is invalid
                System.out.println("Please enter 'E' to encrypt or 'D' to decrypt.");
            }
        }
    }

    /**
     * Validates the message and key provided by the user and calls the appropriate
     * encryption or decryption function if valid.
     * Allows up to 3 attempts to enter valid inputs (lowercase letters only).
     *
     * @param isEncrypt {@code true} if the user chooses encryption, {@code false} for decryption.
     */
    public static void checkMessageAndKeyRestriction(boolean isEncrypt) {
        Scanner sc = new Scanner(System.in);
        String message, key;

        // Allow 3 attempts to enter valid message and key
        for (int a = 0; a < 3; a++) {
            System.out.print("Enter the message (lowercase letters only): ");
            message = sc.nextLine();

            System.out.print("Enter the key (lowercase letters only): ");
            key = sc.nextLine();

            // Check if message and key contain only lowercase letters
            if (message.matches("[a-z]+") && key.matches("[a-z]+")) {
                // Call encryption or decryption function
                if (isEncrypt) {
                    vigenereEncryption(message, key);
                } else {
                    vigenereDecryption(message, key);
                }
                return; // Exit loop if input is valid
            } else {
                // Show error message and remaining attempts
                System.out.println("Your KEY or MESSAGE has INCORRECT syntax : WARNING! "
                        + (3 - a) + " attempts left. Please only use LOWERCASE letters.");
            }
        }

        // If all attempts fail, return to main menu
        System.out.println("Too many invalid attempts.");
    }

    /**
     * Encrypts the given message using the Vigenère cipher with the specified key.
     *
     * @param message The plaintext message to be encrypted. Must contain lowercase letters only.
     * @param key The keyword used for encryption. Must contain lowercase letters only.
     */
    public static void vigenereEncryption(String message, String key) {
        StringBuilder encryptedMessage = new StringBuilder();

        // Encrypt each letter of the message
        for (int i = 0; i < message.length(); i++) {
            char messageChar = message.charAt(i); // Current message character
            char keyChar = key.charAt(i % key.length()); // Current key character (repeats if shorter)
            int shift = keyChar - 'a'; // Calculate shift from key character
            char encryptedChar = (char) (((messageChar - 'a' + shift) % 26) + 'a'); // Encrypt character
            System.out.print(encryptedChar); // Print the encrypted character
        }
    }

    /**
     * Decrypts the given message using the Vigenère cipher with the specified key.
     *
     * @param message The encrypted message to be decrypted. Must contain lowercase letters only.
     * @param key The keyword used for decryption. Must contain lowercase letters only.
     */
    public static void vigenereDecryption(String message, String key) {
        StringBuilder decryptedMessage = new StringBuilder();
        int keyLength = key.length(); // Get the length of the key

        // Decrypt each letter of the message
        for (int i = 0; i < message.length(); i++) {
            char messageChar = message.charAt(i); // Current encrypted character
            char keyChar = key.charAt(i % keyLength); // Current key character (repeats if shorter)
            int shift = keyChar - 'a'; // Calculate shift from key character
            char decryptedChar = (char) (((messageChar - 'a' - shift + 26) % 26) + 'a'); // Decrypt character

            System.out.print(decryptedChar + " "); // Print the decrypted character
        }
    }
}
