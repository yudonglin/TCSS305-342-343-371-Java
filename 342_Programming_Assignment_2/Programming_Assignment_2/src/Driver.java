import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class Driver {

    // the frequency table
    private static final HashMap<Character, CharData> frequencyTable = new HashMap<>();
    // the root of HuffmanTree for final result
    private static HuffmanTreeNode<Character> rootNodeOfTree = null;

    public static void main(String[] args) throws IOException {
        // read data from the input file
        var inputFile = new BufferedReader(new FileReader("input.txt"));
        var inputData = inputFile.lines().toList();
        // close the input file
        inputFile.close();
        // outputString!! I hope I can use ArrayList, but... never mind
        StringBuilder outputLines = new StringBuilder();
        // loop through input strings
        for (String _str : inputData) {
            // make sure the string is not empty
            if (_str.length() > 0) {
                var encodedBit = encode(_str);
                outputLines.append("[").append(_str).append("]\n");
                outputLines.append("======================================\n");
                outputLines.append("char    frequency   code\n");
                outputLines.append("--------------------------------------\n");
                // loop through the frequency table and write the result to the table
                for (char key : frequencyTable.keySet()) {
                    outputLines.append(key).append("        ").append(frequencyTable.get(key).frequency)
                            .append("        ").append(frequencyTable.get(key).code).append("\n");
                }
                outputLines.append("======================================\n");
                outputLines.append("[Encoded Bit: ").append(encodedBit).append("][").append(encodedBit.length()).append("]\n");
                var decodedMsg = decode(encodedBit);
                outputLines.append("[Decoded String: ").append(decodedMsg).append("][").append(decodedMsg.length() * 8).append("]\n");
                outputLines.append("[Compression Ratio: ").append((float) decodedMsg.length() * 8 / encodedBit.length()).append("]\n");
                outputLines.append("\n");
            }
        }
        // write the result tot the output file
        Files.writeString(Paths.get("output.txt"), outputLines.toString(), StandardCharsets.UTF_8);
    }

    public static String encode(String inputString) {
        // clean frequency table
        frequencyTable.clear();
        // update frequency
        for (char key : inputString.toCharArray()) {
            if (frequencyTable.containsKey(key)) {
                frequencyTable.get(key).frequency++;
            } else {
                frequencyTable.put(key, new CharData());
            }
        }
        // create a PriorityQueue for hosting characters
        PriorityQueue<Character> priorityQueueForConstructingTree = new PriorityQueue<>(frequencyTable.size() + 1);
        for (char key : frequencyTable.keySet()) {
            priorityQueueForConstructingTree.addElement(key, frequencyTable.get(key).frequency);
        }
        // generate HuffmanTree
        while (priorityQueueForConstructingTree.size() > 1) {
            var leftNode = priorityQueueForConstructingTree.removeNext();
            var rightNode = priorityQueueForConstructingTree.removeNext();
            // create a new HuffmanTree
            HuffmanTree<Character> hfmTree = new HuffmanTree<>(leftNode.getPriority() + rightNode.getPriority());
            hfmTree.setLeft(leftNode);
            hfmTree.setRight(rightNode);
            // pass the root of new HuffmanTree into PriorityQueue
            priorityQueueForConstructingTree.add(hfmTree.root);
        }
        // since there is only one node left in the queue, that must be the root
        rootNodeOfTree = priorityQueueForConstructingTree.removeNext();
        // traversing the Node and generate codes
        traverse(rootNodeOfTree, new StringBuilder());
        // generate encoded bits
        var encodedBit = new StringBuilder();
        // set the code
        for (char key : inputString.toCharArray()) {
            encodedBit.append(frequencyTable.get(key).code);
        }
        // return the encoded bits
        return encodedBit.toString();
    }

    public static String decode(String encodedBit) {
        var currentNode = rootNodeOfTree;
        var resultStr = new StringBuilder();
        for (char _bit : encodedBit.toCharArray()) {
            // if 0, go left, else go right
            if (_bit == '0') {
                currentNode = currentNode.left;
            } else {
                currentNode = currentNode.right;
            }
            // if we reach the leaf, then append the character and go back and start over
            if (currentNode.isLeaf()) {
                resultStr.append(currentNode.getData());
                currentNode = rootNodeOfTree;
            }
        }
        // we are done! return the result
        return resultStr.toString();
    }

    private static void traverse(HuffmanTreeNode<Character> currentNode, StringBuilder codeForCurrentNode) {
        if (currentNode.left != null || currentNode.right != null) {
            // if a left exists, then go left
            if (currentNode.left != null) {
                var codeForLeftNode = new StringBuilder(codeForCurrentNode);
                codeForLeftNode.append("0");
                // pass in the next node and the code representing the path
                traverse(currentNode.left, codeForLeftNode);
            }
            // if a right exists, then go right
            if (currentNode.right != null) {
                var codeForRightNode = new StringBuilder(codeForCurrentNode);
                codeForRightNode.append("1");
                // pass in the next node and the code representing the path
                traverse(currentNode.right, codeForRightNode);
            }
        } else {
            // we reach a leaf! set the code of the character of current Node
            frequencyTable.get(currentNode.getData()).code = codeForCurrentNode.toString();
        }
    }

    /**
     * a class use to store data for each character
     */
    public static class CharData {
        public int frequency;
        public String code;

        public CharData() {
            this.frequency = 1;
        }

        @Override
        public String toString() {
            return "{" +
                    "frequency=" + frequency +
                    ", code='" + code + '\'' +
                    '}';
        }
    }
}
