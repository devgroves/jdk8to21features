package StringTemplates;


import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RawTemplateExample {
    public static void main(String[] args) throws IOException, InterruptedException {
        String ipAddress = findMyIpAddress();
        JSONObject ipGeoDetails = findGeoDetails(ipAddress);
        int v = 10, w = 20;
        StringTemplate rawST = StringTemplate.RAW."My IP Address: \{ipGeoDetails.getString("ip")} geo latitute: \{ipGeoDetails.getString("loc")}";
        java.util.List<String> fragments = rawST.fragments();
        java.util.List<Object> values = rawST.values();

        System.out.println(rawST.toString());

        fragments.stream().forEach(f -> System.out.print("[" + f + "]"));
        System.out.println();

        values.stream().forEach(val -> System.out.print("[" + val + "]"));
        System.out.println();

        // different ways to process raw template to do the string processing..
//        System.out.println(rawST.process(STR));
//        System.out.println(STR.process(rawST));
        System.out.println(rawST.interpolate());
    }

    private static JSONObject findGeoDetails(String ipAddress) throws IOException, InterruptedException {
        String ipinfoUrl = STR."https://ipinfo.io/\{ipAddress}/geo";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(ipinfoUrl))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        JSONObject responseJson = new JSONObject(response.body());
        return responseJson;
    }


    public static String findMyIpAddress() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.ipify.org"))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        return response.body();
    }
}
