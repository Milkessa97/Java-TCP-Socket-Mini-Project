@echo off
echo ========================================
echo Starting RMI Calculator Client
echo ========================================

REM Check if classes exist
if not exist "target\classes" (
    echo Please compile the project first by running start-server.bat
    pause
    exit /b 1
)

REM Set classpath
set CLASSPATH=target\classes

REM Start the RMI client with security policy
echo Connecting to server...
java -Djava.security.policy=config\client.policy -cp %CLASSPATH% com.calculator.client.CalculatorClient

pause
