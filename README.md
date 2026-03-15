# SAP Inventory Service

Spring Boot WebFlux microservice for managing product inventory. Backed by MongoDB.

## Prerequisites
- Java 17+
- Maven
- MongoDB running on `localhost:27017`

## Setup & Run

```bash
git clone https://github.com/ndhanushkumar/sap-inventory-service.git
cd sap-inventory-service
./mvnw spring-boot:run
```

Runs on **http://localhost:7073**

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | `/sap-inventory/add-item` | Add products to inventory |
| GET | `/sap-inventory/get-all-items` | Get all products |
| POST | `/sap-inventory/inventory-check` | Check stock availability |
| POST | `/sap-inventory/inventory-block` | Reserve stock for an order |
| POST | `/sap-inventory/void-sap-items` | Release reserved stock |

## Swagger UI
Available at: `http://localhost:7073/swagger-ui.html`
