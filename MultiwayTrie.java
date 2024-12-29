/* Class for handling the entirety of the multiway trie structure */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MultiwayTrie {
    private TrieNode root;
    private int phraseNumber = 0;

    public MultiwayTrie() {
        this.root = new TrieNode('\0', -1);
        initialiseTrie();
    }

    //Method for inserting a new node into the trie
    public void insertNode(TrieNode parent, char value){
        // Only add if the child doesn't exist
        if(!parent.checkChildExistsByValue(value)){
            List<TrieNode> children = parent.getChildren();
            children.add(new TrieNode(value, getPhraseNumber()));
            Collections.sort(children, (a, b) -> Character.compare(a.getValue(), b.getValue()));
        }
    }

    //Method for retrieving the root node of the trie
    public TrieNode getRoot() {
        return root;
    }

    //Method to get the current phrase number for entering in a new phrase
    public int getPhraseNumber(){
        int currPhrase = this.phraseNumber;
        this.phraseNumber++;
        return currPhrase;
    }

    //Method for initialising the trie with the 16 starting nodes (hex)
    public void initialiseTrie(){
        List<TrieNode> children = getRoot().getChildren();
        for (int i = 0; i <= 15; i++) {
            char hexValue = (i < 10) ? (char) ('0' + i) : (char) ('A' + i - 10);
            children.add(new TrieNode(hexValue, getPhraseNumber()));
        }

        // Sort the list of children lexicographically
        Collections.sort(children, (a, b) -> Character.compare(a.getValue(), b.getValue()));
    }

    public TrieNode searchTrie(TrieNode currNode, char hexVal) {
        List<TrieNode> children = currNode.getChildren();
        for (TrieNode child : children) {
            if(child.getValue() == hexVal){
                return child;
            }
        }
        return null;
    }

    //Method to print out trie
    public void dumpTrie() {
        TrieNode root = getRoot();
        root.dumpNode(root);
    }
    
}

/* Class for handling a singular node in the trie structure */
class TrieNode {
    private List<TrieNode> children;
    private char value;
    private int phraseNumber;

    //Constructor to initialise possible size of children, its value and phrase number
    public TrieNode(char value, int phraseNumber) {
        this.children = new ArrayList<TrieNode>();
        this.value = value;
        this.phraseNumber = phraseNumber;
    }

    /* Method to check if a child exits for a given value */
    public Boolean checkChildExistsByValue(char value){
        for (TrieNode child : getChildren()) {
            if(child.getValue() == value){
                return true;
            }
        }

        return false;
    }

    //Method to get its children
    public List<TrieNode> getChildren(){
        return this.children;
    }

    //Method to get its value
    public char getValue() {
        return this.value;
    }

    //Method to get its phrase number
    public int getNodePhrase() {
        return this.phraseNumber;
    }

    //Method to dump node and recursively dump its children
    public void dumpNode(TrieNode node) {
        if (node == null) {
            return;
        }
    
        if(node.getNodePhrase() != -1){
            System.out.println("Value: " + node.getValue() + ", Phrase Number: " + node.getNodePhrase());
        }
    
        List<TrieNode> children = node.getChildren();
        for (TrieNode child : children) {
            dumpNode(child);
        }
    }
}