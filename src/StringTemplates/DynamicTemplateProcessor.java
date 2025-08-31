package StringTemplates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DynamicTemplateProcessor {
    public static void main(String[] args) throws IOException {
        List<String> readLines = Files.readAllLines(Path.of("input.txt"));
        List<String> subsetReadLine = new ArrayList<>();
        List<String> values = new ArrayList<>();
        String endDelimiter = "";
        for (String line: readLines) {
            String parsedLines[] = line.split("\\|.*\\|");
            if (subsetReadLine.size() >= 1) {
                endDelimiter = "\n";
            }
            subsetReadLine.add(endDelimiter + parsedLines[0]);
            Pattern pattern = Pattern.compile("\\|(.*)\\|");
            Matcher str = pattern.matcher(line);

            if (str.find()) {

                System.out.println("parsed" + str.group(0));
                String jsonSyntax = str.group(1);
                //JSONPath.parse(requestPayload).read(jsonSyntax);
                values.add(jsonSyntax);
            } else {
                values.add("");
            }
        }
        subsetReadLine.add("\n");
        System.out.println("subread" + subsetReadLine);
        String result = StringTemplate.interpolate(subsetReadLine, values);
//        String result = StringTemplate.interpolate(readLines, List.of("d1", "d2"));
        System.out.println(result);
    }
}
