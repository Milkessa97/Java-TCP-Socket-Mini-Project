package com.calculator.web;

import com.calculator.rmi.CalculatorInterface;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Web Servlet that bridges HTTP requests to the RMI Calculator backend.
 * This servlet accepts calculation requests via HTTP and invokes remote
 * methods.
 */
@WebServlet("/calculate")
public class WebCalculatorServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String SERVER_HOST = "localhost";
    private static final int RMI_PORT = 1099;
    private static final String BINDING_NAME = "CalculatorService";

    private CalculatorInterface calculator;

    @Override
    public void init() throws ServletException {
        try {
            // Connect to RMI server on servlet initialization
            Registry registry = LocateRegistry.getRegistry(SERVER_HOST, RMI_PORT);
            calculator = (CalculatorInterface) registry.lookup(BINDING_NAME);
            System.out.println("WebCalculatorServlet connected to RMI server");
        } catch (Exception e) {
            throw new ServletException("Failed to connect to RMI server", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>RMI Calculator</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; max-width: 600px; margin: 50px auto; padding: 20px; }");
        out.println("h1 { color: #333; }");
        out.println(".form-group { margin: 15px 0; }");
        out.println("label { display: inline-block; width: 120px; }");
        out.println("input, select { padding: 8px; width: 200px; }");
        out.println("button { padding: 10px 20px; background: #007bff; color: white; border: none; cursor: pointer; }");
        out.println("button:hover { background: #0056b3; }");
        out.println(".result { margin-top: 20px; padding: 15px; background: #f0f0f0; border-radius: 5px; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>RMI Calculator Web Interface</h1>");
        out.println("<form method='POST'>");
        out.println("<div class='form-group'>");
        out.println("<label>Operation:</label>");
        out.println("<select name='operation'>");
        out.println("<option value='add'>Addition (+)</option>");
        out.println("<option value='subtract'>Subtraction (-)</option>");
        out.println("<option value='multiply'>Multiplication (*)</option>");
        out.println("<option value='divide'>Division (/)</option>");
        out.println("<option value='power'>Power (^)</option>");
        out.println("<option value='sqrt'>Square Root (√)</option>");
        out.println("<option value='modulo'>Modulo (%)</option>");
        out.println("</select>");
        out.println("</div>");
        out.println("<div class='form-group'>");
        out.println("<label>First Number:</label>");
        out.println("<input type='number' name='num1' step='any' required>");
        out.println("</div>");
        out.println("<div class='form-group'>");
        out.println("<label>Second Number:</label>");
        out.println("<input type='number' name='num2' step='any'>");
        out.println("<small>(Not needed for Square Root)</small>");
        out.println("</div>");
        out.println("<div class='form-group'>");
        out.println("<button type='submit'>Calculate</button>");
        out.println("</div>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try {
            String operation = request.getParameter("operation");
            double num1 = Double.parseDouble(request.getParameter("num1"));
            String num2Str = request.getParameter("num2");

            double result = 0;
            String operationSymbol = "";

            switch (operation) {
                case "add":
                    double num2Add = Double.parseDouble(num2Str);
                    result = calculator.add(num1, num2Add);
                    operationSymbol = num1 + " + " + num2Add;
                    break;

                case "subtract":
                    double num2Sub = Double.parseDouble(num2Str);
                    result = calculator.subtract(num1, num2Sub);
                    operationSymbol = num1 + " - " + num2Sub;
                    break;

                case "multiply":
                    double num2Mul = Double.parseDouble(num2Str);
                    result = calculator.multiply(num1, num2Mul);
                    operationSymbol = num1 + " * " + num2Mul;
                    break;

                case "divide":
                    double num2Div = Double.parseDouble(num2Str);
                    result = calculator.divide(num1, num2Div);
                    operationSymbol = num1 + " / " + num2Div;
                    break;

                case "power":
                    double num2Pow = Double.parseDouble(num2Str);
                    result = calculator.power(num1, num2Pow);
                    operationSymbol = num1 + " ^ " + num2Pow;
                    break;

                case "sqrt":
                    result = calculator.squareRoot(num1);
                    operationSymbol = "sqrt(" + num1 + ")";
                    break;

                case "modulo":
                    double num2Mod = Double.parseDouble(num2Str);
                    result = calculator.modulo(num1, num2Mod);
                    operationSymbol = num1 + " % " + num2Mod;
                    break;

                default:
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    out.println("{\"error\": \"Invalid operation\"}");
                    return;
            }

            // Return JSON response
            out.println("{");
            out.println("  \"success\": true,");
            out.println("  \"operation\": \"" + operationSymbol + "\",");
            out.println("  \"result\": " + result);
            out.println("}");

        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("{\"error\": \"Invalid number format\"}");
        } catch (ArithmeticException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("{\"error\": \"" + e.getMessage() + "\"}");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.println("{\"error\": \"Server error: " + e.getMessage() + "\"}");
        }
    }
}
