package dev.kata.processingtext

interface Processor {
    fun analyse(text:String):String;
    fun calculateReadingTime(text:String):String;
}