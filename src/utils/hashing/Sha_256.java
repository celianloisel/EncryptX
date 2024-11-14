package utils.hashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility class for computing SHA-256 hashes.
 * <p>
 * This class provides a method to hash a given text using the SHA-256 algorithm.
 * The resulting hash is returned as a hexadecimal string.
 * </p>
 */
public class Sha_256 {
    private static final Logger logger = Logger.getLogger(Sha_256.class.getName());

    /**
     * Computes the SHA-256 hash of the given text.
     *
     * @param text The input text to be hashed.
     * @return The SHA-256 hash as a hexadecimal string, or {@code null} if an error occurs.
     */
    public String sha256Hash(String text) {
        try {
            // Create a MessageDigest instance for SHA-256
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

            // Update the digest with the text string's bytes
            byte[] hashBytes = messageDigest.digest(text.getBytes());

            StringBuilder hexString = getHexString(hashBytes);

            return String.valueOf(hexString);
        } catch (NoSuchAlgorithmException e) {
            logger.log(Level.SEVERE, "Error computing SHA-256 hash", e);
            return null;
        }
    }

    /**
     * Converts a byte array to a hexadecimal string.
     *
     * @param hashBytes The byte array to be converted.
     * @return A {@link StringBuilder} containing the hexadecimal representation of the byte array.
     */
    private static StringBuilder getHexString(byte[] hashBytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            // Format each byte as a two-digit hexadecimal value and append to the hexString
            // "%02x" means to convert the byte to a two-character hexadecimal string
            // %02x: %x means that the number will be formatted in hexadecimal, and 02 indicates that the number will always be displayed with two digits (with a leading zero if necessary).
            // (padded with a leading zero if it's a single character, e.g., 0f instead of f)
            hexString.append(String.format("%02x", b));
        }
        return hexString;
    }
}
