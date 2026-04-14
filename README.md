# 🛒 Online Shopping System (Java OOP + GUI)

## 📌 Project Overview

This project is a **Java-based Online Shopping System** built using **Object-Oriented Programming (OOP)** concepts and **Java Swing GUI**.

It simulates a real-world e-commerce platform where:

* 👤 Users can browse products and shop
* 👑 Admin can manage products (add/remove)
* 🛒 Cart system handles purchases

---

## 🚀 Features

### 👤 User Features

* User Signup & Login
* View available products
* Add products to cart
* Remove products from cart
* View cart items
* Checkout system

### 👑 Admin Features

* Admin Login
* Add new products
* Remove unavailable products
* Manage product listings

---

## 🧠 OOP Concepts Used

| Concept       | Implementation                |
| ------------- | ----------------------------- |
| Encapsulation | User & Product classes        |
| Inheritance   | Admin extends User            |
| Abstraction   | GUI interaction & system flow |
| Polymorphism  | Method handling (if applied)  |

---

## 🖥️ Technologies Used

* ☕ Java
* 🧩 Java Swing (GUI)
* 📦 ArrayList (Data Structure)
* 🎨 AWT (Graphics & Layouts)

---

## 📂 Project Structure

```
├── Main.java              # Entry point
├── AuthWindow.java       # Login & Signup UI
├── ShoppingGUI.java      # User shopping interface
├── AdminPanel.java       # Admin dashboard
├── Product.java          # Product class
├── User.java             # User class
├── Admin.java            # Admin class (inherits User)
├── Cart.java             # Cart logic
```

---

## ▶️ How to Run

1. Open project in any Java IDE (IntelliJ / Eclipse / NetBeans)
2. Make sure all `.java` files are in the same package
3. Run:

```
Main.java
```

4. GUI window will open 🎉

---

## 🔐 Default Admin Login

```
Username: admin  
Password: 123
```

---

## 🖼️ Image Setup (Important)

If product images are not showing:

* Make sure images are inside the project folder
* Use correct path:

```
images/product1.jpg
```

---

## ⚠️ Known Issues

* Images may not load if path is incorrect
* No database (data resets after restart)
* Basic UI design (can be improved further)

---

## 🔥 Future Improvements

* 💾 Database integration (MySQL)
* 🌐 Web-based version
* 💳 Payment gateway integration
* 🎨 Advanced UI (Modern design)
* 🔍 Search & filter system

---

## 👨‍💻 Author

**Md. Zawadul Haque Zawad**
CSE Student | Premier University

---

## 📜 License

This project is for educational purposes only.

---
