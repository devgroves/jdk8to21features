package DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetSummingZero {
    public static void main(String[] args) {
        List<Integer> inputIntegers = List.of(10, 20, -20, 40, 50);
        List<Integer[]> results = findSubsetsSummingZero(inputIntegers);
        for (Integer[] subarr: results) {
            System.out.println(Arrays.asList(subarr));
        }
    }

    private static List<Integer[]> findSubsetsSummingZero(List<Integer> inputIntegers) {
        List<Integer[]> output = new ArrayList<>();
        int size = inputIntegers.size();
        List<Integer> sumList = new ArrayList<>();
        int sum = inputIntegers.get(0);
        for (int i=1; i<size; i++) {
            sum += inputIntegers.get(i);
            if (sum ==0) {
                Integer[] res = new Integer[]{0, i};
                output.add(res);
            }
            sumList.add(sum);
        }

        for (int i=0; i<inputIntegers.size()-2; i++) {
            for (int j=0; j<sumList.size(); j++) {
                if ((sumList.get(j) - inputIntegers.get(i)) ==0) {
                    Integer[] res = new Integer[]{i+1, j+i+1};
                    output.add(res);
                }
            }
        }
        return output;
    }
}
