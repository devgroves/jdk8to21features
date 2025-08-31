package StringTemplates;
import org.json.JSONObject;
import org.json.*;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomTemplateProcessor {
    public static void main(String[] args) {
        StringTemplate.Processor<JSONObject, JSONException> JSON_VALIDATE =
                (StringTemplate stJVAL) -> {
                    String[] invalidStrings = new String[] { "\"", "'" };
                    List<Object> filtered = new ArrayList<>();
                    for (Object value : stJVAL.values()) {
                        if (value instanceof String str) {
                            if (Arrays.stream(invalidStrings).anyMatch(str::contains)) {
                                throw new JSONException("Injection vulnerability");
                            }
                            filtered.add(str);
                        } else if (value instanceof Number ||
                                value instanceof Boolean) {
                            filtered.add(value);
                        } else {
                            throw new JSONException("Invalid value type");
                        }
                    }

                    String jsonSource =
                            StringTemplate.interpolate(stJVAL.fragments(), filtered);

                    return new JSONObject(jsonSource);
//                    try (JsonReader jsonReader = Json.createReader(new StringReader(
//                            jsonSource))) {
//                        return jsonReader.readObject();
//                    }
                };

        String accountType = "user";
        String userName = "Duke";
        String pw = "my_password";

        try {
            JSONObject newAccount = JSON_VALIDATE."""
    {
        "account":    "\{accountType}",
        "user":       "\{userName}",
        "password":   "\{pw}"
    }
    """;

            System.out.println(newAccount);

            userName = "Duke\", \"account\": \"administrator";

            newAccount = JSON_VALIDATE."""
    {
        "account":    "\{accountType}",
        "user":       "\{userName}",
        "password":   "\{pw}"
    }
    """;

            System.out.println(newAccount);

        } catch (JSONException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
