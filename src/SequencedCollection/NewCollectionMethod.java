package SequencedCollection;

import java.util.*;

public class NewCollectionMethod {
    public static void main(String[] args) {
        Set<String> sets = Set.of("test1", "test2");
        System.out.println("sets" + sets);
        try {
            sets.add("test3");
            System.out.println("sets" + sets);
        } catch(Exception exception) {
            System.out.println("unsupported operation exception happened "+ exception);
        }
        List<Integer> lists = List.of(1, 2, 3, 4, 5, 6, 7);
        ListIterator<Integer> listIterator = lists.listIterator(2);


        while(listIterator.hasNext()) {
            System.out.println("elem : " + listIterator.next() + " index " + listIterator.nextIndex());
            System.out.println("previous index: " + listIterator.previousIndex() + " previous element: " + listIterator.previous());
        }

        Map<String, String> checkSequence = Map.of("one", "one", "two", "two", "three", "three");
        try {
            checkSequence.put("misc", "misc");
        } catch (Exception exception) {
            System.out.println("unsupported operation exception happened " + exception);
        }
        System.out.println("checkSequence "+ checkSequence );

    }
}
