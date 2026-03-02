package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Objects;

public class Differ {
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    private static final ObjectMapper YAML_MAPPER = new ObjectMapper(new YAMLFactory());

    public static String readFile(String path) throws Exception {
        Path fullPath = Paths.get(path).toAbsolutePath().normalize();
        return Files.readString(fullPath).trim();
    }

    private static ObjectMapper getMapperByExtension(String path) throws Exception{
        String ext = getExtension(path);
        return switch (ext) {
            case "json" -> JSON_MAPPER;
            case "yml", "yaml" -> YAML_MAPPER;
            default -> throw new IllegalArgumentException("Unsupported extension: " + ext);
        };
    }

    private static String getExtension(String path) {
        int dot = path.lastIndexOf('.');
        return dot == -1 ? "" : path.substring(dot + 1).toLowerCase();
    }

    public static Set<String> getAllKeys(Map<String, Object> file1, Map<String, Object> file2) {
        Set<String> allKey = new TreeSet<>(file1.keySet());
        allKey.addAll(file2.keySet());
        return allKey;
    }
    public static String generate(String path1, String path2) throws Exception {
        return generate( path1, path2, null);
    }

    public static String generate(String path1, String path2, String format) throws Exception {
        ObjectMapper mapper1 = getMapperByExtension(path1);
        ObjectMapper mapper2 = getMapperByExtension(path2);
        Map<String, Object> data1 = Parser.parse(readFile(path1), mapper1);
        Map<String, Object> data2 = Parser.parse(readFile(path2), mapper2);

        Set<String> allKeys = getAllKeys(data1, data2);

        List<DiffEntry> diff = new ArrayList<>();

        for (String key : allKeys) {
            boolean in1 = data1.containsKey(key);
            boolean in2 = data2.containsKey(key);

            Object v1 = data1.get(key);
            Object v2 = data2.get(key);

            if (in1 && in2) {
                if (Objects.equals(v1, v2)) {
                    DiffEntry unchanged = new DiffEntry(key, "unchanged", v1, v2);
                    diff.addLast(unchanged);
                } else {
                    DiffEntry changed = new DiffEntry(key, "changed", v1, v2);
                    diff.addLast(changed);
                }
            } else if (in1) {
                DiffEntry deleted = new DiffEntry(key, "removed", v1, v2);
                diff.addLast(deleted);
            } else {
                DiffEntry added = new DiffEntry(key, "added", v1, v2);
                diff.addLast(added);
            }
        }
        return Formatter.render(diff, format);
    }
}
