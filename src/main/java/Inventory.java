import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.dbcp2.BasicDataSource;

public class Inventory {
    private Map<String, Double> inventoryList;

    public Inventory() {
        inventoryList = new HashMap<>();

        try {
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setUrl("jdbc:postgresql://localhost:5432/Argos");
            dataSource.setUsername("postgres");
            dataSource.setPassword("postgres1");

            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(
                    "SELECT name, price FROM ProductInfo INNER JOIN ProductVariants ON ProductInfo.product_id = ProductVariants.product_id;"
            );

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                Double price = resultSet.getDouble("price");
                inventoryList.put(name, price);
            }

            resultSet.close();
            statement.close();
            connection.close();
            dataSource.close();
        } catch (Exception e) {
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
