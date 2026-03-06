package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.util.Map;

public class Parser {
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    private static final ObjectMapper YAML_MAPPER = new ObjectMapper(new YAMLFactory());

    public static Map<String, Object> parse(String content, ObjectMapper mapper) throws Exception {
        return mapper.readValue(content, new TypeReference<Map<String, Object>>() { });
    }

    public static ObjectMapper getMapperByExtension(String path) {
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

}
