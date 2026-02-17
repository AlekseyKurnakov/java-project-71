package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static String readFile(String path) throws Exception {
        Path fullPath = Paths.get(path).toAbsolutePath().normalize();
        return Files.readString(fullPath).trim();
    }

    public static Map<String, Object> getData(String path) throws Exception {
        String json = readFile(path);
        return MAPPER.readValue(json, new TypeReference<Map<String, Object>>() { });
    }
}