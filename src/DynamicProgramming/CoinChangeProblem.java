package DynamicProgramming;

import java.util.*;

public class CoinChangeProblem {
    public static int coinChange(Integer[] coins, int T){
        // your code here
        Arrays.sort(coins, Collections.reverseOrder());
        LinkedHashMap<Integer, Integer> memTables = new LinkedHashMap<>();
        memTables.put(T, null);
//        int remaining = T;
//        int prevRemaining = remaining;
        for (int i=0; i<coins.length; i++) {
            List<Integer> backTrackArr = new ArrayList<>(memTables.sequencedKeySet().reversed());
            for (Integer remaining: backTrackArr) {
                if (remaining < coins[i]) continue;
                Integer result = digestValue(remaining, coins[i], memTables);
                if (result == 0)
                    return memTables.get(0);
            }
        }
        System.out.println(memTables);
        return memTables.get(0);
    }

    public static Integer digestValue(Integer remaining, Integer coin, LinkedHashMap<Integer, Integer> memTables) {
        while (remaining > 0) {
            if (remaining - coin <0) {
                break;
            }
            int count = memTables.get(remaining) == null? 0 : memTables.get(remaining);
            remaining = remaining - coin;
            memTables.put(remaining, count+1);
        }
        return remaining;
    }

    public static void main(String[] args) {
        Integer[] arr1 = new Integer[]{ 2, 3,  5 };
        System.out.println("number of coins:" + coinChange(arr1,11));
        Integer[] arr2 = new Integer[]{ 2, 3,  5, 7 };
        System.out.println("number of coins:" + coinChange(arr2,17));
        Integer[] arr3 = new Integer[]{ 2, 3,  7 };
        System.out.println("number of coins:" + coinChange(arr3,15));
    }
}
