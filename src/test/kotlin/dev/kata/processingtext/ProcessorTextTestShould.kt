package dev.kata.processingtext

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ProcessorTextTestShould{
    @Test
    fun ` show the 10 most used words in the text`(){
        val text = "Hello, this is an example for you to practice. You should grab this text and make it as your test case."
        val processText = ProcessorText()
        val outputResult = processText.analyse(text)
        val result = "Those are the top 10 words used:\n" +
                "\n" +
                "1. you\n" +
                "2. this\n" +
                "3. your\n" +
                "4. to\n" +
                "5. text\n" +
                "6. test\n" +
                "7. should\n" +
                "8. practice\n" +
                "9. make\n" +
                "10. it\n" +
                "The text has in total 21 words"
        assertEquals(result, outputResult)
    }
}