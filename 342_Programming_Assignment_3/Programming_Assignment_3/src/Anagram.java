import java.util.Arrays;
import java.util.LinkedList;

public class Anagram {

    private final String key;
    private final LinkedList<String> values;

    public Anagram(String key) {
        // convert the key to char array
        var keyInCharArray = key.toCharArray();
        // sort the char array
        Arrays.sort(keyInCharArray);
        // use the sorted String as the key
        this.key = Arrays.toString(keyInCharArray);
        // init the values (using a linked list to store values)
        this.values = new LinkedList<>();
    }

    /**
     * @return the Anagram key
     */
    public String getKey() {
        return key;
    }

    /**
     * @return the collection of value(s)
     */
    public LinkedList<String> getValues() {
        return values;
    }

    /**
     * takes a value as parameter and adds it to the existing collection of value(s).
     *
     * @param newValue the new key to daa
     */
    public void addValue(String newValue) {
        values.add(newValue);
    }
}
