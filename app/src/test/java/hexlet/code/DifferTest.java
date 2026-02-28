package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.NoSuchFileException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DifferTest {
    private static final String STYLISH_FORMAT = "stylish";
    private static final String PLAIN_FORMAT = "plain";
    private static final String JSON_FORMAT = "json";
    private static final String PATH = "src/test/resources/fixtures/";
    private static final String JSON_FILE1 = PATH + "file1.json";
    private static final String JSON_FILE2 = PATH + "file2.json";
    private static final String YML_FILE1 = PATH + "file1.yml";
    private static final String YML_FILE2 = PATH + "file2.yml";
    private static String expectedStylish;
    private static String expectedPlain;
    private static String expectedJson;

    @BeforeAll
    static void loadExpectedResults() throws Exception {
        expectedStylish = Parser.readFile(PATH + "expected_stylish.txt");
        expectedPlain = Parser.readFile(PATH + "expected_plain.txt");
        expectedJson = Parser.readFile(PATH + "expected_json.txt");
    }

    @Test
    void shouldGenerateStylishFromJson() throws Exception {
        String actual = Differ.generate(JSON_FILE1, JSON_FILE2, STYLISH_FORMAT);
        assertEquals(expectedStylish, actual);
    }

    @Test
    void shouldGenerateStylishFromYml() throws Exception {
        String actual = Differ.generate(YML_FILE1, YML_FILE2, STYLISH_FORMAT);
        assertEquals(expectedStylish, actual);
    }

    @Test
    void shouldGeneratePlainFromJson() throws Exception {

        String actual = Differ.generate(JSON_FILE1, JSON_FILE2, PLAIN_FORMAT);
        assertEquals(expectedPlain, actual);
    }

    @Test
    void shouldGeneratePlainFromYml() throws Exception {
        String actual = Differ.generate(YML_FILE1, YML_FILE2, PLAIN_FORMAT);
        assertEquals(expectedPlain, actual);
    }

    @Test
    void shouldGenerateJsonFromJson() throws Exception {
        String actual = Differ.generate(JSON_FILE1, JSON_FILE2, JSON_FORMAT);
        assertEquals(expectedJson, actual);
    }

    @Test
    void shouldGenerateJsonFromYml() throws Exception {
        String actual = Differ.generate(YML_FILE1, YML_FILE2, JSON_FORMAT);
        assertEquals(expectedJson, actual);
    }

    @Test
    void shouldThrowWhenFileNotFound() {
        assertThrows(NoSuchFileException.class,
                () -> Differ.generate("no-such-file.json", JSON_FILE2, STYLISH_FORMAT));
    }
}
