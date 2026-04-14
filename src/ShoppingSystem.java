import javax.swing.SwingUtilities;
class ShoppingSystem {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AuthWindow());
    }
}