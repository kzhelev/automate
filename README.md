# Vehicle Reports API

A Spring Boot REST API for retrieving vehicle-related information from public registries. The service provides individual report endpoints as well as a combined endpoint that executes multiple checks in parallel.

## Features

* Vehicle VIN report
* Toll vignette validation
* Traffic fines and obligations check
* Parallel execution of all checks through a single endpoint
* Unified response format with error handling
* Extensible architecture designed for easy integration of new report types

The application is built with a high level of abstraction, allowing new checks and external registry integrations to be plugged into the system with minimal changes to the existing codebase.

---

## Available Endpoints

### 1. VIN Report

Retrieves vehicle technical inspection information using a VIN number.

**Endpoint**

```http
GET /vin-report?vin={VIN}
```

**Example**

```http
GET /vin-report?vin=WVWZZZ1JZXW000001
```

**Response**

```json
{
  "vin": "WVWZZZ1JZXW000001",
  "nextTechnicalTestDate": "2026-08-24",
  "lastTechnicalTestDate": "2025-08-24",
  "lastCheckKmCovered": "193920",
  "errorCode": 0,
  "errorMessage": null
}
```

---

### 2. Traffic Fines Report

Checks for outstanding traffic fines and obligations.

**Endpoint**

```http
GET /traffic-fines-report?idn={IDN}&drivingLicenseNumber={LICENSE_NUMBER}
```

**Example**

```http
GET /traffic-fines-report?idn=1234567890&drivingLicenseNumber=123456789
```

**Response**

```json
{
  "idn": "1234567890",
  "hasObligations": false,
  "errorCode": 0,
  "errorMessage": null
}
```

---

### 3. Toll Report

Checks whether a vehicle has a valid toll vignette.

**Endpoint**

```http
GET /toll-report?licensePlateNumber={PLATE_NUMBER}
```

**Example**

```http
GET /toll-report?licensePlateNumber=PB1234AB
```

**Response**

```json
{
  "licensePlateNumber": "PB1234AB",
  "validToDate": "2027-04-05T23:59:59",
  "hasVignette": true,
  "errorCode": 0,
  "errorMessage": null
}
```

---

## Full Check

Executes all available checks in parallel and returns a combined response.

**Endpoint**

```http
POST /full-check
```

### Request Body

```json
{
  "TOLL_REQUEST": {
    "licensePlateNumber": "PB1234AB"
  },
  "TRAFFIC_FINES_REQUEST": {
    "idn": "1234567890",
    "drivingLicenseNumber": "123456789"
  },
  "VIN_REQUEST": {
    "vin": "WVWZZZ1JZXW000001"
  }
}
```

### Response

```json
{
  "tollReport": {
    "licensePlateNumber": "PB1234AB",
    "validToDate": "2027-04-05T23:59:59",
    "hasVignette": true,
    "errorCode": 0,
    "errorMessage": null
  },
  "trafficFinesReport": {
    "idn": "1234567890",
    "hasObligations": false,
    "errorCode": 0,
    "errorMessage": null
  },
  "vinReport": {
    "vin": "WVWZZZ1JZXW000001",
    "nextTechnicalTestDate": "2026-08-24",
    "lastTechnicalTestDate": "2025-08-24",
    "lastCheckKmCovered": "193920",
    "errorCode": 0,
    "errorMessage": null
  }
}
```

---

## How It Works

The API integrates with publicly available registries and aggregates data into a unified response model.

Each report can be executed:

* Individually through dedicated REST endpoints.
* Simultaneously through the `/full-check` endpoint, where all requests are processed in parallel for improved performance.

---

## Error Handling

Every report contains the following fields:

```json
{
  "errorCode": 0,
  "errorMessage": null
}
```

* `errorCode = 0` indicates successful execution.
* Non-zero values indicate an error during data retrieval or processing.
* `errorMessage` contains additional error details when available.

---

## Disclaimer

The API retrieves information from publicly available registries. The integrations with these services rely on unofficial APIs of the respective institutions. This project is intended strictly for educational purposes and is not intended for commercial use. Data availability and accuracy depend on external providers.
