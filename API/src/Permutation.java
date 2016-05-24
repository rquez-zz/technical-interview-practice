import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class Permutation {

    private static Stream<String> permutateAsStream(String s) {

        if (s.isEmpty()) return Stream.of("");

        // s.substring(0,i) + s.substring(i+1) drops the char at i
        // s.charAt(i) + t, prepends the dropped char to the string

        return IntStream.range(0, s.length())
                .boxed()
                .flatMap(i ->
                        permutateAsStream(s.substring(0, i) + s.substring(i+1)).map(t -> s.charAt(i) + t));
    }

    public static List<String> permutate(String s) {
        return permutateAsStream(s).collect(Collectors.toList());
    }

    public static class UnitTest {

        @Test
        public void test_permutateString() {
            assertThat(permutate("cat"), hasItems("cat", "atc", "tca", "cta", "tac", "act"));
        }
    }
}
