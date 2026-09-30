package com.helpdesk.main;

import com.helpdesk.config.AppConfig;
import com.helpdesk.enums.Plan;
import com.helpdesk.enums.Role;
import com.helpdesk.model.Customer;
import com.helpdesk.model.User;
import com.helpdesk.service.CustomerService;
import com.helpdesk.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        CustomerService customerService = context.getBean(CustomerService.class);
        UserService userService = context.getBean(UserService.class);


        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("----------HELPDESK APP------------");
            System.out.println("1. Customer Signup");
            System.out.println("2. Customer Login");
            System.out.println("3. Executive Login");
            System.out.println("0. to Exit");
            System.out.println("-----------------------------------");
            int input = sc.nextInt();
            if(input == 0) {
                System.out.println("Exiting...");
                break;
            }
            switch(input){
                case 1 -> {
                    System.out.println("Customer sign up");
                    // taken input from user/customer
                    sc.nextLine(); //hold the console line
                    Customer customer = new Customer();
                    User user = new User();

                    System.out.println("Enter your name");
                    customer.setName(sc.nextLine());

                    System.out.println("Enter ur age");
                    customer.setAge(sc.nextInt());

                    System.out.println("Select Plan: ");
                    Arrays.stream(Plan.values()).forEach(System.out::println);
                    customer.setPlan(Plan.valueOf(sc.next().toUpperCase()));

                    System.out.println("-- lets set up ur login credentials --");
                    System.out.println("Enter the username");
                    user.setUsername(sc.next());

                    System.out.println("Enter the password");
                    user.setPassword(sc.next());

                    user.setRole(Role.CUSTOMER);
                    user.setActive(true);

                    // attach user to customer
                    customer.setUser(user);

                    // give these objects to service classes which will save them in DB
                    try{
                        customerService.createCustomer(customer);
                        System.out.println("Sign Up Success");
                    }
                    catch(Exception e){
                        System.out.println(e.getMessage());
                    }

                    break;
                }
                case 2 -> {
                    System.out.println("Customer login");
                    break;
                }
                default -> {
                    System.out.println("Invalid option..");
                    return;
                }

            }
        }

        sc.close();
    }
}
