import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

// Base Product class with subclasses Electronics, Clothing, Grocery
abstract class Product {
    private String name;
    private double price;
    private String category;

    public Product(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return name + " (" + category + ") - $" + price;
    }
}

class Electronics extends Product {
    private int warranty;

    public Electronics(String name, double price, int warranty) {
        super(name, price, "Electronics");
        this.warranty = warranty;
    }

    public int getWarranty() {
        return warranty;
    }
}

class Clothing extends Product {
    private String size;

    public Clothing(String name, double price, String size) {
        super(name, price, "Clothing");
        this.size = size;
    }

    public String getSize() {
        return size;
    }
}

class Grocery extends Product {
    private String expiryDate;

    public Grocery(String name, double price, String expiryDate) {
        super(name, price, "Grocery");
        this.expiryDate = expiryDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }
}

// Base User class with subclasses Customer and Admin
abstract class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }
}

class Customer extends User {
    private List<Order> orders;
    private List<Product> cart;

    public Customer(String username, String password) {
        super(username, password);
        this.orders = new ArrayList<>();
        this.cart = new ArrayList<>();
    }

    public void addToCart(Product product) {
        cart.add(product);
    }

    public Order placeOrder() {
        Order order = new Order(cart);
        orders.add(order);
        cart.clear();
        return order;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public List<Product> getCart() {
        return cart;
    }
}

class Admin extends User {
    public Admin(String username, String password) {
        super(username, password);
    }

    // Additional admin functionality can be added here
}

// Order class representing a customer's order with methods for calculating totals
class Order {
    private List<Product> products;
    private double taxRate = 0.1;  // 10% tax
    private double shippingFee = 5.0;

    public Order(List<Product> products) {
        this.products = new ArrayList<>(products);
    }

    public double calculateSubtotal() {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }

    public double calculateTotal() {
        double subtotal = calculateSubtotal();
        double tax = subtotal * taxRate;
        return subtotal + tax + shippingFee;
    }

    @Override
    public String toString() {
        return "Order{" +
                "products=" + products +
                ", total=" + calculateTotal() +
                '}';
    }
}

// E-commerce platform with methods to search, filter, and sort products
class ECommercePlatform {
    private List<Product> products;

    public ECommercePlatform() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> searchProducts(String keyword) {
        return products.stream()
                .filter(p -> p.getName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Product> filterProducts(Predicate<Product> predicate) {
        return products.stream().filter(predicate).collect(Collectors.toList());
    }

    public List<Product> sortProductsByPrice() {
        return products.stream()
                .sorted(Comparator.comparingDouble(Product::getPrice))
                .collect(Collectors.toList());
    }
}

public class Main {
    public static void main(String[] args) {
        ECommercePlatform platform = new ECommercePlatform();

        // Adding products to the platform
        platform.addProduct(new Electronics("Smartphone", 299.99, 2));
        platform.addProduct(new Clothing("T-Shirt", 19.99, "M"));
        platform.addProduct(new Grocery("Milk", 2.99, "2024-12-31"));

        // Searching products by keyword
        List<Product> searchResults = platform.searchProducts("Smartphone");
        System.out.println("Search results: " + searchResults);

        // Filtering products by category
        List<Product> electronics = platform.filterProducts(p -> p.getCategory().equals("Electronics"));
        System.out.println("Electronics: " + electronics);

        // Sorting products by price
        List<Product> sortedProducts = platform.sortProductsByPrice();
        System.out.println("Products sorted by price: " + sortedProducts);

        // Customer placing an order
        Customer customer = new Customer("john_doe", "password123");
        customer.addToCart(new Electronics("Smartphone", 299.99, 2));
        customer.addToCart(new Grocery("Milk", 2.99, "2024-12-31"));

        Order order = customer.placeOrder();
        System.out.println("Order placed: " + order);
    }
}
