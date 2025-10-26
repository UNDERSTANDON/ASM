# Student Management System

A comprehensive Java application for managing student records with advanced sorting algorithms, data persistence, and statistical analysis capabilities.

## Features

- **Complete CRUD Operations**
  - Add, edit, and delete student records
  - Search by ID, name, mark range, and ranking
  - Bulk data import and export

- **Advanced Sorting Algorithms**
  - Bubble Sort
  - Quick Sort
  - Merge Sort
  - Sort by ID or marks

- **Student Ranking System**
  - Excellent (9.0 - 10.0)
  - Very Good (7.5 - 8.9)
  - Good (6.5 - 7.4)
  - Medium (5.0 - 6.4)
  - Fail (0.0 - 4.9)

- **Data Import/Export**
  - CSV format support
  - Markdown report generation
  - Statistical analysis export

## Project Structure

```
ASM/
├── src/                    # Source code
│   ├── App.java           # Main application entry
│   ├── Display/           # UI components
│   ├── IO/                # Data persistence
│   ├── Object/            # Data models
│   └── StudentManager/    # Business logic
├── Test/                  # Testing resources
│   ├── data/             # Test datasets
│   ├── results/          # Test outputs
│   ├── TestPlan.md       # Test documentation
│   └── README.md         # Testing guide
├── bin/                   # Compiled files
└── lib/                   # Dependencies
```

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 11 or higher
- Visual Studio Code with Java extensions

### Running the Application

1. Compile the source files:
```bash
javac -d bin src/*.java src/*/*.java
```

2. Run the application:
```bash
java -cp bin App
```

### Running Tests

1. Run the test suite:
```bash
java -cp bin TestSuite
```

2. Run performance tests:
```bash
java -cp bin PerformanceTest
```

### Sample Data

The application includes sample datasets in `Test/data/`:
- `sample_students.csv`: General testing data
- `edge_cases.csv`: Boundary testing
- `large_dataset.csv`: Performance testing
- `ranking_test.csv`: Ranking system validation

## Usage Guide

1. **Adding Students**
   - Select option 1 from main menu
   - Enter student ID, name, and mark
   - System automatically calculates ranking

2. **Searching Students**
   - Option 4 provides multiple search methods
   - Search by ID for exact matches
   - Search by name supports partial matches
   - Search by mark range or ranking

3. **Sorting**
   - Option 5 offers various sorting algorithms
   - Compare performance with different data sizes
   - Sort by ID or marks

4. **Data Import/Export**
   - Use option 9 for data management
   - Export reports in CSV or Markdown
   - Import existing data
   - Generate sample data files

## Performance

### Sorting Algorithm Comparison

| Size | Bubble Sort | Quick Sort | Merge Sort |
|------|-------------|------------|------------|
| 10   | < 1ms      | < 1ms      | < 1ms      |
| 100  | ~2ms       | < 1ms      | ~1ms       |
| 1000 | ~95ms      | ~3ms       | ~8ms       |

## Testing

The project includes comprehensive testing:
- Unit tests for all components
- Performance benchmarks
- Edge case handling
- Data persistence validation

See `Test/README.md` for detailed testing documentation.

## File Formats

### CSV Format
```csv
ID,Name,Mark
1,John Doe,8.5
2,Jane Smith,7.2
```

### Markdown Export
Includes:
- Student list in table format
- Statistical analysis
- Ranking distribution
- Raw data for import

## Contributing

1. Write clear, documented code
2. Follow existing code structure
3. Add tests for new features
4. Update documentation

## License

This project is created for educational purposes as part of a Data Structures course.

## Acknowledgments

- Course instructors and peers
- Testing data contributors
