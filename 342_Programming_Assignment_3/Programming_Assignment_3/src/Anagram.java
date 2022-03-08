import java.util.Arrays;
import java.util.LinkedList;

public class Anagram {

    private final String key;
    private final LinkedList<String> values;

    public Anagram(String key) {
        var keyInCharArray = key.toCharArray();
        Arrays.sort(keyInCharArray);
        this.key = Arrays.toString(keyInCharArray);
        this.values = new LinkedList<>();
    }

    public String getKey() {
        return key;
    }

    public LinkedList<String> getValues() {
        return values;
    }

    public void addValue(String newValue) {
        values.add(newValue);
    }
}
