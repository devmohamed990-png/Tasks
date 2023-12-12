package problem;

import java.util.Scanner;
import java.util.regex.Pattern;

public class ProblemSolving {

    public static void main(String[] args) {
        String reversed = reverseStrBetweenParentheses(new Scanner(System.in).next());
        System.out.println(reversed);
    }

    private static String reverseStrBetweenParentheses(String input) {
        int length = input.length();
        if(length <= 0 || length > 2000) {
            return "input must be between: 1 <= s.length <= 2000";
        }
        Pattern pattern = Pattern.compile("[a-z()]+");
        if(!pattern.matcher(input).matches()) {
            return "input must be contain only on lower case English characters and parentheses";
        }
        long startParenthesesCount = input.chars().filter(ch -> ch == '(').count();
        long endParenthesesCount = input.chars().filter(ch -> ch == ')').count();
        if (startParenthesesCount != endParenthesesCount) {
            return "All parentheses must be balanced.";
        }
        StringBuilder result = new StringBuilder();
        StringBuilder subStr = new StringBuilder();
        boolean checker = false;
        for(int i = 0; i <= (length - 1); i++) {
            char character = input.charAt(i);
            if(character == '(') {
                result.append(character);
                checker = true;
            } else if(character == ')') {
                result.append(reverse(subStr.toString())).append(character);
                checker = false;
                subStr.setLength(0);
            } else if(checker) {
                subStr.append(character);
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }

    private static String reverse(String str) {
        int length = str.length();
        StringBuilder reversed = new StringBuilder();
        for(int i = (length - 1); i >= 0; i--) {
            reversed.append(str.charAt(i));
        }
        return reversed.toString();
    }
}