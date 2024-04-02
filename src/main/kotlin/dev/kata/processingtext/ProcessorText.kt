package dev.kata.processingtext

import java.util.*
import kotlin.collections.HashMap

class ProcessorText:Processor {
    override fun analyse(text: String): String {
        var msg = "Those are the top 10 words used:\n\n"
        val mostUsedWords = this.getTopTenMostUsedWord(text)

        msg = addListWordsToText(mostUsedWords, msg)
        msg += "The text has in total ${this.getTotalWords(text)} words"

        return msg
    }


    private fun getTotalWords(text:String):Int{
        return text.lowercase(Locale.getDefault()).split(" ").size
    }


    private fun divideTextByWords(text:String): MutableList<String>{
        return text.lowercase(Locale.getDefault()).split(Regex("([,.\\s]+)")).toMutableList()
    }


    private fun obtainWordsWithTheirIterations(words:MutableList<String>):HashMap<String, Int>{
        val wordsWithTheirIteration = HashMap<String, Int>()
        for (word in words){
            val oldCount = wordsWithTheirIteration.getOrDefault(word, 0)
            wordsWithTheirIteration[word] = oldCount + 1
        }
        return wordsWithTheirIteration
    }


    private fun obtainIterationWithWords(wordsWithIterations:HashMap<String, Int>):HashMap<Int, MutableList<String>>{
        val iterationWithWords = HashMap<Int, MutableList<String>>()
        wordsWithIterations.forEach{
            var valueDefault = iterationWithWords.getOrDefault(it.value, mutableListOf())
            valueDefault.add(it.key)
            valueDefault = valueDefault.sorted().toMutableList()
            iterationWithWords[it.value] = valueDefault
        }
        return iterationWithWords
    }


    private fun getTopTenMostUsedWord(text:String):List<String>{
        val dividedText = this.divideTextByWords(text)
        val wordsWithTheirIteration = this.obtainWordsWithTheirIterations(dividedText)
        val iterationWithWords = this.obtainIterationWithWords(wordsWithTheirIteration)
        val mostUsedWords = mutableListOf<String>()

        iterationWithWords.forEach{ it->
            it.value.forEach{mostUsedWords.add(it)}
        }

        return mostUsedWords.reversed().slice(0..9)
    }


    private fun addListWordsToText(words:List<String>, text:String):String{
        var msg = text
        var count = 1
        words.forEach{
            msg += "${count}. ${it}\n"
            count += 1
        }
        return msg
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