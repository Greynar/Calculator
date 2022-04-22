package main.java;

import java.util.TreeMap;

class NumberService {

    public static void checkForConditions(Numbers firstNumber, Numbers secondNumber) {
        if (firstNumber.getType() != secondNumber.getType())
            throw new IllegalArgumentException("Введенные числа разных типов, необходимо использовать один тип вводимых значений!");
        if (firstNumber.getValue() < 1 || firstNumber.getValue() > 10 || secondNumber.getValue() < 1 || secondNumber.getValue() > 10)
            throw new IllegalArgumentException("Вводимые числа должны быть в промежутке от 1 до 10 включительно!");
    }

    public static Numbers parseNumber(String string) {
        Numbers number = new Numbers();
        try {
            number.setValue(Integer.parseInt(string));
            number.setType(NumberType.ARABIC);
        } catch (NumberFormatException e) {
            number.setValue(romanToArabic(string));
            number.setType(NumberType.ROMAN);
        }
        return number;
    }

    private static int romanToArabic(String roman){
        int value = 0;
        try {
            for (int i = 0; i < roman.length(); i++) {
                char ch = roman.charAt(i);
                int number = RomeNumbers.valueOf(String.valueOf(ch)).getValue();
                value += number;
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Неверный формат числа!");
        }
        if (roman.equals("IV") || roman.equals("IX")) value -= 2;
        return value;
    }

    public static String arabicToRoman(int result) {
        RomeNumbers [] romeNumbers = RomeNumbers.values();
        TreeMap<Integer, String> numbers = new TreeMap<>();
        for (RomeNumbers romeNumber : romeNumbers) {
            numbers.put(romeNumber.getValue(), romeNumber.toString());
        }
        String romeAnswer = "";
        while (result > 0) {
            int digital = numbers.floorKey(result);
            romeAnswer += numbers.get(digital);
            result -= digital;
        }
        return romeAnswer;
    }
}
