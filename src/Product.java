class Product {
    int id;
    String name;
    double price;
    String imagePath;
    Category category;
    String description;

    public Product(int id, String name, double price, String imagePath, Category category, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imagePath = imagePath;
        this.category = category;
        this.description = description;
    }
}