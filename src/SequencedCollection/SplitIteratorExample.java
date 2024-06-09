package SequencedCollection;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Stream;

public class SplitIteratorExample {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Spliterator<String> spliterator = list.spliterator();
        int expected = Spliterator.ORDERED | Spliterator.SIZED | Spliterator.SUBSIZED;
        System.out.println("expected -- " + expected);
        System.out.println(spliterator.characteristics() == expected);  //true
        if (spliterator.hasCharacteristics(Spliterator.ORDERED)) {
            System.out.println("ORDERED");
        }
        if (spliterator.hasCharacteristics(Spliterator.DISTINCT)) {
            System.out.println("DISTINCT");
        }
        if (spliterator.hasCharacteristics(Spliterator.SORTED)) {
            System.out.println("SORTED");
        }
        if (spliterator.hasCharacteristics(Spliterator.SIZED)) {
            System.out.println("SIZED");
        }
        if (spliterator.hasCharacteristics(Spliterator.CONCURRENT)) {
            System.out.println("CONCURRENT");
        }
        if (spliterator.hasCharacteristics(Spliterator.IMMUTABLE)) {
            System.out.println("IMMUTABLE");
        }
        if (spliterator.hasCharacteristics(Spliterator.NONNULL)) {
            System.out.println("NONNULL");
        }
        if (spliterator.hasCharacteristics(Spliterator.SUBSIZED)) {
            System.out.println("SUBSIZED");
        }
        List<String> bigList = Stream.generate(() -> "Hello").limit(30000).toList();
        Spliterator<String> split = bigList.spliterator();
        System.out.println(split.estimateSize()); // 30000
        Spliterator<String> split1 = split.trySplit();
        System.out.println(split.estimateSize()); // 15000
        System.out.println(split1.estimateSize()); // 15000
        new Thread(() -> split.forEachRemaining(elem -> System.out.print("TH1 " + elem))).start();
        new Thread(() -> split1.forEachRemaining(elem -> System.out.print("TH2 " + elem))).start();
    }
}
