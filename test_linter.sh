#!/bin/bash
# Test script for Java Linter
# This script compiles test files and runs the linter on them

echo "========================================"
echo "Building project and test classes..."
echo "========================================"
./gradlew build testClasses
if [ $? -ne 0 ]; then
    echo "Build failed!"
    exit 1
fi

echo ""
echo "========================================"
echo "Running linter on test cases..."
echo "========================================"
echo ""

echo "--- Testing Good Design Example (should have no warnings) ---"
./gradlew run --args "build/classes/java/test/testcases/GoodDesignExample.class"
echo ""

echo "--- Testing Bad Class Naming ---"
./gradlew run --args "build/classes/java/test/testcases/badClassNaming.class"
echo ""

echo "--- Testing Bad Field Naming ---"
./gradlew run --args "build/classes/java/test/testcases/BadFieldNaming.class"
echo ""

echo "--- Testing Bad Method Naming ---"
./gradlew run --args "build/classes/java/test/testcases/BadMethodNaming.class"
echo ""

echo "--- Testing Missing HashCode ---"
./gradlew run --args "build/classes/java/test/testcases/MissingHashCode.class"
echo ""

echo "--- Testing Missing Equals ---"
./gradlew run --args "build/classes/java/test/testcases/MissingEquals.class"
echo ""

echo "--- Testing Program to Concrete Class ---"
./gradlew run --args "build/classes/java/test/testcases/ProgramToConcreteClass.class"
echo ""

echo "--- Testing Public Fields ---"
./gradlew run --args "build/classes/java/test/testcases/PublicFields.class"
echo ""

echo "--- Testing All Bad Example (multiple violations) ---"
./gradlew run --args "build/classes/java/test/testcases/allBadExample.class"
echo ""

echo "========================================"
echo "Testing entire test directory..."
echo "========================================"
./gradlew run --args "build/classes/java/test"
echo ""

echo "========================================"
echo "All tests completed!"
echo "========================================"


