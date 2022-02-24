package hexlet.code.formatter.formatFactory;

import java.util.List;
import java.util.Map;

public abstract class Format {

    public abstract String format(List<Map<String, Object>> mapToFormat);

}
