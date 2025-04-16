The e-commerce prototype solution centers on three main classes (Product, User, and Order) and leverages inheritance, lambdas, and extra features to create a basic but functional e-commerce structure. Here’s an overview and explanation of the methods used:

Classes and Inheritance
Product Class and Subclasses:

The Product class is the base class containing essential attributes like name, price, and category.
Three subclasses (Electronics, Clothing, and Grocery) inherit from Product, each adding specific attributes (e.g., warranty for electronics, size for clothing, and expiry date for groceries). This structure facilitates organizing product types while allowing specific features per category.
User Class and Subclasses:

The User class serves as the base class with core attributes such as username and password.
Two subclasses, Customer and Admin, inherit from User, each with unique responsibilities: Customer has a shopping cart and order history, while Admin can manage inventory and view sales reports. This design ensures role-based access and functionality.
Order Class:

The Order class represents a customer’s order, including items, quantities, and methods for calculating total costs. Attributes like order_items, tax, and shipping allow for a breakdown of costs, and methods calculate the subtotal, total with tax, and final total with shipping.
Methods Using Lambdas
Filtering Products:

Lambda expressions filter products by categories, like filtering only Electronics products. This quick filtering enhances the efficiency of finding products within a large dataset.
Calculating Discounts:

Lambdas apply discounts on products dynamically (e.g., 10% off on items above a certain price), reducing the need for multiple discount-related methods and promoting concise code.
Processing Cart Totals:

Lambda functions assist in calculating cart totals, taxes, and shipping fees. For instance, total_price = sum(map(lambda item: item.price * item.quantity, cart_items)) aggregates item totals, while other lambdas calculate tax and shipping based on conditions.
Extra Features
Search and Sorting:
Search functionality uses lambda expressions and streams to locate products by name or description, offering quick keyword-based search results.
Sorting functions organize products and orders by price, date, or alphabetical order using lambdas in conjunction with stream sorting.
