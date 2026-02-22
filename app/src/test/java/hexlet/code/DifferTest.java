package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.NoSuchFileException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
public class DifferTest {

    private static final String PATH = "src/test/resources/fixtures/";
    private static final String EXPECTED = "{\n"
            + "  - follow: false\n"
            + "    host: hexlet.io\n"
            + "  - proxy: 123.234.53.22\n"
            + "  - timeout: 50\n"
            + "  + timeout: 20\n"
            + "  + verbose: true\n"
            + "}";

    @Test
    void generateTestJson() throws Exception {

        String actual = Differ.generate(PATH + "file1.json", PATH + "file2.json");
        assertEquals(EXPECTED, actual);

    }
    @Test
    void generateTestYml() throws Exception {

        String actual = Differ.generate(PATH + "file1.yml", PATH + "file2.yml");
        assertEquals(EXPECTED, actual);
    }

    @Test
    void generateThrowsWhenFileNotFound() {
        assertThrows(NoSuchFileException.class,
                () -> Differ.generate("no-such-file.json", PATH + "file2.json"));
    }


}

