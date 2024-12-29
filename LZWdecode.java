import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class LZWdecode {
    private static HashMap<Integer, Tuple> encodingDict = new HashMap<>();
    private static int dictIndex = 0;
    public static void main(String[] args) {
        InitialiseDict();

        try {
            Scanner scanner = new Scanner(System.in);

            // Loop through all the phrase numbers received
            while (scanner.hasNext()) {
                int phraseNumber = scanner.nextInt();

                // Create next element in dict
                Tuple tuple = new Tuple(phraseNumber, null);
                int index = GetDictIndex();
                encodingDict.put(index, tuple);
                
                // Search through the dict - adding to a stack (of the values)
                List<Character> charStack = SearchDict(phraseNumber, index);

                // Append each char to the message
                for (Character character : charStack) {
                    System.out.print(character);
                }
            }

            scanner.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* Method for call recursive function and checking some boundaries */
    public static List<Character> SearchDict(int phraseNumber, int index){
        List<Character> stack = new ArrayList<Character>();
        searchRecursive(phraseNumber, index, stack);

        Tuple prevTuple = encodingDict.get(index - 1);
        //Check to see if we need to add the following val to the previous dictionary element
        if (prevTuple.getFollowingValue() == null) {
            prevTuple.setFollowingValue(stack.get(0));
            //Check to see if we are looking at previous dict element, in this case we need to add this to the stack again once we know what this value is
            if(phraseNumber == index -1){
                stack.add(prevTuple.getFollowingValue());
            }
        }
    
        return stack;
    }

    /* Method for recursively searching down the dictionary add to the charstack */
    private static void searchRecursive(int phraseNumber, int index, List<Character> stack) {
        Tuple currElement = encodingDict.get(index);
        if (currElement.getPhrase() != null) {
            searchRecursive(phraseNumber, currElement.getPhrase(), stack);
        }
        if (currElement.getFollowingValue() != null) {
            stack.add(currElement.getFollowingValue());
        }
    }

    /* Method for inserting inital values for dictionary */
    public static void InitialiseDict(){
        for (int i = 0; i <= 15; i++) {
            char hexValue = (i < 10) ? (char) ('0' + i) : (char) ('A' + i - 10);
            Tuple tuple = new Tuple(null, hexValue);
            encodingDict.put(GetDictIndex(), tuple);
        }
    }

    /* Method for retrieving the next dict index and incrementing it */
    public static int GetDictIndex(){
        int index = dictIndex;
        dictIndex++;
        return index;
    }

    /* Class to handle the values being inserted into the dictionary (2 vals, phrase number and the following value of the pattern) */
    static class Tuple {
        private Integer phrase;
        private Character followingValue;
    
        public Tuple(Integer phrase, Character followingValue) {
            this.phrase = phrase;
            this.followingValue = followingValue;
        }
    
        //Getter for phrase number
        public Integer getPhrase() {
            return phrase;
        }
    
        //Getter for following value
        public Character getFollowingValue() {
            return followingValue;
        }
    
        //setter for following value
        public void setFollowingValue(Character followingVal) {
            this.followingValue = followingVal;
        }
    }

}
