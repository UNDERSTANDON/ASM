# Space Complexity Analysis

| Algorithm | Space Complexity | Notes |
|-----------|------------------|---------|
| Max-Heap (getTopK) | O(k) | Only stores top K students |
| Merge Sort | O(n) | Requires temporary arrays for merging |
| Quick Sort | O(log n) | In-place sorting, recursion stack |

## Summary

- Max-Heap approach is most space-efficient when retrieving only top K students
- For complete sorting, Quick Sort uses least extra space (in-place)
- Merge Sort guarantees O(n log n) time but uses O(n) extra space
- Date: 2025-11-29T13:35:24.329554200
