package com.company.list

fun main() {
    val list1 = mutableListOf(0, 0, 1, 1, 1, 2, 2)
    val len1 = removeDuplicates(list1)
    println("New length: $len1")            // 3
    println("Result: ${list1.subList(0, len1)}") // [0, 1, 2]

    val list2 = mutableListOf(1, 1, 1, 1)
    println("New length: ${removeDuplicates(list2)}") // 1

    val list3 = mutableListOf(1, 2, 3)
    println("New length: ${removeDuplicates(list3)}") // 3
}

/**
 * Given a sorted list of numbers with length at least 1, remove duplicates
 * in-place and return the new length. No extra memory is used.
 *
 * Example:
 *   Input:  [0, 0, 1, 1, 1, 2, 2]
 *   Output: 3  (list modified so first 3 elements are [0, 1, 2])
 *
 * Two-pointer approach:
 *   - [slow] is the write pointer — it marks the last confirmed unique position.
 *   - [fast] scans every element looking for values different from arr[slow].
 *   - When a new unique value is found, slow advances and arr[slow] is overwritten.
 */
fun removeDuplicates(arr: MutableList<Int>): Int {
    if (arr.isEmpty()) return 0 //check if array is empty

    var slow = 0 // write pointer: index of the last unique element written

    for (fast in 1 until arr.size) {
        if (arr[fast] != arr[slow]) {
            slow++
            arr[slow] = arr[fast]
        }
    }

    return slow + 1 // length = last unique index + 1
}
