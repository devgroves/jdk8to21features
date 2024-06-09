package SequencedCollection;

import java.util.LinkedHashMap;
public class LinkedHashMapExample {
    public static void main(String[] args) {
        LinkedHashMap<String, String> sample = new LinkedHashMap<>();
        sample.put("one", "one");
        sample.put("two", "two");
        sample.put("three", "three");
        sample.put("four", "four");
        sample.put("five", "five");
        sample.put("six", "six");
        sample.putFirst("seven", "seven");
        sample.putLast("eight", "eight");
        System.out.println(sample.sequencedEntrySet().getFirst());
        System.out.println(sample.sequencedEntrySet().getLast());
    }
}
