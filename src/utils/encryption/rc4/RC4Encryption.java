package utils.encryption.rc4;

/**
 * The type Rc 4 encryption.
 */
public class RC4Encryption {

    private byte[] S = new byte[256];
    private int i, j;

    /**
     * Instantiates a new Rc 4 encryption.
     *
     * @param key the key
     */
    public RC4Encryption(String key) {
        initKey(key);
    }

    private void initKey(String key) {
        byte[] keyBytes = key.getBytes();
        int keyLength = keyBytes.length;

        // S array initialization
        for (int i = 0; i < 256; i++) {
            S[i] = (byte) i;
        }

        // Initial permutation of S according to key
        int j = 0;
        for (int i = 0; i < 256; i++) {
            j = (j + S[i] + keyBytes[i % keyLength]) & 0xFF;
            swap(S, i, j);
        }

        // Resetting keystream indices
        this.i = 0;
        this.j = 0;
    }

    /**
     * Encrypt decrypt byte [ ].
     *
     * @param data the data
     * @return the byte [ ]
     */
    public byte[] encryptDecrypt(byte[] data) {
        byte[] result = new byte[data.length];

        for (int k = 0; k < data.length; k++) {
            // Keystream byte generation
            i = (i + 1) & 0xFF;
            j = (j + S[i]) & 0xFF;
            swap(S, i, j);

            int keystreamByte = S[(S[i] + S[j]) & 0xFF];
            result[k] = (byte) (data[k] ^ keystreamByte);
        }

        return result;
    }

    private void swap(byte[] array, int a, int b) {
        byte temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}