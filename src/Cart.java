import java.util.*;

class Cart {
    List<CartItem> items = new ArrayList<>();

    public void addItem(Product p) {
        for (CartItem i : items) {
            if (i.product.id == p.id) {
                i.quantity++;
                return;
            }
        }
        items.add(new CartItem(p, 1));
    }

    public void removeItem(int productId) {
        items.removeIf(i -> i.product.id == productId);
    }

    public void updateQuantity(int productId, int quantity) {
        for (CartItem i : items) {
            if (i.product.id == productId) {
                i.quantity = Math.max(1, quantity);
                return;
            }
        }
    }

    public double getTotal() {
        double total = 0;
        for (CartItem i : items) {
            total += i.product.price * i.quantity;
        }
        return total;
    }

    public int getTotalItems() {
        int count = 0;
        for (CartItem i : items) {
            count += i.quantity;
        }
        return count;
    }

    public void clear() {
        items.clear();
    }
}