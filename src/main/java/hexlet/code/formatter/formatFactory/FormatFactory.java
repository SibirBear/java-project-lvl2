package hexlet.code.formatter.formatFactory;

import hexlet.code.formatter.formatFactory.imp.Stylish;

import static hexlet.code.formatter.formatFactory.FormatConstant.STYLISH;

public class FormatFactory {

    /**
     * Returns the Factory for format type. If specified format is not found - returns null.
     * @param formatType format for data processing,
     *                   @see FormatConstant
     * @return Returns the factory according to the specified format
     */

    public Format setFormat(final String formatType) {
        Format format = null;

        if (STYLISH.equals(formatType)) {
            format = new Stylish();
        }

        return format;

    }

}
