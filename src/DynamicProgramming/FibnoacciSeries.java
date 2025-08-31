package DynamicProgramming;

public class FibnoacciSeries {
    public static void main(String[] args) {
        int i = 10;
        System.out.println(calculateFibnoacci(i));
    }

    private static int calculateFibnoacci(int i) {
        int mem[] = new int[i];
        return calculateSubFibnoacci(i, mem);
    }

    private static int calculateSubFibnoacci(int i, int[] mem) {

        if (i==1) {
            mem[i-1] = 0;
            return mem[i-1];
        }
        if (i==2) {
            mem[i-1] = 1;
            return mem[i-1];
        }
        if (i==3) {
            mem[i-1] = 1;
            return mem[i-1];
        }
        if (mem[i-1] != 0) return mem[i-1];
        mem[i-1] = calculateSubFibnoacci(i-1, mem) + calculateSubFibnoacci(i-2, mem);
        return mem[i-1];
    }
}
