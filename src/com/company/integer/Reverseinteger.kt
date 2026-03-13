package com.company.integer


    fun main() {
        /** Given a integer Input - 4321 Output - 1234
         * `reverseInteger` takes an integer and returns it with its digits in reverse order.
         * It works by repeatedly extracting the last digit of the input using the modulo operator (`% 10`),
         * building up the reversed number by shifting the result left one decimal place (`* 10`) and appending that digit,
         * then chopping off the last digit from the input (`/ 10`). This repeats until the input reaches 0.
         * __Input:__ `4321` → __Output:__ `1234`
         */


        println(reverseInteger(4321)) // Expected: 1234

        println(reverseInteger(1000)) // Expected: 1

        println(reverseInteger(7)) // Expected: 7
    }

    fun reverseInteger(number: Int): Int {
        var number = number
        var reverse = 0
        while (number != 0) {
            reverse = reverse * 10
            reverse = reverse + (number % 10)
            number = number / 10
        }

        return reverse
    }
