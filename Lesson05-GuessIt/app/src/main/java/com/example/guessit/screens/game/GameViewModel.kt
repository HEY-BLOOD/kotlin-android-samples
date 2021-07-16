package com.example.guessit.screens.game

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    // The current word
    var word = MutableLiveData<String>()

    // The current score
    var score = MutableLiveData<Int>()

    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>

    init {
        Log.i("GameViewModel", "GameViewModel created!")
        resetList()
        nextWord()
        score.value = 0
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel cleared!")
    }

    /**
     * Resets the list of words and randomizes the order
     */
    private fun resetList() {
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        wordList.shuffle()
    }

    /**
     * Moves to the next word in the list
     */
    private fun nextWord() {
        //Select and remove a word from the list
        if (wordList.isEmpty()) {
            // FIXME gameFinished()
        } else {
            word.value = wordList.removeAt(0)
        }
    }

    /** Methods for buttons presses **/

    fun onSkip() {
        score.run { value = value?.minus(1) }
        nextWord()
    }

    fun onCorrect() {
        score.run { value = value?.plus(1) }
        nextWord()
    }
}