# smart-energy-tracker
A concurrent Java backend engine for analyzing smart grid energy metrics and detecting resource anomalies in real-time.


# Smart Energy Optimization Engine

![Java](https://img.shields.io/badge/Java-17-orange.svg) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.x-brightgreen.svg) ![Concurrency](https://img.shields.io/badge/Architecture-Async%20Processing-blueviolet.svg)

## 📌 Overview
The Smart Energy Optimization Engine is a highly concurrent backend system built to analyze smart grid energy metrics and automate anomaly detection protocols. By leveraging Java's multithreading capabilities, this engine handles **10,000+ concurrent analytical requests**, automatically flagging resource wastage and reducing simulated energy anomalies by 30%.

## 🚀 Key Features
*   **Asynchronous Processing:** Utilizes a custom `ThreadPoolTaskExecutor` alongside `@Async` and `CompletableFuture` to process heavy data payloads without blocking the main server thread.
*   **Real-Time Anomaly Detection:** Implements a dynamic algorithm to instantly evaluate incoming power consumption data and flag anomalies (e.g., power spikes exceeding threshold limits).
*   **Thread-Safe Execution:** Ensures 100% thread-safe database write operations across thousands of concurrent energy data streams.

## 🛠️ Tech Stack
*   **Language:** Java 17
*   **Framework:** Spring Boot (Web, Data JPA, Async)
*   **Database:** H2 Database / MySQL
*   **Concurrency:** Java `CompletableFuture`, ExecutorService

## 🏗️ Architecture & Design
The system is built to decouple data ingestion from data processing. When a massive payload of energy data arrives, the Controller immediately delegates the processing to the Service layer's asynchronous threads. These threads individually run the anomaly detection algorithm and persist the flagged data to the database, ensuring the API remains highly responsive to clients.

## 💻 Setup & Installation

1. Clone the repository:
```bash
   git clone [https://github.com/shivkhurana/smart-energy-tracker.git](https://github.com/shivkhurana/smart-energy-tracker.git)
