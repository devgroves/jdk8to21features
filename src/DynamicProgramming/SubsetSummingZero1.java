package DynamicProgramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubsetSummingZero1 {
    public static void main(String[] args) {
        System.out.println(findSumZeroSubsets(new int[] { 1, 2, 0, -3, -2 }));
    }
    public static List<List<Integer>> findSumZeroSubsets(int[] arr) {
        List<List<Integer>> subsetList = new ArrayList<>();
        Map<List<Integer>, Integer> subsetSumCache = new HashMap<>();
        for (int i=0; i<arr.length;i++) {
            for (int j=i+1; j<arr.length; j++ ) {
                int sum = -1;
                if (j-i ==1) {
                    sum = arr[i] + arr[j];
                } else {
                    sum = subsetSumCache.get(List.of(i,j-1)) + arr[j];
                }
                subsetSumCache.put(List.of(i,j), sum);
                if (sum==0) {
                    System.out.println(List.of(i, j));
                    subsetList.add(List.of(i, j));
                }
            }
        }
        return subsetList;
    }
}
