
# Mobile Price Range Prediction

## Overview

This project predicts the price range for mobile devices based on various specifications. It uses a machine learning model trained on device features, allowing users to predict the price category for new devices by interacting with an API.

## Project Structure

- **Device.java**: Entity representing a mobile device with its specifications.
- **DeviceController.java**: Handles API requests related to mobile device management.
- **DeviceService.java**: Contains the business logic for managing devices.
- **DeviceRepository.java**: Repository interface for interacting with the database.
- **DeviceApplication.java**: Main application class to start the Spring Boot application.
- **AppConfig.java**: Configuration file for setting up services.
- **EDA.ipynb**: Jupyter notebook used for Exploratory Data Analysis (EDA) of the dataset.
- **preprocessing.ipynb**: Jupyter notebook for data preprocessing, including feature selection and scaling.
- **Predict_Api.py**: Flask API to predict mobile device price ranges using a trained Support Vector Machine (SVM) model.

## Swagger API documentation
- http://localhost:8080/swagger-ui/index.html

## Setup Instructions

### Prerequisites

- Java 11+
- Maven
- Python 3.8+
- Flask
- Scikit-learn
- Joblib
- Pandas
- Numpy
- Jupyter

### Steps to Run

#### 1. Backend (Spring Boot Application)

1. Clone the repository.
2. Navigate to the project directory.
3. Run the Spring Boot application:

   ```bash
   mvn spring-boot:run
   ```

   This starts the API for managing mobile device data.

#### 2. Prediction API (Flask)

1. Navigate to the `Predict_Api.py` file directory.
2. Install required Python packages:

   ```bash
   pip install flask scikit-learn joblib pandas numpy
   ```

3. Run the Flask server:

   ```bash
   python Predict_Api.py
   ```

   The API runs on `localhost:5000`.

### API Endpoints

- **POST `/api/predict/{id}`**: Accepts a JSON object with mobile device specifications (without `priceRange` and `id`) and returns the predicted price range.

   - Description: Predict the price range for a mobile device.
   - Response: A JSON object with the device details and predicted price range.
   - Status Code: 200 OK
   - Example Response:

   ```json
   {
      "batteryPower": 842,
      "blue": 0,
      "clockSpeed": 2.2,
      "dualSim": 1,
      "fc": 1,
      "fourG": 0,
      "intMemory": 7,
      "mDep": 0.6,
      "mobileWt": 188,
      "nCores": 2,
      "pc": 2,
      "pxHeight": 20,
      "pxWidth": 756,
      "ram": 2549,
      "scH": 9,
      "scW": 7,
      "talkTime": 19,
      "threeG": 0,
      "touchScreen": 0,
      "wifi": 1,
      "priceRange": 2
   }
   ```

- **GET `/api/devices`**:
   - Description: Retrieve all devices.
   - Response: A list of devices.
   - Status Code: 200 OK
   - Example Response:
   ```java
   [
      {
         "id": 1,
         "batteryPower": 842,
         "blue": 0,
         "clockSpeed": 2.2,
         "dualSim": 0,
         "fc": 1,
         "fourG": 0.0,
         "intMemory": 7,
         "mDep": 0.6,
         "mobileWt": 188,
         "nCores": 2,
         "pc": 2,
         "pxHeight": 20,
         "pxWidth": 756,
         "ram": 2549,
         "scH": 9,
         "scW": 7,
         "talkTime": 19,
         "threeG": 0,
         "touchScreen": 0,
         "wifi": 1,
         "priceRange": 1
      },
      {
         "id": 2,
         "batteryPower": 1021,
         "blue": 1,
         "clockSpeed": 0.5,
         "dualSim": 1,
         "fc": 0,
         "fourG": 1.0,
         "intMemory": 53,
         "mDep": 0.7,
         "mobileWt": 136,
         "nCores": 3,
         "pc": 6,
         "pxHeight": 905,
         "pxWidth": 1988,
         "ram": 2631,
         "scH": 17,
         "scW": 3,
         "talkTime": 7,
         "threeG": 1,
         "touchScreen": 1,
         "wifi": 0,
         "priceRange": 2
      },
      {
         "id": 3,
         "batteryPower": 563,
         "blue": 1,
         "clockSpeed": 0.5,
         "dualSim": 1,
         "fc": 2,
         "fourG": 1.0,
         "intMemory": 41,
         "mDep": 0.9,
         "mobileWt": 145,
         "nCores": 5,
         "pc": 6,
         "pxHeight": 1263,
         "pxWidth": 1716,
         "ram": 2603,
         "scH": 11,
         "scW": 2,
         "talkTime": 9,
         "threeG": 1,
         "touchScreen": 1,
         "wifi": 0,
         "priceRange": 2
      },
      {
         "id": 4,
         "batteryPower": 615,
         "blue": 1,
         "clockSpeed": 2.5,
         "dualSim": 0,
         "fc": 0,
         "fourG": 0.0,
         "intMemory": 10,
         "mDep": 0.8,
         "mobileWt": 131,
         "nCores": 6,
         "pc": 9,
         "pxHeight": 1216,
         "pxWidth": 1786,
         "ram": 2769,
         "scH": 16,
         "scW": 8,
         "talkTime": 11,
         "threeG": 1,
         "touchScreen": 0,
         "wifi": 0,
         "priceRange": 2
      },
      {
         "id": 5,
         "batteryPower": 1821,
         "blue": 1,
         "clockSpeed": 1.2,
         "dualSim": 0,
         "fc": 13,
         "fourG": 1.0,
         "intMemory": 44,
         "mDep": 0.6,
         "mobileWt": 141,
         "nCores": 2,
         "pc": 14,
         "pxHeight": 1208,
         "pxWidth": 1212,
         "ram": 1411,
         "scH": 8,
         "scW": 2,
         "talkTime": 15,
         "threeG": 1,
         "touchScreen": 1,
         "wifi": 0,
         "priceRange": 1
      }
   ]
   ```

