import java.io.*;

public class ByteToHex {
    public static void main(String[] args) {
        try {
            // Reading from standard input
            InputStream inputStream = System.in;
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            
            // Read bytes directly from input stream
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            // Convert the bytes to hex values
            byte[] inputBytes = outputStream.toByteArray();
            for (byte b : inputBytes) {
                String hex = String.format("%02X", b & 0xFF);
                System.out.print(hex);
            }

            // Close the input stream
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}