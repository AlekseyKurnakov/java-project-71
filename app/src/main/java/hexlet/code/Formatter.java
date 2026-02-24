package hexlet.code;

import java.util.List;

public class Formatter {

    public static String render(List<DiffEntry> diff, String format) {

        StringBuilder sb = new StringBuilder();

        if (format.equals("stylish")) {
            sb.append("{\n");
            for (DiffEntry diffEntry : diff) {

                String key = diffEntry.getKey();
                Object v1 = diffEntry.getOldValue();
                Object v2 = diffEntry.getNewValue();
                String status = diffEntry.getStatus();

                if (status.equals("unchanged")) {
                    sb.append("    ").append(key).append(": ").append(v1).append("\n");
                }
                else if (status.equals("deleted")) {
                    sb.append("  - ").append(key).append(": ").append(v1).append("\n");
                }
                else if (status.equals("added")) {
                    sb.append("  + ").append(key).append(": ").append(v2).append("\n");
                }
            }
            sb.append("}");
        }
        return sb.toString();

    }
}
