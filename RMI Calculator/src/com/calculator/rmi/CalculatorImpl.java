package com.calculator.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Implementation of the CalculatorInterface.
 * This class provides the actual calculator logic that will be executed remotely.
 */
public class CalculatorImpl extends UnicastRemoteObject implements CalculatorInterface {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructor that exports the remote object
     * @throws RemoteException if object export fails
     */
    public CalculatorImpl() throws RemoteException {
        super();
    }
    
    @Override
    public double add(double a, double b) throws RemoteException {
        System.out.println("Executing: " + a + " + " + b);
        return a + b;
    }
    
    @Override
    public double subtract(double a, double b) throws RemoteException {
        System.out.println("Executing: " + a + " - " + b);
        return a - b;
    }
    
    @Override
    public double multiply(double a, double b) throws RemoteException {
        System.out.println("Executing: " + a + " * " + b);
        return a * b;
    }
    
    @Override
    public double divide(double a, double b) throws RemoteException {
        if (b == 0) {
            throw new ArithmeticException("Division by zero is not allowed");
        }
        System.out.println("Executing: " + a + " / " + b);
        return a / b;
    }
    
    @Override
    public double power(double base, double exponent) throws RemoteException {
        System.out.println("Executing: " + base + " ^ " + exponent);
        return Math.pow(base, exponent);
    }
    
    @Override
    public double squareRoot(double a) throws RemoteException {
        if (a < 0) {
            throw new ArithmeticException("Cannot calculate square root of negative number");
        }
        System.out.println("Executing: sqrt(" + a + ")");
        return Math.sqrt(a);
    }
    
    @Override
    public double modulo(double a, double b) throws RemoteException {
        if (b == 0) {
            throw new ArithmeticException("Modulo by zero is not allowed");
        }
        System.out.println("Executing: " + a + " % " + b);
        return a % b;
    }
}
