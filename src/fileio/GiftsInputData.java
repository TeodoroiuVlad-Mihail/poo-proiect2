package fileio;

public class GiftsInputData {
    private String productName;
    private double price;
    private String category;
    private int quantity;

    public GiftsInputData(final String productName, final double price, final String category,
                          final int quantity) {
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    /**
     * getter for productname
     */
    public String getProductName() {
        return productName;
    }

    /**
     * setter for product name
     */
    public void setProductName(final String productName) {
        this.productName = productName;
    }

    /**
     * getter for price
     */
    public double getPrice() {
        return price;
    }

    /**
     * setter for price
     */
    public void setPrice(final double price) {
        this.price = price;
    }

    /**
     * getter for category
     */
    public String getCategory() {
        return category;
    }

    /**
     * setter for category
     */
    public void setCategory(final String category) {
        this.category = category;
    }

    /**
     * getter for quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * getter for quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
