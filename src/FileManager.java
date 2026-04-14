import java.io.*;
import java.util.ArrayList;

// ============= FILE MANAGER CLASS =============
class FileManager {
    private static final String USERS_FILE = "users.txt";
    private static final String ORDERS_FILE = "orders.txt";

    // Save user to file
    public static void saveUser(User user) {
        try (FileWriter fw = new FileWriter(USERS_FILE, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(user.toString());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load user by username
    public static User loadUser(String username) {
        try (BufferedReader br = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts[0].equals(username)) {
                    return new User(parts[0], parts[1], parts[2], parts[3]);
                }
            }
        } catch (IOException e) {
            // File doesn't exist yet
        }
        return null;
    }

    // Check if username exists
    public static boolean userExists(String username) {
        return loadUser(username) != null;
    }

    // Save order to file
    public static void saveOrder(Order order) {
        try (FileWriter fw = new FileWriter(ORDERS_FILE, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(order.toString());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load all orders for a user
    public static ArrayList<Order> loadUserOrders(String username) {
        ArrayList<Order> orders = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ORDERS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 4 && parts[1].equals(username)) {
                    Order order = new Order(parts[1], Double.parseDouble(parts[2]), new ArrayList<>());
                    order.orderId = parts[0];
                    order.orderDate = parts[3];
                    orders.add(order);
                }
            }
        } catch (IOException e) {
            // File doesn't exist yet
        }
        return orders;
    }
}
