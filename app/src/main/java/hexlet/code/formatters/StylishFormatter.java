package hexlet.code.formatters;

import hexlet.code.DiffEntry;

import java.util.List;

public class StylishFormatter {

    public static String format(List<DiffEntry> diff) {

        StringBuilder sb = new StringBuilder();
        sb.append("{\n");

        for (DiffEntry diffEntry : diff) {

            String key = diffEntry.getKey();
            Object v1 = diffEntry.getOldValue();
            Object v2 = diffEntry.getNewValue();
            String status = diffEntry.getStatus();

            if ("unchanged".equals(status)) {
                sb.append("    ").append(key).append(": ").append(v1);
            } else if ("changed".equals(status)) {
                sb.append("  - ").append(key).append(": ").append(v1);
                sb.append("\n");
                sb.append("  + ").append(key).append(": ").append(v2);
            } else if ("removed".equals(status)) {
                sb.append("  - ").append(key).append(": ").append(v1);
            } else if ("added".equals(status)) {
                sb.append("  + ").append(key).append(": ").append(v2);
            }
            sb.append("\n");
        }
        sb.append("}");
        return sb.toString();
    }
}
