package hexlet.code.formatters;

import hexlet.code.DiffEntry;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class JsonFormatter {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static String format(List<DiffEntry> diff) throws Exception {
        return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(diff);
    }

}
