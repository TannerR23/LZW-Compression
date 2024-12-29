import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HexToByte {
    public static void main(String[] args) {
        try {
            // Read hex string from standard input
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String hexString = reader.readLine();

            // Convert hex string to bytes
            int len = hexString.length();
            byte[] data = new byte[len / 2];
            for (int i = 0; i < len; i += 2) {
                // Convert 2 hex values into one byte
                if(i + 1 < len){
                    data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                                        + Character.digit(hexString.charAt(i + 1), 16));
                }
            }

            // Print the byte array
            System.out.write(data);
            System.out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}