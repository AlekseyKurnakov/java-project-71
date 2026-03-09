package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Differ {

    public static String readContent(String path) throws Exception {
        Path fullPath = Paths.get(path).toAbsolutePath().normalize();
        return Files.readString(fullPath).trim();
    }

    private static String getFormat(String path) {
        int dot = path.lastIndexOf('.');
        return dot == -1 ? "" : path.substring(dot + 1).toLowerCase();
    }

    public static String generate(String path1, String path2) throws Exception {
        return generate(path1, path2, "stylish");
    }

    public static String generate(String path1, String path2, String formatName) throws Exception {
        String content1 = readContent(path1);
        String content2 = readContent(path2);

        String dataFormat1 = getFormat(path1);
        String dataFormat2 = getFormat(path2);

        Map<String, Object> data1 = Parser.parse(content1, dataFormat1);
        Map<String, Object> data2 = Parser.parse(content2, dataFormat2);

        List<DiffEntry> diff = DiffBuilder.build(data1, data2);

        return Formatter.render(diff, formatName);
    }
}
