import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Tester {

    public static boolean test(Class c, String in, String out) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        StringBuilder inLines = new StringBuilder();
        Path inPath = Paths.get(in);
        try (Stream<String> lines = Files.lines(inPath)) {
            lines.forEach(s -> inLines.append(s + "\n"));
        } catch (IOException io) {}

        StringBuilder outLines = new StringBuilder();
        Path outPath = Paths.get(out);
        try (Stream<String> lines = Files.lines(outPath)) {
            lines.forEach(s -> outLines.append(s + "\n"));
        } catch (IOException io) {}

        in = inLines.toString();
        out = outLines.toString();

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        PrintStream systemOut = System.out;
        ByteArrayInputStream inStream = new ByteArrayInputStream(in.getBytes());
        System.setIn(inStream);
        System.setOut(new PrintStream(outStream));

        Method main = c.getMethod("main", String[].class);
        Object[] params = new String[]{};
        main.invoke(null, new Object[] {params});

        String[] actual = outStream.toString().split("\n");
        String[] expected = out.toString().split("\n");

        for (int i = 0; i < actual.length; i++) {
            actual[i] = actual[i].trim();
            expected[i] = expected[i].trim();
        }

        System.setOut(systemOut);
        for (int i = 0; i < actual.length; i++) {
            if (!actual[i].equals(expected[i])) {
                System.out.format("line:%d\tactual: %s\texpected:%s\n", (1+i), actual[i], expected[i]);
            }
        }

        try {
            org.junit.Assert.assertArrayEquals(actual, expected);
        } catch (AssertionError as) {
            return false;
        }

        return true;
    }
}
