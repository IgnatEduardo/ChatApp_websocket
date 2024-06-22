# Chat Application

## Description:

Chat App is a real-time one-to-one messaging application designed to provide seamless communication between users. Built using Spring Boot and WebSockets, this application ensures instant delivery of messages, providing a smooth and interactive chatting experience. MongoDB is used to store messages, ensuring that users can access their chat history at any time. The front-end is crafted with JavaScript, HTML, and CSS to offer a user-friendly interface.

## Overview

The Chat App is designed to provide real-time messaging functionality using Websockets for efficient communication between users. Users can log in with a nickname and full name, see other connected users, and start one-to-one chats. The backend, built with Spring Boot, handles user management, message storage, and WebSocket connections. The frontend provides an intuitive interface for sending and receiving messages.

## Demo

https://github.com/IgnatEduardo/ChatApp_websocket/assets/81325426/4aab7947-e225-4bd4-97f2-1ee8e1187ccc


## Flow


![one_to_one_comm](https://github.com/IgnatEduardo/ChatApp_websocket/assets/81325426/a1ab92dd-f4d5-43fb-8ccd-96fd5d649a3c)


![websocket_chat_flow](https://github.com/IgnatEduardo/ChatApp_websocket/assets/81325426/0766da05-be14-4e9a-bc0a-fae1678a3f05)

___

## Technologies

- Websocket
- Spring Boot 3.x.x
- MongoDB
- JS
- HTML & CSS

## Setup

To run the Chat App locally, follow these steps:

### Prerequisites

Ensure you have the following installed on your system:
- **Java Development Kit (JDK) 17** or later
- **Maven** for managing the project's build lifecycle
- **MongoDB** for storing chat data
- **Node.js** and **npm** for managing frontend dependencies (optional, if not using a separate frontend build system)

### Steps

1. **Clone the Repository:**

    ```bash
    git clone https://github.com/IgnatEduardo/ChatApp_websocket/tree/master
    cd chat-app
    ```

2. **Configure MongoDB:**

    Make sure MongoDB is running on its default port (`27017`). If MongoDB is running on a different port or requires authentication, update the `application.properties` file in the `src/main/resources` directory with the correct configuration.

    ```properties
    spring.data.mongodb.host=localhost
    spring.data.mongodb.port=27017
    spring.data.mongodb.database=chatapp
    ```

3. **Build the Application:**

    Use Maven to build the Spring Boot application.

    ```bash
    mvn clean install
    ```

4. **Run the Application:**

    Start the Spring Boot application using Maven.

    ```bash
    mvn spring-boot:run
    ```

    Alternatively, you can run the generated JAR file directly.

    ```bash
    java -jar target/chat-app-0.0.1-SNAPSHOT.jar
    ```

5. **Access the Application:**

    Open your web browser and navigate to `http://localhost:8080`. You should see the login page of the Chat App.
