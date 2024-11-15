package utils.encryption;

/**
 * The {@code Enigma} class simulates the operation of the Enigma machine, used for encryption and decryption.
 * It implements a cipher that passes the text through three rotors, a reflector, and then back through the rotors.
 * The class supports the encryption process by rotating the rotors after each character is processed.
 * Non-alphabetic characters are unchanged.
 */
public class Enigma {

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final String FAST_ROTOR = "EKMFLGDQVZNTOWYHXUSPAIBRCJ";
    private static final String MIDDLE_ROTOR = "AJDKSIRUXBLHWTMCQGZNPYFVOE";
    private static final String SLOW_ROTOR = "BDFHJLCPRTXVZNYEIWGAKMUSQO";

    private static final String REFLECTOR = "YRUHQSLDPXNGOKMIEBFZCWVJAT";

    private int fastRotorPosition = 0;
    private int middleRotorPosition = 0;
    private int slowRotorPosition = 0;

    /**
     * Encrypts the given text using the Enigma cipher.
     * The text is converted to uppercase, and each alphabetic character is processed through
     * the rotors and reflector. Non-alphabetic characters are appended to the result unchanged.
     *
     * @param text the text to be encrypted (may contain alphabetic and non-alphabetic characters)
     * @return the encrypted text
     */
    public String cipher(String text) {
        resetRotors();

        StringBuilder result = new StringBuilder();
        text = text.toUpperCase();

        for (char letter : text.toCharArray()) {
            if (ALPHABET.indexOf(letter) != -1) {
                int encryptedIndex = encryptThroughRotors(letter);
                result.append(ALPHABET.charAt(encryptedIndex));
                rotateRotors();
            } else {
                result.append(letter);  // Keep non-alphabet characters unchanged.
            }
        }
        return result.toString();
    }

    /**
     * Resets the positions of all rotors to their initial positions (0).
     * This is done before each encryption to ensure the rotors start at the same position.
     */
    private void resetRotors() {
        fastRotorPosition = 0;
        middleRotorPosition = 0;
        slowRotorPosition = 0;
    }

    /**
     * Rotates the positions of the rotors after each character is processed.
     * The fast rotor rotates after every character, and when it completes a full rotation,
     * the middle rotor moves one position. Similarly, when the middle rotor completes a full rotation,
     * the slow rotor advances.
     */
    private void rotateRotors() {
        fastRotorPosition = (fastRotorPosition + 1) % 26;

        if (fastRotorPosition == 0) {
            middleRotorPosition = (middleRotorPosition + 1) % 26;

            if (middleRotorPosition == 0) {
                slowRotorPosition = (slowRotorPosition + 1) % 26;
            }
        }
    }

    /**
     * Encrypts a single letter by passing it through all three rotors and the reflector.
     * The letter goes through the rotors in the forward direction, then reflects, and returns through the rotors in reverse order.
     *
     * @param letter the character to be encrypted
     * @return the index of the encrypted character in the alphabet
     */
    private int encryptThroughRotors(char letter) {
        int index = ALPHABET.indexOf(letter);

        // Pass through the fast rotor
        index = (index + fastRotorPosition) % 26;
        char fastRotorChar = FAST_ROTOR.charAt(index);
        index = (ALPHABET.indexOf(fastRotorChar) - fastRotorPosition + 26) % 26;

        // Pass through the middle rotor
        index = (index + middleRotorPosition) % 26;
        char middleRotorChar = MIDDLE_ROTOR.charAt(index);
        index = (ALPHABET.indexOf(middleRotorChar) - middleRotorPosition + 26) % 26;

        // Pass through the slow rotor
        index = (index + slowRotorPosition) % 26;
        char slowRotorChar = SLOW_ROTOR.charAt(index);
        index = (ALPHABET.indexOf(slowRotorChar) - slowRotorPosition + 26) % 26;

        // Reflect the signal
        char reflectedChar = REFLECTOR.charAt(index);
        index = ALPHABET.indexOf(reflectedChar);

        // Pass back through the slow rotor
        char reverseSlowRotorChar = ALPHABET.charAt((index + slowRotorPosition) % 26);
        index = SLOW_ROTOR.indexOf(reverseSlowRotorChar);
        index = (index - slowRotorPosition + 26) % 26;

        // Pass back through the middle rotor
        char reverseMiddleRotorChar = ALPHABET.charAt((index + middleRotorPosition) % 26);
        index = MIDDLE_ROTOR.indexOf(reverseMiddleRotorChar);
        index = (index - middleRotorPosition + 26) % 26;

        // Pass back through the fast rotor
        char reverseFastRotorChar = ALPHABET.charAt((index + fastRotorPosition) % 26);
        index = FAST_ROTOR.indexOf(reverseFastRotorChar);
        index = (index - fastRotorPosition + 26) % 26;

        return index;
    }
}
