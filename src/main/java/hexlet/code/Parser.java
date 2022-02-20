package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.HashMap;
import java.util.Map;

public class Parser {

    private static final String JSON = "json";
    private static final String YAML1 = "yml";
    private static final String YAML2 = "yaml";

    public static Map<String, Object> convertStringToMap(final String content,
                                                         final String file) throws JsonProcessingException {
        String[] fileName = file.split("\\.", 0); //limit - for errorprone
        String extension = fileName[fileName.length - 1];
        
        if (JSON.equals(extension)){
            ObjectMapper jsonObjMapper = new ObjectMapper();
            return jsonObjMapper.readValue(content, new TypeReference<>() { });
        }

        if (YAML1.equals(extension) || YAML2.equals(extension)) {
            ObjectMapper ymlObjMapper = new ObjectMapper(new YAMLFactory());
            return ymlObjMapper.readValue(content, new TypeReference<>() { });
        }

        return new HashMap<>();
    }

}
