package DataStructures;

import java.util.Arrays;
/*
 * matrix rotation both rowwise and columnwise supported. It can be rotated clockwise or anticlockwise
 */
public class MatrixRotation {
    // false - ANTICLOCKWISE , true - CLOCKWISE
    private static final boolean ROTATION_DIRECTION = false;
    private static final boolean ROW_ROTATION_AXIS = false;
    private static final boolean COLUMN_ROTATION_AXIS = true;
    public static void main(String[] args) {
    //        int matrix[][] = {
    //                {1, 2, 3},
    //                {4, 5, 6},
    //                {7, 8, 9}
    //        };
        int matrix[][] = {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20},
        };
        char transform[] = {'l', 'u', 'l', 'u', 'u'};
        // excepted output..
        //        [9, 10, 6, 7, 8]
        //        [14, 15, 11, 12, 13]
        //        [19, 20, 16, 17, 18]
        //        [4, 5, 1, 2, 3]
        printMatrix(matrix);
        /*
         * colAxis - true pos - 0 ---> left circular shift happens on first row and it comes like 2 3 1
         * colAxis - true pos - 1 ---> left circular shift happens on second row and it comes like 4 5 6
         * colAxis - false pos - 0 ---> down circular shift happens on first column and it comes like 4 7 1
         */
        for (char t: transform) {
            switch (t) {
                case 'l':
                    for (int i=0; i<matrix.length; i++) {
                        circularShift(matrix, COLUMN_ROTATION_AXIS, i, ROTATION_DIRECTION);
                    }
                    break;
                // for doing the top cirular shift, loop all the columns with down circular shift on column wise..
                case 'u':
                    for (int j = 0; j < matrix[0].length; j++) {
                        circularShift(matrix, ROW_ROTATION_AXIS, j, ROTATION_DIRECTION);
                    }
                    break;
            }
        }

    }

    private static void circularShift(int[][] matrix, boolean colAxis, int pos, boolean isClockwise) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        // column shift if colAxis is True
        int len = colAxis ? cols: rows;
        int i = colAxis? pos : 0; // row is fixed
        int j = colAxis? 0: pos; // col is fixed
//        System.out.println("decided i " + i + " j " + j);
        int firstBk = isClockwise ? matrix[i][j] : (colAxis ? matrix[i][cols-1] : matrix[rows-1][j] );
        while (checkEnd(i, j, colAxis, len)) {
            int nextItem = findNextItemIndex(i, j, colAxis, len);
//            System.out.println("next col" + nextItem + "decided i " + i + " j " + j);
            if (isClockwise) {
                swapNumbersClockwise(matrix, firstBk, colAxis, nextItem, i, j);
            } else {
                firstBk = swapNumbersAntiClockwise(matrix, firstBk, colAxis, i, j);
            }
            if (colAxis) j++;
            else i++;
        }
        System.out.println("after doing rotation: ");
        printMatrix(matrix);
    }

    private static void swapNumbersClockwise(int matrix[][], int firstBk, boolean colAxis, int nextItem, int i, int j) {
        if (nextItem == 0) {
            matrix[i][j] = firstBk;
        } else {
            matrix[i][j] = colAxis ? matrix[i][nextItem] : matrix[nextItem][j];
        }
    }

    private static int swapNumbersAntiClockwise(int matrix[][], int firstBk, boolean colAxis, int i, int j) {
        int tmp = 0;
        if (colAxis) {
            tmp = matrix[i][j];
            matrix[i][j] = firstBk ;
        } else {
            tmp = matrix[i][j];
            matrix[i][j] = firstBk ;
        }
        return tmp;
    }

    private static int findNextItemIndex(int i, int j, boolean colAxis, int len) {
        int nextItem;
        if (colAxis) {
            nextItem = j+1;
            if (nextItem==len) {
                return 0;
            } else {
                return nextItem;
            }
        } else {
            nextItem = i+1;
            if (nextItem==len) {
                return 0;
            } else {
                return nextItem;
            }
        }
    }

    private static int findPrevItemIndex(int i, int j, boolean colAxis, int len) {
        int prevItem;
        if (colAxis) {
            prevItem = j-1;
            if (prevItem==len) {
                return 0;
            } else {
                return prevItem;
            }
        } else {
            prevItem = i-1;
            if (prevItem==len) {
                return 0;
            } else {
                return prevItem;
            }
        }
    }

    private static boolean checkEnd(int i, int j, boolean colAxis, int len) {
        boolean result;
        if (colAxis) {
            result = j < len;
        } else {
            result = i < len;
        }
        return result;
    }

    private static void printMatrix(int[][] matrix) {
        for (int i=0; i<matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }


}
