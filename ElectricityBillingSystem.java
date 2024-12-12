package com.EBS;
import java.util.*;

class Customer {
    private String name;
    private int customerId;
    private int unitsConsumed;
    private double billAmount;

    public Customer(String name, int customerId) {
        this.name = name;
        this.customerId = customerId;
        this.unitsConsumed = 0;
        this.billAmount = 0.0;
    }

    public String getName() {
        return name;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getUnitsConsumed() {
        return unitsConsumed;
    }

    public double getBillAmount() {
        return billAmount;
    }

    public void recordUnits(int units) {
        this.unitsConsumed += units;
    }

    public void calculateBill(double tariffPerUnit) {
        this.billAmount = unitsConsumed * tariffPerUnit;
    }

    public void resetBillingCycle() {
        this.unitsConsumed = 0;
        this.billAmount = 0.0;
    }

    @Override
    public String toString() {
        return "Customer ID: " + customerId + ", Name: " + name + ", Units Consumed: " + unitsConsumed + ", Bill Amount: $" + String.format("%.2f", billAmount);
    }
}

public class ElectricityBillingSystem {
    static List<Customer> customers = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static double tariffPerUnit = 5.0; // Default tariff rate

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Electricity Billing System Menu ===");
            System.out.println("1. Add New Customer");
            System.out.println("2. Record Electricity Usage");
            System.out.println("3. Generate Monthly Bills");
            System.out.println("4. View Customer Details");
            System.out.println("5. Reset Billing Cycle");
            System.out.println("6. Update Tariff Rate");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addCustomer();
                case 2 -> recordUsage();
                case 3 -> generateBills();
                case 4 -> viewCustomers();
                case 5 -> resetBillingCycle();
                case 6 -> updateTariffRate();
                case 7 -> {
                    System.out.println("Exiting system. Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    static void addCustomer() {
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        int customerId = customers.size() + 1;
        customers.add(new Customer(name, customerId));
        System.out.println("Customer added successfully with ID: " + customerId);
    }

    static void recordUsage() {
        if (customers.isEmpty()) {
            System.out.println("No customers available. Add customers first.");
            return;
        }
        System.out.print("Enter Customer ID: ");
        int customerId = scanner.nextInt();
        Customer customer = findCustomerById(customerId);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }
        System.out.print("Enter units consumed: ");
        int units = scanner.nextInt();
        customer.recordUnits(units);
        System.out.println("Usage recorded successfully for " + customer.getName());
    }

    static void generateBills() {
        if (customers.isEmpty()) {
            System.out.println("No customers available.");
            return;
        }
        System.out.println("\n=== Monthly Bills ===");
        for (Customer customer : customers) {
            customer.calculateBill(tariffPerUnit);
            System.out.println(customer);
        }
    }

    static void viewCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers available.");
            return;
        }
        System.out.println("\n=== Customer Details ===");
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    static void resetBillingCycle() {
        if (customers.isEmpty()) {
            System.out.println("No customers available.");
            return;
        }
        for (Customer customer : customers) {
            customer.resetBillingCycle();
        }
        System.out.println("Billing cycle reset successfully.");
    }

    static void updateTariffRate() {
        System.out.print("Enter new tariff rate per unit: ");
        tariffPerUnit = scanner.nextDouble();
        System.out.println("Tariff rate updated to $" + tariffPerUnit + " per unit.");
    }

    static Customer findCustomerById(int customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId() == customerId) {
                return customer;
            }
        }
        return null;
    }
}
