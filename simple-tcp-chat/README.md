# Simple TCP Chat Application

A lightweight, real-time chat application built with pure Java sockets. This application enables one-to-one communication between a server and a client over TCP/IP protocol.

## 🚀 Features

- **Real-time Messaging**: Instant message delivery between server and client
- **Bidirectional Communication**: Both server and client can send and receive messages simultaneously
- **Custom Usernames**: Users can set their own display names
- **Pure Java Implementation**: No external dependencies or frameworks required
- **Multi-threaded**: Separate threads for sending and receiving messages for smooth user experience

## 📋 Prerequisites

- Java Development Kit (JDK) 8 or higher
- Terminal/Command Prompt access

## 🔧 Installation

### Clone from GitHub

```bash
git clone https://github.com/yourusername/simple-tcp-chat.git
cd simple-tcp-chat
```

> **Note**: Replace `yourusername` with your actual GitHub username once you've uploaded the repository.

## 🏗️ Project Structure

```
simple-tcp-chat/
├── src/
│   ├── ChatServer.java    # Server-side implementation
│   └── ChatClient.java    # Client-side implementation
├── bin/                   # Compiled .class files (generated after compilation)
└── README.md             # This file
```

## 💻 How to Use

### Step 1: Compile the Application

Navigate to the `src` directory and compile both Java files:

```bash
cd src
javac ChatServer.java ChatClient.java
```

This will generate `ChatServer.class` and `ChatClient.class` files in the same directory.

### Step 2: Run the Server

Open a new terminal window and start the server:

```bash
java ChatServer
```

You'll be prompted to enter a name for the server side. For example:

```
Enter your name (Server side): Alice
Server started. Waiting for a single client to connect...
```

### Step 3: Run the Client(s)

Open another terminal window and start a client:

```bash
java ChatClient
```

You'll be prompted to enter a name for the client side. For example:

```
Enter your name (Client side): Bob
```

Once connected, both terminals will display a confirmation message:

```
System: Connected to [other user's name]
```

### Step 4: Start Chatting!

- Type your message in either terminal and press Enter to send
- Messages will appear in real-time on both sides
- The format is: `[Username]: [Message]`

## 📝 Example Usage

**Terminal 1 (Server):**
```
Enter your name (Server side): Alice
Server started. Waiting for a single client to connect...
Client detected! Establishing secure 1-to-1 link...
System: Connected to Bob
Bob: Hey Alice, how are you?
Good! How about you?
```

**Terminal 2 (Client):**
```
Enter your name (Client side): Bob
System: Connected to Alice
Hey Alice, how are you?
Alice: Good! How about you?
```

## 🔍 How It Works

### Server (`ChatServer.java`)
1. Creates a `ServerSocket` listening on port 5000
2. Waits for a client connection
3. Exchanges usernames with the connected client
4. Spawns a separate thread to listen for incoming messages
5. Main thread handles sending messages to the client

### Client (`ChatClient.java`)
1. Connects to the server at `localhost:5000`
2. Exchanges usernames with the server
3. Spawns a separate thread to listen for incoming messages from the server
4. Main thread handles sending messages to the server

### Communication Flow
- **Port**: 5000
- **Protocol**: TCP/IP
- **Connection Type**: One-to-one (single client per server instance)
- **Message Format**: Plain text with newline delimiters

## ⚙️ Technical Details

- **Language**: Java
- **Networking**: `java.net.Socket` and `java.net.ServerSocket`
- **I/O**: `BufferedReader`, `PrintWriter`, `InputStreamReader`
- **Concurrency**: Java Threads for simultaneous send/receive operations
- **Default Port**: 5000
- **Host**: localhost (127.0.0.1)

## 🛠️ Customization

### Change the Port Number

In both `ChatServer.java` and `ChatClient.java`, modify the port number:

```java
// ChatServer.java (line 13)
ServerSocket serverSocket = new ServerSocket(5000); // Change 5000 to your desired port

// ChatClient.java (line 11)
Socket socket = new Socket("localhost", 5000); // Change 5000 to match server port
```

### Connect Over Network

To connect from a different machine, replace `"localhost"` in `ChatClient.java` with the server's IP address:

```java
Socket socket = new Socket("192.168.1.100", 5000); // Replace with actual server IP
```

## 🐛 Troubleshooting

### Port Already in Use
If you see `Address already in use` error:
- Make sure no other application is using port 5000
- Wait a few seconds and try again
- Or change the port number in both files

### Connection Refused
If the client can't connect:
- Ensure the server is running before starting the client
- Check that both are using the same port number
- Verify firewall settings aren't blocking the connection

### Messages Not Appearing
- Check that you're pressing Enter after typing your message
- Ensure both applications are still running
- Verify the connection wasn't interrupted

## 📄 License

This project is open source and available for educational purposes.

## 🤝 Contributing

Feel free to fork this project and submit pull requests for any improvements!

## 📧 Contact

For questions or suggestions, please open an issue on GitHub.

---

**Happy Chatting! 💬**
