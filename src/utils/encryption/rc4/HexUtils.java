package utils.encryption.rc4;

/**
 * The type Hex utils.
 */
public class HexUtils {

    /**
     * Bytes to hex string.
     *
     * @param bytes the bytes
     * @return the string
     */
// Converts an array of bytes into a hexadecimal string
    public static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xFF & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    /**
     * Hex to bytes byte [ ].
     *
     * @param hex the hex
     * @return the byte [ ]
     */
// Converts a hexadecimal string into an array of bytes
    public static byte[] hexToBytes(String hex) {
        int length = hex.length();
        byte[] bytes = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            bytes[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i+1), 16));
        }
        return bytes;
    }
}