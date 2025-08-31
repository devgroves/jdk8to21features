package DataStructures;
import java.util.*;

public class MatrixEncryption {
    public static void main() {
        System.out.println("hello, world");
        String str = "a quick brown fox jumps over the fence.";
        /*   **RESULT**
         *   aquick
         *   brownf
         *   oxjump
         *   sovert
         *   hefenc
         *   e.
         */
        String modifiedStr = str.replaceAll(" ", "");
        double sqrt = Math.sqrt(modifiedStr.length());
        int rowVal = (int) Math.floor(sqrt);
        int colVal = (int) Math.ceil(sqrt);
        System.out.println("row val" + rowVal + " colVal " + colVal);
        StringBuilder result = new StringBuilder();
        for (int i=0; i<rowVal; i++) {
            for (int j=i; j<modifiedStr.length(); j = j+colVal) {
                result.append(modifiedStr.charAt(j));
            }
        }
        System.out.println("str value : " + result.toString());
    }
}
