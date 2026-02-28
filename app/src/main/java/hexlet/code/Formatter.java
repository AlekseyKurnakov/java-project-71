package hexlet.code;

import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;
import hexlet.code.formatters.JsonFormatter;

import java.util.List;

public class Formatter {

    public static String render(List<DiffEntry> diff, String format) throws Exception {

        String value = (format == null) ? "stylish" : format;

        return switch (value) {
            case "stylish" -> StylishFormatter.format(diff);
            case "plain" -> PlainFormatter.format(diff);
            case "json" -> JsonFormatter.format(diff);
            default -> throw new IllegalArgumentException("Unsupported format: " + format);
        };

    }
}
