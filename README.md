# LastPay Simulation Project

A simplified simulation of LastPay's core workflow using Next.js frontend and Spring Boot backend. This project demonstrates the integration between dealer, insurance, and banking services for car financing.

## 🏗️ Project Structure

```
lastpay-simulation/
├── backend/                 # Spring Boot backend
│   ├── src/main/java/com/lastpay/
│   │   ├── controller/     # REST controllers
│   │   ├── service/        # Business logic services
│   │   ├── model/          # Data models
│   │   └── repository/     # Data access layer
│   └── pom.xml
├── frontend/               # Next.js frontend
│   ├── src/
│   │   ├── app/           # App router pages
│   │   └── components/    # React components
│   ├── package.json
│   └── tailwind.config.js
└── README.md
```

## 🔄 Application Flow

### Frontend Flow (Next.js)
```
User fills form → Submit → API call to backend → Display results
```

**Step-by-step:**
1. User visits form page (`/`)
2. Fills out customer name and selects a car from dropdown
3. Form submits to `/api/request` endpoint on backend
4. Frontend receives combined response and navigates to summary page
5. Summary page displays all results in cards

### Backend Flow (Spring Boot)
```
Receive request → Call mock services → Store in DB → Return combined response
```

**Step-by-step:**
1. `RequestController` receives POST to `/api/request`
2. `RequestService` orchestrates calls to three mock services:
   - `DealerService.getCarDetails()` - Gets car information
   - `InsuranceService.calculatePremium()` - Calculates insurance premium
   - `BankService.processLoan()` - Processes loan approval
3. Creates `RequestEntity` and saves to H2 database
4. Returns combined `RequestResponse` with all data

## 🔧 What's Hardcoded

### Backend Hardcoded Data

#### 1. Car Models (DealerService.java)
```java
// Only 3 predefined cars available
"Toyota Camry" → $25,000, 4-cylinder, automatic
"Honda Civic" → $22,000, 4-cylinder, manual  
"BMW X5" → $55,000, V6, automatic
```

#### 2. Insurance Logic (InsuranceService.java)
```java
// Fixed premium calculation based on car model
Toyota Camry → $1,200/year
Honda Civic → $1,000/year  
BMW X5 → $2,500/year
// No real risk assessment or customer data consideration
```

#### 3. Bank Approval Logic (BankService.java)
```java
// Simple approval rules based only on car price
BMW X5 → Always REJECTED (too expensive)
Toyota Camry & Honda Civic → Always APPROVED
// No credit checks, income verification, or real banking logic
```

#### 4. Database Configuration
```java
// H2 in-memory database
// Data is lost on application restart
// No real persistence or data migration
```

### Frontend Hardcoded Data

#### 1. Car Selection Options
```jsx
// Fixed dropdown options in form
<option value="Toyota Camry">Toyota Camry</option>
<option value="Honda Civic">Honda Civic</option>
<option value="BMW X5">BMW X5</option>
```

#### 2. API Endpoint
```jsx
// Hardcoded backend URL
const response = await fetch('http://localhost:8080/api/request', {
```

## 🎯 Mock vs Real Integration Points

### What's Currently Mocked
- ✅ Dealer API calls (no real car database)
- ✅ Insurance calculations (fixed rates)
- ✅ Bank loan processing (simple approval rules)
- ✅ Database (H2 in-memory)
- ✅ Customer data validation

### What Would Be Real in Production
- 🔄 External dealer APIs for real-time car inventory
- 🔄 Insurance company APIs for actual quotes
- 🔄 Bank APIs for credit checks and loan approval
- 🔄 Persistent database (PostgreSQL, MySQL)
- 🔄 Authentication and user management
- 🔄 Real customer verification and KYC
- 🔄 Payment processing integration
- 🔄 Document management system

## 🚀 Getting Started

### Prerequisites
- Java 17 or higher
- Node.js 18 or higher
- Maven 3.6 or higher

### Backend Setup (Spring Boot)

1. **Navigate to backend directory:**
```bash
cd backend
```

2. **Install dependencies and compile:**
```bash
mvn clean compile
```

3. **Run the application:**
```bash
mvn spring-boot:run
```

The backend will start on `http://localhost:8080`

### Frontend Setup (Next.js)

1. **Navigate to frontend directory:**
```bash
cd frontend
```

