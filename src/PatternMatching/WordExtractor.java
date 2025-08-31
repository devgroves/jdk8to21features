package PatternMatching;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordExtractor {
    public static void main(String[] args) {
        String line = "Happy thanksgiving, I am so full";
        String regex = "[\\s,()!.?]+";
        System.out.println(Arrays.toString(line.split(regex)));
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        System.out.println(" match " + matcher.hasMatch());
        while(matcher.hasMatch()) {
            System.out.println("mfind " + matcher.find());
            System.out.println("mfind " + matcher.group());
        }
    }
}
