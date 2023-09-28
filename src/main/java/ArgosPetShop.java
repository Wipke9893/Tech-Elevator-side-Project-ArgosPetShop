import java.util.Scanner;

public class ArgosPetShop implements PaymentProcessor {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Inventory inventory = new Inventory();
        double cart = 0;

        do {
            cart = Methods.addProductToCart(input, inventory, cart);

            boolean wantsMoreItems = Methods.askForMoreItems(input);
            if (!wantsMoreItems) {
                Delivery delivery = new Delivery();
                boolean wantsDelivery = delivery.processDelivery(cart);
                if (wantsDelivery) {
                    cart += 25.0;
                }
                if (Methods.processPayment(input, cart)) {
                    break;
                }
            }
        } while (true);

        input.close();
    }

    @Override
    public boolean processPayment(double amountToPay, double amountPaid) {
        if (amountPaid < amountToPay) {
            System.out.println("Insufficient amount. Please enter a valid amount.");
            return false;
        }
        double change = amountPaid - amountToPay;
        System.out.println("Thank you for your order today! Your change is: $" + change);
        return true;
    }
}
