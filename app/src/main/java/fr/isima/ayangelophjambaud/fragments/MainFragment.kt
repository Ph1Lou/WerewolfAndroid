package fr.isima.ayangelophjambaud.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import fr.isima.ayangelophjambaud.MainActivity
import fr.isima.ayangelophjambaud.R

class MainFragment : Fragment() {

    private fun onButtonClick(view : View) {
        view.findNavController().navigate(R.id.gameFragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view  = inflater.inflate(R.layout.main_fragment, container, false)
        val button = view.findViewById<Button>(R.id.button)

        button.setOnClickListener {
            onButtonClick(view)
        }
        // Inflate the layout for this fragment
        removeBackButton()
        return view
    }

    private fun removeBackButton() {
        if (activity is MainActivity) {
            (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
    }
}