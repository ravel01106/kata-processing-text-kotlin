package dev.kata.processingtext

import java.util.*
import kotlin.collections.HashMap

class ProcessorText:Processor {
    override fun analyse(text: String): String {
        var msg = "Those are the top 10 words used:\n\n"

        val dividedText = text.lowercase(Locale.getDefault()).split(Regex("([,.\\s]+)")).toMutableList()
        val wordsWithTheirIteration = this.obtainWordsWithTheirIterations(dividedText)
        val eachIterationWithWords = HashMap<Int, MutableList<String>>()
        val mostUsedWords = mutableListOf<String>()


        wordsWithTheirIteration.forEach{
            var valueDefault = eachIterationWithWords.getOrDefault(it.value, mutableListOf())
            valueDefault.add(it.key)
            valueDefault = valueDefault.sorted().toMutableList()
            eachIterationWithWords[it.value] = valueDefault
        }

        eachIterationWithWords.forEach{ it->
            it.value.forEach{mostUsedWords.add(it)}
        }
        var count = 1
        mostUsedWords.reversed().slice(0..9).forEach{
            msg += "${count}. ${it}\n"
            count += 1
        }
        val totalWords = text.lowercase(Locale.getDefault()).split(" ").size
        msg += "The text has in total $totalWords words"
        return msg
    }

    private fun obtainWordsWithTheirIterations(words:MutableList<String>):HashMap<String, Int>{
        val wordsWithTheirIteration = HashMap<String, Int>()
        for (word in words){
            val oldCount = wordsWithTheirIteration.getOrDefault(word, 0)
            wordsWithTheirIteration[word] = oldCount + 1
        }
        return wordsWithTheirIteration
    }


    override fun calculateReadingTime(text: String): String {
        var msg = "The reading time for this article is "
        val  READING_SPEED = 200.0

        val totalWords = text.split(" ", "\n").toMutableList().size
        val speedInMinutes =  (totalWords / READING_SPEED).toInt()
        val speedInSeconds = ((totalWords / READING_SPEED) - speedInMinutes) * 0.60
        var seconds = speedInSeconds.toString().split(".")[1].substring(0,2)
        seconds = if (seconds.startsWith("0")) seconds.substring(1) else seconds

         msg += "$speedInMinutes minutes and $seconds seconds."
        return msg
    }
}