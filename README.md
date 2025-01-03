# CurrencyTrend

<!-- LOGO DEL PROYECTO -->
<br />
<div align="center">
  <a href="#">
    <img src="https://kotlinlang.org/docs/images/kotlin-logo.png" alt="Logo" width="700">
  </a>
</div>

## About The Project

`CurrencyTrend` is a backend application built with **Kotlin** and **Ktor** to showcase advanced backend development skills. This application interacts with an external currency API to:

- Perform accurate currency conversions.
- Analyze currency trends over a defined time range.

This project serves as a practical demonstration of my expertise in server-side programming and API integrations.

---

## Features

- **External API Integration**:
    - Integrated with `https://api.frankfurter.app` for currency data.
    - Available endpoints:
        - `/getTrend`: Calculates currency trends over a date range.
        - `/getConversion`: Performs currency conversions.

- **JSON Serialization**:
    - Efficiently handles data using `kotlinx.serialization`.

- **Centralized Configuration**:
    - Manages properties via `application.conf`.

---

## Technologies Used

- **Language**: [Kotlin](https://kotlinlang.org/)
- **Framework**: [Ktor](https://ktor.io/)
- **HTTP Client**: Ktor Client (CIO)
- **Serialization**: `kotlinx.serialization`
- **Testing**: [MockK](https://mockk.io/), [Kotlin Test](https://kotlinlang.org/docs/kotlin-test.html)
- **Configuration**: `application.conf`

---

## Installation

### Prerequisites

1. [JDK 17+](https://adoptium.net/)
2. [Gradle](https://gradle.org/install/)
3. [IntelliJ IDEA](https://www.jetbrains.com/idea/)

### Steps

1. Clone the repository:
   ```bash
   git clone https://github.com/Thecode42/CurrencyTrend.git
   cd CurrencyTrend
2. Start the application:
   ```bash
   ./gradlew run

### Usage
### Available Endpoints

1. POST /api/getTrend:
   - **Description:** Calculates a currency trend over a date range.
     - **Request Body:**
        ```bash
              {
                  "base": "USD",
                  "symbol": "CAD",
                  "start_date": "2024-01-01",
                  "end_date": "2024-12-27"
              }
     - **Response:**
          ```bash
            {
                 "amount": 1.0,
                 "base": "USD - United States Dollar",
                 "symbol": "CAD - Canadian Dollar",
                 "start_date": "2023-12-29",
                 "end_date": "2024-12-27",
                 "net_change": "0.11260",
                 "result": "Analysis of USD to CAD exchange rate:\nThe USD has grown against the CAD by 8.50% \nNet Change: 0.11260 CAD per USD."
            }
2. POST /api/getConversion:
    - **Description:** Converts an amount from one currency to another.
        - **Request Body:**
           ```bash
                 {
                     "base": "USD",
                     "symbol": "CAD",
                     "amount": 100.00
                 }
        - **Response:**
             ```bash
               {
                    "base": "USD",
                    "symbol": "CAD",
                    "result": "143.77"
               }
### Project Structure
    ```bash
    CurrencyTrend/
    ├── src/
    │   ├── main/
    │   │   ├── kotlin/
    │   │   │   └── com/
    │   │   │       └── currency/
    │   │   │           ├── dto/
    │   │   │           │   ├── ExchangeRangeRequest.kt
    │   │   │           │   ├── ExchangeTrendResponse.kt
    │   │   │           │   └── ExchangeConversionRequest.kt
    │   │   │           ├── routes/
    │   │   │           │   ├── ApiRoutes.kt
    │   │   │           ├── services/
    │   │   │           │   └── TrendApiService.kt
    │   │   │           ├── utils/
    │   │   │           │   └── DateUtils.kt
    │   │   │           └── Application.kt
    │   └── resources/
    │       └── application.conf
    └── test/
        ├── kotlin/
        │   └── com/
        │       └── currency/
        │           └── ApplicationTest.kt
        └── resources/
            └── logback-test.xml

## License

This project is licensed under the MIT License. Please consult the `LICENSE` file for more details.
