import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;

/*
Problem Statement

        Today, a large live concert is going to take place. Some interprets (called "idols") are going to perform at the concert. Different idols have different names.
        There are M distinct songs that can be included in the concert. The songs are numbered 0 through M-1. Song i can only be performed by the idol s[i]. Including this song in the concert will increase the happiness of the audience by h[i].
        We have to choose the set list for this concert. Our goal is to make the audience as happy as possible. However, the concert time is limited and therefore each idol can only perform at most one song.
        You are given the int[] h and the String[] s with M elements each. Find the set of songs that should be played at the concert. The set of songs must have the following properties:
        Each idol will perform at most one song.
        After hearing the songs, the happiness of the audience will increase by the largest amount possible.
        Return the largest possible amount of happiness the audience can gain.

        Notes
        The value M is not given explicitly. You can determine M as the length of h.

        Constraints
        M will be between 1 and 100, inclusive.
        h and s will contain exactly M elements each.
        All numbers in h will be between 1 and 100, inclusive.
        All strings in s will have between 1 and 10 characters, inclusive.
        For each valid i, each character in s[i] will be a lowercase letter ('a'-'z').
*/
public class LiveConcert {

    public static int maxHappiness(int[] songHappiness, String[] idolNames) {
        HashSet<String> idolsThatAlreadySang = new HashSet<String>();
        int totalHappiness = 0;
        for (int i = 0; i < idolNames.length; i++) {
            if (!idolsThatAlreadySang.contains(idolNames[i])) {
                int happiness = songHappiness[i];
                if (i != songHappiness.length - 1) {
                    for (int j = i + 1; j < idolNames.length; j++) {
                        if (idolNames[j] == idolNames[i] && happiness < songHappiness[j])
                            happiness = songHappiness[j];
                    }
                }
                totalHappiness += happiness;
                idolsThatAlreadySang.add(idolNames[i]);
            }
        }
        return totalHappiness;
    }

    public static class UnitTest {

        @Test
        public void testMaxHappiness() {
            assertEquals(23, maxHappiness(new int[]{10, 5, 6, 7, 1, 2}, new String[]{"ciel", "bessie", "john", "bessie", "bessie", "john"}));
            assertEquals(4, maxHappiness(new int[]{3,3,4,3,3}, new String[]{"a", "a", "a", "a", "a"}));
            assertEquals(100, maxHappiness(new int[]{100}, new String[]{"a"}));
        }
    }
}
