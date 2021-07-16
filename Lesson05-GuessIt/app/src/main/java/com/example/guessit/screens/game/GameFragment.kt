package com.example.guessit.screens.game

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.guessit.R
import com.example.guessit.databinding.FragmentGameBinding

/**
 * Fragment where the game is played
 */
class GameFragment : Fragment() {

    // ViewModel initialization by lazy
    private val viewModel: GameViewModel by viewModels()

    private lateinit var binding: FragmentGameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_game,
            container,
            false
        )

        // Call the function of ViewModel so that it is actually created
        Log.i("GameViewModel", viewModel.hashCode().toString())

        binding.correctButton.setOnClickListener {
            viewModel.onCorrect()
        }
        binding.skipButton.setOnClickListener {
            viewModel.onSkip()
        }

        /** Setting up LiveData observation relationship **/
        viewModel.word.observe(
            viewLifecycleOwner,
            Observer { newWord -> binding.wordText.text = newWord })
        viewModel.score.observe(
            viewLifecycleOwner,
            Observer { newScore -> binding.scoreText.text = newScore.toString() })

        return binding.root

    }

    /**
     * Called when the game is finished
     */
    private fun gameFinished() {
        val currentScore = viewModel.score.value ?: 0
        val action = GameFragmentDirections.actionGameToScore(currentScore)
        findNavController(this).navigate(action)
    }
}
