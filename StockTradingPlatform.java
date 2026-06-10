package stocktrading;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

class Stock {

    String stockName;
    double stockPrice;
    int quantity;

    Stock(String stockName, double stockPrice) {

        this.stockName = stockName;
        this.stockPrice = stockPrice;
        this.quantity = 0;
    }
}

public class StockTradingPlatform {

    public static void savePortfolio(Stock stocks[], double balance) {

        try {

            FileWriter writer = new FileWriter("portfolio.txt");

            writer.write("===== PORTFOLIO DATA =====\n");

            for(Stock s : stocks) {

                writer.write(s.stockName + " : " + s.quantity + " shares\n");
            }

            writer.write("Available Balance: ₹" + balance);

            writer.close();

            System.out.println("Portfolio saved successfully!");

        }
        catch(IOException e) {

            System.out.println("Error saving portfolio.");
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Stock stocks[] = {
            new Stock("TCS", 3500),
            new Stock("Infosys", 1500),
            new Stock("Wipro", 450),
            new Stock("HCL", 1200)
        };

        double balance = 20000;

        int choice;

        do {

            System.out.println("\n====================================");
            System.out.println("      STOCK TRADING PLATFORM");
            System.out.println("====================================");

            System.out.println("1. View Available Stocks");
            System.out.println("2. Buy Stocks");
            System.out.println("3. Sell Stocks");
            System.out.println("4. View Portfolio");
            System.out.println("5. Save Portfolio");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch(choice) {

                case 1:

                    System.out.println("\n===== AVAILABLE STOCKS =====");

                    for(Stock s : stocks) {

                        System.out.println("Stock Name : " + s.stockName);
                        System.out.println("Stock Price: ₹" + s.stockPrice);
                        System.out.println("----------------------------");
                    }

                    break;

                case 2:

                    System.out.print("Enter stock name to buy: ");
                    String buyStock = sc.next();

                    boolean foundBuy = false;

                    for(Stock s : stocks) {

                        if(s.stockName.equalsIgnoreCase(buyStock)) {

                            foundBuy = true;

                            System.out.print("Enter quantity: ");
                            int qty = sc.nextInt();

                            double total = qty * s.stockPrice;

                            if(balance >= total) {

                                balance -= total;
                                s.quantity += qty;

                                System.out.println("Stock purchased successfully!");
                                System.out.println("Total Cost: ₹" + total);
                                System.out.println("Remaining Balance: ₹" + balance);
                            }
                            else {

                                System.out.println("Insufficient balance!");
                            }
                        }
                    }

                    if(!foundBuy) {

                        System.out.println("Stock not found!");
                    }

                    break;

                case 3:

                    System.out.print("Enter stock name to sell: ");
                    String sellStock = sc.next();

                    boolean foundSell = false;

                    for(Stock s : stocks) {

                        if(s.stockName.equalsIgnoreCase(sellStock)) {

                            foundSell = true;

                            System.out.print("Enter quantity to sell: ");
                            int sellQty = sc.nextInt();

                            if(s.quantity >= sellQty) {

                                double totalSell = sellQty * s.stockPrice;

                                balance += totalSell;
                                s.quantity -= sellQty;

                                System.out.println("Stock sold successfully!");
                                System.out.println("Amount Received: ₹" + totalSell);
                                System.out.println("Updated Balance: ₹" + balance);
                            }
                            else {

                                System.out.println("Not enough shares to sell!");
                            }
                        }
                    }

                    if(!foundSell) {

                        System.out.println("Stock not found!");
                    }

                    break;

                case 4:

                    System.out.println("\n===== YOUR PORTFOLIO =====");

                    for(Stock s : stocks) {

                        System.out.println(s.stockName + " : " + s.quantity + " shares");
                    }

                    System.out.println("Available Balance: ₹" + balance);

                    break;

                case 5:

                    savePortfolio(stocks, balance);

                    break;

                case 6:

                    System.out.println("Thank you for using Stock Trading Platform.");
                    break;

                default:

                    System.out.println("Invalid choice!");
            }

        } while(choice != 6);

        sc.close();
    }
}
