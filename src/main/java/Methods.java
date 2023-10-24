import java.util.*;

public class Methods {
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
                } else {
                    // Handle other categories here
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid category. Please enter a valid category.");
            }
        }
    }

    public static double addBedToCart(Scanner input, Inventory inventory, double cart) {
        List<Bed> beds = inventory.getBeds();
        System.out.println("Available beds sizes:");
        for (int i = 0; i < beds.size(); i++) {
            System.out.println((i + 1) + ". " + beds.get(i).getSize() + " - $" + beds.get(i).getPrice());
        }

        System.out.println("Please specify your selected orders by number: ");

        while (true) {
            try {
                String line = input.nextLine().replaceAll("[^0-9]", ""); // remove all non-numeric characters
                int choice = Integer.parseInt(line.split(" ")[0]); // get the first part and try to convert it to an integer
                Bed selectedBed = beds.get(choice - 1);
                int quantity = getQuantity(input);
                cart += selectedBed.getPrice() * quantity;
                System.out.println("You have added a " + selectedBed.getSize() + " bed to your cart.");
                break; // if successful, break the loop
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid choice. Please select a valid bed size.");
            }
        }
        return cart;
    }



    public static double addFoodToCart(Scanner input, Inventory inventory, double cart) {
        List<Food> foods = inventory.getFoods();
        System.out.println("Available food sizes:");
        for (int i = 0; i < foods.size(); i++) {
            System.out.println((i + 1) + ". " + foods.get(i).getSize() + " - " + foods.get(i).getBlend() + " - $" + foods.get(i).getPrice());
        }

        System.out.println("Please specify your selected orders by number: ");

        while (true) {
            try {
                String line = input.nextLine().replaceAll("[^0-9]", ""); // remove all non-numeric characters
                int choice = Integer.parseInt(line.split(" ")[0]); // get the first part and try to convert it to an integer
                Food selectedFood = foods.get(choice - 1);
                int quantity = getQuantity(input);
                cart += selectedFood.getPrice() * quantity;
                System.out.println("You have added a " + selectedFood.getSize() + " " + selectedFood.getBlend() + " food to your cart.");
                break; // if successful, break the loop
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid choice. Please select a valid food size.");
            }
        }
        return cart;
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



    public static boolean askForMoreItems(Scanner input) {
        String response;
        while (true) {
            System.out.print("Would you like to order anything else? (yes/no): ");
            response = input.nextLine().trim().toLowerCase();
            if ("yes".equals(response) || "no".equals(response)) {
                break;  // Exit the loop if a valid answer is provided
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }
        if ("yes".equals(response)) {
            System.out.println("Great! What else would you like to order?");
            return true;
        }
        return false;
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
                input.nextLine();  // consume the invalid token
            }
        }
    }
}
