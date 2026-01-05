@echo off
echo ========================================
echo Starting RMI Calculator Server
echo ========================================

REM Compile the project if needed
if not exist "target\classes" (
    echo Compiling project...
    call mvn compile
    if errorlevel 1 (
        echo Compilation failed!
        pause
        exit /b 1
    )
)

REM Set classpath
set CLASSPATH=target\classes

REM Start the RMI server with security policy
echo Starting server...
java -Djava.security.policy=config\server.policy -cp %CLASSPATH% com.calculator.server.CalculatorServer

pause
