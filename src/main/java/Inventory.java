import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Bed> beds;
    private List<Food> foods;

    public Inventory() {
        this.beds = new ArrayList<>();
        this.foods = new ArrayList<>();

        try {
            // Connect to the database
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Argos", "postgres", "postgres1");

            // Fetch beds from the database
            Statement bedStatement = connection.createStatement();
            ResultSet bedResultSet = bedStatement.executeQuery("SELECT size_name, price FROM ProductVariants " +
                    "INNER JOIN ProductInfo ON ProductVariants.product_id = ProductInfo.product_id " +
                    "INNER JOIN ProductSize ON ProductVariants.size_id = ProductSize.size_id " +
                    "WHERE ProductInfo.name = 'bed'");
            while (bedResultSet.next()) {
                String size = bedResultSet.getString("size_name");
                double price = bedResultSet.getDouble("price");
                beds.add(new Bed(size, price));
            }
            bedResultSet.close();
            bedStatement.close();

            // Fetch foods from the database
            Statement foodStatement = connection.createStatement();
            ResultSet foodResultSet = foodStatement.executeQuery("SELECT size_name, blend, price FROM ProductVariants " +
                    "INNER JOIN ProductInfo ON ProductVariants.product_id = ProductInfo.product_id " +
                    "INNER JOIN ProductSize ON ProductVariants.size_id = ProductSize.size_id " +
                    "WHERE ProductInfo.name = 'food'");
            while (foodResultSet.next()) {
                String size = foodResultSet.getString("size_name");
                String blend = foodResultSet.getString("blend");
                double price = foodResultSet.getDouble("price");
                foods.add(new Food(size, blend, price));
            }
            foodResultSet.close();
            foodStatement.close();

            // Close the connection
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Bed> getBeds() {
        return beds;
    }

    public List<Food> getFoods() {
        return foods;
    }
}
