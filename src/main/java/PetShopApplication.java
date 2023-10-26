import java.util.Scanner;

public class PetShopApplication implements PetShopUI {
    private Scanner input;
    private Inventory inventory;
    private Delivery delivery;
    private double cart;

    public PetShopApplication() {
        this.input = new Scanner(System.in);
        this.inventory = new Inventory();
        this.delivery = new Delivery();
        this.cart = 0.0;
    }

    @Override
    public void start() {
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
