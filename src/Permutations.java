/*
	Problem:
		Write a method to return all permutations of a string

	Errors:
		I had no idea how to do permutations

	Process:
		Permutations can be represented in a n-ary tree and traversed by DFA
		The root is where you start and those children nodes are all the letters
		of the
 */
import java.util.ArrayList;

class Permutations {
	public static void main(String args[]) {
		permutate("CAT");
	}
	public static void permutate(String s1) {
		ArrayList list = new ArrayList<String>();
		permutate("", s1, list);
		Object[] array = list.toArray();
		for (Object s : array ) {
			System.out.print(s + " ");
		}
	}
	public static void permutate(String prefix, String sString, ArrayList<String> list) {
		int length = sString.length();
		if ( length == 0 )
			list.add(prefix);
		else {
			for (int i = 0 ; i < length; i++)
				permutate(prefix + sString.charAt(i), sString.substring(0, i) + sString.substring(i + 1), list);
		}
	}
}