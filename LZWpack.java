import java.util.Scanner;

public class LZWpack {
    public static void main(String[] args) {
        int phraseCount = 16;
        int currentBitPosition = 0;
        byte outputByte = 0;

        try {
            Scanner scanner = new Scanner(System.in);

            //Continue reading each phrase number
            while (scanner.hasNext()) {
                int phraseNumber = scanner.nextInt();
                int numBits = (int) Math.ceil(Math.log(phraseCount) / Math.log(2));

                while (numBits > 0) {
                    int remainingBits = 8 - currentBitPosition;
                    //If we have space, add bits to the byte
                    if (remainingBits >= numBits) {
                        outputByte = (byte) ((outputByte << numBits) | phraseNumber);
                        currentBitPosition += numBits;
                        numBits = 0;
                    //Else add any remaining bits we can, output it and start a new byte
                    } else {
                        outputByte = (byte) ((outputByte << remainingBits) | (phraseNumber >> (numBits - remainingBits)));
                        System.out.write(outputByte);
                        System.out.flush();
                        outputByte = 0;
                        phraseNumber &= (1 << (numBits - remainingBits)) - 1;
                        numBits -= remainingBits;
                        currentBitPosition = 0;
                    }
                }

                //If we have fulled out the byte, output it
                if (currentBitPosition == 8) {
                    System.out.write(outputByte);
                    System.out.flush();
                    outputByte = 0;
                    currentBitPosition = 0;
                }

                phraseCount++;
            }

            //Account for any left over bits
            if (currentBitPosition > 0) {
                outputByte <<= (8 - currentBitPosition);
                System.out.write(outputByte);
                System.out.flush();
            }

            scanner.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}