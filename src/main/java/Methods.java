import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Methods {
    public static double addProductToCart(Scanner input, Inventory inventory, double cart) {
        System.out.println("Which type of product are you interested in?");
        System.out.println("We have: beds, toys, foods, treats, CBD, supplements, waste bags, shampoos, conditioners, brushes");
        System.out.println("harnesses, collars, leashes, bowls, crates, sweaters, winter coats, and flea and tick prevention");
        String category = input.nextLine().toLowerCase();

        if (category.endsWith("es")) {
            category = category.substring(0, category.length() - 2);
        } else if (category.endsWith("s")) {
            category = category.substring(0, category.length() - 1);
        }


        Map<String, Double> productsByCategory = inventory.getProductsByCategory(category);


        if (!productsByCategory.isEmpty()) {
            System.out.println("Here are the products we have in the " + category + " category:");
            for (Map.Entry<String, Double> entry : productsByCategory.entrySet()) {
                System.out.println(entry.getKey() + ": $" + entry.getValue());
            }

            System.out.print("What specific product would you like to order? ");
            String order = input.nextLine().toLowerCase();

            Double cost = inventory.getProductPrice(order);

            if (cost != null) {
                System.out.print("How many would you like to order? ");
                int quantity = input.nextInt();
                input.nextLine();

                cart += cost * quantity;
                System.out.println("Tendered Amount: $" + (cost * quantity));
            } else {
                System.out.println("We're sorry, but that item is currently out of stock.");
                System.out.println("Please try again.");
            }
        } else {
            System.out.println("We don't have products in that category. Please try again.");
        }

        return cart;
    }


    public static boolean askForMoreItems(Scanner input) {
        System.out.print("Would you like to order anything else? (yes/no): ");
        String response = input.nextLine();
        if (response.equalsIgnoreCase("yes")) {
            System.out.println("Great! What else would you like to order?");
            return true;
        }
        return false;
    }

    public static boolean processPayment(Scanner input, double cart) {
        while (true) {
            System.out.println("Your total cost is: $" + cart);
            System.out.print("Please enter the amount you are paying today: $");
            double payment = input.nextDouble();
            input.nextLine();

            if (payment < cart) {
                System.out.println("Insufficient amount. Please enter a valid amount.");
                System.out.print("Would you like to try again? (yes/no): ");
                String tryAgain = input.nextLine();
                if (tryAgain.equalsIgnoreCase("no")) {
                    System.out.println("If you can't pay today, please come again another day.");
                    System.exit(0);
                }
            } else {
                double change = payment - cart;
                System.out.println("Thank you for your order today! Your change is: $" + change);
                return true;
            }
        }
    }
}
