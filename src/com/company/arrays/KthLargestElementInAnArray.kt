package com.company.arrays

fun main(){
    println("=== SORTING APPROACH (O(n log n)) ===")
    // Test 1: Find 3rd largest element
    val test1 = intArrayOf(1000, 2, 4, 5, 10, 21, 100, 2)
    println("Test 1 - Array: ${test1.contentToString()}, k=3")
    println("Result: ${findKthLargest(test1.copyOf(), 3)}")  // Expected: 21
    println()
    
    // Test 2: Find 1st largest (maximum element)
    val test2 = intArrayOf(3, 2, 1, 5, 6, 4)
    println("Test 2 - Array: ${test2.contentToString()}, k=1")
    println("Result: ${findKthLargest(test2.copyOf(), 1)}")  // Expected: 6
    println()
    
    // Test 3: Find 2nd largest with duplicates
    val test3 = intArrayOf(3, 2, 3, 1, 2, 4, 5, 5, 6)
    println("Test 3 - Array: ${test3.contentToString()}, k=2")
    println("Result: ${findKthLargest(test3.copyOf(), 2)}")  // Expected: 5 (includes duplicates)
    println()

    println("=== QUICKSELECT APPROACH (O(n) average) ===")
    // Test the same cases with Quickselect
    println("Test 1 - Array: ${test1.contentToString()}, k=3")
    println("Result: ${findKthLargestQuickselect(test1.copyOf(), 3)}")  // Expected: 21
    println()
    
    println("Test 2 - Array: ${test2.contentToString()}, k=1")
    println("Result: ${findKthLargestQuickselect(test2.copyOf(), 1)}")  // Expected: 6
    println()
    
    println("Test 3 - Array: ${test3.contentToString()}, k=2")
    println("Result: ${findKthLargestQuickselect(test3.copyOf(), 2)}")  // Expected: 5 (includes duplicates)
}


fun findKthLargest(nums: IntArray, k: Int): Int {
    // Function takes two parameters:
    // - nums: IntArray containing the numbers to search through
    // - k: Integer representing which largest element to find (e.g., k=1 means largest, k=2 means 2nd largest)
    // Returns: The kth largest element as an Int
    
    //val numberSet = nums.toSet()
    // Commented out: Would convert array to Set to remove duplicates (not currently used)
    
    val numsSorted = nums.sortedDescending()
    // sortedDescending() creates a new list with elements sorted from largest to smallest
    // Example: [1000, 2, 4, 5, 10, 21, 100, 2] becomes [1000, 100, 21, 10, 5, 4, 2, 2]
    
    return numsSorted[k-1]
    // Return the element at index k-1 (arrays are 0-indexed)
    // For k=3 (3rd largest), access index 2 to get the 3rd element
    // Example: numsSorted[2] returns 21 from [1000, 100, 21, ...]
}


/**
 * QUICKSELECT ALGORITHM - Optimized O(n) average time solution
 * 
 * Uses partitioning (similar to Quicksort) to find kth largest element without fully sorting.
 * 
 * Algorithm:
 * 1. Pick a pivot and partition array so elements > pivot are on left, <= pivot on right
 * 2. If pivot is at position k-1, we found the kth largest element
 * 3. If pivot position < k-1, search right partition (larger elements needed)
 * 4. If pivot position > k-1, search left partition (too far right)
 * 
 * Time Complexity: O(n) average case, O(n²) worst case
 * Space Complexity: O(1) - in-place algorithm (excluding recursion stack)
 */
fun findKthLargestQuickselect(nums: IntArray, k: Int): Int {
    // We want kth largest, which is at index k-1 in descending order
    // Use helper function starting from entire array (left=0, right=last index)
    return quickselect(nums, 0, nums.size - 1, k - 1)
}

/**
 * Recursive helper function that implements Quickselect
 * 
 * @param nums: The array to search in
 * @param left: Left boundary of current search range
 * @param right: Right boundary of current search range  
 * @param k: Target index (0-based) we're looking for
 * @return: The element at the kth position when sorted in descending order
 */
fun quickselect(nums: IntArray, left: Int, right: Int, k: Int): Int {
    // Base case: if we've narrowed down to a single element, return it
    if (left == right) {
        return nums[left]
    }
    
    // Partition the array and get the final position of the pivot
    // After partition: all elements > pivot are to the left, <= pivot to the right
    val pivotIndex = partition(nums, left, right)
    
    // Check if pivot ended up at our target position k
    if (pivotIndex == k) {
        // Found it! The pivot is the kth largest element
        return nums[k]
    } else if (pivotIndex < k) {
        // Pivot is too far left, kth largest must be in right partition
        // Search right side: from (pivotIndex + 1) to right
        return quickselect(nums, pivotIndex + 1, right, k)
    } else {
        // Pivot is too far right, kth largest must be in left partition  
        // Search left side: from left to (pivotIndex - 1)
        return quickselect(nums, left, pivotIndex - 1, k)
    }
}

/**
 * Partition function (similar to Quicksort)
 * 
 * Rearranges elements so all elements greater than pivot are on the left,
 * and elements less than or equal to pivot are on the right.
 * This gives us a descending order partition.
 * 
 * @param nums: Array to partition
 * @param left: Left boundary of partition range
 * @param right: Right boundary of partition range
 * @return: Final index position of the pivot element
 */
fun partition(nums: IntArray, left: Int, right: Int): Int {
    // Choose rightmost element as pivot (could choose randomly for better average case)
    val pivot = nums[right]
    
    // i tracks the boundary between elements > pivot (left side) and <= pivot (right side)
    var i = left
    
    // Scan through elements from left to right-1
    for (j in left until right) {
        // If current element is greater than pivot, it belongs on the left side
        if (nums[j] > pivot) {
            // Swap nums[i] with nums[j] to move larger element to left partition
            val temp = nums[i]
            nums[i] = nums[j]
            nums[j] = temp
            // Move boundary one position right
            i++
        }
        // If nums[j] <= pivot, leave it on the right side (do nothing)
    }
    
    // Finally, place pivot in its correct position (at index i)
    // Swap pivot (currently at right) with element at boundary position i
    val temp = nums[i]
    nums[i] = nums[right]
    nums[right] = temp
    
    // Return the final position of the pivot
    return i
}
