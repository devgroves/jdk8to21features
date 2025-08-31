package DynamicProgramming;

import java.util.*;

public class CoinChangeProblem1 {
    public static int coinChange(int[] coins, int T){
        // your code here
        LinkedHashMap<Integer, Integer> memTables = new LinkedHashMap<>();
        int currentSum = 0;
        int prevSum = 0;
        memTables.put(coins[coins.length-1], 1);
        for (int i = coins.length-1; i >= 0; i--) {
            List<Integer> keysList = new ArrayList<>(memTables.keySet());
            ListIterator<Integer> backtrackSums = keysList.listIterator(keysList.size());
            while (backtrackSums.hasPrevious()) {
                currentSum = backtrackSums.previous();
                while (currentSum < T) {
                    prevSum = currentSum;
                    currentSum += coins[i];
                    if (currentSum <= T) {
                        memTables.put(currentSum, memTables.getOrDefault(prevSum, 0)+1);
                    }
                }
                if (T == currentSum) {
                    // matched and found the solution..
//                    System.out.println("matched T " + memTables);
                    return memTables.getOrDefault(T, -1);
                }
            }
        }
        return memTables.getOrDefault(T, -1);
    }



    public static void main(String[] args) {
        int[] arr1 = new int[]{ 2, 3,  5 };
        System.out.println("number of coins:" + coinChange(arr1,11));
        int[] arr2 = new int[]{ 2, 3,  5, 7 };
        System.out.println("number of coins:" + coinChange(arr2,17));
        int[] arr3 = new int[]{ 2, 3,  7 };
        System.out.println("number of coins:" + coinChange(arr3,15));
    }
}
