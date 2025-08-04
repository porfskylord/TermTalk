# 🔨 TermTalk

A lightweight, terminal-based chat system built in Java — inspired by retro mail apps.
Run the JAR on any system and chat instantly over a socket connection. Think of it as **your own terminal messenger**.

---

## 🎮 UI Preview

```
▄▄▄█████▓▓█████  ██▀███   ███▄ ▄███▓▄▄▄█████▓ ▄▄▄       ██▓     ██ ▄█▀  
▓  ██▒ ▓▒▓█   ▀ ▓██ ▒ ██▒▓██▒▀█▀ ██▒▓  ██▒ ▓▒▒████▄    ▓██▒     ██▄█▒   
▒ ▓██░ ▒░▒███   ▓██ ░▄█ ▒▓██    ▓██░▒ ▓██░ ▒░▒██  ▀█▄  ▒██░    ▓███▄░   
░ ▓██▓ ░ ▒▓█  ▄ ▒██▀▀█▄  ▒██    ▒██ ░ ▓██▓ ░ ░██▄▄▄▄██ ▒██░    ▓██ █▄   
  ▒██▒ ░ ░▒████▒░██▓ ▒██▒▒██▒   ░██▒  ▒██▒ ░  ▓█   ▓██▒░██████▒▒██▒ █▄  
  ▒ ░░   ░░ ▒░ ░░ ▒▓ ░▒▓░░ ▒░   ░  ░  ▒ ░░    ▒▒   ▓▒█░░ ▒░▓  ░▒ ▒▒ ▓▒  
    ░     ░ ░  ░  ░▒ ░ ▒░░  ░      ░    ░      ▒   ▒▒ ░░ ░ ▒  ░░ ░▒ ▒░  
  ░         ░     ░░   ░ ░      ░     ░        ░   ▒     ░ ░   ░ ░░ ░   
            ░  ░   ░            ░                  ░  ░    ░  ░░  ░     
-<>--<>--<>--<>--<>--<>--<>--<>--<>--<>--<>--<>--<>--<>--<>--<>--<>--<>-
```

---

## 📦 Tech Stack

* **Java 21**
* **Maven** (build tool)
* **PostgreSQL** (for user & message storage)
* **Multi-threaded Socket Server**

---

## 🏗️ Project Structure

```
TermTalk/
├── client/          # Client module
│   └── src/
│       └── main/
│           └── java/
│               └── com/lordscave/client/
│                   ├── ConsoleUI.java
│                   ├── MessageReceiver.java
│                   └── TermTalkClient.java
├── server/          # Server module
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       │   └── com/lordscave/server/
│   │       │       ├── ClientHandler.java
│   │       │       ├── DBManager.java
│   │       │       └── TermTalkServer.java
│   │       └── resources/
│   │           └── config.properties
│   └── pom.xml
├── shared/          # Shared module
│   └── src/
│       └── main/
│           └── java/
│               └── com/lordscave/shared/
│                   └── (shared classes)
├── .gitignore
├── pom.xml          # Parent POM
└── README.md
```

---

## 🛠️ How to Build and Run

### Prerequisites
- Java 21 or later
- Maven 3.6.0 or later
- PostgreSQL 12 or later

### 1. Build the Project

```bash
# Clone the repository
git clone <repository-url>
cd TermTalk

# Build all modules
mvn clean package
```

### 2. Configure the Server

1. Create a PostgreSQL database and update the configuration in:
   ```
   server/src/main/resources/config.properties
   ```

2. Configure the following properties:
   ```properties
   db.url=jdbc:postgresql://localhost:5432/your_database
   db.user=your_username
   db.password=your_password
   ```

### 3. Start the Server

```bash
cd server/target
java -jar TermTalk-Server-1.0-SNAPSHOT.jar
```

The server will start on port `5656` by default.

### 4. Start Clients

Open a new terminal for each client:

```bash
cd client/target
java -jar TermTalk-Client-1.0-SNAPSHOT.jar
```

### 5. Using the Client

1. **Register** a new account or **Login** with existing credentials
2. **Send messages** to other users by their username
3. **View your inbox** to see received messages
4. **Exit** the application when done

---

## ✨ Features

* 📥 Inbox-style message delivery
* 🔐 Secure password hashing (SHA-256)
* 🧵 Handles multiple users using threads
* 🆔 Unique User IDs
* 💻 CLI-Only interface
* 🚀 Easy to deploy and run

---

## 🔐 Security

* Passwords are **hashed with SHA-256**
* All message timestamps are logged
* Server validates all incoming messages
* Clean session management

---

## 🧑‍💻 Author

Made with ☕ and ❤️ by Azad

---

## 📜 License

MIT License – free to use, modify, and distribute.
