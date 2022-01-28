package src;

public class RLE {
    

    public static String encode(LinkedList<String> _list) {
        var result = new StringBuilder();
        int count = 1;
        var current = _list.get(0);
        String current_str = current.getData();
        while (current.next != null) {
            current = current.next;
            if (count <= 0) {
                count++;
                current_str = current.getData();
            } else if (current.getData().equals(current_str)){
                count++;
            } else {
                result.append(count);
                result.append(current_str);
                count = 0;
            }
        }
        return result.toString();
    }

    public static LinkedList<String> decode(String msg) {
        var result_list = new LinkedList<String>();
        var msg_in_char_array = msg.toCharArray();
        for (int i = 0; i < msg_in_char_array.length; i++) {
            if (Character.isDigit(msg_in_char_array[i])) {
                i++;
                for (int a = 0; a < Character.getNumericValue(msg_in_char_array[i]); a++) {
                    result_list.add(String.valueOf(msg_in_char_array[i]));
                }
            }
        }

        return result_list;
    }

}
