package services

import kotlin.math.abs
import kotlin.math.ceil
import kotlin.system.exitProcess

class Splitter {
    fun splitString(message: String): Unit {
        val messageToCharArray = message.toCharArray()

        val spacePositions: MutableList<Int> = emptyList<Int>().toMutableList()
        val tweetStorm = emptyList<String>().toMutableList()

        val sampleHeaderLength = "xx/yy ".length
        val usefulTweetContent = 140 - sampleHeaderLength
        val tweetsNeeded = message.chunked(usefulTweetContent).size

        extractSpacePositions(messageToCharArray, spacePositions)

        var startingCharIndex = 0
        var messageCounter = 1
        while (tweetStorm.size < tweetsNeeded) {
            val header = "${messageCounter.toString().padStart(2, '0')}/${tweetsNeeded.toString().padStart(2, '0')} "

            val endPosition = getIterationLastIndex(startingCharIndex, usefulTweetContent, message)
            val lineBreak: Int? = if (endPosition == message.length - 1) message.length
            else spacePositions.closestValue(endPosition)
            spacePositions.removeIf { it <= (lineBreak ?: 0) }

            val splittedMessage =
                "$header${message.substring(startingCharIndex, lineBreak ?: endPosition).trimStart().trimEnd()}"

            tweetStorm.add(splittedMessage)

            messageCounter++

            startingCharIndex += (lineBreak?.minus(startingCharIndex)) ?: 134
        }

        printTweetStorm(tweetStorm)

        exitProcess(0)
    }

    private fun printTweetStorm(tweetStorm: MutableList<String>) {
        tweetStorm.map { println(it) }
    }

    private fun getIterationLastIndex(
        startingCharIndex: Int,
        usefulTweetContent: Int,
        message: String
    ): Int {
        val endPosition = when {
            startingCharIndex == 0 -> usefulTweetContent
            (startingCharIndex + usefulTweetContent) >= message.length - 1 -> message.length - 1
            else -> (startingCharIndex + usefulTweetContent)
        }
        return endPosition
    }

    private fun extractSpacePositions(charArray: CharArray, spacePositions: MutableList<Int>) {
        for (i in charArray.indices) {
            if (charArray[i].isWhitespace()) spacePositions.add(i)
        }
    }


    private fun List<Int>.closestValue(value: Int): Int? {
        var closest: Int? = 0
        for (item in this) {
            if (item <= value) closest = item
            else continue
        }
        return closest
    }
}
