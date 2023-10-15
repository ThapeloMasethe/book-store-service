# book-store-service
Service to manage books, customers, orders and payments.

#### Please note, this project is packaged by feature.

## The Problem

* ### Only authenticated users can make orders.
* ### The Database Design
  * Books: BookID (PK), Title, Author, Price, Stock
  * Customers: CustomerID (PK), Name, Email, PasswordHash, IsAdmin
  * Orders: OrderID (PK), CustomerID (FK), CreatedAt, TotalPrice
  * OrderItems: OrderItemID (PK), OrderID (FK), BookID (FK), Quantity, Price
  * Payments: PaymentID (PK), OrderID (FK), PaymentAmount, PaymentDate

A Customer can have many Orders.
An Order can have many OrderItems.
An Order has one Payment.
An OrderItem refers to one Book.

### Connect to the DB
* Use an ORM like Entity Framework or Hibernate to map your entities to the database tables. This will handle the database connections and allow you to work with objects in your code.

### Add API Endpoints

* `GET /books`: Retrieve all books.

* `POST /books`: Add a new book (Admin only).

* `DELETE /books/{bookId}`: Delete a book by ID (Admin only).

* `GET /orders`: Retrieve all orders (Admin only).

* `POST /orders`: Create a new order.

* `GET /orders/{orderId}`: Retrieve a specific order.

* `POST /payments`: Process a payment for an order.

### Authentication
* Use JWT (JSON Web Tokens) to handle user authentication. 
* Users will log in, receive a token, and use that token for the next requests.
