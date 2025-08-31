package StringTemplates;

import org.json.JSONObject;

public class DynamicJsonBuilder {
    static StringTemplate.Processor<JSONObject, RuntimeException> JSON = StringTemplate.Processor.of(
            (StringTemplate st) -> new JSONObject(st.interpolate())
    );

    record CustomerInfo(String fName, String phone, String address) {};

    public static void main(String[] args) {
        String name    = "Joan Smith";

        String phone   = "555-123-4567";

        String address = "1 Maple Drive, Anytown";


        CustomerInfo c1 = new CustomerInfo(name, phone, address);

        System.out.println("json object"+ generateJsonObject(name, phone, address));
        System.out.println("json object"+ generateJsonObject(c1));
    }

    public static JSONObject generateJsonObject(CustomerInfo c1) {
        return JSON."""
    {

        "name":    "\{c1.fName()}",

        "phone":   "\{c1.phone()}",

        "address": "\{c1.address()}"

    };

    """;
    }

    public static JSONObject generateJsonObject(String name, String phone, String address) {
        return JSON."""
    {

        "name":    "\{name}",

        "phone":   "\{phone}",

        "address": "\{address}"

    };

    """;
    }

    public static class Customer {

    }

}
