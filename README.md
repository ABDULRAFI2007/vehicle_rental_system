# 🚗 Vehicle Rental Listing System

A simple vehicle rental listing web application built with **Java Spring Boot** (backend) and **HTML/CSS/JavaScript** (frontend), connected to a **MySQL** database.

Customers can browse bikes and cars, view details, and contact owners. Admins can manage vehicle listings through a dashboard.

---

## 📁 Project Structure

```
vehicle rental system/
├── pom.xml                             # Maven config
├── database/
│   └── vehicle_rental.sql              # MySQL setup script
├── frontend/
│   ├── index.html                      # Homepage
│   ├── vehicles.html                   # Vehicle listing
│   ├── vehicle-details.html            # Vehicle details
│   ├── admin-login.html                # Admin login
│   ├── admin-dashboard.html            # Admin dashboard
│   ├── add-vehicle.html                # Add vehicle form
│   ├── edit-vehicle.html               # Edit vehicle form
│   ├── css/
│   │   └── style.css                   # Global styles
│   └── js/
│       └── api.js                      # API calls + utilities
└── src/main/
    ├── resources/
    │   └── application.properties      # Spring Boot config
    └── java/com/example/vehiclerental/
        ├── VehicleRentalApplication.java   # Main class + data seeder
        ├── config/
        │   └── WebConfig.java              # CORS config
        ├── controller/
        │   ├── VehicleController.java      # Vehicle REST APIs
        │   └── AdminController.java        # Admin login API
        ├── service/
        │   ├── VehicleService.java         # Business logic
        │   └── AdminService.java           # Auth logic
        ├── repository/
        │   ├── VehicleRepository.java      # JPA repository
        │   └── AdminRepository.java        # JPA repository
        ├── entity/
        │   ├── Vehicle.java                # Vehicle entity
        │   └── Admin.java                  # Admin entity
        ├── dto/
        │   ├── VehicleDTO.java             # Vehicle DTO
        │   └── AdminLoginDTO.java          # Login DTO
        └── exception/
            ├── GlobalExceptionHandler.java # Error handling
            ├── ResourceNotFoundException.java
            └── UnauthorizedException.java
```

---

## 🛠️ Prerequisites

1. **Java 17+** — [Download JDK](https://adoptium.net/)
2. **Maven 3.8+** — [Download Maven](https://maven.apache.org/download.cgi)
3. **MySQL 8.0+** — [Download MySQL](https://dev.mysql.com/downloads/)

---

## 🚀 How to Run

### Step 1: Set Up MySQL Database

Open MySQL command line or MySQL Workbench and run:

```sql
CREATE DATABASE IF NOT EXISTS vehicle_rental;
```

> The tables and sample data are auto-created by Spring Boot on startup (using `spring.jpa.hibernate.ddl-auto=update` and the `CommandLineRunner` in the main class).

**Alternatively**, you can manually run the full SQL script:
```bash
mysql -u root -p < database/vehicle_rental.sql
```

### Step 2: Configure Database Password

Edit `src/main/resources/application.properties`:

```properties
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD
```

### Step 3: Run the Spring Boot Backend

```bash
cd "vehicle rental system"
mvn spring-boot:run
```

The backend will start at **http://localhost:8080**.

> On first run, it will create tables and seed sample data (8 vehicles + 1 admin).

### Step 4: Open the Frontend

Simply open `frontend/index.html` in your browser.

> Or use a simple HTTP server:
> ```bash
> cd frontend
> npx -y serve .
> ```

---

## 🔑 Admin Login Credentials

| Username | Password   |
|----------|------------|
| `admin`  | `admin123` |

---

## 📡 REST API Endpoints

### Customer APIs (Public)

| Method | Endpoint                   | Description             |
|--------|----------------------------|-------------------------|
| GET    | `/api/vehicles`            | Get all vehicles        |
| GET    | `/api/vehicles/available`  | Get available vehicles  |
| GET    | `/api/vehicles/{id}`       | Get vehicle by ID       |
| GET    | `/api/vehicles/type/{type}`| Get vehicles by type    |
| GET    | `/api/vehicles/search?q=`  | Search vehicles         |

### Admin APIs

| Method | Endpoint                          | Description              |
|--------|-----------------------------------|--------------------------|
| POST   | `/api/admin/login`                | Admin login              |
| POST   | `/api/vehicles`                   | Add new vehicle          |
| PUT    | `/api/vehicles/{id}`              | Update vehicle           |
| DELETE | `/api/vehicles/{id}`              | Delete vehicle           |
| PATCH  | `/api/vehicles/{id}/availability` | Toggle availability      |

---

## 🔄 How It Works: Frontend → Backend → Database

```
┌─────────────────┐     fetch()      ┌────────────────────┐      JPA/Hibernate     ┌─────────┐
│   HTML/CSS/JS   │ ──────────────▶  │  Spring Boot REST  │ ──────────────────────▶ │  MySQL  │
│   (Frontend)    │ ◀──────────────  │  API (Port 8080)   │ ◀────────────────────── │   DB    │
│                 │     JSON          │                    │       SQL Queries       │         │
└─────────────────┘                  └────────────────────┘                         └─────────┘
```

1. **Frontend** → HTML pages use JavaScript `fetch()` to call the Spring Boot REST API
2. **Backend** → Spring Boot controllers receive the request, call services, which use JPA repositories
3. **Database** → JPA/Hibernate translates repository calls into SQL queries against MySQL
4. **Response** → Data flows back as JSON, and JavaScript renders it on the page

---

## 📋 Sample Vehicle Data

| Brand          | Model         | Type | Price/Day | Location            |
|----------------|---------------|------|-----------|---------------------|
| Honda          | Activa 6G     | Bike | ₹400      | Kattur, Trichy      |
| Royal Enfield  | Classic 350   | Bike | ₹800      | Srirangam, Trichy   |
| TVS            | Apache RTR 200| Bike | ₹600      | Thillai Nagar, Trichy|
| Maruti Suzuki  | Swift Dzire   | Car  | ₹1500     | Cantonment, Trichy  |
| Hyundai        | i20           | Car  | ₹1800     | KK Nagar, Trichy    |
| Toyota         | Innova Crysta | Car  | ₹3000     | Woraiyur, Trichy    |
| Yamaha         | FZ-S V3       | Bike | ₹500      | Anna Nagar, Trichy  |
| Honda          | City          | Car  | ₹2000     | Puthur, Trichy      |

---

## ⚠️ Notes

- This is a **demo/academic** project. Admin auth is simple (no JWT/OAuth).
- Vehicle images use Unsplash URLs. In production, implement file upload.
- No booking, payment, or customer registration — it's a **listing-only** platform.
- The frontend communicates with the backend via CORS-enabled REST APIs.

---

## 📜 License

This project is for educational/academic purposes.
