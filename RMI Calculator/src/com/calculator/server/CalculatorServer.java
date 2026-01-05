package com.calculator.server;

import com.calculator.rmi.CalculatorImpl;
import com.calculator.rmi.CalculatorInterface;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CalculatorServer {

    public static final String BINDING_NAME = "CalculatorService";
    public static final int RMI_PORT = 1099;

    public static void main(String[] args) {
        try {
            CalculatorInterface calculator = new CalculatorImpl();

            Registry registry = LocateRegistry.createRegistry(RMI_PORT);

            registry.rebind(BINDING_NAME, calculator);

            System.out.println("========================================");
            System.out.println("Calculator RMI Server is ready!");
            System.out.println("========================================");
            System.out.println("Service Name: " + BINDING_NAME);
            System.out.println("RMI Port: " + RMI_PORT);
            System.out.println("Waiting for client connections...");
            System.out.println("========================================");
            Thread.currentThread().join();

        } catch (Exception e) {
            System.err.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
