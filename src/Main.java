import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] shop = {"Булка", "Сыр", "Молоко"};
        int[] prices = {50, 100, 70};
        int[] purchases = new int[3];
        int sumProduct = 0;

        System.out.println("Список товаров, доступных к покупке:");
        for (int i = 0; i < shop.length; i++) {
            System.out.println((i + 1) + "\t" + shop[i] + "\t" + prices[i] + " руб.");
        }

        while (true) {
            System.out.println("Введи номер товара и его количество (через пробел); " +
                    "для подсчета результатов и выхода набери end.");
            String input = scanner.nextLine();
            if ("end".equals(input)) {
                break;
            }
            String[] choice = input.split(" ");

            //TODO если массив не из двух чисел:
            if (choice.length != 2) {
                System.out.println("надо вводить два числа, вы ввели: " + input);
                continue;
            }
            //TODO если введены не цифры а буквы:
            int cellNum;
            int productCount;
            try {
                cellNum = Integer.parseInt(choice[0]) - 1;
                productCount = Integer.parseInt(choice[1]);
            } catch (NumberFormatException e) {
                System.out.println("вводить надо цифры, вы ввели: " + input);
                continue;
            }
            //TODO если введённые числа не подходят по условиям:
            if (cellNum < 0 || cellNum >= shop.length) {
                System.out.println("нет товара под таким номером");

            } else if (productCount < 0) {
                System.out.println("Товар " + shop[cellNum] + " в корзине будет уменьшен на " + -productCount + " шт.");
                if (purchases[cellNum] + productCount <= 0) {
                    purchases[cellNum] = 0;
                } else {
                    purchases[cellNum] = purchases[cellNum] + productCount;
                }
            } else if (productCount == 0) {
                System.out.println("Следующий товар был удален из корзины: " + shop[cellNum]);
                purchases[cellNum] = 0;
            } else {
                purchases[cellNum] += productCount;

                System.out.println("В вашей корзине: " + shop[cellNum] + ", " + productCount + " шт");
            }
        }

        for (int i = 0; i < shop.length; i++) {
            if (purchases[i] != 0) {
                System.out.println(shop[i] + " по " + prices[i] + " руб. - "
                        + purchases[i] + " шт. (на " + (purchases[i] * prices[i]) + " руб.)");
                sumProduct += (purchases[i] * prices[i]);
            }
        }
        System.out.println("Общая стоимость: " + sumProduct + " руб.");
    }
}