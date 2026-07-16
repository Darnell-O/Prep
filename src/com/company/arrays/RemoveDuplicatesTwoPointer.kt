package com.company.arrays

fun main() {
    val numList = listOf(0, 0, 1, 1, 1, 2, 2)
    //println(removeDuplicatesTwoPointer(numList as MutableList<Int>))
    println(removeDuplicatesTwoPointer2(numList as MutableList<Int>))
}


/**
 *Given a sorted list of numbers with length at least 1, remove duplicates and return the new length.
 * You must do this in-place and without using extra memory.
 *
 * Input: [0, 0, 1, 1, 1, 2, 2].
 *
 * Output: 3.
 *
 * Your function should modify the list in place so that the first three elements become 0, 1, 2.
 * Return 3 because the new length is 3.
 *
 * what i want to do is
 * iterate over the list with one pointer starting at one in front of the other a left and a right
 * j
 *
 * */

fun removeDuplicatesTwoPointer(numbers: MutableList<Int>): Int {
    var leftPointer = 0
    var rightPointer = 1
    while (leftPointer < numbers.lastIndex && rightPointer < numbers.lastIndex) {
        if (numbers[leftPointer] == numbers[rightPointer]) {
            numbers.removeAt(leftPointer)
            rightPointer + 1
            leftPointer = leftPointer + 1

        }
    }
    return leftPointer
}

fun removeDuplicatesTwoPointer2(numbers: MutableList<Int>): Int {
    if (numbers.isEmpty()) return 0
    var leftPointer = 0
    for (rightPointer in 1 until numbers.size) {
        if (numbers[rightPointer] != numbers[leftPointer]) {
            leftPointer++
            numbers[leftPointer] = numbers[rightPointer]
        }
    }
    return leftPointer + 1
}