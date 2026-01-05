package com.calculator.client;

import com.calculator.rmi.CalculatorInterface;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class CalculatorClient {

    private static final String SERVER_HOST = "localhost";
    private static final int RMI_PORT = 1099;
    private static final String BINDING_NAME = "CalculatorService";

    public static void main(String[] args) {
        try {
            // Locate the RMI registry
            Registry registry = LocateRegistry.getRegistry(SERVER_HOST, RMI_PORT);

            // Lookup the remote calculator object
            CalculatorInterface calculator = (CalculatorInterface) registry.lookup(BINDING_NAME);

            System.out.println("========================================");
            System.out.println("Connected to Calculator RMI Server");
            System.out.println("========================================");

            // Demonstrate calculator operations
            demonstrateOperations(calculator);

            // Interactive mode
            interactiveMode(calculator);

        } catch (Exception e) {
            System.err.println("Client exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void demonstrateOperations(CalculatorInterface calculator) {
        try {
            System.out.println("\n--- Demonstrating Calculator Operations ---\n");

            double a = 10.0;
            double b = 5.0;

            System.out.println("Addition: " + a + " + " + b + " = " + calculator.add(a, b));
            System.out.println("Subtraction: " + a + " - " + b + " = " + calculator.subtract(a, b));
            System.out.println("Multiplication: " + a + " * " + b + " = " + calculator.multiply(a, b));
            System.out.println("Division: " + a + " / " + b + " = " + calculator.divide(a, b));
            System.out.println("Power: " + a + " ^ " + b + " = " + calculator.power(a, b));
            System.out.println("Square Root: sqrt(" + a + ") = " + calculator.squareRoot(a));
            System.out.println("Modulo: " + a + " % " + b + " = " + calculator.modulo(a, b));

            System.out.println("\n--- All operations completed successfully! ---\n");

        } catch (Exception e) {
            System.err.println("Error during demonstration: " + e.getMessage());
        }
    }

    private static void interactiveMode(CalculatorInterface calculator) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("========================================");
        System.out.println("Interactive Calculator Mode");
        System.out.println("========================================");
        System.out.println("Available operations:");
        System.out.println("1. Add");
        System.out.println("2. Subtract");
        System.out.println("3. Multiply");
        System.out.println("4. Divide");
        System.out.println("5. Power");
        System.out.println("6. Square Root");
        System.out.println("7. Modulo");
        System.out.println("0. Exit");
        System.out.println("========================================");

        while (running) {
            try {
                System.out.print("\nSelect operation (0-7): ");
                int choice = scanner.nextInt();

                if (choice == 0) {
                    running = false;
                    System.out.println("Goodbye!");
                    continue;
                }

                double a, b, result;

                switch (choice) {
                    case 1:
                        System.out.print("Enter first number: ");
                        a = scanner.nextDouble();
                        System.out.print("Enter second number: ");
                        b = scanner.nextDouble();
                        result = calculator.add(a, b);
                        System.out.println("Result: " + a + " + " + b + " = " + result);
                        break;

                    case 2:
                        System.out.print("Enter first number: ");
                        a = scanner.nextDouble();
                        System.out.print("Enter second number: ");
                        b = scanner.nextDouble();
                        result = calculator.subtract(a, b);
                        System.out.println("Result: " + a + " - " + b + " = " + result);
                        break;

                    case 3:
                        System.out.print("Enter first number: ");
                        a = scanner.nextDouble();
                        System.out.print("Enter second number: ");
                        b = scanner.nextDouble();
                        result = calculator.multiply(a, b);
                        System.out.println("Result: " + a + " * " + b + " = " + result);
                        break;

                    case 4:
                        System.out.print("Enter dividend: ");
                        a = scanner.nextDouble();
                        System.out.print("Enter divisor: ");
                        b = scanner.nextDouble();
                        result = calculator.divide(a, b);
                        System.out.println("Result: " + a + " / " + b + " = " + result);
                        break;

                    case 5:
                        System.out.print("Enter base: ");
                        a = scanner.nextDouble();
                        System.out.print("Enter exponent: ");
                        b = scanner.nextDouble();
                        result = calculator.power(a, b);
                        System.out.println("Result: " + a + " ^ " + b + " = " + result);
                        break;

                    case 6:
                        System.out.print("Enter number: ");
                        a = scanner.nextDouble();
                        result = calculator.squareRoot(a);
                        System.out.println("Result: sqrt(" + a + ") = " + result);
                        break;

                    case 7:
                        System.out.print("Enter dividend: ");
                        a = scanner.nextDouble();
                        System.out.print("Enter divisor: ");
                        b = scanner.nextDouble();
                        result = calculator.modulo(a, b);
                        System.out.println("Result: " + a + " % " + b + " = " + result);
                        break;

                    default:
                        System.out.println("Invalid choice. Please select 0-7.");
                }

            } catch (ArithmeticException e) {
                System.err.println("Calculation error: " + e.getMessage());
                scanner.nextLine(); // Clear buffer
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
                scanner.nextLine(); // Clear buffer
            }
        }

        scanner.close();
    }
}
