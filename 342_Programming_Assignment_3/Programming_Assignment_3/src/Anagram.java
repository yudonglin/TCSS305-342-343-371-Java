import java.util.LinkedList;

public class Anagram {

    private final String key;
    private final LinkedList<String> values;

    public Anagram(String key) {
        this.key = key;
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
