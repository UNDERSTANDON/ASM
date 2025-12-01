# Student Management System

A comprehensive Java application for managing student records with advanced sorting algorithms, data persistence, and statistical analysis capabilities. This system demonstrates core data structure concepts including sorting algorithms and heap-based operations.

## Features

- **Complete CRUD Operations**
  - Add, edit, and delete student records
  - Search by ID, name, mark range, and ranking
  - Bulk data import and export from CSV files

- **Advanced Sorting Algorithms**
  - Bubble Sort (O(n²))
  - Quick Sort (O(n log n) average case)
  - Merge Sort (O(n log n) guaranteed)
  - Sort by student ID or marks
  - Silent sorting variants for performance testing

- **Student Ranking System**
  - Excellent: 9.0 - 10.0
  - Very Good: 7.5 - 8.9
  - Good: 6.5 - 7.4
  - Medium: 5.0 - 6.4
  - Fail: 0.0 - 4.9

- **Data Import/Export**
  - CSV format support (ID, Name, Mark)
  - Markdown report generation with statistics
  - Ranking distribution analysis
  - Sample data generation

- **Heap-Based Operations**
  - Max-Heap for extracting top K students by mark
  - Priority queue implementation

## Project Structure

```
ASM/
├── src/                           # Source code
│   ├── App.java                  # Main application entry point
│   ├── TestSuite.java            # Test suite runner
│   ├── PerformanceTest.java      # Performance benchmarking
│   ├── HeapSortComparisonTest.java # Heap-based comparison tests
│   ├── Display/                  # UI and menu handling
│   │   ├── DisplayInfo.java      # Display formatting
│   │   └── DisplayProcessor.java # Menu processing and input handling
│   ├── IO/                       # Data persistence
│   │   ├── StudentDataIO.java    # CSV/Markdown import-export
│   │   └── StreamWriter.java     # File writing utilities
│   ├── Object/                   # Data models
│   │   └── Student.java          # Student entity class
│   ├── StudentManager/           # Business logic
│   │   └── StudentManager.java   # Core CRUD and sorting operations
│   └── DataRandomizer/           # Test data generation
│       └── DataGenerator.java    # Random test data creator
├── Test/                          # Testing resources
│   ├── data/                      # Test datasets
│   │   ├── sample_students.csv
│   │   ├── edge_cases.csv
│   │   ├── large_dataset.csv
│   │   ├── ranking_test.csv
│   │   └── small_dataset.csv
│   ├── results/                   # Test output files
│   ├── TestPlan.md               # Comprehensive test documentation
│   └── README.md                 # Testing guide
├── Data/                          # Sample data files
├── bin/                           # Compiled bytecode files
└── demo_io.java                   # I/O demonstration file
```

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 11 or higher
- No external dependencies required

### Compilation

Compile all source files to the `bin/` directory:
```bash
javac -d bin src/*.java src/*/*.java src/*/**.java
```

### Running the Application

Start the interactive student management system:
```bash
java -cp bin App
```

The application provides an interactive menu with the following options:
1. Add new student
2. Edit existing student
3. Delete student
4. Search students (by ID, name, mark range, or ranking)
5. Sort students (multiple algorithms, by ID or mark)
6. Display all students
7. Display statistics
8. Clear all students
9. Data import/export (CSV/Markdown)
0. Exit

### Running Tests

**Run comprehensive test suite:**
```bash
java -cp bin TestSuite
```

**Run performance benchmarks:**
```bash
java -cp bin PerformanceTest
```

**Run heap/priority queue comparison tests:**
```bash
java -cp bin HeapSortComparisonTest
```

### Test Data

Sample datasets are available in `Test/data/`:
- `sample_students.csv` - 10 students for general testing
- `small_dataset.csv` - 5 students for basic tests
- `edge_cases.csv` - Boundary values and special cases
- `large_dataset.csv` - 25+ students for performance testing
- `ranking_test.csv` - Data covering all ranking categories

## Usage Guide

### Adding Students
1. Select option 1 from main menu
2. Enter student ID (must be unique)
3. Enter student name
4. Enter mark (0-10 scale)
5. System automatically calculates and assigns ranking

### Searching Students
**Option 4** provides multiple search methods:
- **By ID**: Returns exact match (single student)
- **By Name**: Partial matching (case-insensitive)
- **By Mark Range**: Returns all students within specified range
- **By Ranking**: Returns all students with specified ranking

### Sorting
**Option 5** offers three sorting algorithms:
- **Bubble Sort**: Simple comparison-based sort (educational)
- **Quick Sort**: Fast average-case performance
- **Merge Sort**: Stable sort with guaranteed O(n log n) performance
- Choose to sort by **ID** or **Marks**

