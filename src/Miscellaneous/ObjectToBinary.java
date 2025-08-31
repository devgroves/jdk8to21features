package Miscellaneous;

public class ObjectToBinary {
    public static void main(String[] args) {
        Object obj = 'c';
        switch (obj) {
            case Character c -> {
                String bitStr = "";
//                while (c == '0') {
//                    bitStr += String.valueOf(c & 1);
//                    c = c.hashCode() >> 1;
//                }
                System.out.println("bit str "+ bitStr);
            }
            default -> {
                System.out.println("Not acceptable data type");
                break;
            }
        }
    }
}
