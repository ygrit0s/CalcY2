import java.text.ParseException;
import java.util.Scanner;
import java.text.DecimalFormat;

public class Main {
    public static void wrongExit() {
        System.out.println("Wrong input!");
        System.exit(0);
    }
    public static void main(String[] args) {

        //  App description
        System.out.println("Calculator 2.0 - can perform 4 basic arithmetic operations");
        System.out.println("(addition, subtraction, multiplication and division)");
        System.out.println("between 3 (or 2) integer numbers from 1 to 10 inclusive");
        System.out.println();

        //  Explanation for user
        System.out.println("Enter math expression in format 'a X b X c' or 'a X b'");
        System.out.println("where a, b, c are integers in range from 1 to 10 inclusive");
        System.out.println("and X is any of 4 arithmetic operations: +, -, * or /");

        //  Processing user's input
        Scanner scanner = new Scanner(System.in);
        String mathExpr = scanner.nextLine();
        mathExpr = mathExpr.replace(" ", "");
        if (!mathExpr.startsWith("-"))
            mathExpr = "+" + mathExpr;

        //  Declaring necessary variables
        DecimalFormat decimalFormat = new DecimalFormat("#");
        String[] monomial = mathExpr.split("(?=[+-])");
        String[] factor;
        int power;
        double result = 0;

        //  Solving problem
        for (String mon_ : monomial) {
            if (mon_.contains("*") || mon_.contains("/")) {
                factor = mon_.split("(?=[*/])");
                result = 1;
                for (String fac_ : factor) {
                    power = 1;
                    if (fac_.startsWith("/"))
                        power = -1;
                    else if (!(fac_.startsWith("*") || fac_.startsWith("+") || fac_.startsWith("-")))
                        wrongExit();
                    fac_ = fac_.substring(1);
                    try {
                        decimalFormat.parse(fac_.substring(1)).intValue();
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    if (Integer.parseInt(fac_) > 10)
                        wrongExit();
                    result *= Math.pow(Double.parseDouble(fac_), power);
                }
            } else {
                try {
                    decimalFormat.parse(mon_.substring(1)).intValue();
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                if (Integer.parseInt(mon_) > 10)
                    wrongExit();
                else result += Double.parseDouble(mon_);
            }
        }
        System.out.println(result);
    }
}