package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.NoSuchFileException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DifferTest {
    private static final String STYLISH_FORMAT = "stylish";
    private static final String PLAIN_FORMAT = "plain";
    private static final String PATH = "src/test/resources/fixtures/";
    private static final String EXPECTED_STYLISH =
                      "{\n"
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
    private static final String EXPECTED_PLAIN =
                      "Property 'chars2' was updated. From [complex value] to false\n"
                    + "Property 'checked' was updated. From false to true\n"
                    + "Property 'default' was updated. From null to [complex value]\n"
                    + "Property 'id' was updated. From 45 to null\n"
                    + "Property 'key1' was removed\n"
                    + "Property 'key2' was added with value: 'value2'\n"
                    + "Property 'numbers2' was updated. From [complex value] to [complex value]\n"
                    + "Property 'numbers3' was removed\n"
                    + "Property 'numbers4' was added with value: [complex value]\n"
                    + "Property 'obj1' was added with value: [complex value]\n"
                    + "Property 'setting1' was updated. From 'Some value' to 'Another value'\n"
                    + "Property 'setting2' was updated. From 200 to 300\n"
                    + "Property 'setting3' was updated. From true to 'none'";

    @Test
    void generateJsonStylishTest() throws Exception {
        String actual = Differ.generate(PATH + "file1.json", PATH + "file2.json", STYLISH_FORMAT);
        assertEquals(EXPECTED_STYLISH, actual);
    }
    @Test
    void generateYmlStylishTest() throws Exception {
        String actual = Differ.generate(PATH + "file1.yml", PATH + "file2.yml", STYLISH_FORMAT);
        assertEquals(EXPECTED_STYLISH, actual);
    }
    @Test
    void generateJsonPlainTest() throws Exception {

        String actual = Differ.generate(PATH + "file1.json", PATH + "file2.json", PLAIN_FORMAT);
        assertEquals(EXPECTED_PLAIN, actual);
    }
    @Test
    void generateYmlPlainTest() throws Exception {
        String actual = Differ.generate(PATH + "file1.yml", PATH + "file2.yml", PLAIN_FORMAT);
        assertEquals(EXPECTED_PLAIN, actual);
    }
    @Test
    void generateThrowsWhenFileNotFound() {
        assertThrows(NoSuchFileException.class,
                () -> Differ.generate("no-such-file.json", PATH + "file2.json", STYLISH_FORMAT));
    }
}
