package com.design_pattern;

public class App {
    public static void main(String[] args) {
        CustomerDao customerDao = new CustomerDao();
        customerDao.getData();

        OrderDao orderDao = new OrderDao();
        orderDao.getData();

        ProductDao productDao = new ProductDao();
        productDao.getData();
    }
}
