package hexlet.code;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import java.nio.file.NoSuchFileException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
public class DifferTest {

    private static Map<String, Object> expectedFile1;
    private static final String PATH = "src/test/resources/fixtures/";

    @BeforeAll
    static void getMap() {
        expectedFile1 = new HashMap<>();
        expectedFile1.put("host", "hexlet.io");
        expectedFile1.put("timeout", 50);
        expectedFile1.put("proxy", "123.234.53.22");
        expectedFile1.put("follow", false);
    }

    @Test
    void getDataTest() throws Exception {
        Map<String, Object> actual = Differ.getData(PATH + "file1.json");
        assertEquals(expectedFile1, actual);
    }

    @Test
    void generateTest1() throws Exception{
        String expected = "{\n" +
                "  - follow: false\n" +
                "    host: hexlet.io\n" +
                "  - proxy: 123.234.53.22\n" +
                "  - timeout: 50\n" +
                "  + timeout: 20\n" +
                "  + verbose: true\n" +
                "}";

        String actual = Differ.generate(PATH + "file1.json", PATH + "file2.json");
        assertEquals(expected, actual);

    }
    @Test
    void generateTest2() throws Exception {
        String expected = "{\n" +
                "    common: 1\n" +
                "    debug: true\n" +
                "  + extra: 0\n" +
                "  - mode: prod\n" +
                "  - port: 8080\n" +
                "  + port: 8081\n" +
                "  - setting: null\n" +
                "  + setting: enabled\n" +
                "}";
        String actual = Differ.generate(PATH + "file3.json", PATH + "file4.json");
        assertEquals(expected, actual);
    }

    @Test
    void generate_throwsWhenFileNotFound() {
        assertThrows(NoSuchFileException.class,
                () -> Differ.generate("no-such-file.json", PATH + "file2.json"));
    }


}

