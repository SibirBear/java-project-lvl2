package hexlet.code.formatFactory;

import hexlet.code.formatters.Format;
import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import static hexlet.code.formatFactory.FormatConstant.JSON;
import static hexlet.code.formatFactory.FormatConstant.PLAIN;
import static hexlet.code.formatFactory.FormatConstant.STYLISH;

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

        if (PLAIN.equals(formatType)) {
            format = new Plain();
        }

        if (JSON.equals(formatType)) {
            format = new Json();
        }

        return format;

    }

}
