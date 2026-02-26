package hexlet.code.formatters;

import hexlet.code.DiffEntry;

import java.util.List;
import java.util.Map;

public class PlainFormatter {

    public static String formatValue(Object value) {
        StringBuilder sb = new StringBuilder();
        if (value instanceof Map || value instanceof List) {
            sb.append("[complex value]");
        } else if (value instanceof String) {
            sb.append("'").append(value).append("'");
        } else {
            sb.append(value);
        }
        return sb.toString();
    }

    public static String format(List<DiffEntry> diff) {

        StringBuilder sb = new StringBuilder();

        for (DiffEntry diffEntry : diff) {
            String status = diffEntry.getStatus();
            if ("unchanged".equals(status)) {
                continue;
            }
            String key = diffEntry.getKey();
            sb.append("Property '").append(key);
            String v1 = formatValue(diffEntry.getOldValue());
            String v2 = formatValue(diffEntry.getNewValue());

            if ("changed".equals(status)) {
                sb.append("' was updated. From ").append(v1).append(" to ").append(v2);
            } else if ("removed".equals(status)) {
                sb.append("' was removed");
            } else if ("added".equals(status)) {
                sb.append("' was added with value: ").append(v2);
            }
            sb.append("\n");
        }
        return sb.toString().stripTrailing();
    }
}
