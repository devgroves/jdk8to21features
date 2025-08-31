package Miscellaneous;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JollyJumper {
    public static void main(String[] args) {
        System.out.println("Start giving the numbers");
        Scanner inputScanner = new Scanner(System.in);
        List<Integer> inputNumbers = new ArrayList<>();
        try {
            while(true) {
                inputNumbers.add(inputScanner.nextInt());
            }
        } catch (Exception e) {
            System.out.println("going to check for jolly jumper sequence");
        }

        boolean isJollyJumperSequence = true;
        for (int index=0; index<(inputNumbers.size()-1); index++) {
            int diff = Math.abs(inputNumbers.get(index) - inputNumbers.get(index+1));
            if (diff > inputNumbers.size()){
                System.out.println("It is not jolly jumper");
                isJollyJumperSequence = false;
                break;
            }
        }
        if (isJollyJumperSequence) {
            System.out.println("It is a jolly jumper sequence");
        }

    }
}
