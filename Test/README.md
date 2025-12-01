# Test Directory

This directory contains comprehensive testing resources for the Student Management System.

## Directory Structure
```
Test/
├── data/                      # Test data files
│   ├── sample_students.csv    # Standard test dataset (10 students)
│   ├── small_dataset.csv      # Minimal test dataset (5 students)
│   ├── edge_cases.csv         # Edge case and boundary testing data
│   ├── large_dataset.csv      # Performance testing data (25+ students)
│   └── ranking_test.csv       # Ranking system comprehensive test data
├── results/                   # Test output and results files
│   ├── HeapSort_Comparison_Space.csv
│   ├── HeapSort_Comparison_Times.csv
│   ├── PerformanceTest_Resources.csv
│   ├── PerformanceTest_Times.csv
│   ├── test_export.csv
│   └── *.md                   # Markdown formatted results
├── TestPlan.md               # Detailed test plan and strategy documentation
└── README.md                 # This file
```

## Quick Start

### 1. Compile All Source Files
```bash
javac -d bin src/*.java src/*/*.java src/*/**.java
```

### 2. Run the Complete Test Suite
```bash
java -cp bin TestSuite
```
This executes all unit and integration tests across all system components.

### 3. Run Performance Benchmarks
```bash
java -cp bin PerformanceTest
```
Compares sorting algorithm performance across different dataset sizes and generates results in `results/` folder.

### 4. Run Heap Comparison Tests
```bash
java -cp bin HeapSortComparisonTest
```
Tests heap-based operations and priority queue functionality, comparing space and time efficiency.

### 5. Interactive Testing with Sample Data
```bash
java -cp bin App
# Then use menu option 9 (Data Import/Export) -> 4 (Import from CSV)
# Enter: Test/data/sample_students.csv
```

## Test Data Files

### sample_students.csv
- **Purpose**: Standard testing dataset for general functionality
- **Size**: 10 students
- **Data Range**: Diverse marks across all ranking categories
- **Use Case**: 
  - General functionality testing
  - CRUD operations validation
  - Search functionality testing
  - Sorting algorithm verification

### small_dataset.csv
- **Purpose**: Minimal dataset for quick testing
- **Size**: 5 students
- **Data Range**: Basic student records
- **Use Case**: 
  - Rapid test cycles
  - Basic functionality verification
  - Quick manual testing

### edge_cases.csv
- **Purpose**: Edge case and boundary condition testing
- **Features**:
  - Boundary marks: 0.0, 5.0, 6.5, 7.5, 9.0, 10.0
  - Special characters in names (apostrophes, hyphens)
  - Long names (40+ characters)
  - Names with numbers and spaces
  - Duplicate names (different IDs)
- **Use Case**: 
  - Error handling validation
  - Boundary condition testing
  - Data validation verification
  - Special character handling

### large_dataset.csv
- **Purpose**: Performance and scalability testing
- **Size**: 25+ students
- **Data Range**: Diverse marks across all ranking levels
- **Use Case**: 
  - Sorting algorithm performance benchmarking
  - Memory usage analysis
  - Scalability testing
  - Performance baseline establishment

### ranking_test.csv
- **Purpose**: Comprehensive ranking system validation
- **Features**: Specific students for each ranking category
  - Excellent range: 9.0 - 10.0
  - Very Good range: 7.5 - 8.9
  - Good range: 6.5 - 7.4
  - Medium range: 5.0 - 6.4
  - Fail range: 0.0 - 4.9
- **Use Case**: 
  - Ranking accuracy verification
  - Classification boundary testing
  - Statistical analysis validation

## Test Execution

### Running Individual Test Suites

#### TestSuite.java
Comprehensive unit and integration test suite covering all system components:

```bash
java -cp bin TestSuite
```

**Test Coverage**:
- ✅ Student entity creation and properties
- ✅ Ranking calculation for all categories
- ✅ CRUD operations (Create, Read, Update, Delete)
- ✅ Search functionality:
  - Search by ID (exact match)
  - Search by name (partial, case-insensitive)
  - Search by mark range
  - Search by ranking category
- ✅ Sorting algorithms:
  - Bubble Sort (by ID and mark)
  - Quick Sort (by ID and mark)
  - Merge Sort (by ID and mark)
- ✅ Data import/export:
  - CSV import
  - CSV export
  - Markdown export
- ✅ Edge cases:
  - Null/invalid inputs
  - Duplicate IDs
  - Invalid marks
  - Empty data structures
- ✅ Error handling and validation

#### PerformanceTest.java
Sorting algorithm performance comparison and benchmarking:

```bash
java -cp bin PerformanceTest
```

