package com.example.library.utils

class GeneratedAlphabetId {
    companion object {

        fun generateId(id: String): String {
            var prefix = id.take(2)              // AA
            val number = id.substring(2).toInt() // 0001 -> 1

            val (newPrefix, newNumber) =
                if (number >= 9999) {
                    nextAlphabet(prefix) to 1
                } else {
                    prefix to (number + 1)
                }

            return newPrefix + newNumber.toString().padStart(4, '0')
        }

        fun nextAlphabet(input: String): String {
            val chars = input.toCharArray()

            if (chars[1] == 'Z') {
                chars[1] = 'A'
                chars[0] = chars[0] + 1
            } else {
                chars[1] = chars[1] + 1
            }

            return chars.concatToString()
        }
    }
}
