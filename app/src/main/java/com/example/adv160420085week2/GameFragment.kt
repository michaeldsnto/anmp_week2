package com.example.adv160420085week2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_game.*
import kotlin.random.Random


class GameFragment : Fragment() {

    var angka1: Int = 0
    var angka2: Int = 0
    var point: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            val playerName =
                GameFragmentArgs.fromBundle(requireArguments()).playerName
            txtTurn.text = "$playerName's Turn"
        }
//        arguments?.let {
//            val playerName =
//                GameFragmentArgs.fromBundle(requireArguments()).playerName
//            val txtTurn = view.findViewById<TextView>(R.id.txtTurn)
//            txtTurn.text = "$playerName's Turn"
//        }

        val btnAnswer = view.findViewById<Button>(R.id.btnAnswer)
        val txtNumber = view.findViewById<TextView>(R.id.txtNumber)
        val txtAnswer = view.findViewById<TextView>(R.id.txtAnswer)
        RandomAngka()

        btnAnswer.setOnClickListener {
            CekJawaban()
        }
    }

    private fun RandomAngka() {
        angka1 = Random.nextInt(101)
        angka2 = Random.nextInt(101)

        txtNumber.setText("${angka1.toString()} + ${angka2.toString()}")
    }

    private fun CekJawaban() {
        val answer = txtAnswer.text.toString()
        val jawabanUser = answer.toInt()
        val hasil = angka1 + angka2

        if (jawabanUser == hasil) {
            RandomAngka()
            point+=1
        } else {
            val action = GameFragmentDirections.actionResultFragment(point)
            Navigation.findNavController(requireView()).navigate(action)
        }
    }
}