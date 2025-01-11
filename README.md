# Spring Boot API Application

This repository contains a Spring Boot API application for managing student data. It provides endpoints to register, retrieve, and update student details. Additionally, it includes a performance testing strategy and detailed Telegraf integration for monitoring.

## Features

- **POST /api/students/register**: Registers a new student.
- **GET /api/students/{id}**: Retrieves a student's information by their ID.
- **PUT /api/students/{id}**: Updates an existing student's details.

---

## Installation and Setup

### Clone the Repository
To get started, clone the repository using the following command:

```bash
git clone https://github.com/pavanautomationbox/student-api.git

# Prerequisites

Ensure you have the following installed:

- **Java 11 or higher**
- **Maven or Gradle**
- **Docker** (optional, for running the monitoring stack)

---

## Running the Application

1. Navigate to the project directory:
   ```bash
   cd student-api

## Running the Application

1. Navigate to the project directory:
   ```bash
   cd student-api

## Build and Run the Application

2. Build the application using Maven:
   ```bash
   mvn clean install

## Run the Spring Boot Application

3. Run the application:
   ```bash
   mvn spring-boot:run
   The application will be available at http://localhost:8080.

## Performance Testing Strategy

Performance testing is implemented using **Apache JMeter**. The test strategy focuses on the following API endpoints:

- **POST /api/students/register**: Measure registration performance.
- **GET /api/students/{id}**: Evaluate the time to retrieve student details by ID.
- **PUT /api/students/{id}**: Analyze the performance of updating student details.

## Monitoring Architecture

### Key Components:

- **Spring Boot API**: The application generating metrics.
- **Prometheus**: Scrapes and stores application metrics.
- **Telegraf**: Handles both system and application metrics collection.
- **InfluxDB**: Stores system metrics as a time-series database.
- **Grafana**: Visualizes the metrics via dashboards.

The following diagram illustrates the monitoring architecture for the Spring Boot API application:


+------------------------------------------+
|          Spring Boot API                |
| (Application: Register/Retrieve Student)|
+------------------------------------------+
                  |
      Application Metrics (HTTP, Custom)
                  v
          +----------------+
          |  Prometheus    |
          |  (Scraper)     |
          +----------------+
                  |
+---------------------------------------------+
| Push Application Metrics                    |
v                                             |
+------------------+                      +------------+
|  Application     |                      | System     |
|  Metrics (Prom)  |                      | Metrics    |
|                  |                      | (Telegraf) |
+------------------+                      +------------+
                  |                         |
                  +-------------------------+
                          |
                          v
                  +----------------+
                  |   Telegraf     |
                  | (Handles Both  |
                  | Application &  |
                  | System Metrics)|
                  +----------------+
                          |
              Push Metrics to InfluxDB
                          |
                          v
                  +----------------+
                  |   InfluxDB     |
                  | (Time-Series   |
                  |   Database)    |
                  +----------------+
                          |
                          v
                  +----------------+
                  |   Grafana      |
                  | (Visualization |
                  |   Dashboards)  |
                  +----------------+

## Steps to Set Up Monitoring

1. **Install Monitoring Tools**  
   Install the following tools using Docker or a local installation:  
   - **Prometheus**  
   - **Telegraf**  
   - **InfluxDB**  
   - **Grafana**

2. **Configure Telegraf**  
   Set up **Telegraf** to collect:  
   - **System metrics**  
   - **Application metrics**

3. **Integrate Metrics**  
   - Push **application metrics** to **Prometheus**.  
   - Push **system metrics** to **InfluxDB**.

4. **Visualize Metrics**  
   Use **Grafana** to:  
   - Create dashboards  
   - Visualize the performance of your API

