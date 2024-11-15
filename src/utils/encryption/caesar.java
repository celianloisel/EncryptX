package utils.encryption;

import java.util.Scanner;

/**
 * The {@code cesar} class provides methods for encrypting and decrypting messages using the Caesar cipher.
 * The cipher shifts each letter of the message by a specified number of places in the alphabet.
 *
 * <p>
 * This class supports user interaction to choose encryption or decryption, validates the input
 * (lowercase letters only and positive numeric shift), and performs the encryption or decryption process.
 * </p>
 *
 * <p>
 * Usage: Users can encrypt or decrypt messages by providing appropriate inputs.
 * </p>
 *
 * @author Your Name
 */
public class caesar {

    /**
     * Encrypts the given message using the Caesar cipher with the specified shift value.
     *
     * @param message The plaintext message to be encrypted. Must contain lowercase letters only.
     * @param shift   The number of positions to shift each letter. Must be a positive integer.
     * @return
     */
    public static String cesarEncryption(String message, int shift) {
        int moduleShift = shift % 26;
        StringBuilder encryptedMessage = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            char charToEncrypt = message.charAt(i);
            char encryptedChar = (char) (((charToEncrypt - 'a' + moduleShift) % 26) + 'a');
            encryptedMessage.append(encryptedChar);
        }

        return encryptedMessage.toString();
    }

    /**
     * Decrypts the given message using the Caesar cipher with the specified shift value.
     *
     * @param message The encrypted message to be decrypted. Must contain lowercase letters only.
     * @param shift   The number of positions to reverse-shift each letter. Must be a positive integer.
     * @return
     */
    public static String cesarDecryption(String message, int shift) {
        int moduleShift = shift % 26;
        StringBuilder decryptedMessage = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            char charToDecrypt = message.charAt(i);
            char decryptedChar = (char) (((charToDecrypt - 'a' - moduleShift + 26) % 26) + 'a');
            decryptedMessage.append(decryptedChar);
        }

        return decryptedMessage.toString();
    }

    /**
     * Validates the message provided by the user and ensures it meets the format restrictions.
     * If valid, it proceeds to check the shift value.
     *
     * @param isEncrypt {@code true} if the user chooses encryption, {@code false} for decryption.
     */
    public static void checkMessageRestriction(Boolean isEncrypt) {
        Scanner sc = new Scanner(System.in);
        String message;

        System.out.print("Enter the message (ONLY LOWERCASE, no spaces or special characters): ");
        for (int a = 0; a < 3; a++) { // Allow up to 3 attempts
            message = sc.nextLine();
            if (message.matches("[a-z]+")) { // Validate the message with regex
                caesar.checkShiftRestriction(message, isEncrypt); // Proceed to check the shift
                return; // Exit the loop upon successful validation
            } else {
                System.out.println("The message must contain only lowercase letters. "
                        + (3 - a) + " attempt(s) left.");
            }
        }
    }

    /**
     * Validates the shift value provided by the user and ensures it is a positive integer.
     * If valid, it performs the encryption or decryption process.
     *
     * @param message   The validated message to be encrypted or decrypted.
     * @param isEncrypt {@code true} if the user chooses encryption, {@code false} for decryption.
     * @return
     */
    public static String checkShiftRestriction(String message, Boolean isEncrypt) {
        Scanner sc = new Scanner(System.in);
        String shift;

        System.out.print("How much do you want to shift? ");
        for (int a = 0; a < 3; a++) { // Allow up to 3 attempts
            shift = sc.nextLine();
            if (shift.matches("^[1-9][0-9]*$")) { // Validate the shift as a positive integer
                if (isEncrypt) {
                    caesar.cesarEncryption(message, Integer.parseInt(shift));
                } else {
                    caesar.cesarDecryption(message, Integer.parseInt(shift));
                }
                return shift;
            } else {
                System.out.println("Please enter a POSITIVE number. "
                        + (3 - a) + " attempt(s) left.");
            }
        }
        return message;
    }
}