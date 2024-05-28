package StringTemplates;

public class URLBuilder {
    public static void main(String[] args) {
        String analyticsId = "NNNNNNN"; // DUMMY ANALYTICS ID
        String analyticsURL = STR."https://www.googletagmanager.com/gtag/js?id=\{analyticsId}";
        System.out.println("prepared analytics url: " + analyticsURL);
    }
}
