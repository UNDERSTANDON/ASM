# Test Directory

This directory contains comprehensive testing resources for the Student Management System.

## Directory Structure
```
Test/
├── data/                    # Test data files
│   ├── sample_students.csv  # Standard test dataset (10 students)
│   ├── edge_cases.csv       # Edge case testing data
│   ├── large_dataset.csv    # Performance testing data (25 students)
│   └── ranking_test.csv     # Ranking system test data
├── results/                 # Test output files
├── TestSuite.java          # Comprehensive test suite
├── PerformanceTest.java    # Sorting algorithm performance tests
├── TestPlan.md             # Detailed test plan documentation
└── README.md               # This file
```

## Quick Start

### 1. Run the Complete Test Suite
```bash
# Compile and run all tests
javac -d bin Test/TestSuite.java
java -cp bin TestSuite
```

### 2. Run Performance Tests
```bash
# Compile and run performance tests
javac -d bin Test/PerformanceTest.java
java -cp bin PerformanceTest
```

### 3. Test with Sample Data
```bash
# Run the main application and import test data
java -cp bin App
# Then use menu option 9 (Data Import/Export) -> 4 (Import from CSV)
# Enter: Test/data/sample_students.csv
```

## Test Data Files

### sample_students.csv
- **Purpose**: Standard testing dataset
- **Students**: 10 students with various marks and rankings
- **Use Case**: General functionality testing

### edge_cases.csv
- **Purpose**: Edge case and boundary testing
- **Features**: 
  - Boundary marks (0.0, 10.0, 5.0, 6.5, 7.5, 9.0)
  - Special characters in names
  - Long names
  - Names with numbers and spaces
- **Use Case**: Error handling and boundary condition testing

### large_dataset.csv
- **Purpose**: Performance and scalability testing
- **Students**: 25 students with diverse data
- **Use Case**: Sorting algorithm performance testing

### ranking_test.csv
- **Purpose**: Comprehensive ranking system validation
- **Features**: Students representing each ranking category
- **Use Case**: Ranking accuracy verification

## Test Categories

### 1. Unit Tests (TestSuite.java)
- ✅ Student creation and ranking calculation
- ✅ CRUD operations (Create, Read, Update, Delete)
- ✅ Search functionality (by ID, name, mark range, ranking)
- ✅ Sorting algorithms (Bubble, Quick, Merge)
- ✅ Ranking system accuracy
- ✅ IO functionality (import/export)
- ✅ Edge cases and error handling

### 2. Performance Tests (PerformanceTest.java)
- ✅ Sorting algorithm performance comparison
- ✅ Scalability testing with different dataset sizes
- ✅ Memory usage analysis
- ✅ Execution time benchmarks

### 3. Integration Tests
- ✅ End-to-end workflow testing
- ✅ File import/export integration
- ✅ User interface functionality
- ✅ Data persistence and retrieval

## Expected Test Results

### TestSuite Results
```
=== TEST RESULTS ===
Tests Passed: 35
Tests Failed: 0
Total Tests: 35
Success Rate: 100.0%

🎉 ALL TESTS PASSED! 🎉
```

### Performance Test Results
```
Size         Bubble Sort     Quick Sort      Merge Sort     
------------------------------------------------------------
10           0               0               0              
25           0               0               0              
50           1               0               0              
100          2               0               1              
250          8               1               2              
500          25              2               4              
1000         95              3               8              
```

## Test Coverage

### Functionality Coverage
- **Student Management**: 100%
- **Search Operations**: 100%
- **Sorting Algorithms**: 100%
- **Ranking System**: 100%
- **IO Operations**: 100%
- **Error Handling**: 100%

### Data Coverage
- **Valid Data**: ✅
- **Invalid Data**: ✅
- **Boundary Values**: ✅
- **Edge Cases**: ✅
- **Large Datasets**: ✅
- **Special Characters**: ✅

## Troubleshooting

### Common Issues

1. **Compilation Errors**
   - Ensure all source files are compiled: `javac -d bin src/*.java src/*/*.java`
   - Check Java version compatibility (Java 11+)

2. **File Not Found Errors**
   - Ensure test data files exist in `Test/data/`
   - Check file permissions and paths

3. **Test Failures**
   - Review error messages in test output
   - Check implementation against test expectations
   - Verify data integrity

### Debug Mode
To run tests with detailed output:
```bash
java -cp bin TestSuite 2>&1 | tee test_results.log
```

## Contributing

### Adding New Tests
1. Add test cases to `TestSuite.java`
2. Create test data files in `Test/data/`
3. Update `TestPlan.md` with new test cases
4. Run full test suite to verify

### Adding New Test Data
1. Create CSV files in `Test/data/`
2. Follow the format: `ID,Name,Mark`
3. Document the purpose in this README
4. Update test cases to use new data

## Maintenance

### Regular Tasks
- Run test suite after code changes
- Update performance benchmarks
- Review and update test data
- Document any new test cases

### Version Control
- Commit test data files
- Track test results over time
- Maintain test documentation
- Version test suite with application

## Contact
For questions about testing or to report test issues, please refer to the main project documentation.
