package src;

public class RLE {

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

    public static String encode(LinkedList<String> _list) {
        if (_list.size() > 0) {
            var result = new StringBuilder();
            int count = 1;
            var current = _list.get(0);
            String current_str = current.getData();
            while (current.next != null) {
                current = current.next;
                if (current.getData().equals(current_str)) {
                    count++;
                } else {
                    result.append(count);
                    result.append(current_str);
                    current_str = current.getData();
                    count = 1;
                }
            }
            result.append(count);
            result.append(current_str);
            return result.toString();
        } else {
            return "";
        }
    }

    public static LinkedList<String> decode(String msg) {
        var result_list = new LinkedList<String>();
        var msg_in_char_array = msg.toCharArray();
        for (int i = 0; i < msg_in_char_array.length; i++) {
            if (Character.isDigit(msg_in_char_array[i])) {
                i++;
                for (int a = 0; a < Character.getNumericValue(msg_in_char_array[i - 1]); a++) {
                    result_list.add(String.valueOf(msg_in_char_array[i]));
                }
            }
        }
        return result_list;
    }

    public static boolean equal(String msg1, String msg2) {
        return RLE.decode(msg1).equals(RLE.decode(msg2));
    }

}
