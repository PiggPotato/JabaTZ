import javax.script.ScriptException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {


    private static String numberToLatter (int numArabian) {
        String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };
        final String s = roman[numArabian];
        return s;
    }

    private static int letterToNumber(char letter) {
        return switch (letter) {
            case 'I' -> 1;
            case 'V' -> 5;
            case 'X' -> 10;
            default -> throw new NumberFormatException("Invalid format");
        };
    }

    public static int roman2Decimal(String roman) {
        if (roman.length() == 0)
            return 0;
        int integerValue = 0;
        int prevNumber = letterToNumber(roman.charAt(0));
        for (int i = 1; i < roman.length(); i++) {
            char ch = roman.charAt(i);
            int number = letterToNumber(ch);
            if (number <= prevNumber)
                integerValue += prevNumber;
            else
                integerValue -= prevNumber;
            prevNumber = number;
        }
        integerValue += prevNumber;
        return integerValue;
    }

    public static int returnValue(String str, char num) {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        if (str.trim().equals("10")) { // если число равно 10, оно получает 10
            return 10;
        } else if (str.contains("I") || str.contains("V") || str.contains("X")) { //преобразование в арабские числа
            if (roman2Decimal(str) > 10 || roman2Decimal(str) < 1) {
                return 0;
            } else {
                return roman2Decimal(str);
            }
        } else {
            for (int i = 0; i < numbers.length - 1; i++) { // любое число до 9 включительно
                if (str.trim().length() < 2) {
                    if (Integer.parseInt(String.valueOf(str.trim().charAt(0))) == numbers[i]) {
                        return numbers[i];
                    }
                } else {
                    System.out.println("Введено неверное число " + num);
                    return 0;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) throws ScriptException {
        Scanner in = new Scanner(System.in);
        int a, b;
        System.out.println("Введите математическое выражение:");
        String[] input = in.nextLine().trim().split(" "); // Ввод строки пользователем, удаление лишних пробелов по бокам строки
        if (input.length > 3){
            System.out.println("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            return;
        }

        a = returnValue(input[0], 'a');
        b = returnValue(input[2], 'b');
        if (a == 0 || b == 0) {
            return;
        }

        Pattern patternArab = Pattern.compile("\\d", Pattern.CASE_INSENSITIVE);
        Pattern patternRim = Pattern.compile("\\D", Pattern.CASE_INSENSITIVE);

        if (patternArab.matcher(input[0]).find() && patternArab.matcher(input[2]).find()) {
            switch (input[1].trim()) {
                case "+" -> System.out.println(a + b);
                case "-" -> System.out.println(a - b);
                case "*" -> System.out.println(a * b);
                case "/" -> System.out.println(a / b);
                default -> System.out.println("Введён неверный оператор");
            }
        } else if (patternRim.matcher(input[0]).find() && patternRim.matcher(input[2]).find()) {
            int result;
            switch (input[1].trim()) {
                case "+":
                    result = a + b;
                    System.out.println(numberToLatter(result));
                    break;
                case "-":
                    result = a - b;
                    System.out.println(numberToLatter(result));
                    break;
                case "*":
                    result = a * b;
                    System.out.println(numberToLatter(result));
                    break;
                case "/":
                    result = a / b;
                    System.out.println(numberToLatter(result));
                    break;
                default:
                    System.out.println("Введён неверный оператор");
                    break;
            }
        } else {
            System.out.println("используются одновременно разные системы счисления");
        }


    }
}