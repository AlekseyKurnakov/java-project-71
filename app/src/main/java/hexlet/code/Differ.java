package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Objects;

public class Differ {

    public static Set<String> getAllKeys(Map<String, Object> file1, Map<String, Object> file2) {
        Set<String> allKey = new TreeSet<>(file1.keySet());
        allKey.addAll(file2.keySet());
        return allKey;
    }

    public static String generate(String path1, String path2, String format) throws Exception {
        Map<String, Object> data1 = Parser.parse(path1);
        Map<String, Object> data2 = Parser.parse(path2);

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
