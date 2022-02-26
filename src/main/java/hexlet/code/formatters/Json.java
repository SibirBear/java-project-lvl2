package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class Json extends Format {

    /**
     * Returns string after analyse data and output in Json format.
     * @param mapToFormat data from two files which need to analyse and formatting.
     * @return Returns string after analyse data and output in Json format.
     */
    @Override
    public String format(List<Map<String, Object>> mapToFormat) {
        ObjectMapper objectMapper = new ObjectMapper();
        String result = "";

        try {
            result = objectMapper.writeValueAsString(mapToFormat);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }

        return result;

    }
}
