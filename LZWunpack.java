import java.io.ByteArrayInputStream;

public class LZWunpack {
    public static void main(String[] args) {
        int phraseCount = 16;
        int currentBitPosition = 0;
        int numBitsForPhrase = 0;
        boolean isFirstPhrase = true;

        try {
            // Read all bytes at once into a byte array
            byte[] inputBytes = System.in.readAllBytes();
            ByteArrayInputStream inputStream = new ByteArrayInputStream(inputBytes);
            int outputPhrase = 0;
            int byteRead;
            
            while ((byteRead = inputStream.read()) != -1) {
                
                for (int i = 0; i < 8; i++) { // Process each bit
                    numBitsForPhrase = (int) Math.ceil(Math.log(phraseCount) / Math.log(2));
                    int bit = ((byteRead >> (7 - i)) & 1); // Get current bit
                    outputPhrase = (outputPhrase << 1) | bit;
                    currentBitPosition++;
                    if (currentBitPosition == numBitsForPhrase) {
                        // Print
                        if (!isFirstPhrase) {
                            System.out.print(" ");
                        }

                        System.out.print(outputPhrase);
                        isFirstPhrase = false;

                        // Reset variables
                        outputPhrase = 0;
                        currentBitPosition = 0;
                        // Increase phrase count
                        phraseCount++;
                    }
                }
            }
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}