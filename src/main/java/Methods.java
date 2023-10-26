import java.util.*;

public class Methods {
    // Step 1: Show available items
    public static void showAvailableItems(List<?> items) {
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i).toString());
        }
    }
    public static double addProductToCart(Scanner input, Inventory inventory, double cart) {
        List<String> validCategories = Arrays.asList("bed", "toy", "food", "treat", "CBD", "supplement",
                "waste bag", "shampoo", "conditioner", "brush",
                "harness", "collar", "leash", "bowl", "crate",
                "sweater", "winter coat", "flea and tick prevention");

        while (true) { // Loop until a valid category is entered
            System.out.println("Which type of product are you interested in?");
            System.out.println("We have: beds, toys, foods, treats, CBD, supplements, waste bags, shampoos, conditioners, brushes");
            System.out.println("harnesses, collars, leashes, bowls, crates, sweaters, winter coats, and flea and tick prevention");
            System.out.print("Please enter a category: ");
            String category = input.nextLine().toLowerCase();

            // Map plural to singular
            if (category.endsWith("s")) {
                category = category.substring(0, category.length() - 1);
            }
            try {
                if (!validCategories.contains(category)) {
                    throw new IllegalArgumentException("Invalid category");
                }
                // If the category is valid, break the loop
                if ("bed".equals(category)) {
                    return addBedToCart(input, inventory, cart);
                } else if ("food".equals(category)) {
                    return addFoodToCart(input, inventory, cart);
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid category. Please enter a valid category.");
            }
        }
    }
    public static int getUserChoice(Scanner input, int itemCount) {
        int choice;
        while (true) {
            try {
                System.out.print("Please enter the number of the item you would like to order: ");
                choice = input.nextInt() - 1;
                input.nextLine();

                if (choice < 0 || choice >= itemCount) {
                    System.out.println("Invalid choice. Please try again.");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                input.nextLine();
            }
        }
        return choice;
    }

    public static double addBedToCart(Scanner input, Inventory inventory, double cart) {
        List<Bed> beds = inventory.getBeds();

        // Show available beds
        showAvailableItems(beds);

        // Get user choice for bed
        int choice = getUserChoice(input, beds.size());

        // Get quantity
        int quantity = getQuantity(input);

        // Add to cart
        cart = addToCart(cart, beds.get(choice).getPrice(), quantity);

        System.out.println("You have added " + quantity + " " + beds.get(choice).getSize() + " bed(s) to your cart.");

        return cart;
    }
    public static double addFoodToCart(Scanner input, Inventory inventory, double cart) {
        List<Food> foods = inventory.getFoods();

        showAvailableItems(foods);

        int choice = getUserChoice(input, foods.size());

        int quantity = getQuantity(input);

        cart = addToCart(cart, foods.get(choice).getPrice(), quantity);

        System.out.println("You have added " + quantity + " " + foods.get(choice).getSize() + " " + foods.get(choice).getBlend() + " food(s) to your cart.");

        return cart;
    }
    public static boolean askForMoreItems(Scanner input) { // bug here --------------------->
        String response;
        while (true) {
            System.out.print("Would you like to order anything else? (yes/no): ");
            response = input.nextLine().trim().toLowerCase();
            if ("yes".equals(response) || "no".equals(response)) {
                break;  // Exit the loop if a valid answer is provided
            } else {
                System.out.println("Invalid response. Please enter yes or no.");
            }
        }
        if ("yes".equals(response)) {
            System.out.println("Great! What else would you like to order?");
            return true;
        }
        return false;
    }
    public static int getQuantity(Scanner input) {
        while (true) {
            try {
                System.out.print("Please enter the quantity: ");
                int quantity = input.nextInt();
                input.nextLine();
                if (quantity <= 0) {
                    throw new IllegalArgumentException("Invalid quantity");
                }
                return quantity;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                input.nextLine();  // consume the invalid token
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid quantity. Please enter a valid quantity.");
            }
        }
    }

    public static double addToCart(double cart, double price, int quantity) {
        return cart + (price * quantity);
    }


    public static boolean processPayment(Scanner input, double cart) {
        while (true) {
            System.out.println("Your total cost is: $" + cart);
            try {
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
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                input.nextLine();
            }
        }
    }
}