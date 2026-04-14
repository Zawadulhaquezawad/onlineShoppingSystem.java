import javax.swing.*;
import java.awt.*;
import java.util.Random;

class AdminPanel extends JFrame {

    public AdminPanel() {
        setTitle("Admin Panel");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        JButton add = new JButton("➕ Add Product");
        JButton remove = new JButton("❌ Remove Product");
        JButton openShop = new JButton("🛍️ Open Shop");

        add(add);
        add(remove);
        add(openShop);

        add.addActionListener(e -> {
            try {
                String name = JOptionPane.showInputDialog("Product Name:");
                double price = Double.parseDouble(JOptionPane.showInputDialog("Price:"));
                String desc = JOptionPane.showInputDialog("Description:");

                Product p = new Product(
                        new Random().nextInt(9999),
                        name,
                        price,
                        "img",
                        Category.ELECTRONICS,
                        desc
                );

                ProductStore.products.add(p);

                // 🔥 REFRESH SHOP
                for (Frame f : Frame.getFrames()) {
                    if (f instanceof ShoppingGUI) {
                        ((ShoppingGUI) f).refreshShop();
                    }
                }

                JOptionPane.showMessageDialog(this, "Product Added!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid Input!");
            }
        });

        remove.addActionListener(e -> {
            try {
                int id = Integer.parseInt(JOptionPane.showInputDialog("Enter Product ID:"));
                ProductStore.products.removeIf(p -> p.id == id);
                JOptionPane.showMessageDialog(this, "Product Removed!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid ID!");
            }
        });

        openShop.addActionListener(e -> {
            new ShoppingGUI("Admin Preview");
        });

        setVisible(true);
    }
}