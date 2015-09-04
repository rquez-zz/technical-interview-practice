/*
Problem:
    Given unsigned integer 'x', write an algorithm that returns unsigned integer 'y'
    such that it has the same number of bits set as 'x'
    and is not equal to 'x' and the distance |x-y| is minimized.

    Example:
    x: 01
    y: 10

    Note that one bit is set and 'x' is not equal 'y'.
    You may assume that x is positive integer between zero and 2^32-2;

Errors:
    You didn't realize that dealing with unsigned ints isn't an issue since you can assume that
    the input integers are positive.
    You didn't know exactly how the bounds of the string.substring() method worked
    You didn't know how to manipulate bitstrings.
    You implemented all the parts of a for loop but didn't use it.

Process:
    Convert the input int into a binarystring by using Integer.toBinaryString(x)
    Check the least significant bit in the string. This will be the bit you'll modify most of the time to guarrentee
    a minimal change between x and y.
    There's two cases, if the LSB is 1 and if the LSB is 0
    Loop through the string in reverse
    IF the LSB is 1
        AND the current index is 0, then add a 0 behind the most sigificant bit
        OR the next char is 0, then swap it with the adject 1 bit
    IF the LSB is 0
        AND the next char is 0, then sawp it with the adject 0 bit
    ELSE
        move the index to the next bit

Optimization:
    Can this be done without bitstrings?
    Can it be done with binary numbers, bit masking, and bit shifting?

*/
public class UnsignedIntsUnequal {

	public static void main(String args[]) {

        System.out.println(unsignedIntUnequal(15));
        System.out.println(unsignedIntUnequal(13));
        System.out.println(unsignedIntUnequal(14));
        System.out.println(unsignedIntUnequal(0b01));
        System.out.println(unsignedIntUnequal(0b100));
	}
	public static int unsignedIntUnequal(int x) {
		String bitString = Integer.toBinaryString(x);
		int n = bitString.length() - 1;
        for (int i = n; i > 0 - 1; i--) {
            if (bitString.charAt(n) == '1') {
                if (i == 0) {
                    bitString = "10" + bitString.substring(1);
                    break;
                } else if (bitString.charAt(i - 1) == '0') {
                    bitString = bitString.substring(0, i - 1) + "10" + bitString.substring(i + 1);
                    break;
                }
            } else if (bitString.charAt(i - 1) == '1' && bitString.charAt(n) == '0') {
                bitString = bitString.substring(0, i - 1) + "01" + bitString.substring(i + 1);
                break;
            }
        }
        return Integer.parseInt(bitString, 2);
	}
}
