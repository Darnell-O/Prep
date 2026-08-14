package com.company.arrays

    /**
     * Find Second Maximum Value in an Array
     */
    fun main(){
        val myArray = intArrayOf(1000, 2, 4, 5, 10, 21, 100)

        println(secondMaxNumber(myArray))
    }

    fun secondMaxNumber(array: IntArray): Int {
        var largest = Int.MIN_VALUE
        var secondLargest = Int.MIN_VALUE

        for (i in array.indices) {
            if (array[i] > largest) {
                secondLargest = largest
                largest = array[i]
            } else if (array[i] > secondLargest) {
                secondLargest = array[i]
            }
        }

        return secondLargest
    }


