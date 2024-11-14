package utils.encryption;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The Polybius class provides methods for encrypting and decrypting text using the Polybius cipher.
 * <p>
 * The Polybius cipher is a simple substitution cipher that replaces each letter with its coordinates in a 5x5 grid.
 * This implementation assumes a lowercase alphabet and substitutes 'W' with 'VV' to fit the alphabet into the grid.
 * Spaces in the input text are preserved in both encryption and decryption.
 * </p>
 */
public class Polybius {

    /**
     * Encrypts the given text using the Polybius cipher.
     * <p>
     * Each letter in the input text is converted to its corresponding coordinates in a 5x5 grid, where each cell
     * represents a letter of the alphabet (excluding 'w' to fit into the grid). The coordinates are represented
     * by two digits: the row and column numbers (1-indexed).
     * </p>
     *
     * @param text The input text to be encrypted. This text will be converted to lowercase, and spaces are preserved.
     * @return A String containing the encrypted text, where each letter is replaced by its coordinates in the Polybius
     * square, and spaces are retained as-is.
     */
    public String polybiusCipher(String text) {
        char[] textArray = text.toLowerCase().toCharArray();
        StringBuilder result = new StringBuilder();

        String[][] polybiusSquare = generatePolybiusSquare();

        for (char c : textArray) {
            if (Character.isWhitespace(c)) {
                result.append(c);
            } else {
                for (int iRows = 0; iRows < 5; iRows++) {
                    for (int iColumns = 0; iColumns < 5; iColumns++) {
                        if (Character.toString(c).equals(polybiusSquare[iRows][iColumns])) {
                            result.append(iRows + 1).append(iColumns + 1);
                        }
                    }
                }
            }
        }

        return String.valueOf(result);
    }

    /**
     * Decrypts the given Polybius cipher text back to the original text.
     * <p>
     * This method takes a string of digit pairs, where each pair represents coordinates (row, column) in a 5x5 Polybius
     * grid. Each coordinate pair is converted back to its corresponding letter. Spaces in the input text are preserved.
     * </p>
     *
     * @param text The encrypted text containing digit pairs, where each pair represents the coordinates of a letter
     *             in the Polybius square. Spaces are retained as-is.
     * @return A String containing the decrypted text, with each coordinate pair replaced by its corresponding letter.
     */
    public String polybiusDecipher(String text) {
        char[] textArray = text.toCharArray();
        StringBuilder result = new StringBuilder();
        String[][] polybiusSquare = generatePolybiusSquare();

        for (int i = 0; i < textArray.length; i++) {
            if (Character.isWhitespace(textArray[i])) {
                result.append(" ");
            } else {
                // Extract the substring of two digits and convert them directly to row and column indices
                int row = Integer.parseInt(text.substring(i, i + 1)) - 1;
                int column = Integer.parseInt(text.substring(i + 1, i + 2)) - 1;

                String letter = polybiusSquare[row][column];
                result.append(letter);

                // Skip the next character since we processed two characters as one pair
                i++;
            }
        }

        return String.valueOf(result);
    }

    /**
     * Generates a Polybius square, a 5x5 grid containing the alphabet letters.
     * <p>
     * The Polybius square generated here excludes the letter 'w' to fit into the 5x5 grid. Each cell in the grid
     * holds a single lowercase letter, allowing for easy coordinate-based substitution.
     * </p>
     *
     * @return A 5x5 two-dimensional array of strings, representing the Polybius square. Each cell in the array
     * contains a single lowercase letter.
     */
    public String[][] generatePolybiusSquare() {
        int columns = 5;
        int rows = 5;

        ArrayList<String> alphabet = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "x", "y", "z"));

        String[][] polybeSquare = new String[columns][rows];

        for (int iRows = 0; iRows < rows; iRows++) {
            for (int iColumns = 0; iColumns < columns; iColumns++) {
                polybeSquare[iRows][iColumns] = alphabet.remove(0); // add in polybeSquare and remove of alphabet after.
            }
        }

        return polybeSquare;
    }
}
