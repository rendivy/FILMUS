package com.example.cinema_app.data.converter

class BudgetConverter {
    companion object {
        fun convertBudget(number: Int): String {
            val formattedNumber = number.toLong()
            val numberString = formattedNumber.toString()

            val reversed = numberString.reversed()
            val result = StringBuilder()

            for ((index, char) in reversed.withIndex()) {
                result.append(char)
                if (index % 3 == 2 && index < reversed.length - 1) {
                    result.append(' ')
                }
            }

            return "$" + result.reverse().toString()

        }
    }
}