package src;

public class RLE {

    /**
     * quickly converse a string to char_array then to its equivalent linked list
     * using for testing purpose
     *
     * @param str_chars a string that will be used to covert to its equivalent linked list
     * @return the equivalent linked list
     */
    public static LinkedList<String> convert_string_charters_to_LinkedList(String str_chars) {
        var _list = new LinkedList<String>();
        for (char item : str_chars.toCharArray()) {
            _list.add(String.valueOf(item));
        }
        return _list;
    }

    public static String encode(String str_chars) {
        return RLE.encode(RLE.convert_string_charters_to_LinkedList(str_chars));
    }

    /**
     * takes the SLL as the parameter & generate the encoded string
     *
     * @param _list the linked list that will be encoded
     * @return a encoded linked list
     */
    public static String encode(LinkedList<String> _list) {
        var final_result = "";
        if (_list.size() > 0) {
            // using a StringBuilder to hold the result
            var result = new StringBuilder();
            int count = 1;
            var current = _list.get(0);
            String current_str = current.getData();
            // loop through the linked list
            while (current.next != null) {
                current = current.next;
                // add 1 to count if the same character appears
                if (current.getData().equals(current_str)) {
                    count++;
                } else {
                    // add the times of duplication and the string that is keeping track of into the StringBuilder
                    result.append(count);
                    result.append(current_str);
                    // update the string that will be needed to keep track of
                    current_str = current.getData();
                    // reset count to 1
                    count = 1;
                }
            }
            // add the result regarding the final character into the StringBuilder
            result.append(count);
            result.append(current_str);
            // generate the result
            final_result = result.toString();
        }
        // print the result
        System.out.println(final_result);
        // return the result
        return final_result;
    }

    /**
     * takes the encoded string as the parameter and prints the decoded SLL
     *
     * @param msg the string that will be decoded
     * @return a decoded string
     */
    public static LinkedList<String> decode(String msg) {
        var result_list = new LinkedList<String>();
        // covert string to char array for better access
        var msg_in_char_array = msg.toCharArray();
        // loop through the array and decode the string
        for (int i = 0; i < msg_in_char_array.length; i++) {
            if (Character.isDigit(msg_in_char_array[i])) {
                i++;
                for (int a = 0; a < Character.getNumericValue(msg_in_char_array[i - 1]); a++) {
                    // adding the character to the linked list
                    result_list.add(String.valueOf(msg_in_char_array[i]));
                }
            }
        }
        // print the result
        System.out.println(result_list.toStringWithoutBracket());
        // return the result
        return result_list;
    }

    /**
     * takes 2 SLL as parameters and returns true if they represent the same sequence and false if they are not the same.
     *
     * @param msg1 the first string
     * @param msg2 the second string
     * @return whether they are equal
     */
    public static boolean equal(String msg1, String msg2) {
        return RLE.decode(msg1).equals(RLE.decode(msg2));
    }

}
