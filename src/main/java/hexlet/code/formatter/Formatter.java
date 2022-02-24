package hexlet.code.formatter;

import hexlet.code.formatter.formatFactory.Format;
import hexlet.code.formatter.formatFactory.FormatFactory;

import java.util.List;
import java.util.Map;

public class Formatter {

    private static final String MSG_ERROR_FORMAT = "This format of files is not supported";

    public static String useFormat(List<Map<String, Object>> mapToFormat, String format) {

        FormatFactory factory = new FormatFactory();
        Format formatType = factory.setFormat(format);

        if (formatType == null) {
            return MSG_ERROR_FORMAT;
        }

        return formatType.format(mapToFormat);

    }

}
