/*
Problem:
    Two strings s1 and s2 are given.
    You have make a new string s3 such that it has both s1 and s2 as one of
    its subsequences and length of s3 is minimum.

    input:
    apple pear
    output:
    applear

Errors:
    You forgot to add your closing parenthesis for println
    You forgot to add the method parenthesis for s1.length()

Process:
    Compare the first character in s2 to all the characters in s1 until there’s a match
    Upon matching, remove that character from s2 and loop
    Once all the characters in s1 are compared then append the remaining s2 string
    to the end of s1
*/
public class StringOverlapMerge {
    public static void main(String args[]) {
        System.out.println(overlapMerge("apple", "pear"));
        System.out.println(overlapMerge("apple", "apple"));
        System.out.println(overlapMerge("ABCD", "EFGH"));
    }
    public static String overlapMerge(String s1, String s2) {
        for ( int i = 0; i < s1.length(); i++) {
            if ( s1.charAt(i) == s2.charAt(0) ) {
                s2 = s2.substring(1);
            }
        }
        return s1 + s2;
    }
}
