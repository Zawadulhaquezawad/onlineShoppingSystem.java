enum Category {
    ELECTRONICS("Electronics"),
    FASHION("Fashion"),
    HOME("Home & Garden"),
    SPORTS("Sports"),
    BOOKS("Books");

    final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }
}