import java.util.Arrays;

public class HashTable {

    // the size of the table
    public static final int SIZE = 50_000;
    // an array used to store Anagram
    private final Anagram[] table;
    // count the collisions
    private int collisions;
    // a flag used to indicate whether a collision is taking place
    private boolean isCollisionAppeared = false;

    public HashTable() {
        this.table = new Anagram[SIZE];
        this.collisions = 0;
    }

    /**
     * takes key as a parameter, computes the correct index position (location in the hash table) and returns it
     * This approach guarantees that all words (values) which are anagrams of one another are stored in the same
     * index of the hash table.
     *
     * @param key the key
     * @return the correct index position (location in the hash table)
     */
    public int hashCode(String key) {
        int prime_index = 0;
        // using character's ascii code value to compute a result (method 1 from lecture)
        for (char c : key.toCharArray()) {
            prime_index += c * c;
        }
        // add prime_index by left shift the bits of length by 1
        prime_index += key.length() << 1;
        // find a valid position and return it
        return findValidIndex(key, prime_index);
    }

    /**
     * look for a valid position based on the prime index
     *
     * @param key           the key
     * @param hashCodeOfKey the hashcode of the key
     * @return a valid position if exist
     */
    private int findValidIndex(String key, int hashCodeOfKey) {
        // sort the key first from a-z
        var keyInCharArray = key.toCharArray();
        Arrays.sort(keyInCharArray);
        var keyInString = Arrays.toString(keyInCharArray);
        // compute the valid index
        hashCodeOfKey = Math.abs(hashCodeOfKey) % SIZE;
        // see if an object exist on this position or its Anagram object exists
        if (this.table[hashCodeOfKey] == null || this.table[hashCodeOfKey].getKey().equals(keyInString)) {
            return hashCodeOfKey;
        } else {
            // if not, then we have a collision
            isCollisionAppeared = true;
            // using linear probing to find a valid spot
            for (int i = 1; i < SIZE - 1; i++) {
                hashCodeOfKey++;
                hashCodeOfKey %= SIZE;
                if (this.table[hashCodeOfKey] == null || this.table[hashCodeOfKey].getKey().equals(keyInString)) {
                    return hashCodeOfKey;
                }
            }
        }
        return -1;
    }

    /**
     * a helper method that is used to insert value into table
     *
     * @param key   a new key
     * @param index the index where wants to insert
     */
    private void add(String key, int index) {
        if (this.table[index] == null) {
            this.table[index] = new Anagram(key);
        }
        this.table[index].addValue(key);
        // if a collision appeared during the adding process, then increment collision counter
        if (isCollisionAppeared) {
            collisions++;
            isCollisionAppeared = false;
        }
    }

    /**
     * adds a new (if key not found) Anagram object to the Hash Table at the appropriate index position returned by the
     * hashCode() or updates (if key found) the existing Anagram object with the new word as value.
     *
     * @param key a new key
     */
    public void add(String key) {
        var key2 = key.toLowerCase();
        this.add(key2, this.hashCode(key2));
    }

    /**
     * similar to the add method, but using Java's String hashCode method to insert the key
     *
     * @param key a new key
     */
    public void addUsingJavaHashing(String key) {
        var key2 = key.toLowerCase();
        // since String's hashcode function will not give us the correct position, we need to use findValidIndex method
        // to find the position based on the hashcode of the key
        this.add(key2, this.findValidIndex(key2, key2.hashCode()));
    }

    /**
     * takes the search word as the parameter, compute its key and then use the hashCode() method
     * to return the index location where the key can be found.
     *
     * @param word the word we are looking for
     * @return its index
     */
    public int search(String word) {
        var key2 = word.toLowerCase();
        var index = this.findValidIndex(key2, this.hashCode(key2));
        // do not count the collision for searching
        isCollisionAppeared = false;
        return (index < 0 || this.table[index] == null) ? -1 : index;
    }

    /**
     * get the Anagram on the given index
     *
     * @param index the index to get
     * @return Anagram on the given index
     */
    public Anagram get(int index) {
        return table[index];
    }

    /**
     * get how many collisions have taken place so far
     *
     * @return the amount of collisions has taken place so far
     */
    public int getCollisions() {
        return collisions;
    }
}
