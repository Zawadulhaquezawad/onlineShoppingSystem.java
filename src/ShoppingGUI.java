import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

class ShoppingGUI extends JFrame {
    String currentUsername;
    Cart cart = new Cart();
    JLabel cartLabel;
    JComboBox<Category> categoryFilter;
    JTextField searchField;
    ArrayList<Product> allProducts = ProductStore.products;;
    JPanel productPanel;

    public ShoppingGUI(String username) {
        this.currentUsername = username;
        setTitle("🛍️ Online Shopping System - " + username);
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // TOP PANEL
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(30, 30, 30));

        JLabel title = new JLabel("🛍️ Smart Shop");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 22));

        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.setBackground(new Color(30, 30, 30));

        searchField = new JTextField(15);
        searchField.setFont(new Font("Arial", Font.PLAIN, 12));
        JButton searchBtn = new JButton("🔍 Search");
        searchBtn.setBackground(new Color(0, 123, 255));
        searchBtn.setForeground(Color.WHITE);

        categoryFilter = new JComboBox<>(Category.values());
        categoryFilter.insertItemAt(null, 0);
        categoryFilter.setSelectedIndex(0);

        searchBtn.addActionListener(e -> filterProducts());
        categoryFilter.addActionListener(e -> filterProducts());

        searchPanel.add(new JLabel("Category:"));
        searchPanel.add(categoryFilter);
        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(searchField);
        searchPanel.add(searchBtn);

        JPanel rightPanel = new JPanel(new FlowLayout());
        rightPanel.setBackground(new Color(30, 30, 30));

        JButton orderHistoryBtn = new JButton("📋 Order History");
        orderHistoryBtn.setBackground(new Color(255, 193, 7));
        orderHistoryBtn.setForeground(Color.BLACK);
        orderHistoryBtn.addActionListener(e -> openOrderHistory());

        // CART BUTTON
        ImageIcon cartIcon = new ImageIcon("cart.png");
        Image img = cartIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        JButton cartBtn = new JButton("🛒 Cart");
        cartBtn.setBackground(Color.WHITE);

        cartLabel = new JLabel("0");
        cartLabel.setForeground(Color.WHITE);
        cartLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JButton logoutBtn = new JButton("🚪 Logout");
        logoutBtn.setBackground(new Color(220, 53, 69));
        logoutBtn.setForeground(Color.WHITE);
        logoutBtn.addActionListener(e -> {
            dispose();
            new AuthWindow();
        });

        rightPanel.add(orderHistoryBtn);
        rightPanel.add(cartBtn);
        rightPanel.add(cartLabel);
        rightPanel.add(logoutBtn);

        topPanel.add(title, BorderLayout.WEST);
        topPanel.add(searchPanel, BorderLayout.CENTER);
        topPanel.add(rightPanel, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        // PRODUCT PANEL
        productPanel = new JPanel(new GridLayout(0, 3, 15, 15));
        productPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        initializeProducts();
        displayProducts(allProducts);

        JScrollPane scrollPane = new JScrollPane(productPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        add(scrollPane, BorderLayout.CENTER);

        // CART BUTTON ACTION
        cartBtn.addActionListener(e -> openCartWindow());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeProducts() {

        // only load once
        if (ProductStore.products.isEmpty()) {

            ProductStore.products.add(new Product(1, "Laptop", 800, "img", Category.ELECTRONICS, "High-performance laptop"));
            ProductStore.products.add(new Product(2, "Smartphone", 500, "img", Category.ELECTRONICS, "Latest smartphone"));
            ProductStore.products.add(new Product(3, "Headphones", 100, "img", Category.ELECTRONICS, "Noise cancelling"));
            ProductStore.products.add(new Product(4, "T-Shirt", 25, "img", Category.FASHION, "Cotton T-Shirt"));
            ProductStore.products.add(new Product(5, "Jeans", 60, "img", Category.FASHION, "Blue Jeans"));
            ProductStore.products.add(new Product(6, "Jeans shirt", 600, "img", Category.FASHION, "Blue Jeans"));
            ProductStore.products.add(new Product(7, "Java book", 650, "img", Category.ELECTRONICS, "Blue code"));
            ProductStore.products.add(new Product(8, "wifi", 5600, "img", Category.ELECTRONICS, "Blue"));
            ProductStore.products.add(new Product(9, "Jet brains", 260, "img", Category.FASHION, "Blue 25ns"));
            ProductStore.products.add(new Product(10, "Jens", 600, "img", Category.FASHION, "Blueans"));

        }

        allProducts = ProductStore.products;
    }

    private void displayProducts(ArrayList<Product> products) {
        productPanel.removeAll();
        for (Product p : products) {
            productPanel.add(createProductCard(p));
        }
        productPanel.revalidate();
        productPanel.repaint();
    }

    private void filterProducts() {
        ArrayList<Product> filtered = new ArrayList<>();
        Category selectedCategory = (Category) categoryFilter.getSelectedItem();
        String searchTerm = searchField.getText().toLowerCase();

        for (Product p : allProducts) {
            boolean matchesCategory = (selectedCategory == null || p.category == selectedCategory);
            boolean matchesSearch = searchTerm.isEmpty() || p.name.toLowerCase().contains(searchTerm) || p.description.toLowerCase().contains(searchTerm);

            if (matchesCategory && matchesSearch) {
                filtered.add(p);
            }
        }

        displayProducts(filtered);
    }

    // CREATE PRODUCT CARD
    private JPanel createProductCard(Product p) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
        card.setBackground(Color.WHITE);

        // IMAGE AREA - MARKED WITH RED TEXT
        JLabel imageLabel = new JLabel(p.imagePath, SwingConstants.CENTER);
        imageLabel.setPreferredSize(new Dimension(150, 120));
        imageLabel.setBackground(new Color(240, 240, 240));
        imageLabel.setOpaque(true);
        imageLabel.setFont(new Font("Arial", Font.BOLD, 12));
        imageLabel.setForeground(new Color(220, 53, 69)); // RED COLOR

        JLabel name = new JLabel(p.name, SwingConstants.CENTER);
        name.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel price = new JLabel("$" + String.format("%.2f", p.price), SwingConstants.CENTER);
        price.setFont(new Font("Arial", Font.BOLD, 13));
        price.setForeground(new Color(40, 167, 69));

        JLabel description = new JLabel(p.description, SwingConstants.CENTER);
        description.setFont(new Font("Arial", Font.PLAIN, 10));
        description.setForeground(Color.GRAY);

        JButton addBtn = new JButton("➕ Add to Cart");
        addBtn.setBackground(new Color(40, 167, 69));
        addBtn.setForeground(Color.WHITE);
        addBtn.setFont(new Font("Arial", Font.BOLD, 12));

        addBtn.addActionListener(e -> {
            cart.addItem(p);
            updateCartLabel();
            JOptionPane.showMessageDialog(this, p.name + " added to cart!", "Success", JOptionPane.INFORMATION_MESSAGE);
        });

        JPanel info = new JPanel(new GridLayout(4, 1, 5, 5));
        info.setBackground(Color.WHITE);
        info.add(name);
        info.add(description);
        info.add(price);
        info.add(addBtn);

        card.add(imageLabel, BorderLayout.CENTER);
        card.add(info, BorderLayout.SOUTH);

        return card;
    }

    // CART WINDOW
    private void openCartWindow() {
        if (cart.items.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Cart is empty!");
            return;
        }

        JFrame frame = new JFrame("🛒 Shopping Cart");
        frame.setSize(600, 500);
        frame.setLocationRelativeTo(this);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (CartItem item : cart.items) {
            JPanel row = new JPanel(new BorderLayout());
            row.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            row.setBackground(new Color(250, 250, 250));
            row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

            // Image placeholder with RED text
            JLabel imgLabel = new JLabel("[🖼️ IMAGES]", SwingConstants.CENTER);
            imgLabel.setPreferredSize(new Dimension(80, 80));
            imgLabel.setBackground(new Color(240, 240, 240));
            imgLabel.setOpaque(true);
            imgLabel.setFont(new Font("Arial", Font.BOLD, 11));
            imgLabel.setForeground(new Color(220, 53, 69)); // RED COLOR

            JLabel text = new JLabel(item.product.name + " x" + item.quantity + " = $" + String.format("%.2f", item.product.price * item.quantity));
            text.setFont(new Font("Arial", Font.PLAIN, 13));

            JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(item.quantity, 1, 100, 1));
            quantitySpinner.addChangeListener(e -> {
                int newQty = (int) quantitySpinner.getValue();
                cart.updateQuantity(item.product.id, newQty);
                updateCartLabel();
                frame.dispose();
                openCartWindow();
            });

            JButton remove = new JButton("❌ Remove");
            remove.setBackground(new Color(220, 53, 69));
            remove.setForeground(Color.WHITE);
            remove.addActionListener(e -> {
                cart.removeItem(item.product.id);
                frame.dispose();
                updateCartLabel();
                if (cart.items.isEmpty()) {
                    JOptionPane.showMessageDialog(ShoppingGUI.this, "Cart is now empty!");
                } else {
                    openCartWindow();
                }
            });

            JPanel controls = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
            controls.add(new JLabel("Qty:"));
            controls.add(quantitySpinner);
            controls.add(remove);

            row.add(imgLabel, BorderLayout.WEST);
            row.add(text, BorderLayout.CENTER);
            row.add(controls, BorderLayout.EAST);

            panel.add(row);
            panel.add(Box.createVerticalStrut(5));
        }

        JPanel totalPanel = new JPanel(new BorderLayout());
        totalPanel.setBorder(new LineBorder(Color.BLACK, 2));
        totalPanel.setBackground(new Color(40, 167, 69));

        JLabel totalLabel = new JLabel("TOTAL: $" + String.format("%.2f", cart.getTotal()));
        totalLabel.setForeground(Color.WHITE);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalLabel.setHorizontalAlignment(SwingConstants.CENTER);

        totalPanel.add(totalLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        buttonPanel.setBackground(Color.WHITE);

        JButton continueShopping = new JButton("🛍️ Continue Shopping");
        continueShopping.setBackground(new Color(0, 123, 255));
        continueShopping.setForeground(Color.WHITE);
        continueShopping.addActionListener(e -> frame.dispose());

        JButton checkoutBtn = new JButton("💰 Checkout");
        checkoutBtn.setBackground(new Color(40, 167, 69));
        checkoutBtn.setForeground(Color.WHITE);
        checkoutBtn.setFont(new Font("Arial", Font.BOLD, 13));
        checkoutBtn.addActionListener(e -> {
            Order order = new Order(currentUsername, cart.getTotal(), (ArrayList<CartItem>) cart.items);
            FileManager.saveOrder(order);
            JOptionPane.showMessageDialog(frame, "✅ Order Placed!\n\nOrder ID: " + order.orderId + "\nTotal: $" + String.format("%.2f", order.totalAmount));
            cart.clear();
            frame.dispose();
            updateCartLabel();
        });

        buttonPanel.add(continueShopping);
        buttonPanel.add(checkoutBtn);

        JScrollPane scroll = new JScrollPane(panel);
        frame.add(scroll, BorderLayout.CENTER);
        frame.add(totalPanel, BorderLayout.SOUTH);

        // Add button panel above checkout area
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(totalPanel, BorderLayout.SOUTH);
        bottomPanel.add(buttonPanel, BorderLayout.NORTH);

        frame.remove(scroll);
        frame.setLayout(new BorderLayout());
        frame.add(scroll, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    // ORDER HISTORY WINDOW
    private void openOrderHistory() {
        ArrayList<Order> orders = FileManager.loadUserOrders(currentUsername);

        JFrame frame = new JFrame("📋 Order History - " + currentUsername);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(this);

        if (orders.isEmpty()) {
            JLabel noOrdersLabel = new JLabel("No orders yet!");
            noOrdersLabel.setFont(new Font("Arial", Font.BOLD, 16));
            noOrdersLabel.setHorizontalAlignment(SwingConstants.CENTER);
            frame.add(noOrdersLabel);
        } else {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            for (Order order : orders) {
                JPanel orderRow = new JPanel(new BorderLayout());
                orderRow.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
                orderRow.setBackground(new Color(245, 245, 245));
                orderRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));

                JLabel orderInfo = new JLabel(
                        "<html><b>Order ID:</b> " + order.orderId + "<br>" +
                                "<b>Date:</b> " + order.orderDate + "<br>" +
                                "<b>Total:</b> $" + String.format("%.2f", order.totalAmount) + "</html>"
                );
                orderInfo.setFont(new Font("Arial", Font.PLAIN, 12));

                JButton viewBtn = new JButton("👁️ View Details");
                viewBtn.setBackground(new Color(0, 123, 255));
                viewBtn.setForeground(Color.WHITE);
                viewBtn.addActionListener(e -> {
                    String details = "Order ID: " + order.orderId + "\nDate: " + order.orderDate +
                            "\nTotal: $" + String.format("%.2f", order.totalAmount) +
                            "\nItems: " + order.items.size();
                    JOptionPane.showMessageDialog(frame, details);
                });

                orderRow.add(orderInfo, BorderLayout.CENTER);
                orderRow.add(viewBtn, BorderLayout.EAST);

                panel.add(orderRow);
                panel.add(Box.createVerticalStrut(5));
            }

            JScrollPane scroll = new JScrollPane(panel);
            frame.add(scroll, BorderLayout.CENTER);
        }

        frame.setVisible(true);
    }

    // UPDATE CART LABEL
    private void updateCartLabel() {
        cartLabel.setText(String.valueOf(cart.getTotalItems()));
    }
    // 🔥 REFRESH SHOP (ADMIN UPDATE SUPPORT)
    public void refreshShop() {
        displayProducts(allProducts);
    }

}
