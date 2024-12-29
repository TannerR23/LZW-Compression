import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LZWencode {
    private static List<Integer> encodedMessage = new ArrayList<Integer>();
    private static MultiwayTrie trie = new MultiwayTrie();

    public static void main(String[] args) {
        try {
            // Read hex string from standard input
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String hexString = reader.readLine();

            // Call method to process hex message
            ProcessHexString(hexString.toUpperCase());

            //Print to standard output the phrase encoded message
            for (int i = 0; i < encodedMessage.size(); i++) {
                System.out.print(encodedMessage.get(i));
                if (i < encodedMessage.size() - 1) {
                    System.out.print(" ");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* Method for processing the hex string */
    public static void ProcessHexString(String hexString){
        Boolean endOfString = false;
        //Loop through hex string
        for (int index = 0; index < hexString.length(); index ++) {
            TrieNode currNode = trie.getRoot();
            TrieNode parent = currNode;
            // Loop through until we have no longer seen this pattern
            int depth = 0;
            while(currNode != null){
                parent = currNode;
                //Check if we have finished the string mid search
                if(index+depth >= hexString.length()){
                    endOfString = true;
                    break;
                }

                currNode = trie.searchTrie(parent, hexString.charAt(index+depth++));
            }

            //If we have reached the end of string, add last phrase to encoded message and break out of loop
            encodedMessage.add(parent.getNodePhrase());
            if(endOfString){
                break;
            }
            else if(index+depth < hexString.length()){
                //insert next char node
                trie.insertNode(parent, hexString.charAt(index+depth-1));
            }

            //Account for index skipping ahead on encoding (-2 as will increment again on next loop iteration)
            index += depth - 2;
        }
    }
}
