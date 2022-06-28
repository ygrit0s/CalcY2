import java.util.Scanner;
public class Main {
//* Exiting program in case of wrong input
    public static void wrongExit() {
        System.out.println("Wrong input!");
        System.exit(0);
    }
//* Converting digital part of input to number type
    public static double stringToNumber(String num_) {
        var number = Integer.parseInt(num_);
        if (number > 10)
            wrongExit();
        return number;
    }
    public static void main(String[] args) {
//  App description
        System.out.println("\nCalculator 2.0 - can perform 4 basic arithmetic operations");
        System.out.println("(addition, subtraction, multiplication and division)");
        System.out.println("between 2 or more integer numbers from 1 to 10 inclusive");
//  Explanation for user
        System.out.println("\nEnter math expression in format 'a X b X c ...' ");
        System.out.println("where a, b, c ... are integers in range from 1 to 10 inclusive");
        System.out.println("and X is any of 4 arithmetic operations: +, -, * or /");
        System.out.println("\nOr type \"q\" for exit");
//  Problem Solving
        while (true) {
//  -   Processing user's input
            var input = new Scanner(System.in);
            var mathExpr = input.nextLine();
            mathExpr = mathExpr.replace(" ", "");
            if (mathExpr.equals("q"))
                break;
            if (mathExpr.startsWith("*") || mathExpr.startsWith("/"))
                wrongExit();
            if (!(mathExpr.startsWith("-") || mathExpr.startsWith("+")))
                mathExpr = "+" + mathExpr;
//  -   Solving algorithm
            String[] polynomial = mathExpr.split("(?=[+-])");
            double result = 0;
            for (String pol_ : polynomial) {
                String[] monomial = pol_.split("(?=[*/])");
                double product = 1;
                for (String mon_ : monomial) {
                    byte power = 1;
                    if (mon_.startsWith("/"))
                        power = -1;
                    if (!mon_.startsWith("-"))
                        mon_ = mon_.substring(1);
                    product *= Math.pow(stringToNumber(mon_), power);
                }
                result += product;
            }
            System.out.println(result);
        }
    }
}