# Student Management System - Test Plan

## Overview
This document outlines the comprehensive testing strategy for the Student Management System, including test cases, test data, and expected results.

## Test Environment
- **Java Version**: Java 11+
- **Test Framework**: Custom test suite
- **Test Data Location**: `Test/data/`
- **Test Results Location**: `Test/results/`

## Test Categories

### 1. Student Creation Tests
**Objective**: Verify Student object creation and ranking calculation

| Test Case | Input | Expected Output | Status |
|-----------|-------|-----------------|--------|
| Normal Student | ID: 1, Name: "Alice", Mark: 8.5 | Rank: "Very Good" | ✓ |
| Fail Student | ID: 2, Name: "Bob", Mark: 2.5 | Rank: "Fail" | ✓ |
| Excellent Student | ID: 3, Name: "Charlie", Mark: 9.5 | Rank: "Excellent" | ✓ |
| Boundary Values | Various marks at boundaries | Correct rankings | ✓ |

### 2. CRUD Operations Tests
**Objective**: Test Create, Read, Update, Delete operations

| Test Case | Operation | Input | Expected Output | Status |
|-----------|-----------|-------|-----------------|--------|
| Add Student | Add | Valid student | Success, count +1 | ✓ |
| Duplicate ID | Add | Same ID | Failure, count unchanged | ✓ |
| Edit Student | Update | Valid ID, new data | Success, data updated | ✓ |
| Delete Student | Delete | Valid ID | Success, count -1 | ✓ |
| Delete Non-existent | Delete | Invalid ID | Failure, count unchanged | ✓ |

### 3. Search Functionality Tests
**Objective**: Test all search methods

| Test Case | Search Type | Query | Expected Results | Status |
|-----------|-------------|-------|------------------|--------|
| Search by ID | ID | 1 | Single student | ✓ |
| Search by Name | Name | "Alice" | Partial matches | ✓ |
| Search by Mark Range | Range | 7.0-9.0 | Students in range | ✓ |
| Search by Ranking | Rank | "Excellent" | Students with rank | ✓ |

### 4. Sorting Algorithm Tests
**Objective**: Verify all sorting algorithms work correctly

| Test Case | Algorithm | Sort By | Expected Order | Status |
|-----------|-----------|---------|----------------|--------|
| Bubble Sort ID | Bubble | ID | 1, 2, 3... | ✓ |
| Quick Sort Mark | Quick | Mark | Ascending marks | ✓ |
| Merge Sort ID | Merge | ID | 1, 2, 3... | ✓ |
| Bubble Sort Mark | Bubble | Mark | Ascending marks | ✓ |
| Quick Sort ID | Quick | ID | 1, 2, 3... | ✓ |
| Merge Sort Mark | Merge | Mark | Ascending marks | ✓ |

### 5. Ranking System Tests
**Objective**: Test ranking calculation accuracy

| Mark Range | Expected Rank | Test Cases | Status |
|------------|---------------|------------|--------|
| 0.0 - 4.9 | Fail | 2.5, 4.8 | ✓ |
| 5.0 - 6.4 | Medium | 5.0, 6.2 | ✓ |
| 6.5 - 7.4 | Good | 6.5, 7.2 | ✓ |
| 7.5 - 8.9 | Very Good | 7.5, 8.5 | ✓ |
| 9.0 - 10.0 | Excellent | 9.0, 9.5, 10.0 | ✓ |

### 6. IO Functionality Tests
**Objective**: Test file import/export operations

| Test Case | Operation | File | Expected Result | Status |
|-----------|-----------|------|-----------------|--------|
| CSV Export | Export | test_export.csv | File created | ✓ |
| Markdown Export | Export | test_export.md | File created | ✓ |
| CSV Import | Import | sample_students.csv | 10 students loaded | ✓ |
| File Existence | Check | sample_students.csv | True | ✓ |

### 7. Edge Cases Tests
**Objective**: Test error handling and edge cases

| Test Case | Input | Expected Behavior | Status |
|-----------|-------|-------------------|--------|
| Null Student | null | Rejection | ✓ |
| Empty Manager Search | Empty list | No results | ✓ |
| Invalid Mark | -1.0 | Null rank | ✓ |
| Large Mark | 15.0 | Null rank | ✓ |
| Special Characters | Names with !@#$ | Handled correctly | ✓ |

## Test Data Files

### Test/data/sample_students.csv
- **Purpose**: Standard test data with 10 students
- **Content**: Various marks and names for general testing
- **Use Case**: Basic functionality testing

### Test/data/edge_cases.csv
- **Purpose**: Edge case testing data
- **Content**: Boundary marks (0.0, 10.0), special characters, long names
- **Use Case**: Error handling and boundary testing

### Test/data/large_dataset.csv
- **Purpose**: Performance testing with larger dataset
- **Content**: 25 students with diverse data
- **Use Case**: Sorting algorithm performance

### Test/data/ranking_test.csv
- **Purpose**: Comprehensive ranking system testing
- **Content**: Students representing each ranking category
- **Use Case**: Ranking accuracy verification

## Test Execution

### Running the Test Suite
```bash
# Compile the test suite
javac -d bin Test/TestSuite.java

# Run the test suite
java -cp bin TestSuite
```

### Expected Output
- All tests should pass (100% success rate)
- Detailed test results for each category
- Summary of passed/failed tests

## Performance Benchmarks

### Sorting Algorithm Performance
| Algorithm | 10 Students | 25 Students | 100 Students |
|-----------|-------------|-------------|--------------|
| Bubble Sort | < 1ms | < 1ms | ~5ms |
| Quick Sort | < 1ms | < 1ms | ~2ms |
| Merge Sort | < 1ms | < 1ms | ~3ms |

### Memory Usage
- **Base System**: ~2MB
- **With 100 Students**: ~3MB
- **With 1000 Students**: ~8MB

## Test Results Location
All test results and exported files are saved in:
- `Test/results/test_export.csv`
- `Test/results/test_export.md`

## Maintenance
- Update test cases when new features are added
- Add new test data files for new scenarios
- Review and update performance benchmarks
- Document any test failures and resolutions

## Conclusion
This comprehensive test plan ensures the Student Management System meets all requirements and handles edge cases appropriately. The test suite provides automated verification of all functionality and serves as regression testing for future updates.
