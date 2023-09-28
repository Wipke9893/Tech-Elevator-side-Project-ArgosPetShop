import java.util.HashMap;
import java.util.Map;

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

        // Initialize price list
        inventoryList.put("small bed", 20.0);
        inventoryList.put("medium bed", 30.0);
        inventoryList.put("large bed", 40.0);
        inventoryList.put("small toy", 5.0);
        inventoryList.put("medium toy", 10.0);
        inventoryList.put("large toy", 15.0);
        inventoryList.put("5 lb chicken blend food", 10.0);
        inventoryList.put("5 lb beef blend food", 12.0);
        inventoryList.put("5 lb salmon blend food", 15.0);
        inventoryList.put("25 lb chicken blend food", 20.0);
        inventoryList.put("25 lb beef blend food", 25.0);
        inventoryList.put("25 lb salmon blend food", 30.0);
        inventoryList.put("50 lb chicken blend food", 30.0);
        inventoryList.put("50 lb beef blend food", 35.0);
        inventoryList.put("50 lb salmon blend food", 40.0);
        inventoryList.put("small treat", 5.0);
        inventoryList.put("medium treat", 10.0);
        inventoryList.put("large treat", 15.0);
        inventoryList.put("calming CBD", 80.00);
        inventoryList.put("Supplements", 60.00);
        inventoryList.put("waste bags", 5.00);
        inventoryList.put("shampoo", 15.00);
        inventoryList.put("conditioner", 15.00);
        inventoryList.put("small brush", 10.00);
        inventoryList.put("medium brush", 12.00);
        inventoryList.put("large brush", 15.00);
        inventoryList.put("small harness", 20.00);
        inventoryList.put("medium harness", 22.00);
        inventoryList.put("large harness", 25.00);
        inventoryList.put("small collar", 10.00);
        inventoryList.put("medium collar", 12.00);
        inventoryList.put("large collar", 15.00);
        inventoryList.put("small leash", 10.00);
        inventoryList.put("medium leash", 12.00);
        inventoryList.put("large leash", 15.00);
        inventoryList.put("small bowl", 8.00);
        inventoryList.put("medium bowl", 10.00);
        inventoryList.put("large bowl", 12.00);
        inventoryList.put("small crate", 50.00);
        inventoryList.put("medium crate", 60.00);
        inventoryList.put("large crate", 70.00);
        inventoryList.put("small sweater", 18.00);
        inventoryList.put("medium sweater", 22.00);
        inventoryList.put("large sweater", 25.00);
        inventoryList.put("small winter coat", 25.00);
        inventoryList.put("medium winter coat", 30.00);
        inventoryList.put("large winter coat", 35.00);
        inventoryList.put("flea and tick prevention", 50.00);

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
