import java.util.Scanner;

public class Delivery {

    public double processDelivery(double cart) {
        double deliveryCost = 25.0;
        Scanner input = new Scanner(System.in);
        String wantsDelivery;

        while (true) {
            System.out.print("Would you like your items to be delivered for a $25 fee? (yes/no): ");
            wantsDelivery = input.next().trim().toLowerCase();

            if ("yes".equals(wantsDelivery) || "no".equals(wantsDelivery)) {
                break;  // Exit the loop if a valid answer is provided
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }

        if ("yes".equals(wantsDelivery)) {

            System.out.println("An additional $25 has been added to your total for delivery.");

            System.out.println("Please provide the following details for delivery:");

            input.nextLine();  // Consume the newline character left behind by input.next()

            System.out.println("Enter your address: ");
            String address = input.nextLine();

            System.out.println("Enter your contact number: ");
            String contact = input.nextLine();

            System.out.println("Enter your preferred delivery time: ");
            String time = input.nextLine();

            System.out.println("Your items will be delivered to " + address + " at " + time + ".");
            System.out.println("Our delivery person will contact you at " + contact + " before arrival.");

            cart = cart + deliveryCost;
        } else {
            System.out.println("You have opted for in-store pickup.");
        }
        return cart;
    }

    public double getDeliveryCost() {
        return 25.0;
    }
}

