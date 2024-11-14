package utils.hashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility class for computing MD5 hashes.
 */
public class MD5 {
    private static final Logger logger = Logger.getLogger(MD5.class.getName());

    /**
     * Computes the MD5 hash of the given text.
     *
     * @param text The input text to be hashed.
     * @return The MD5 hash as a hexadecimal string, or {@code null} if an error occurs.
     */
    public String MD5Hash(String text) {
        try {
            // Create a MessageDigest instance for MD5
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");

            // Update the digest with the text string's bytes
            byte[] hashBytes = messageDigest.digest(text.getBytes());

            StringBuilder hexString = getHexString(hashBytes);

            return String.valueOf(hexString);
        } catch (NoSuchAlgorithmException e) {
            logger.log(Level.SEVERE, "Error computing MD5 hash", e);
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
            hexString.append(String.format("%02x", b));
        }
        return hexString;
    }
}