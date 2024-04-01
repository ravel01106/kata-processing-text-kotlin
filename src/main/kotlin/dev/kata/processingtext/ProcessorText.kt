package dev.kata.processingtext

import java.util.*
import kotlin.collections.HashMap

class ProcessorText:Processor {
    override fun analyse(text: String): String {
        var msg = "Those are the top 10 words used:\n\n";
        val mostUsedWords = mutableListOf<String>()
        val wordsWithTheirIteration = HashMap<String, Int>()
        val eachIterationWithWords = HashMap<Int, MutableList<String>>()
        val dividedText = text.lowercase(Locale.getDefault()).split(Regex("([,.\\s]+)")).toMutableList()

        for (word in dividedText){
            val oldCount = wordsWithTheirIteration.getOrDefault(word, 0)
            wordsWithTheirIteration[word] = oldCount + 1
        }

        wordsWithTheirIteration.forEach{ it ->
            var valueDefault = eachIterationWithWords.getOrDefault(it.value, mutableListOf<String>())
            valueDefault.add(it.key)
            valueDefault = valueDefault.sorted().toMutableList()
            eachIterationWithWords[it.value] = valueDefault
        }

        eachIterationWithWords.forEach{ it->
            it.value.forEach{mostUsedWords.add(it)}
        }
        var count = 1;
        mostUsedWords.reversed().slice(0..9).forEach{ it ->
            msg += "${count}. ${it}\n"
            count += 1
        }
        val totalWords = text.lowercase(Locale.getDefault()).split(" ").size
        msg += "The text has in total $totalWords words"
        return msg
    }
}