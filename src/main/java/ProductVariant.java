public class ProductVariant {
        private String size;
        private String blend;
        private double price;

        public ProductVariant(String size, String blend, double price) {
            this.size = size;
            this.blend = blend;
            this.price = price;
        }

        // Getter for size
        public String getSize() {
            return size;
        }

        // Setter for size
        public void setSize(String size) {
            this.size = size;
        }

        // Getter for blend
        public String getBlend() {
            return blend;
        }

        // Setter for blend
        public void setBlend(String blend) {
            this.blend = blend;
        }

        // Getter for price
        public double getPrice() {
            return price;
        }

        // Setter for price
        public void setPrice(double price) {
            this.price = price;
        }
    }
