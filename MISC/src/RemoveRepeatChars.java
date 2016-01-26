/*
Problem:
    Remove repeated chars in a string.

Errors:
    I didn't realize there were classes that had these sort of properties
    I had no idea how to do this

Process:
    The objective is to remove duplicate characters in a string.
    By utilizing the existing java tools I can do this.
    By using a LinkedHashSet I can maintain both the order and
    the uniquness of the characters in the string.
    So first I add the characters to the set, which will ignore any duplicates in a queue fashion
    Then I make a string builder to make the new string.
    There I do a for each loop to iterate through the characters in the stringset and append it to the
    string builder.

Notes:
    You need to keep reminding yourself of the goal as you’re coding.
    Utilize the java libraries at your disposal.
    Here you’re using the LinkedHashSet because when you add to a Set, you
    remove duplicates and you maintain the order. If you used a hashset you wouldn’t keep the order of the string.
    Remember to import the library that you are using.
    This shows you are proficient using a language by utilizing the tools that it provides.
 */
import java.util.LinkedHashSet;

class RemoveRepeatChars {
    public static void main(String args[]) {
        System.out.println(removeRepeatChars("hadfa"));
        System.out.println(removeRepeatChars("honooney"));
    }
    public static String removeRepeatChars(String sTestString) {
        LinkedHashSet<Character> stringSet =
                new LinkedHashSet<Character>();
        char[] stringArray = sTestString.toCharArray();
        for ( char c : stringArray ) {
            stringSet.add(c);
        }
        StringBuilder sb = new StringBuilder();
        for ( Character c : stringSet ) {
            sb.append(c);
        }
        return sb.toString();
    }
}