2. **Install dependencies:**
```bash
npm install
```

3. **Start development server:**
```bash
npm run dev
```

The frontend will start on `http://localhost:3000`

### Quick Start (Both Services)

You can also use the provided batch file to start both services:
```bash
start-services.bat
```

## 📡 API Endpoints

### Backend REST APIs

#### Main Endpoint
- **POST** `/api/request` - Submit customer request and get combined response

#### Mock Service Endpoints
- **GET** `/api/dealer/{carModel}` - Get car details
- **POST** `/api/insurance` - Calculate insurance premium
- **POST** `/api/bank` - Process loan application

#### Request/Response Examples

**POST /api/request**
```json
// Request
{
  "customerName": "John Doe",
  "carModel": "Toyota Camry"
}

// Response
{
  "id": 1,
  "customerName": "John Doe",
  "carDetails": {
    "model": "Toyota Camry",
    "price": 25000,
    "engine": "4-cylinder",
    "transmission": "automatic"
  },
  "insuranceResult": {
    "premium": 1200,
    "coverage": "Full Coverage"
  },
  "loanResult": {
    "status": "APPROVED",
    "amount": 25000,
    "interestRate": 5.5
  },
  "timestamp": "2025-08-17T10:30:00"
}
```

## 🎨 Frontend Pages

### 1. Form Page (`/`)
- Customer name input field
- Car selection dropdown
- Submit button
- Form validation

### 2. Summary Page (`/summary`)
- Customer information card
- Car details card
- Insurance information card
- Loan approval status card
- Responsive layout with Tailwind CSS

## 🗄️ Database Schema

### RequestEntity (H2 Database)
```sql
CREATE TABLE requests (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(255) NOT NULL,
    car_model VARCHAR(255) NOT NULL,
    car_price DECIMAL(10,2),
    car_engine VARCHAR(100),
    car_transmission VARCHAR(50),
    insurance_premium DECIMAL(10,2),
    insurance_coverage VARCHAR(255),
    loan_status VARCHAR(50),
    loan_amount DECIMAL(10,2),
    loan_interest_rate DECIMAL(5,2),
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

## 🔍 Key Limitations & Assumptions

### Current Limitations
1. **No Error Handling**: Mock services never fail
2. **No Validation**: Limited input validation on both frontend and backend
3. **No Authentication**: No user login or session management
4. **Fixed Data**: Cannot add new car models dynamically
5. **Simple Logic**: Loan approval based only on car price
6. **No State Management**: Frontend doesn't persist data between pages
7. **No Real Integrations**: All external services are mocked

### Business Logic Assumptions
- All customers are eligible for insurance
- Loan approval is binary (approved/rejected)
- No down payment calculations
- No customer credit history consideration
- Fixed interest rates for all approved loans

## 🛠️ Technology Stack

### Backend
- **Framework**: Spring Boot 3.2.0
- **Language**: Java 17
- **Database**: H2 (in-memory)
- **ORM**: Spring Data JPA
- **Build Tool**: Maven

### Frontend
- **Framework**: Next.js 14
- **Language**: JavaScript/React
- **Styling**: Tailwind CSS
- **HTTP Client**: Fetch API

## 🔮 Future Enhancements

### Backend Improvements
- Add real database persistence (PostgreSQL)
- Implement proper error handling and logging
- Add API rate limiting and security
- Create comprehensive unit and integration tests
- Add API documentation with Swagger/OpenAPI

### Frontend Improvements
- Add form validation with proper error messages
- Implement loading states and better UX
- Add responsive design for mobile devices
- Create a proper state management solution
- Add unit tests with Jest and React Testing Library

### Business Logic Enhancements
- Integrate with real external APIs
- Add complex loan calculation logic
- Implement customer credit scoring
- Add document upload functionality
- Create admin dashboard for managing requests

## 📝 Development Notes

This is a **simulation project** designed to demonstrate the LastPay workflow concept. In a production environment, you would need:

- Proper security measures (authentication, authorization, HTTPS)
- Real external API integrations
- Comprehensive error handling and logging
- Database migrations and backup strategies
- CI/CD pipeline setup
- Performance monitoring and optimization
- Compliance with financial regulations

## 🤝 Contributing

This is a demonstration project. For improvements or suggestions, please feel free to create issues or submit pull requests.

## 📄 License

This project is for educational and demonstration purposes only.
