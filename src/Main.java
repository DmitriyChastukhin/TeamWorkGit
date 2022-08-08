import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //TODO ТОВАРЫ БЕЗ АКЦИИ:
        String[] shop = {"Булка", "Сыр", "Молоко"};
        int[] prices = {50, 100, 70}; //цены
        int[] purchases = new int[shop.length]; //массив для выбранных товаров
        int sumProducts = 0; //стоимость покупки без акции

        //TODO ТОВАРЫ ПО АКЦИИ:
        String[] sale = {"Кофе", "Мидии", "Шоколад"};
        int[] pricesSale = {1000, 500, 300}; //цены
        int[] purchasesSale = new int[sale.length]; //массив для выбранных товаров по акции
        int sumSale; // стоимость товара по скидке
        int sumPrSale = 0; //стоимость покупки по акции

        //TODO ВЫВОД ПРЕДЛОЖЕНИЯ НА ЭКРАН:
        System.out.println("список товаров, доступных к покупке:");
        for (int i = 0; i < shop.length; i++) {
            System.out.println((i + 1) + "\t" + shop[i] + "\t" + prices[i] + " руб.");
        }
        System.out.println("товары по акции (три по цене двух): ");
        for (int j = 0; j < sale.length; j++) {
            System.out.println((j + 4) + "\t" + sale[j] + "\t" + pricesSale[j] + " руб.");
        }
        int cellNum; // введённый номер продукта -1
        int cellNumSale; //введённый номер продукта по скидке
        int productCount; //введённое кол-во выбранных продуктов
        //TODO ВВОД С КОНСОЛИ + ПРОВЕРКА ДАННЫХ:
        while (true) {
            System.out.println("Введи номер товара и его количество (через пробел); " + "для подсчета результатов и выхода набери end.");
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
            try {
                cellNum = Integer.parseInt(choice[0]) - 1;
                productCount = Integer.parseInt(choice[1]);
            } catch (NumberFormatException e) {
                System.out.println("вводить надо цифры, вы ввели: " + input);
                continue;
            }
            //TODO если введённые числа не подходят по условиям:
            if (cellNum < 0 || cellNum >= (shop.length + sale.length)) {
                System.out.println("нет товара под таким номером");
                continue;
            }
            if (cellNum < shop.length) {
                if (productCount == 0) {
                    System.out.println("Следующий товар был удален из корзины: " + shop[cellNum]);
                    purchases[cellNum] = 0;
                }

                //TODO если кол-во продукта < 0:
                if (productCount < 0) {
                    System.out.println("Товар " + shop[cellNum] + " в корзине будет уменьшен на " + -productCount + " шт.");
                }
                if ((purchases[cellNum] + productCount) < 0) {
                    purchases[cellNum] = 0;
                } else {
                    purchases[cellNum] += productCount;
                }
            } else {
                cellNumSale = cellNum - shop.length;
                if (productCount == 0) {
                    System.out.println("Следующий товар был удален из корзины: " + sale[cellNumSale]);
                    purchasesSale[cellNumSale] = 0;
                }
                if (productCount < 0) {
                    System.out.println("Товар " + sale[cellNumSale] + " в корзине будет уменьшен на " + -productCount + " шт.");
                }
                if ((purchasesSale[cellNumSale] + productCount) < 0) {
                    purchasesSale[cellNumSale] = 0;
                } else {
                    purchasesSale[cellNumSale] += productCount;
                }
            }
//            //TODO увеличение массивов с количеством продуктов:
//            if (cellNum < shop.length) {
//                purchases[cellNum] += productCount;
//            } else {
//                cellNumSale = cellNum - shop.length;
//                purchasesSale[cellNumSale] += productCount;
//            }
        }

        //TODO ПОДСЧЕТ КОЛИЧЕСТВА И СУММЫ ПОКУПКИ БЕЗ АКЦИИ:
        System.out.println("в вашей корзине: ");
        for (int i = 0; i < purchases.length; i++) {
            if (purchases[i] > 0) {
                System.out.println(shop[i] + " по " + prices[i] + " руб. - "
                        + purchases[i] + " шт. (на " + (purchases[i] * prices[i]) + " руб.)");
                sumProducts += (purchases[i] * prices[i]);
            }
        }
        System.out.println("всего товаров без акции на сумму: " + sumProducts + " руб.");
        System.out.println();

        //TODO ПОДСЧЕТ КОЛИЧЕСТВА И СУММЫ ПОКУПКИ ПО АКЦИИ:
        System.out.println("товары по акции: ");
        for (int i = 0; i < purchasesSale.length; i++) {
            if (purchasesSale[i] > 0) {
                sumSale = ((3 * (purchasesSale[i] / 3) * pricesSale[i]) / 3 * 2 + (purchasesSale[i] - 3 * (purchasesSale[i] / 3)) * pricesSale[i]);
                System.out.println(sale[i] + " по " + pricesSale[i] + " руб. - " + purchasesSale[i] + " шт. (на " + sumSale + " руб.)");
                sumPrSale += sumSale;
            }
        }
        System.out.println("всего товаров по акции на сумму: " + sumPrSale + " руб.");
        System.out.println();
        System.out.println("общая стоимость покупки: " + (sumProducts + sumPrSale) + " руб.");
    }
}