package ru.portfolio;

public class LZ77 {

    // Функция кодирования строки алгоритмом LZ77
    public static void encode(String input) {
        int length = input.length();
        int pos = 0;
        while (pos < length) {
            int maxLength = 0;
            int offset = 0;

            // Ищем вхождение наибольшей подстроки
            for (int i = 1; i <= pos && i <= 255; i++) {
                String curStr = input.substring(pos, pos + i);
                int index = input.lastIndexOf(curStr, pos - 1);
                if (index == -1) break;
                int curLength = i;
                while (curLength < maxLength && input.charAt(index + curLength) == input.charAt(pos + curLength)) {
                    curLength++;
                }
                if (curLength > maxLength) {
                    maxLength = curLength;
                    offset = pos - index;
                }
            }

            // Если мы не нашли вхождения, то кодируем следующий символ
            if (maxLength == 0) {
                System.out.println("(0,0," + input.charAt(pos) + ")");
                pos++;
            } else {
                System.out.println("(" + offset + "," + maxLength + "," + input.charAt(pos + maxLength) + ")");
                pos += maxLength + 1;
            }
        }
    }

    // Тестирование
    public static void main(String[] args) {
        String input = "bobbobbop";
        System.out.println("Сжатая строка:");
        encode(input);
    }
}





