import java.util.HashMap;
import java.util.Map;

/**
 *
 *Given an array of strings with only lowercase letters , create a function that returns an array of those same strings,
 * but each string has its letters rearranged such that it becomes a palindrome (if possible, if not, return -1)
 *
 * TODO: Optimize!
 * Currently this runs in O(n * m + m) where n is the number of strings and m is the number of characters per string
 */
public class Palindromify {

    public static void main(String[] args) {
        String[] array1 = {"abcd", "efgh", "ijkl"};
        String[] array2 = {"aabbc", "ddee", "fffff", "aabbcccck", "aabbcccc"};
        //palindromifyArray(array1);
        palindromifyArray(array2);
    }

    public static int palindromifyArray(String[] array) {
        for (int i = 0; i < array.length; i++) {
            HashMap<Character,Integer> map = buildFrequencyMap(array[i]);
            Integer[] frequencies = new Integer[map.size()];
            map.values().toArray(frequencies);
            boolean isEven = array[i].length() % 2 == 0;
            if (isPossiblePalindrome(frequencies, isEven))
                array[i] = palindromify(map, array[i]);
            else
                return -1;
        }
        printArray(array);
        return 0;
    }
    public static void printArray(String[] array) {
        for (String s : array) {
            System.out.print(s + ", ");
        }
    }
    public static String palindromify(HashMap<Character, Integer> map, String string) {
        StringBuilder result = new StringBuilder(string);
        int n = 0;
        for (HashMap.Entry<Character, Integer> entry : map.entrySet() ) {
            int frequency = entry.getValue();
            if ( frequency % 2 != 0 ) {
                result.setCharAt((string.length() - 1) / 2, entry.getKey());
                continue;
            }
            int counter = frequency;
            int nTemp = n;
            while(counter > frequency / 2) {
                result.setCharAt(n, entry.getKey());
                counter--;
                n++;
            }
            while(counter > 0) {
                result.setCharAt(string.length() - nTemp - 1, entry.getKey());
                counter--;
                nTemp++;
            }
        }
        return result.toString();
    }
    public static boolean isPossiblePalindrome(Integer[] frequencies, boolean isEven) {
        if (isEven) {
            for ( int value : frequencies ) {
                if( value % 2 != 0)
                    return false;
            }
        } else {
            int exception = 0;
            for (int value : frequencies ) {
                if ( value % 2 != 0 )
                    exception++;
                if ( value % 2 != 0 && exception > 1 )
                    return false;
            }
        }
        return true;
    }

    public static HashMap buildFrequencyMap(String string) {
        HashMap map = new HashMap<Character, Integer>();
        char[] charArray = string.toCharArray();
        for (char c : charArray) {
            if (map.containsKey(c))
                map.put(c, (int)map.get(c) + 1);
            else
                map.put(c, 1);
        }
        return map;
    }
}
