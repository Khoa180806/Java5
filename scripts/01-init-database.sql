-- MariaDB version of the database initialization script

USE ASS_SOF3021;

-- Drop tables if they exist
DROP TABLE IF EXISTS OrderDetails;
DROP TABLE IF EXISTS Orders;
DROP TABLE IF EXISTS Products;
DROP TABLE IF EXISTS Categories;
DROP TABLE IF EXISTS Accounts;

-- Create Accounts table
CREATE TABLE Accounts(
    Username VARCHAR(50) NOT NULL PRIMARY KEY,
    Password VARCHAR(50) NOT NULL,
    Fullname VARCHAR(50) NOT NULL,
    Email VARCHAR(50) NOT NULL,
    Photo VARCHAR(255) NULL,
    Activated BOOLEAN DEFAULT 0,
    Role BOOLEAN DEFAULT 0
);

-- Create Categories table
CREATE TABLE Categories(
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(50) NOT NULL
);

-- Create Products table
CREATE TABLE Products(
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(50) NOT NULL,
    Image VARCHAR(255) NULL,
    Price DECIMAL(10,2),
    CreateDate DATE DEFAULT CURRENT_DATE,
    Available BOOLEAN DEFAULT 1,
    CategoryID INT,
    
    FOREIGN KEY (CategoryID) REFERENCES Categories(ID)
);

-- Create Orders table
CREATE TABLE Orders(
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    Username VARCHAR(50) NOT NULL,
    Phone CHAR(10) NOT NULL,
    CreateDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    Address VARCHAR(100) NOT NULL,
    Note VARCHAR(200) NULL,
    Status INT DEFAULT 0,
    
    FOREIGN KEY (Username) REFERENCES Accounts(Username)
);

-- Create OrderDetails table
CREATE TABLE OrderDetails(
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    OrderID BIGINT,
    ProductID INT,
    Price DECIMAL(10,2),
    Quantity INT,
    
    FOREIGN KEY (OrderID) REFERENCES Orders(ID),
    FOREIGN KEY (ProductID) REFERENCES Products(ID)
);

-- Insert data into Accounts
INSERT INTO Accounts(Username, Password, Fullname, Email, Photo, Activated, Role)
VALUES ('Admin', 'admin', 'ADMIN', 'admin@email.com', 'admin.png', 1, 1);
INSERT INTO Accounts(Username, Password, Fullname, Email, Photo, Activated, Role)
VALUES ('user', '123', 'USER', 'user@email.com', 'user.png', 1, 0);
INSERT INTO Accounts(Username, Password, Fullname, Email, Photo, Activated, Role)
VALUES ('userFake', '321', 'userFake', 'userFake@email.com', 'userFake.png', 0, 0);

-- Insert data into Categories
INSERT INTO Categories (Name) VALUES ('Áo');
INSERT INTO Categories (Name) VALUES ('Giày');
INSERT INTO Categories (Name) VALUES ('Quần');

-- Insert data into Products
INSERT INTO Products(Name, Image, Price, Available, CategoryID)
VALUES ('Áo Thun', 'aa.jpg', 99000.00, 1, 1);
INSERT INTO Products(Name, Image, Price, Available, CategoryID)
VALUES ('Quần Đùi Hoa', 'bb.jpg', 49000.00, 1, 3);
INSERT INTO Products(Name, Image, Price, Available, CategoryID)
VALUES ('Giày Bata', 'cc.jpg', 69000.00, 1, 2);
INSERT INTO Products(Name, Image, Price, Available, CategoryID)
VALUES ('Áo Ba Lỗ', 'dd.jpg', 98000.00, 1, 1);
INSERT INTO Products(Name, Image, Price, Available, CategoryID)
VALUES ('Áo Dài', 'ee.jpg', 199000.00, 1, 1);
INSERT INTO Products(Name, Image, Price, Available, CategoryID)
VALUES ('Quần Bò', 'ff.jpg', 149000.00, 1, 3);
INSERT INTO Products(Name, Image, Price, Available, CategoryID)
VALUES ('Dép Tổ Ong', 'zzz.jpg', 99999.00, 0, 3);
INSERT INTO Products(Name, Image, Price, Available, CategoryID)
VALUES ('Áo Croptop', 'xxx.jpg', 99001.00, 1, 1);
INSERT INTO Products(Name, Image, Price, Available, CategoryID)
VALUES ('Đây không phải Áo', 'notao.jpg', 10001.00, 1, 1);
INSERT INTO Products(Name, Image, Price, Available, CategoryID)
VALUES ('Đây không phải Quần', 'notquan.jpg', 20002.00, 1, 3);
INSERT INTO Products(Name, Image, Price, Available, CategoryID)
VALUES ('Đây không phải Giày', 'notgiay.jpg', 11111.00, 1, 2);

-- Insert data into Orders
INSERT INTO Orders(Username, Phone, Address, Status)
VALUES ('user', '0123456789', 'Address User 111', 2);
INSERT INTO Orders(Username, Phone, Address, Status)
VALUES ('userFake', '0987654321', 'Address NamNT', 1);

-- Insert data into OrderDetails
INSERT INTO OrderDetails(OrderID, ProductID, Price, Quantity)
VALUES (1, 1, 99000.00, 2);
INSERT INTO OrderDetails(OrderID, ProductID, Price, Quantity)
VALUES (2, 2, 49000.00, 2);
INSERT INTO OrderDetails(OrderID, ProductID, Price, Quantity)
VALUES (1, 8, 99001.00, 1);
INSERT INTO OrderDetails(OrderID, ProductID, Price, Quantity)
VALUES (2, 3, 69000.00, 1);
INSERT INTO OrderDetails(OrderID, ProductID, Price, Quantity)
VALUES (2, 9, 10001.00, 1);
INSERT INTO OrderDetails(OrderID, ProductID, Price, Quantity)
VALUES (1, 10, 20002.00, 1);
INSERT INTO OrderDetails(OrderID, ProductID, Price, Quantity)
VALUES (1, 11, 11111.00, 1);

-- Show all data
SELECT * FROM Accounts;
SELECT * FROM Categories;
SELECT * FROM Products;
SELECT * FROM Orders;
SELECT * FROM OrderDetails;

-- Sample queries
SELECT o.ID, o.Username, p.Name, od.Price, od.Quantity, (od.price * od.Quantity) as ThanhTien
FROM Orders o 
JOIN OrderDetails od ON o.ID = od.OrderID 
JOIN Products p ON od.ProductID = p.ID
WHERE o.ID = 1;

SELECT o.ID, o.Username, o.Address, SUM(od.Price) as Tong_Tien
FROM Orders o 
JOIN OrderDetails od ON o.ID = od.OrderID
GROUP BY o.ID, o.Username, o.Address;

SELECT p.*, SUM(od.Quantity) as TotalSold
FROM Products p 
JOIN OrderDetails od ON p.ID = od.ProductID
GROUP BY p.ID, p.Name, p.Price, p.Image, p.CreateDate, p.Available, p.CategoryID
ORDER BY TotalSold DESC
LIMIT 3;
