import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

class Order {
    String orderId;
    String username;
    double totalAmount;
    String orderDate;
    ArrayList<CartItem> items;

    public Order(String username, double totalAmount, ArrayList<CartItem> items) {
        this.orderId = "ORD-" + System.currentTimeMillis();
        this.username = username;
        this.totalAmount = totalAmount;
        this.orderDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        this.items = new ArrayList<>(items);
    }

    @Override
    public String toString() {
        StringBuilder itemStr = new StringBuilder();
        for (CartItem item : items) {
            itemStr.append(item.product.id).append(":").append(item.quantity).append(",");
        }
        return orderId + "|" + username + "|" + totalAmount + "|" + orderDate + "|" + itemStr.toString();
    }
}
