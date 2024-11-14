package utils.encryption;

import java.util.Scanner;

public class vigenere {

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

    public static void checkMessageAndKeyRestriction(boolean isEncrypt) {
        Scanner sc = new Scanner(System.in);
        String message, key;
        int a = 0;

        // Allow 3 attempts to enter valid message and key
        for ( a = 0; a <3; a++) {
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
                System.out.println("Your KEY or MESSAGE has INCORECT syntax : WARNING!" + (3-a)+ " attempts: please only LOWER CASE");
            }
        }

        // If all attempts fail, return to main menu
        System.out.println("Too many invalid attempts. ");
    }

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

    public static void vigenereDecryption(String message, String key) {
        StringBuilder decryptedMessage = new StringBuilder();
        int keyLength = key.length(); // Get the length of the key

        // Decrypt each letter of the message
        for (int i = 0; i < message.length(); i++) {
            char messageChar = message.charAt(i); // Current encrypted character
            char keyChar = key.charAt(i % keyLength); // Current key character (repeats if shorter)
            int shift = keyChar - 'a'; // Calculate shift from key character
            char decryptedChar = (char) (((messageChar - 'a' - shift + 26) % 26) + 'a'); // Decrypt character

            System.out.print(decryptedChar + " " + ""); // Print the decrypted character
        }

    }
}
