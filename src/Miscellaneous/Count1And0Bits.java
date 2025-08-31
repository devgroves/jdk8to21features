package Miscellaneous;

public class Count1And0Bits {
    public static void main(String[] args) {
        int inp = 16;
        int count1s =0, count0s =0;
        while (inp>0) {
            if ((inp & 1) ==1) {
                count1s += 1;
            } else {
                count0s += 1;
            }
            inp = inp >> 1;
        }
        System.out.println("count1s:" + count1s );
        System.out.println("count0s:" + count0s );
        System.out.println("find parity for each number(number of 1s odd means 1, even means 0y)");
        for (long i=0; i<15; i++) {
            System.out.println(i + " --> " + parity(i));
        }
    }

    public static short parity(long x) {
        short result = 0;
        while (x != 0) {
            result ^= (x & 1);
            x >>>= 1;
        }
        return result;
    }
}
