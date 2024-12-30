package ShoppingListApplication;
import java.util.Scanner;
import java.util.ArrayList;


public class ShoppingListApplication {

    @SuppressWarnings("unchecked")
    private final ArrayList<ShoppingList>[] shoppingLists = new ArrayList[365];

    /**
     * Main function for starting the program
     */
    public static void main(String[] args) {
        ShoppingListApplication application = new ShoppingListApplication();
        application.start();
    }

    /**
     * Function for running menu and reading users inputs.
     */
    private void start() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("");
            System.out.println("----------------------------------");
            System.out.println("# Shopping List Application");
            System.out.println("----------------------------------");
            System.out.println("\nChoose Your Option:");
            System.out.println("1. Add A New List");
            System.out.println("2. Add An Item To The List");
            System.out.println("3. Show Shopping Lists");
            System.out.println("4. Exit");
            System.out.println("");
            System.out.print("Enter Your Option: ");
            System.out.println("");


            int choice = validateIntInput(scanner);
            switch (choice) {
                case 1:
                    addNewList(scanner);
                    break;
                case 2:
                    addNewItem(scanner);
                    break;
                case 3:
                    displayLists(scanner);
                    break;
                case 4:
                    System.out.println("Exiting Program...");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid Option, Choose an Option From 1 to 4.");
            }
        }
    }

    /**
     * Function for creating a list for a specific day of the year
     */
    private void addNewList(Scanner scanner) {
        int day = getDayOfTheYear(scanner);
        if (shoppingLists[day] == null) {
            shoppingLists[day] = new ArrayList<>();
            System.out.println("");
            System.out.println("A New Shopping List Has Been Created For Day " + (day + 1) + "!");
        } else {
            System.out.println("There is already an existing list for this day " + (day + 1) + ". Use this one for adding new items or create a new list for other day(s) of the year.");
            System.out.println("");
        }
    }

    /**
     * Function for adding a new item to a previously created list for a specific day of the year
     */
    private void addNewItem(Scanner scanner) {
        int day = getDayOfTheYear(scanner);

        if (shoppingLists[day] == null) {
            System.out.println("There Is No List For The Day " + (day + 1) + ". Create A New One, Please.");
            return;
        }

        scanner.nextLine();
        System.out.print("Provide Items Name: ");
        String itemName = scanner.nextLine();
        System.out.print("Provide Items Price: ");
        double price = validateDoubleInput(scanner);
        System.out.println("");
        shoppingLists[day].add(new ShoppingList(itemName, price));
        System.out.println("This Item Has Been Added To The List Of The Day " + (day + 1) + ".");
    }

    /**
     * Show All Existing Lists for all days of the year inlusive items
     */
    private void displayLists(Scanner scanner) {
        System.out.println("\nShopping Lists:");
        for (int i = 0; i < shoppingLists.length; i++) {
            if (shoppingLists[i] != null) {
                System.out.println("");
                System.out.println("Day " + (i + 1) + ":");
                for (ShoppingList item : shoppingLists[i]) {
                    System.out.println(item);
                }
            }
        }
        
    }

    /**
     * Handling and validating of Integers typed in by user
     */
    private int validateIntInput(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid Input. Type In An Integer: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    /**
     * Handling and validating of Decimals typed in by user
     */
    private double validateDoubleInput(Scanner scanner) {
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid Input. Type In A Decimal Number: ");
            scanner.next();
        }
        return scanner.nextDouble();
    }

    /**
     * Function for getting and validating (from 1 to 365) a specific day of the year provided by user
     */
    private int getDayOfTheYear(Scanner scanner) {
        int day;
        while (true) {
            System.out.print("Provide Number Of The Day (1-365): ");
            day = validateIntInput(scanner) - 1;
            if (day >= 0 && day < 365) {
                break;
            } else {
                System.out.println("Invalid Day Number. Please, Try Again Any Day Number from 1 To 365.");
            }
        }
        return day;
    }
}

/**
 * A class for a shopping list that contains item name and price for each item added
 */
class ShoppingList {
    private final String itemName;
    private final double price;

    public ShoppingList(String itemName, double price) {
        this.itemName = itemName;
        this.price = price;
    }

    @Override
    public String toString() {
        return itemName + " - " + price + " SEK";
    }
}