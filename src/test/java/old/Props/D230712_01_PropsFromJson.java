package old.Props;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Map;

@Slf4j
public class D230712_01_PropsFromJson {

    @Test
    public static Map<String, Object> loadJsonFile(String filePath) {
        Map<String, Object> map = null;

        try (FileReader reader = new FileReader(filePath)) {
            Gson gson = new Gson();
            Type type = new TypeToken<Map<String, Object>>() {}.getType();
            map = gson.fromJson(reader, type);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

}
