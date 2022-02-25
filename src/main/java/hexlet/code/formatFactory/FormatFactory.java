package hexlet.code.formatFactory;

import hexlet.code.formatters.Format;
import hexlet.code.formatters.Stylish;

public class FormatFactory {

    /**
     * Returns the Factory for format type. If specified format is not found - returns null.
     * @param formatType format for data processing,
     *                   @see FormatConstant
     * @return Returns the factory according to the specified format
     */

    public Format setFormat(final String formatType) {
        Format format = null;

        if (FormatConstant.STYLISH.equals(formatType)) {
            format = new Stylish();
        }

        return format;

    }

}
