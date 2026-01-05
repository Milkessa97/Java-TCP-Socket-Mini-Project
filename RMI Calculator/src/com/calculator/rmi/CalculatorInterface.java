package com.calculator.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Remote interface for calculator operations.
 * This interface defines all calculator methods that can be invoked remotely via RMI.
 */
public interface CalculatorInterface extends Remote {

    double add(double a, double b) throws RemoteException;
    double subtract(double a, double b) throws RemoteException;
    double multiply(double a, double b) throws RemoteException;
    double divide(double a, double b) throws RemoteException;
    double power(double base, double exponent) throws RemoteException;
    double squareRoot(double a) throws RemoteException;
    double modulo(double a, double b) throws RemoteException;
}
