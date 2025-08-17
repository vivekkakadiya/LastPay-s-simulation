@echo off
echo Starting LastPay Simulation Project...
echo.

echo Starting Spring Boot Backend...
start "Backend" cmd /k "cd backend && mvnw.cmd spring-boot:run"

echo Waiting for backend to initialize...
timeout /t 10 /nobreak > nul

echo Starting Next.js Frontend...
start "Frontend" cmd /k "cd frontend && npm install && npm run dev"

echo.
echo Both services are starting up...
echo Backend will be available at: http://localhost:8080
echo Frontend will be available at: http://localhost:3000
echo.
pause
