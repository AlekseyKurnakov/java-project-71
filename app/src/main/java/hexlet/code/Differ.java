package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Differ {

    public static String readFile(String path) throws Exception {
        Path fullPath = Paths.get(path).toAbsolutePath().normalize();
        return Files.readString(fullPath).trim();
    }

    public static String generate(String path1, String path2) throws Exception {
        return generate(path1, path2, null);
    }
    public static String generate(String path1, String path2, String format) throws Exception {
        ObjectMapper mapper1 = Parser.getMapperByExtension(path1);
        ObjectMapper mapper2 = Parser.getMapperByExtension(path2);
        Map<String, Object> data1 = Parser.parse(readFile(path1), mapper1);
        Map<String, Object> data2 = Parser.parse(readFile(path2), mapper2);

        List<DiffEntry> diff = DiffBuilder.build(data1, data2);

        return Formatter.render(diff, format);
    }
}
