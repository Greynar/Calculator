package main.java;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Введите выражение для вычисления в формате: A <действие: + - / *> B ");
        Scanner scanner = new Scanner(System.in);
        String expression = scanner.nextLine();

        System.out.println(calc(expression));
    }

    public static String calc(String input) throws Exception {
        try {
            String [] parameters = input.split(" ");
            if (parameters.length != 3) throw new Exception("Формат ввода должен иметь вид: два операнда и один оператор!");

            Numbers firstNumber, secondNumber;
            String action = parameters[1];
            firstNumber = NumberService.parseNumber(parameters[0]);
            secondNumber = NumberService.parseNumber(parameters[2]);
            NumberService.checkForConditions(firstNumber, secondNumber);

            int result = doCalculation(firstNumber, secondNumber, action);
            if (firstNumber.getType() == NumberType.ROMAN)
                return NumberService.arabicToRoman(result);
            return Integer.toString(result);
        } catch (Exception e) {
            throw e;
        }
    }

    private static int doCalculation(Numbers firstNumber, Numbers secondNumber, String action) {
        int result;
        switch (action) {
            case ("+"):
                result = firstNumber.getValue() + secondNumber.getValue();
                return result;
            case ("-"):
                result = firstNumber.getValue() - secondNumber.getValue();
                checkForPositiveResult(firstNumber, secondNumber, result);
                return result;
            case ("*"):
                result = firstNumber.getValue() * secondNumber.getValue();
                return result;
            case ("/"):
                result = firstNumber.getValue() / secondNumber.getValue();
                checkForPositiveResult(firstNumber, secondNumber, result);
                return result;
            default:
                throw new IllegalArgumentException("Данное действие не поддерживается!");
        }
    }

    private static void checkForPositiveResult(Numbers firstNumber, Numbers secondNumber, int result) {
        if (firstNumber.getType() == NumberType.ROMAN && secondNumber.getType() == NumberType.ROMAN && result < 1)
            throw new ArithmeticException("Результатом работы калькулятора с римскими числами могут быть только положительные числа!");
    }
}
