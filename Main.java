import java.util.*;
import java.time.*;

class Customer {

    int id;
    String name;
    double balance;

    String[] type = new String[100];
    double[] amount = new double[100];
    String[] description = new String[100];
    LocalDateTime[] date = new LocalDateTime[100];

    int count = 0;

    Customer(int id, String name, double balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    void purchase(double amt, LocalDateTime d, String desc) {

        balance = balance - amt;

        type[count] = "PURCHASE";
        amount[count] = -amt;
        description[count] = desc;
        date[count] = d;

        count++;

        System.out.println("Purchase added successfully!");
    }

    void refund(double amt, LocalDateTime d, String desc) {

        if (amt <= 0) {
            System.out.println("Invalid amount!");
            return;
        }

        balance = balance + amt;

        type[count] = "REFUND";
        amount[count] = amt;
        description[count] = desc;
        date[count] = d;

        count++;

        System.out.println("Refund processed successfully!");
    }

    void showHistory() {

        System.out.println("\n===== ORDER HISTORY =====");
        System.out.println("Customer ID : " + id);
        System.out.println("Name        : " + name);
        System.out.println("Balance     : " + balance);

        if (count == 0) {
            System.out.println("No orders found!");
            return;
        }

        for (int i = 0; i < count; i++) {

            System.out.println(
                date[i] + " | " +
                type[i] + " | " +
                amount[i] + " | " +
                description[i]
            );
        }
    }
}


public class Main {

    static Scanner sc = new Scanner(System.in);

    static Customer[] customers = new Customer[100];

    static int customerCount = 0;


    // Add customer
    static void addCustomer() {

        System.out.print("Enter Customer ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Customer Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Balance: ");
        double balance = sc.nextDouble();

        customers[customerCount] =
            new Customer(id, name, balance);

        customerCount++;

        System.out.println("Customer added successfully!");
    }


    // Find customer
    static Customer findCustomer(int id) {

        for (int i = 0; i < customerCount; i++) {

            if (customers[i].id == id) {
                return customers[i];
            }
        }

        return null;
    }


    // Purchase
    static void addPurchase() {

        System.out.print("Enter Customer ID: ");
        int id = sc.nextInt();

        Customer c = findCustomer(id);

        if (c == null) {
            System.out.println("Customer not found!");
            return;
        }

        System.out.print("Enter Amount: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        System.out.print("Enter Date-Time: ");
        LocalDateTime date =
            LocalDateTime.parse(sc.nextLine());

        System.out.print("Enter Description: ");
        String desc = sc.nextLine();

        c.purchase(amount, date, desc);
    }


    // Refund
    static void refund() {

        System.out.print("Enter Customer ID: ");
        int id = sc.nextInt();

        Customer c = findCustomer(id);

        if (c == null) {
            System.out.println("Customer not found!");
            return;
        }

        System.out.print("Enter Refund Amount: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        System.out.print("Enter Date-Time: ");
        LocalDateTime date =
            LocalDateTime.parse(sc.nextLine());

        System.out.print("Enter Description: ");
        String desc = sc.nextLine();

        c.refund(amount, date, desc);
    }


    // Main
    public static void main(String[] args) {

        while (true) {

            System.out.println("\n===== SHOPMART =====");
            System.out.println("1. Add Customer");
            System.out.println("2. Add Purchase");
            System.out.println("3. Refund");
            System.out.println("4. Order History");
            System.out.println("5. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addCustomer();
                    break;

                case 2:
                    addPurchase();
                    break;

                case 3:
                    refund();
                    break;

                case 4:

                    System.out.print("Enter Customer ID: ");
                    int id = sc.nextInt();

                    Customer c = findCustomer(id);

                    if (c == null) {
                        System.out.println("Customer not found!");
                    } else {
                        c.showHistory();
                    }

                    break;

                case 5:
                    System.out.println("Thank you!");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}