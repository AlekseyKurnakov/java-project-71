package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Differ {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static String readFile(String path) throws Exception {
        Path fullPath = Paths.get(path).toAbsolutePath().normalize();
        return Files.readString(fullPath).trim();
    }

    public static Map<String, Object> getData(String path) throws Exception {
        String file = readFile(path);
        return MAPPER.readValue(file, new TypeReference<Map<String, Object>>() { });
    }

    public static Set<String> getAllKey(Map<String, Object> file1, Map<String, Object> file2) {
        Set<String> allKey = new TreeSet<>(file1.keySet());
        allKey.addAll(file2.keySet());
        return allKey;
    }

    public static String generate(String path1, String path2) throws Exception {
        Map<String, Object> data1 = getData(path1);
        Map<String, Object> data2 = getData(path2);

        Set<String> allKeys = getAllKey(data1, data2);

        StringBuilder sb = new StringBuilder();
        sb.append("{\n");

        for (String key : allKeys) {
            boolean in1 = data1.containsKey(key);
            boolean in2 = data2.containsKey(key);

            Object v1 = data1.get(key);
            Object v2 = data2.get(key);

            if (in1 && in2) {
                if (Objects.equals(v1, v2)) {
                    sb.append("    ").append(key).append(": ").append(v1).append("\n");
                } else {
                    sb.append("  - ").append(key).append(": ").append(v1).append("\n");
                    sb.append("  + ").append(key).append(": ").append(v2).append("\n");
                }
            } else if (in1) {
                sb.append("  - ").append(key).append(": ").append(v1).append("\n");
            } else { // in2
                sb.append("  + ").append(key).append(": ").append(v2).append("\n");
            }
        }

        sb.append("}");
        return sb.toString();
    }
}