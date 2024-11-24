# Fetch Project

This project implements a receipt processing system with two APIs:
1. **POST /receipts/process**: Processes a receipt and assigns it a unique ID.
2. **GET /receipts/{id}/points**: Retrieves the points associated with a receipt by its ID.

---

## Getting Started

This project was created using **Spring Boot** and **Java**, following a **Maven** project structure.

A **Dockerfile** has also been included to allow seamless containerization and deployment of the application. 
<br>
I have outlined the steps to build, run and use this project below.

---
### Cloning the Repository

1. Clone this repository to your local machine:
   ```bash
   git clone https://github.com/Abhishek-Sai/Fetch_rewards.git
   cd Fetch_rewards
   
---

### Building the Project (Optional)

The **target** folder already contains the generated JAR file, so you can **SKIP** building the project unless you'd like to regenerate the package.

To rebuild the project: 
1. Clean the project:
   ```bash
   mvn clean
   
2. Package the project:
   ```bash
   mvn package

The JAR file will be created in the target directory.

---

### Running with Docker

1. Build the docker image:
   ```bash
   docker build -t fetch-project .

2. Run the Docker container and expose port 8080:
   ```bash
   docker run -p 8080:8080 fetch-project
   
---

### Using the APIs

You can use the APIs using a tool like **Postman** or **curl**.

1. POST /receipts/process
   - Description: Processes a receipt and assigns it a unique ID.
   - Example Request:
     ```json
        {
        "retailer": "M&M Corner Market",
        "purchaseDate": "2022-03-20",
        "purchaseTime": "14:33",
        "total": 9.00,
        "items": [
           {"shortDescription": "Gatorade", "price": 2.25},
           {"shortDescription": "Gatorade", "price": 2.25},
           {"shortDescription": "Gatorade", "price": 2.25},
           {"shortDescription": "Gatorade", "price": 2.25}
          ]
        }
   - Example Response:
     ```json
        {
          "id" : "25d81142-d090-4265-abbb-558d2f0ad9b"
        }

2. GET /receipts/{id}/points
   - Description: Retrieves the points associated with a receipt.
   - Example Request:
     ```bash
     GET http://localhost:8080/receipts/<The id from previous step>/points
   - Example Response
     ```json
       {
         "points": 28
       }
     
---
## Note

### Certain Assumptions
- If all the given fields in the receipt don't exist then the receipt is not valid and a 400 status is returned along with the reason why the receipt is not valid.
- If any of the fields of the receipt is null then the receipt is not valid and a 400 status is returned along with the reason why the receipt is not valid.
- The purchaseTime can be in the format "hh:mm" or "h:mm" or "hh:m" or "h:m"
- The purchaseDate can be in the format "yyyy-mm-dd" or "yyyy-m-d"
     
