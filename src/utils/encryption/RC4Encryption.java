package utils.encryption;

public class RC4Encryption {

    private byte[] key;

    public RC4Encryption(String key) {
        setKey(key);
    }

    private void setKey(String key) {
        this.key = key.getBytes();
    }

    public byte[] encrypt(String plaintext) {
        return process(plaintext.getBytes());
    }

    public String decrypt(byte[] ciphertext) {
        return new String(process(ciphertext));
    }

    private byte[] process(byte[] data) {
        byte[] s = new byte[256];
        byte[] k = new byte[256];
        byte[] result = new byte[data.length];

        for (int i = 0; i < 256; i++) {
            s[i] = (byte) i;
            k[i] = key[i % key.length];
        }

        int j = 0;
        for (int i = 0; i < 256; i++) {
            j = (j + s[i] + k[i]) & 0xFF;
            byte temp = s[i];
            s[i] = s[j];
            s[j] = temp;
        }

        int i = 0;
        j = 0;
        for (int x = 0; x < data.length; x++) {
            i = (i + 1) & 0xFF;
            j = (j + s[i]) & 0xFF;

            byte temp = s[i];
            s[i] = s[j];
            s[j] = temp;

            result[x] = (byte) (data[x] ^ s[(s[i] + s[j]) & 0xFF]);
        }

        return result;
    }
}