import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Inventory implements Purchasable {
    private Map<String, Double> inventoryList;

    @Override
    public double getPrice() {
        return 0;
    }

    @Override
    public String getName() {
        return "inventory";
    }
    public Inventory() {
        inventoryList = new HashMap<>();

        // Reading from a file
        try {
            File file = new File("inventory.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(", ");
                if (parts.length >= 2) { // Making sure "both a name and a price" are present in the txt file.
                    String name = parts[0];
                    Double price = Double.parseDouble(parts[1]);
                    inventoryList.put(name, price);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public Double getProductPrice(String productName) {
        return inventoryList.get(productName);
    }

    public Map<String, Double> getProductsByCategory(String category) {
        Map<String, Double> filteredProducts = new HashMap<>();
        for (Map.Entry<String, Double> entry : inventoryList.entrySet()) {
            if (entry.getKey().contains(category)) {
                filteredProducts.put(entry.getKey(), entry.getValue());
            }
        }
        return filteredProducts;
    }



}
