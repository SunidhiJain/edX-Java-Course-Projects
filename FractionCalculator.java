/**
 *  Microsoft: DEV277x - Object Oriented Programming in Java
 *  Module 2 Project - Fraction Calculator
 *
 *  Created by Megha Verma
 *  (I took some help from the fellow students doing the same course)
**/

import java.util.Arrays;
import java.util.Scanner;

import static javafx.application.Platform.exit;

public class FractionCalculator {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args){

        // Welcoming the user to the program
        System.out.println("THIS PROGRAM IS A FRACTION CALCULATOR.");
        System.out.println("It will add, subtract, multiply and divide fractions until you type Q to quit.");
        System.out.println("Please enter your fractions in the form a/b, where a and b are integers.");
        System.out.println("----------------------------------------------------------------------------");

        String operator;    // Variable to store the return value of getOperation() method
        // STEP 1: Asking the user to enter the desired operator to perform an operation
        operator = getOperation();

        // do-while loop to run the program until the user intends to quit the program
        while(!operator.equals("q") && !operator.equals("Q")){

            // STEP 2: Asking the user to enter fractions one and two
            Fraction fraction1 = getFraction();
            Fraction fraction2 = getFraction();

            // STEP 3: Performing the said operation on the two fractions
            switch (operator){
                case "Q":
                case "q": return;


                case "+":   // to perform Addition
                    System.out.println(fraction1.toString() + " + " + fraction2.toString() +
                            " = " + fraction1.add(fraction2).toString());
                    break;

                case "-":   // to perform Subtraction
                    System.out.println(fraction1.toString() + " - " + fraction2.toString() +
                            " = " + fraction1.subtract(fraction2).toString());
                    break;

                case "*":   // to perform Multiplication
                    System.out.println(fraction1.toString() + " * " + fraction2.toString() +
                            " = " + fraction1.multiply(fraction2).toString());
                    break;

                case "/":   // to perform Division
                    if(fraction1.getDenominator()*fraction2.getNumerator()==0){
                        System.out.println(fraction1.toString() + " / " + fraction2.toString() +
                                " = Undefined");
                    } else
                        System.out.println(fraction1.toString() + " / " + fraction2.toString() +
                                " = " + fraction1.divide(fraction2).toString());
                    break;

                case "=":   // to determine whether two fractions are equal
                    System.out.println(fraction1.toString() + " = " + fraction2.toString() +
                            " is " + fraction1.equals(fraction2));
                    break;

                default:    // default case
                    System.out.println("INVALID OPERATION!");
                    getOperation();
            }
            System.out.println("----------------------------------------------------------------------------\n");
            operator = getOperation();
        }
    }


    // Method to prompt the user to enter a valid operator
    public static String getOperation() {
        // Array of valid operators
        String[] operatorsArray = {"+", "-", "*", "/", "=", "q", "Q"};

        System.out.print("Please enter an operation (+, -, /, *, = or Q to quit) : ");
        String operator = sc.nextLine();

        if(operator.length() > 1){
            System.out.print("Invalid input! Enter again (+, -, *, /, = or Q to quit) : ");
            operator = sc.nextLine();

        }
        // Checking if the user has entered a valid operator.
        // If not, prompting the user to enter a valid operation
        while (!Arrays.asList(operatorsArray).contains(operator)) {
            System.out.print("Invalid input! Enter again (+, -, *, /, = or Q to quit) : ");
            operator = sc.nextLine();
        }
        return operator;
    }


    // Method to prompt the user to enter valid fractions
    public static Fraction getFraction() {
        Fraction fraction = new Fraction();

        System.out.print("Enter a fraction (a/b) or integer (a): ");
        String frac = sc.nextLine();

        if (validFraction(frac)) {
            if (!frac.contains("/")) {
                fraction = new Fraction(Integer.parseInt(frac));
            } else if (frac.substring(0, 1).equals(frac.substring(frac.indexOf("/") + 1))) {
                fraction = new Fraction(1);
            } else
                fraction = new Fraction(Integer.parseInt(frac.substring(0, frac.indexOf("/"))),
                        Integer.parseInt(frac.substring(frac.indexOf("/") + 1)));
            //System.out.println(fraction);
        } else {
            System.out.println("Fraction Invalid! Please enter a valid fraction, where a and b are valid integers and b is not equal to zero.");
            getFraction();
        }
        return fraction;
    }


    // Method to determine if the fraction entered by user is valid
    // If not, prompts the user to enter the fraction again
    public static boolean validFraction(String frac) {
        boolean flag = false;

        if (!frac.contains("/")){
            if (isNumber(frac))
                flag = true;
            else
                flag = false;
        }
        else if (frac.contains("/")){
            String[] new_temp = frac.split("/", 2);

            if (new_temp[1].equals("0"))
                flag = false;
            else if(isNumber(new_temp[0]) && isNumber(new_temp[1]))
                flag = true;
        }
//        if(flag==true){
//            System.out.println("Valid fraction");
//        } else
//            System.out.println("Invalid fraction");
        return flag;
    }


    // Method to check if the fraction contains only numbers
    public static boolean isNumber(String num){
        try {
            int intVal = Integer.parseInt(num);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


}
