import java.util.Scanner;

public class Delivery {

    public boolean processDelivery(double cart) {
        double deliveryCost = 25.0;
        Scanner input = new Scanner(System.in);

        System.out.println("Would you like your items to be delivered for a $25 fee? (yes/no): ");
        String wantsDelivery = input.nextLine();

        if (wantsDelivery.equalsIgnoreCase("yes")) {
            System.out.println("Please provide the following details for delivery:");

            System.out.print("Enter your address: ");
            String address = input.nextLine();

            System.out.print("Enter your contact number: ");
            String contact = input.nextLine();

            System.out.print("Enter your preferred delivery time: ");
            String time = input.nextLine();

            System.out.println("Your items will be delivered to " + address + " at " + time + ".");
            System.out.println("Our delivery person will contact you at " + contact + " before arrival.");

            cart = cart + deliveryCost;
            System.out.println("An additional $25 has been added to your total for delivery.");

            return true;
        } else {
            System.out.println("You have opted for in-store pickup.");
            return false;
        }
    }
}

