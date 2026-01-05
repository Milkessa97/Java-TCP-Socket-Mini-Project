# RMI Calculator Backend

A calculator web application backend built using **Java Remote Method Invocation (RMI)** architecture. This project demonstrates distributed computing by allowing clients to perform calculator operations on a remote server.

## 🏗️ Architecture

The application follows the RMI architecture pattern:

```
┌─────────────┐         RMI          ┌─────────────┐
│   Client    │ ◄─────────────────► │   Server    │
│  (Servlet)  │    Remote Method     │ (Calculator)│
│             │     Invocation       │             │
└─────────────┘                      └─────────────┘
```

### Components

- **RMI Interface** (`CalculatorInterface.java`): Defines remote methods
- **RMI Implementation** (`CalculatorImpl.java`): Implements calculator logic
- **RMI Server** (`CalculatorServer.java`): Hosts the remote object
- **RMI Client** (`CalculatorClient.java`): Command-line client for testing
- **Web Servlet** (`WebCalculatorServlet.java`): HTTP interface to RMI backend

## ✨ Features

- **Basic Operations**: Addition, Subtraction, Multiplication, Division
- **Advanced Operations**: Power, Square Root, Modulo
- **Error Handling**: Division by zero, negative square roots
- **Multiple Interfaces**: 
  - Command-line client
  - Web-based servlet interface
  - JSON API support

## 📋 Prerequisites

- Java Development Kit (JDK) 11 or higher
- Apache Maven 3.6+
- Apache Tomcat 9+ (for servlet deployment)

## 🚀 Quick Start

### 1. Compile the Project

```bash
mvn clean compile
```

### 2. Start the RMI Server

**Windows:**
```bash
start-server.bat
```

**Linux/Mac:**
```bash
java -Djava.security.policy=config/server.policy -cp target/classes com.calculator.server.CalculatorServer
```

You should see:
```
========================================
Calculator RMI Server is ready!
========================================
Service Name: CalculatorService
RMI Port: 1099
Waiting for client connections...
========================================
```

### 3. Run the Client

**Windows:**
```bash
start-client.bat
```

**Linux/Mac:**
```bash
java -Djava.security.policy=config/client.policy -cp target/classes com.calculator.client.CalculatorClient
```

## 🌐 Web Interface (Servlet)

### Deploy to Tomcat

1. Build the WAR file:
   ```bash
   mvn clean package
   ```

2. Copy `target/rmi-calculator-1.0.0.war` to Tomcat's `webapps` directory

3. Start Tomcat and ensure the RMI server is running

4. Access the web interface:
   ```
   http://localhost:8080/rmi-calculator/calculate
   ```

### API Usage

**Endpoint:** `POST /calculate`

**Parameters:**
- `operation`: add, subtract, multiply, divide, power, sqrt, modulo
- `num1`: First number
- `num2`: Second number (not required for sqrt)

**Example Request:**
```bash
curl -X POST http://localhost:8080/rmi-calculator/calculate \
  -d "operation=add&num1=10&num2=5"
```

**Example Response:**
```json
{
  "success": true,
  "operation": "10.0 + 5.0",
  "result": 15.0
}
```

## 📚 API Reference

### Calculator Operations

| Method | Parameters | Description | Example |
|--------|-----------|-------------|---------|
| `add(a, b)` | double a, double b | Addition | `10 + 5 = 15` |
| `subtract(a, b)` | double a, double b | Subtraction | `10 - 5 = 5` |
| `multiply(a, b)` | double a, double b | Multiplication | `10 * 5 = 50` |
| `divide(a, b)` | double a, double b | Division | `10 / 5 = 2` |
| `power(base, exp)` | double base, double exp | Exponentiation | `10 ^ 2 = 100` |
| `squareRoot(a)` | double a | Square root | `sqrt(25) = 5` |
| `modulo(a, b)` | double a, double b | Modulo | `10 % 3 = 1` |

## 🔧 Configuration

### RMI Settings

Default configuration in `CalculatorServer.java`:
- **RMI Port:** 1099
- **Service Name:** CalculatorService
- **Host:** localhost

To change the host or port, modify the constants in the respective classes.

### Security Policies

Security policy files are located in the `config/` directory:
- `server.policy`: Permissions for RMI server
- `client.policy`: Permissions for RMI client

## 🧪 Testing

### Interactive Client Mode

The client provides an interactive command-line interface:

```
========================================
Interactive Calculator Mode
========================================
Available operations:
1. Add
2. Subtract
3. Multiply
4. Divide
5. Power
6. Square Root
7. Modulo
0. Exit
========================================

Select operation (0-7): 1
Enter first number: 10
Enter second number: 5
Result: 10.0 + 5.0 = 15.0
```

## 🐛 Troubleshooting

### Server Won't Start

**Problem:** `Port 1099 already in use`
- **Solution:** Kill existing RMI registry process or change the port

**Problem:** `Access denied` or security exceptions
- **Solution:** Ensure security policy files are correctly configured

### Client Can't Connect

**Problem:** `Connection refused`
- **Solution:** Ensure the RMI server is running before starting the client

**Problem:** `NotBoundException`
- **Solution:** Verify the service name matches in both server and client

### Servlet Issues

**Problem:** Servlet can't connect to RMI server
- **Solution:** Ensure RMI server is running and accessible from Tomcat

## 📁 Project Structure

```
RMI Calculator/
├── src/
│   └── com/
│       └── calculator/
│           ├── rmi/
│           │   ├── CalculatorInterface.java
│           │   └── CalculatorImpl.java
│           ├── server/
│           │   └── CalculatorServer.java
│           ├── client/
│           │   └── CalculatorClient.java
│           └── web/
│               └── WebCalculatorServlet.java
├── config/
│   ├── server.policy
│   └── client.policy
├── pom.xml
├── start-server.bat
├── start-client.bat
└── README.md
```

## 🔐 Security Considerations

> [!WARNING]
> The current security policies grant all permissions for simplicity. In production:
> - Restrict permissions to only what's necessary
> - Use SSL/TLS for RMI communication
> - Implement authentication and authorization
> - Validate all inputs on both client and server

## 📝 License

This project is provided as-is for educational purposes.

## 🤝 Contributing

Feel free to extend this calculator with additional operations:
- Trigonometric functions (sin, cos, tan)
- Logarithmic functions
- Statistical operations
- Memory functions

## 📧 Support

For issues or questions, please refer to the troubleshooting section above.

---

**Built with Java RMI** | **Servlet Integration** | **Distributed Computing**