### Data Management
**Option 9** for import/export:
- **Import from CSV**: Load student data from CSV files
- **Export to CSV**: Save current data to CSV file
- **Export to Markdown**: Generate formatted report with statistics

## Sorting Algorithm Performance

### Time Complexity Comparison

| Algorithm | Best Case | Average Case | Worst Case | Space |
|-----------|-----------|--------------|------------|-------|
| Bubble Sort | O(n) | O(n²) | O(n²) | O(1) |
| Quick Sort | O(n log n) | O(n log n) | O(n²) | O(log n) |
| Merge Sort | O(n log n) | O(n log n) | O(n log n) | O(n) |

### Benchmark Results (typical times)

| Dataset Size | Bubble Sort | Quick Sort | Merge Sort |
|-------------|------------|-----------|-----------|
| 10 students | < 1ms | < 1ms | < 1ms |
| 25 students | < 1ms | < 1ms | < 1ms |
| 100 students | ~5ms | ~2ms | ~3ms |

## File Formats

### CSV Format
```csv
ID,Name,Mark
1,John Doe,8.5
2,Jane Smith,7.2
3,Bob Johnson,5.5
```

### Markdown Export
Generated reports include:
- Student list in table format with ID, Name, Mark, and Ranking
- Ranking distribution with percentages
- Generation timestamp
- Total student count

Example:
```markdown
# Student Management System - Data Export

Generated on: 2025-12-01T10:30:45

| ID | Name | Mark | Ranking |
|----|------|------|---------|
| 1 | John Doe | 8.50 | Very Good |
| 2 | Jane Smith | 7.20 | Good |

## Ranking Statistics

- **Excellent**: 0 (0.0%)
- **Very Good**: 1 (50.0%)
- **Good**: 1 (50.0%)
```

## Testing

The project includes comprehensive testing framework:

### Test Categories
- **Unit Tests**: Individual component validation
- **Integration Tests**: Component interaction verification
- **Performance Tests**: Algorithm efficiency comparison
- **Edge Case Tests**: Boundary and error condition handling
- **Ranking Tests**: Ranking system accuracy

### Test Coverage
- CRUD operations (Create, Read, Update, Delete)
- All sorting algorithms with various dataset sizes
- Search functionality across all methods
- Data import/export operations
- Ranking calculation accuracy
- File I/O error handling
- Data validation and constraints

See `Test/TestPlan.md` for detailed test documentation and results.

## Key Classes

### `Student.java`
- Represents individual student entity
- Manages ID, name, mark, and ranking
- Automatic ranking calculation based on mark thresholds
- Validation for mark range (0-10)

### `StudentManager.java`
- Core business logic for student management
- Implements CRUD operations
- Provides multiple search methods
- Implements three sorting algorithms with both ascending/descending variants
- Supports heap-based top-K student retrieval

### `DisplayProcessor.java`
- Handles menu display and navigation
- Processes user input with validation
- Routes commands to appropriate handlers
- Manages interactive user experience

### `StudentDataIO.java`
- Handles file I/O operations
- Supports CSV import/export
- Generates Markdown reports with statistics
- Includes error handling for file operations

### `DataGenerator.java`
- Generates random test data
- Creates sample datasets of various sizes
- Supports customizable student parameters

## Architecture

The system follows a layered architecture:

1. **Presentation Layer** (`Display/`): User interface and menu handling
2. **Business Logic Layer** (`StudentManager/`): Core operations and algorithms
3. **Data Access Layer** (`IO/`): File operations and data persistence
4. **Data Model Layer** (`Object/`): Entity definitions

This separation ensures modularity and makes testing/maintenance easier.

## Development Notes

- **Language**: Java (no external dependencies)
- **Java Version**: 11+ (uses modern syntax like switch expressions)
- **Code Style**: Documented with javadoc comments
- **Testing**: Custom test framework (no external test libraries)
- **Data Storage**: In-memory ArrayList with file persistence

## Future Enhancements

Potential improvements:
- Database integration (SQL backend)
- GUI implementation (Swing/JavaFX)
- Advanced data structures (B-Trees, Hash Tables)
- Concurrent processing for large datasets
- RESTful API endpoints

## Contributing

1. Write clear, documented code following existing style
2. Include javadoc comments for all public methods
3. Add tests for new features
4. Update TestPlan.md with test cases
5. Verify all tests pass before committing

## License

This project is created for educational purposes as part of a Data Structures course.

## Author

UNDERSTANDON

## Acknowledgments

- Course instructors and peers
- Testing data contributors
- Data Structures course materials
