# CurrencyTrend

<!-- LOGO DEL PROYECTO -->
<br />
<div align="center">
  <a href="#">
    <img src="https://kotlinlang.org/docs/images/kotlin-logo.png" alt="Logo" width="700">
  </a>
</div>

## Acerca del Proyecto

`CurrencyTrend` es una aplicación desarrollada en **Kotlin** utilizando **Ktor**, diseñada para demostrar habilidades avanzadas en desarrollo backend. La aplicación interactúa con una API externa de datos de divisas para:

- Realizar conversiones precisas entre monedas.
- Analizar tendencias de una moneda frente a otra durante un período de tiempo definido.

Este proyecto sirve como una vitrina práctica de mis conocimientos en programación del lado del servidor y la integración con APIs externas.

---

## Características

- **Consumo de APIs externas**:
    - Integración con `https://api.frankfurter.app` para obtener datos de divisas.
    - Endpoints disponibles:
        - `/getTrend`: Calcula la tendencia de una moneda en un rango de fechas.
        - `/getConversion`: Realiza conversiones entre monedas.

- **Serialización y deserialización JSON**:
    - Uso de `kotlinx.serialization` para manejar datos de forma eficiente.

- **Configuración centralizada**:
    - Gestión de propiedades a través de `application.conf`.


---

## Tecnologías Utilizadas

- **Lenguaje**: [Kotlin](https://kotlinlang.org/)
- **Framework**: [Ktor](https://ktor.io/)
- **Cliente HTTP**: Ktor Client (CIO)
- **Serialización**: `kotlinx.serialization`
- **Configuración**: `application.conf`

---

## Instalación

### Prerrequisitos

1. [JDK 17+](https://adoptium.net/)
2. [Gradle](https://gradle.org/install/)
3. [IntelliJ IDEA](https://www.jetbrains.com/idea/)

### Pasos

1. Clona el repositorio:
   ```bash
   git clone https://github.com/Thecode42/CurrencyTrend.git
   cd CurrencyTrend
2. Inicia la aplicación:
   ```bash
   ./gradlew run

### Uso
### Endpoints Disponibles

1. POST /api/getTrend:
   - **Descripción:** Calcula la tendencia de una moneda en un rango de fechas.
     - **Cuerpo de la Solicitud:**
        ```bash
              {
                  "base": "USD",
                  "symbol": "CAD",
                  "start_date": "2024-01-01",
                  "end_date": "2024-12-27"
              }
     - **Respuesta:**
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
    - **Descripción:** Convierte un monto de una moneda a otra.
        - **Cuerpo de la Solicitud:**
           ```bash
                 {
                     "base": "USD",
                     "symbol": "CAD",
                     "amount": 100.00
                 }
        - **Respuesta:**
             ```bash
               {
                    "base": "USD",
                    "symbol": "CAD",
                    "result": "143.77"
               }
### Estructura del Proyecto
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

## Licencia

Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo `LICENSE` para más detalles.
