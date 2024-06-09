package PatternMatching;

import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SwitchPatternCheck {
    public static void main(String[] args)  throws IOException {
        File file = new File("input.json");
        FileReader reader = new FileReader(file);
        char[] cbuf = new char[100];
        int out = reader.read(cbuf);
        System.out.println(" out " + out);
        String content = new String(cbuf);
        System.out.println("content " + content);
        JSONObject jsonObject = new JSONObject(content);
        Object object = jsonObject.get("employee");
        identifyObjectType(object);
        identifyObjectType(jsonObject.getJSONObject("employee").get("id"));
        identifyObjectType(jsonObject.getJSONObject("employee").get("dept"));
        String result = findAge(jsonObject.getJSONObject("employee").getInt("age"));
        System.out.println("age is classified as " + result);
    }

    public static void identifyObjectType(Object object) {
        switch(object) {
            case JSONObject jsonObject1 when !jsonObject1.isEmpty() ->
                System.out.println("it is json object " + jsonObject1.keys());
            case String s ->
                System.out.println(" it is a string " + s);
            case Integer i ->
                System.out.println(" it is a integer " + i);
            default ->
                System.out.println("unable to recognize the object type");
        }
    }

    public static String findAge(int age) {
        return switch (age / 10) {
            case 0 -> "children";
            case 1 -> "teenage";
            case 2, 3 -> "adult";
            case 4, 5 -> "midage";
            case 6 -> "seniorCitizen";
            default -> "unknownage";
        };
    }
}
