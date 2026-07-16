package com.company.arrays


fun main() {

    val numbers = arrayOf(1, 2, 0, 0, 0, 5)
    moveZeroes(numbers)
    println(numbers.toList())

    val numbers2 = arrayOf(1, 2, 3, 4, 0, 5)
    moveZeroes(numbers2)
    println(numbers2.toList())
}


fun moveZeroes(nums: Array<Int>) {
    /**
     * Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
     *
     * Note that you must do this in-place without making a copy of the array.
     *
     *Example 1:
     *
     * Input: nums = [0,1,0,3,12]
     * Output: [1,3,12,0,0]
     * Example 2:
     *
     * Input: nums = [0]
     * Output: [0]
     * */
    var slow = 0
    for (fast in nums.indices) {
        if (nums[fast] != 0) {
            val temp = nums[slow]
            nums[slow] = nums[fast]
            nums[fast] = temp
            slow++
        }
    }

}
