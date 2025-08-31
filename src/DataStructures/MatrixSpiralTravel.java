package DataStructures;

import java.util.ArrayList;

public class MatrixSpiralTravel {
    public static void main(String[] args) {
        int[][] arr = { { 1, 2, 3, 4, 5, 6 }, { 7, 8, 9, 10, 11, 12 } };
        ArrayList<Integer> results = new ArrayList<>();
        int rowMaxIndex = arr.length-1;
        int colMaxIndex = arr[0].length-1;
        int initialRowLength = 0;
        int initialColLength = 0;
        int totalElements = (rowMaxIndex+1) * (colMaxIndex+1);
        System.out.println("total elements in the matrix " + totalElements);
        int count = 1;
        int curr_xpos = 0;
        int curr_ypos = 0;
        results.add(arr[curr_xpos][curr_ypos]);
        System.out.println("x : " + curr_xpos + " y : " + curr_ypos);
        while (count<totalElements) {
            while (curr_ypos < colMaxIndex ) {
                curr_ypos++; // first col left to right
                System.out.println("x : " + curr_xpos + " y : " + curr_ypos);
                results.add(arr[curr_xpos][curr_ypos]);
                count++;
            }
            initialRowLength++;
            while (curr_xpos < rowMaxIndex ) {
                curr_xpos++; // next row top to bottom
                System.out.println("x : " + curr_xpos + " y : " + curr_ypos);
                results.add(arr[curr_xpos][curr_ypos]);
                count++;
            }
            colMaxIndex--;
            while (curr_ypos > initialColLength ) {
//                System.out.println("col right to left" + initialColLength);
                curr_ypos--; // col right to left
                System.out.println("x : " + curr_xpos + " y : " + curr_ypos);
                results.add(arr[curr_xpos][curr_ypos]);
                count++;
            }
            rowMaxIndex--;
            while (curr_xpos > initialRowLength ) {
                curr_xpos--; // row bottom to top
                System.out.println("x : " + curr_xpos + " y : " + curr_ypos);
                results.add(arr[curr_xpos][curr_ypos]);
                count++;
            }
            initialColLength++;


        }
        System.out.println("elements " + results);

    }
}
