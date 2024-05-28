package Miscellaneous;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundlesFormatter {
    record LocalizationProcessor(Locale locale)
            implements StringTemplate.Processor<String, RuntimeException> {

        public String process(StringTemplate st) {
            ResourceBundle resource = ResourceBundle.getBundle("resources", locale);
            String stencil = String.join("_", st.fragments());
            System.out.println("stencil" + stencil);
            String msgFormat = resource.getString(stencil.replace(' ', '.'));
            System.out.println(" msgFormat " + msgFormat);
            return MessageFormat.format(msgFormat, st.values().toArray());
        }
    }

    public static void main(String[] args) {
        var userLocale = new Locale("en", "US");
        var LOCALIZE = new LocalizationProcessor(userLocale);

        String user = "Duke", option = "b";
        System.out.println(LOCALIZE."\{user} chose option \{option}");

//        userLocale = new Locale("fr", "CA");
//        LOCALIZE = new LocalizationProcessor(userLocale);
//        System.out.println(LOCALIZE."\{user} chose option \{option}");
    }
}
