package Miscellaneous;

public class PalindromeNumber {
    public static void main(String[] args) {
        System.out.println("ispalindromeNumber: " + isPalindromeNumber(101));
    }

    public static boolean isPalindromeNumber (int x) {
        if (x < 8) {
            return false;
        }
        final int numDigits = (int) (Math.floor(Math.log10 (x)))+1;
        System.out.println("num digits:" + Math.floor(Math.log10 (x)));
        int msdMask = (int) Math.pow(10 , numDigits - 1);
        System.out.println("msd mask: " + msdMask + " num digits : " + numDigits);
        for (int i = 0; i < (numDigits / 2); ++i){
            if (x / msdMask != x % 10) {
                return false;
            }
            x %= msdMask; // Remove the most significant digit of x.
            System.out.println(x);
            x /= 10; // Remove the least significant digit of x.
            System.out.println(x);
            msdMask /= 100;
            System.out.println(msdMask);
        }
        return true;
    }
}
