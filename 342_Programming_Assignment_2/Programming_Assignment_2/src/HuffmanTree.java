import java.util.LinkedHashMap;

public class HuffmanTree extends PriorityQueue {

    private final LinkedHashMap<Character, CharData> frequencyTable = new LinkedHashMap<>();

    public HuffmanTree() {
        super(100);
    }

    public Result encode(String inputString) {
        for (char key : inputString.toCharArray()) {
            if (frequencyTable.containsKey(key)) {
                frequencyTable.get(key).frequency++;
            } else {
                frequencyTable.put(key, new CharData());
            }
        }
        for (char key : frequencyTable.keySet()) {
            this.addElement(key, frequencyTable.get(key).frequency);
        }
        while (this.size() > 1) {
            var leftNode = this.removeNext();
            var rightNode = this.removeNext();
            var newNode = new HuffmanTreeNode<>(null, leftNode.getPriority() + rightNode.getPriority());
            newNode.left = leftNode;
            newNode.right = rightNode;
            this.add(newNode);
        }
        traverse(this.peek(), new StringBuilder());
        var encodedBit = new StringBuilder();
        for (char key : inputString.toCharArray()) {
            encodedBit.append(frequencyTable.get(key).code);
        }
        return new Result(encodedBit.toString(), frequencyTable);
    }

    public String decode(String encodedBit) {
        var currentNode = this.peek();
        var resultStr = new StringBuilder();
        for (char _bit : encodedBit.toCharArray()) {
            if (_bit == '0') {
                currentNode = currentNode.left;
            } else {
                currentNode = currentNode.right;
            }
            if (currentNode.left == null && currentNode.right == null) {
                resultStr.append(currentNode.getData());
                currentNode = this.peek();
            }
        }
        return resultStr.toString();
    }

    private void traverse(HuffmanTreeNode currentNode, StringBuilder codeForCurrentNode) {
        if (currentNode.left != null || currentNode.right != null) {
            if (currentNode.left != null) {
                var codeForLeftNode = new StringBuilder(codeForCurrentNode);
                codeForLeftNode.append("0");
                traverse(currentNode.left, codeForLeftNode);
            }
            if (currentNode.right != null) {
                var codeForRightNode = new StringBuilder(codeForCurrentNode);
                codeForRightNode.append("1");
                traverse(currentNode.right, codeForRightNode);
            }
        } else {
            frequencyTable.get((Character) currentNode.getData()).code = codeForCurrentNode.toString();
        }
    }

    @Override
    public String toString() {
        return "HuffmanTree{" +
                "frequencyTable=" + frequencyTable +
                '}';
    }

    public static class Result {
        public String encodedBit;
        public LinkedHashMap<Character, CharData> frequencyTable;

        public Result(String encodedBit, LinkedHashMap<Character, CharData> frequencyTable) {
            this.encodedBit = encodedBit;
            this.frequencyTable = frequencyTable;
        }
    }

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
