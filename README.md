# 🔨 TermTalk

A lightweight, terminal-based chat system built in Java — inspired by retro mail apps and gaming-style user IDs (like PUBG/CoC).
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

## 🛠️ How to Run

### 💻 Server

1. Build using Maven:

   ```bash
   mvn clean package
   ```

2. Run the server:

   ```bash
   java -cp target/termtalk.jar com.lordscave.server.TermTalkServer
   ```

3. Server listens on port `5656`

---

### 👤 Client

1. Build the client JAR or use existing one.
2. Run:

   ```bash
   java -cp target/termtalk.jar com.lordscave.client.TermTalkClient
   ```

---

## ✉️ Features

* 📥 Inbox-style message delivery
* 🔐 Secure password hashing (SHA-256)
* 🥵 Handles multiple users using threads
* 🆔 Unique User IDs like `akr3024`, `azd8743`
* ✨ CLI-Only, no dependencies on UI frameworks

---

## 🔐 Security

* Passwords are **hashed with SHA-256**
* All message timestamps are logged
* Server ignores undelivered messages after marking them delivered

---

## 🧑‍💻 Author

Made with ☕ and ❤️ by Azad
Project package root: `com.lordscave.termtalk`

---

## 📜 License

MIT License – free to use, modify, and distribute.