- **GET `/api/devices/{id}`**:
   - Description: Retrieve a device by its ID.
   - Path Parameter: `id` (Long) – The ID of the device.
   - Response: A single device object.
   - Status Code: 200 OK if the device is found, 404 Not Found if the device is not found.
   - Examlpe Request:
   ```java
     GET /api/devices/2
   ```
   - Example Response:
   ```java
   {
      "id": 2,
      "batteryPower": 1021,
      "blue": 1,
      "clockSpeed": 0.5,
      "dualSim": 1,
      "fc": 0,
      "fourG": 1.0,
      "intMemory": 53,
      "mDep": 0.7,
      "mobileWt": 136,
      "nCores": 3,
      "pc": 6,
      "pxHeight": 905,
      "pxWidth": 1988,
      "ram": 2631,
      "scH": 17,
      "scW": 3,
      "talkTime": 7,
      "threeG": 1,
      "touchScreen": 1,
      "wifi": 0,
      "priceRange": 2
   }
   ```

- **POST `/api/devices`**:
   - Description: Add a new device.
   - Request Body: A JSON object representing the device details.
   - Response: The newly created device object.
   - Status Code: 201 Created
   - Examlpe Request:
   ```java
   {
      "batteryPower": 596,
      "blue": 0,
      "clockSpeed": 2.1,
      "dualSim": 1,
      "fc": 9,
      "fourG": 0.0,
      "intMemory": 64,
      "mDep": 0.8,
      "mobileWt": 111,
      "nCores": 8,
      "pc": 15,
      "pxHeight": 885,
      "pxWidth": 1854,
      "ram": 3238,
      "scH": 16,
      "scW": 13,
      "talkTime": 10,
      "threeG": 0,
      "touchScreen": 0,
      "wifi": 0,
      "priceRange": 3
   }
   ```
   - Example Response:
   ```java
   {
      "id": 6,
      "batteryPower": 596,
      "blue": 0,
      "clockSpeed": 2.1,
      "dualSim": 1,
      "fc": 9,
      "fourG": 0.0,
      "intMemory": 64,
      "mDep": 0.8,
      "mobileWt": 111,
      "nCores": 8,
      "pc": 15,
      "pxHeight": 885,
      "pxWidth": 1854,
      "ram": 3238,
      "scH": 16,
      "scW": 13,
      "talkTime": 10,
      "threeG": 0,
      "touchScreen": 0,
      "wifi": 0,
      "priceRange": 3
   }
   ```




### Dataset

The dataset used in this project contains features of mobile devices and their corresponding price range, which is used to train the SVM model. The dataset is preprocessed in the `preprocessing.ipynb` notebook.

### Model

The model is an SVM trained using selected features and scaled using a `StandardScaler`. The model and scaler are saved as `.pkl` files and are loaded when the Flask API runs.