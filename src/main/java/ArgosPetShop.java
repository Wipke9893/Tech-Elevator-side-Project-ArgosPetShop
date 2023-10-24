import java.util.Scanner;

public class ArgosPetShop  {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Inventory inventory = new Inventory();
        Delivery delivery = new Delivery();
        double cart = 0.0;

        do {
            cart = Methods.addProductToCart(input, inventory, cart);
            boolean wantsMoreItems = Methods.askForMoreItems(input);
            if (!wantsMoreItems) {
                cart = delivery.processDelivery(cart);
                if (Methods.processPayment(input, cart)) {
                        break;
                    }
            }
        } while (true);

        input.close();
    }
}
 // put sounds to it??