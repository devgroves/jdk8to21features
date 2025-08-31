package DataStructures;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
    public static void main(String[] args) {
        int result = 0;
        String expr = "2 + (5 - 3)";
        Stack calStack = new Stack();
        String regex = "(\\d+|[+\\- ]+|[()]+)";
        Pattern exprPattern = Pattern.compile(regex);
        Matcher m = exprPattern.matcher(expr);
        int i = 0;
        System.out.println(m.matches() + " group count " + m.groupCount());
        while (m.find()) {
            System.out.println("find data " + m.group()+":");
            if (m.group().trim().equals(")")) {
                result = extractAndCalc(calStack);
                calStack.push(String.valueOf(result));
                System.out.println("output result " + result);
            } else {
                calStack.push(m.group().trim());
            }
        }
        result = extractAndCalc(calStack);
        System.out.println("final result " + result);
    }

    public static int extractAndCalc(Stack<String> stack) {
        String lastOp = "";
        Integer[] elems = new Integer[2];
        int i = 0;
        while (!stack.peek().equals("(")) {
            String elem = stack.pop();
            System.out.println("elem value" + elem);
            if (elem.equals("+")) {
                lastOp = "add";
            } else if (elem.equals("-")) {
                lastOp = "subtract";
            } else {
                elems[i++] = Integer.parseInt(elem);
            }
            if (stack.isEmpty()) {
                System.out.println("stack empty " + stack.isEmpty());
                break;
            }
        }
        if (!stack.isEmpty() && stack.peek().equals("(")) {
            stack.pop();
        }
        if (lastOp.equals("add")) {
            return elems[1] + elems[0];
        } else {
            return elems[1] - elems[0];
        }
    }
}
