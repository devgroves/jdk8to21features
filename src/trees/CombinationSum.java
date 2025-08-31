package trees;

import java.util.ArrayList;

public class CombinationSum {
    public static void main(String args[]) {
        ArrayList<ArrayList<Integer>> result = findCombinationSum(new int[]{2,3,5}, 9);
        System.out.println("final output " + result);
    }

    private static ArrayList<ArrayList<Integer>> findCombinationSum(int[] elements, int target) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<ArrayList<Integer>> interResult = new ArrayList<>();
        for (int i=0; i<elements.length; i++) {
            ArrayList<Integer> combElement = new ArrayList<>();
            combElement.add(elements[i]);
            interResult.add(combElement);
        }
        System.out.println("result of first traversal:" + interResult);
        while (true) {
            interResult = traverseCombination(elements, target, interResult);
            if (interResult.isEmpty()) break;
            result.addAll(addSuccessCombos(interResult, target));
        }

        return result;
    }

    private static ArrayList<ArrayList<Integer>> addSuccessCombos(ArrayList<ArrayList<Integer>> input,  int target) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for (ArrayList<Integer> combination: input) {
            if (findSum(combination, 0) == target) {
                result.add(combination);
            }
        }
        return result;
    }

    private static  ArrayList<ArrayList<Integer>>  traverseCombination(int[] elements, int target, ArrayList<ArrayList<Integer>> input) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for (ArrayList<Integer> inputComb: input) {
            for (int j = 0; j < elements.length; j++) {
                ArrayList<Integer> combOption = new ArrayList<>(inputComb);
                System.out.println("comb option" + combOption);
                if (findSum(combOption, elements[j]) <= target) {
                    combOption.add(elements[j]);
                    System.out.println("result " + combOption);
                    result.add(combOption);
                }
            }
        }
        System.out.println("result of second traversal:" + result);
        return result;
    }

    private static int findSum(ArrayList<Integer> arrayList, int currElem) {
        int result = 0;
        for (Integer elem: arrayList) {
            result += elem;
        }
        return result + currElem;
    }
}
