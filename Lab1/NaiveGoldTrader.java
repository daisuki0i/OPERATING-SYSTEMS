import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Scanner;

public class NaiveGoldTrader {
    static int readGoldPrices(int[] arr, String path) {
        int n = 0;
        try (Scanner f = new Scanner(Paths.get(path))) {
            while (f.hasNextInt()) {
                arr[n] = f.nextInt();
                n++;
            }
        } catch (NoSuchFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return n;
    }

    static int readGoldPrices(int goldPrices[], int n) {
        for (int i = 0; i < n; i++) {
            goldPrices[i] = (int) Math.round(Math.random() * 20000 + 20000);
        }
        return n;
    }

    public static void main(String args[]) {
        int goldPrices[] = new int[1000000];
        // int n = readGoldPrices(goldPrices, args[0]);
        int n = Integer.parseInt(args[0]);
        readGoldPrices(goldPrices, n);
        int bestBuyDate = 0;
        int bestSellDate = 0;
        int maxProfit = Integer.MIN_VALUE;
        long count = 0;
        for (int buyDate = 0; buyDate < n - 1; buyDate++) {
            for (int sellDate = buyDate + 1; sellDate < n; sellDate++) {
                count++;
                int profit = goldPrices[sellDate] - goldPrices[buyDate];
                if (profit > maxProfit) {
                    maxProfit = profit;
                    bestBuyDate = buyDate;
                    bestSellDate = sellDate;
                }
            }
        }
        System.out.printf("Number of days: %,d%n", n);
        System.out.printf("Max profit is: %,d%n", maxProfit);
        System.out.printf("Buy date: %,d%n", bestBuyDate + 1);
        System.out.printf("Sell date: %,d%n", bestSellDate + 1);
        System.out.printf("count: %,d%n", count);
    }
}
