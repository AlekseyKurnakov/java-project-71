package hexlet.code;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    private static Map<String, Object> expectedFile1;

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

        String path = "src/test/resources/fixtures/file1.json";
        Map<String, Object> actual = Differ.getData(path);

        assertEquals(expectedFile1, actual);
    }

    @Test
    void getListKeyTest() {

    }


}
