package utils;

/**
 * The type Gnpa.
 */
public class GNPA {

    /**
     * Run gnpa.
     *
     * @param graine                           the graine
     * @param numberOfRandomlyGeneratedNumbers the number of randomly generated numbers
     */
    // GNPA method that accepts the seed as a parameter
    public static void runGNPA(String graine, Integer numberOfRandomlyGeneratedNumbers) {
        // Check that the seed is not empty
        if (graine == null || graine.isEmpty()) {
            System.out.println("Erreur : la graine ne peut pas être vide.");
            return;
        }

        // Check that the number of numbers generated is positive
        if (numberOfRandomlyGeneratedNumbers == null || numberOfRandomlyGeneratedNumbers <= 0) {
            System.out.println("Erreur : le nombre de nombres générés doit être positif.");
            return;
        }

        int[] enregistrer = new int[graine.length() * 8];
        int index = 0;

        // Convert the seed into a bit sequence
        for (char c : graine.toCharArray()) {
            String binaryString = String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0');
            for (char bit : binaryString.toCharArray()) {
                enregistrer[index++] = bit - '0';
            }
        }

        // Run the GNPA for the specified number of revolutions
        for (int i = 0; i < numberOfRandomlyGeneratedNumbers; i++) {
            // Safe calculation of the new feedback bit
            int newBit = (enregistrer[0] ^ enregistrer[2]) & (enregistrer[1] | enregistrer[3]);

            // Shift the bits to the left and add the new bit at the end.
            System.arraycopy(enregistrer, 1, enregistrer, 0, enregistrer.length - 1);
            enregistrer[enregistrer.length - 1] = newBit;

            // Decimal value calculation and display
            int decimalValue = 0;
            for (int bit : enregistrer) {
                decimalValue = (decimalValue << 1) | bit;
            }
            decimalValue = Math.abs(decimalValue);

            System.out.println("Tour " + (i + 1) + " : Décimal absolu = " + decimalValue);
        }
    }
}