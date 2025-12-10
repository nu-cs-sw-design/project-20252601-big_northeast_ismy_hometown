@echo off
REM Test script for Java Linter
REM This script compiles test files and runs the linter on them

echo ========================================
echo Building project and test classes...
echo ========================================
call gradlew.bat build testClasses
if %ERRORLEVEL% NEQ 0 (
    echo Build failed!
    exit /b 1
)

echo.
echo ========================================
echo Running linter on test cases...
echo ========================================
echo.

echo --- Testing Good Design Example (should have no warnings) ---
call gradlew.bat run --args "build/classes/java/test/testcases/GoodDesignExample.class"
echo.

echo --- Testing Bad Class Naming ---
call gradlew.bat run --args "build/classes/java/test/testcases/badClassNaming.class"
echo.

echo --- Testing Bad Field Naming ---
call gradlew.bat run --args "build/classes/java/test/testcases/BadFieldNaming.class"
echo.

echo --- Testing Bad Method Naming ---
call gradlew.bat run --args "build/classes/java/test/testcases/BadMethodNaming.class"
echo.

echo --- Testing Missing HashCode ---
call gradlew.bat run --args "build/classes/java/test/testcases/MissingHashCode.class"
echo.

echo --- Testing Missing Equals ---
call gradlew.bat run --args "build/classes/java/test/testcases/MissingEquals.class"
echo.

echo --- Testing Program to Concrete Class ---
call gradlew.bat run --args "build/classes/java/test/testcases/ProgramToConcreteClass.class"
echo.

echo --- Testing Public Fields ---
call gradlew.bat run --args "build/classes/java/test/testcases/PublicFields.class"
echo.

echo --- Testing All Bad Example (multiple violations) ---
call gradlew.bat run --args "build/classes/java/test/testcases/allBadExample.class"
echo.

echo ========================================
echo Testing entire test directory...
echo ========================================
call gradlew.bat run --args "build/classes/java/test"
echo.

echo ========================================
echo All tests completed!
echo ========================================


