# Time Complexity Comparison

| Dataset | Size | Top K | Heap (ms) | Merge Sort (ms) | Quick Sort (ms) |
|---------|------|-------|-----------|-----------------|------------------|
| Large | 26 | 10 | 2 | 0 | 0 |
| Extreme | 100001 | 10 | 11 | 55 | 101 |

## Notes

- Heap: getTopStudentsByMark(k) - extracts top K students
- Merge Sort: Full sort by mark (descending)
- Quick Sort: Full sort by mark (descending)
- Date: 2025-11-29T13:35:24.324915800
