package utils.hashing;

import utils.encryption.rc4.HexUtils;

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

            StringBuilder hexString = new StringBuilder(HexUtils.bytesToHex(hashBytes));

            return String.valueOf(hexString);
        } catch (NoSuchAlgorithmException e) {
            logger.log(Level.SEVERE, "Error computing SHA-256 hash", e);
            return null;
        }
    }
}
