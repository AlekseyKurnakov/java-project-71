package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.NoSuchFileException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
public class DifferTest {
    private static final String FORMAT = null;
    private static final String PATH = "src/test/resources/fixtures/";
    private static final String EXPECTED = "{\n"
            + "    chars1: [a, b, c]\n"
            + "  - chars2: [d, e, f]\n"
            + "  + chars2: false\n"
            + "  - checked: false\n"
            + "  + checked: true\n"
            + "  - default: null\n"
            + "  + default: [value1, value2]\n"
            + "  - id: 45\n"
            + "  + id: null\n"
            + "  - key1: value1\n"
            + "  + key2: value2\n"
            + "    numbers1: [1, 2, 3, 4]\n"
            + "  - numbers2: [2, 3, 4, 5]\n"
            + "  + numbers2: [22, 33, 44, 55]\n"
            + "  - numbers3: [3, 4, 5]\n"
            + "  + numbers4: [4, 5, 6]\n"
            + "  + obj1: {nestedKey=value, isNested=true}\n"
            + "  - setting1: Some value\n"
            + "  + setting1: Another value\n"
            + "  - setting2: 200\n"
            + "  + setting2: 300\n"
            + "  - setting3: true\n"
            + "  + setting3: none\n"
            + "}";
    @Test
    void generateJsonTest() throws Exception {

        String actual = Differ.generate(PATH + "file1.json", PATH + "file2.json", FORMAT);
        assertEquals(EXPECTED, actual);

    }
    @Test
    void generateYmlTest() throws Exception {

        String actual = Differ.generate(PATH + "file1.yml", PATH + "file2.yml", FORMAT);
        assertEquals(EXPECTED, actual);
    }

    @Test
    void generateThrowsWhenFileNotFound() {
        assertThrows(NoSuchFileException.class,
                () -> Differ.generate("no-such-file.json", PATH + "file2.json", FORMAT));
    }


}
