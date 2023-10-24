public class Food {
    private String size;
    private String blend;
    private double price;

    // Constructor
    public Food(String size, String blend, double price) {
        this.size = size;
        this.blend = blend;
        this.price = price;
    }

    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }

    public String getBlend() {
        return blend;
    }

    public void setBlend(String blend) {
        this.blend = blend;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
