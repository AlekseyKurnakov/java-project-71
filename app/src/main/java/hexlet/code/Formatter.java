package hexlet.code;

import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.util.List;

public class Formatter {

    public static String render(List<DiffEntry> diff, String format) {

        String value = (format == null) ? "stylish" : format;

        return switch (value) {
            case "stylish" -> StylishFormatter.format(diff);
            case "plain" -> PlainFormatter.format(diff);
            default -> throw new IllegalArgumentException("Unsupported format: " + format);
        };

    }
}
