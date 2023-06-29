package Libraries;

import com.google.gson.GsonBuilder;

public class D230629_01_Utils {

    public static String getPrettyJson(Object o) {
        return new GsonBuilder().setPrettyPrinting().create().toJson(o);
    }

}