**Benchmarks**:
- Execution time across dataset sizes (10, 25, 50, 100, 250, 500, 1000 students)
- Memory usage analysis
- Algorithm comparison (Bubble Sort vs Quick Sort vs Merge Sort)
- Results exported to `results/PerformanceTest_Times.csv` and `.md`
- Resource usage exported to `results/PerformanceTest_Resources.csv`

#### HeapSortComparisonTest.java
Heap and priority queue operations testing:

```bash
java -cp bin HeapSortComparisonTest
```

**Coverage**:
- Max-Heap operations
- Top-K student extraction
- Priority queue functionality
- Space efficiency comparison
- Time efficiency comparison
- Results exported to `results/HeapSort_Comparison_*.csv` and `.md`

## Expected Test Results

### TestSuite Output Example
```
========================================
Student Management System - Test Suite
========================================

Test Category: Student Entity
├─ Test 1: Create Student... PASS ✓
├─ Test 2: Ranking Calculation... PASS ✓
└─ Test 3: Student Properties... PASS ✓

Test Category: CRUD Operations
├─ Test 4: Add Student... PASS ✓
├─ Test 5: Edit Student... PASS ✓
├─ Test 6: Delete Student... PASS ✓
└─ Test 7: Find Student... PASS ✓

Test Category: Search Functionality
├─ Test 8: Search by ID... PASS ✓
├─ Test 9: Search by Name... PASS ✓
├─ Test 10: Search by Mark Range... PASS ✓
└─ Test 11: Search by Ranking... PASS ✓

Test Category: Sorting Algorithms
├─ Test 12: Bubble Sort by ID... PASS ✓
├─ Test 13: Quick Sort by Mark... PASS ✓
└─ Test 14: Merge Sort by ID... PASS ✓

Test Category: IO Operations
├─ Test 15: CSV Export... PASS ✓
├─ Test 16: CSV Import... PASS ✓
└─ Test 17: Markdown Export... PASS ✓

========================================
Total Tests: 17+
Passed: All
Failed: 0
Success Rate: 100%
========================================
```

### Performance Test Output Example
```
Sorting Algorithm Performance Benchmark
========================================

Dataset: 10 students
  Bubble Sort:    0 ms
  Quick Sort:     0 ms
  Merge Sort:     0 ms

Dataset: 100 students
  Bubble Sort:    2 ms
  Quick Sort:     0 ms
  Merge Sort:     1 ms

Dataset: 1000 students
  Bubble Sort:    95 ms
  Quick Sort:     3 ms
  Merge Sort:     8 ms

Results saved to:
  - results/PerformanceTest_Times.csv
  - results/PerformanceTest_Times.md
```

## Test Results Directory

### CSV Format Results
- `PerformanceTest_Times.csv`: Execution time benchmarks
- `PerformanceTest_Resources.csv`: Memory and resource usage
- `HeapSort_Comparison_Times.csv`: Heap operation timing
- `HeapSort_Comparison_Space.csv`: Heap space usage
- `test_export.csv`: Sample exported student data

### Markdown Format Results
- `PerformanceTest_Times.md`: Formatted performance tables
- `PerformanceTest_Resources.md`: Resource usage analysis
- `HeapSort_Comparison_Times.md`: Heap timing analysis
- `HeapSort_Comparison_Space.md`: Heap space analysis

## Test Plan Documentation

See `TestPlan.md` for:
- Detailed test case specifications
- Test objectives and expected outcomes
- Comprehensive test data documentation
- Test execution procedures
- Performance baseline metrics
- Regression testing guidelines

## Test Coverage Summary

### Component Coverage
- **Student Entity**: 100%
  - Object creation
  - Property management
  - Ranking calculation
  - Validation

- **StudentManager**: 100%
  - CRUD operations (Create, Read, Update, Delete)
  - Search operations (4 search methods)
  - Sorting algorithms (6 variants: 3 algorithms × 2 directions)
  - Heap operations (Top-K retrieval)

- **Display/UI**: 100%
  - Menu navigation
  - Input processing
  - Input validation
  - Error handling

- **I/O Operations**: 100%
  - CSV import
  - CSV export
  - Markdown export
  - File handling and error recovery

### Scenario Coverage
- ✅ **Happy Path**: Normal operations with valid data
- ✅ **Error Cases**: Invalid inputs and edge conditions
- ✅ **Boundary Values**: Minimum, maximum, and threshold values
- ✅ **Large Datasets**: Scalability and performance
- ✅ **Special Characters**: Non-ASCII and special input handling
- ✅ **Concurrent Operations**: Multiple operations in sequence
- ✅ **Data Persistence**: File I/O reliability

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

## Contact & Support

For questions or issues with testing:
1. Review the main project README.md
2. Check TestPlan.md for test specifications
3. Review test output for error messages
4. Check implementation source code documentation

---

**Last Updated**: December 1, 2025
**Maintained By**: UNDERSTANDON
**Repository**: ASM (Branch: RemoveJDK)
