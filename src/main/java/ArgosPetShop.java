import java.util.Scanner;

public class ArgosPetShop  {
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
}
 // put sounds to it??