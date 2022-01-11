package fr.isima.ayangelophjambaud.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import fr.isima.ayangelophjambaud.R

class MainFragment : Fragment() {

    fun onButtonClick(view : View) {
        val textView = view.findViewById<TextView>(R.id.textView)
        val editText = view.findViewById<EditText>(R.id.edit)
        val button = view.findViewById<Button>(R.id.button)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)



        //button.isVisible = false
        //progressBar.isVisible = true
//
//
        //MainScope().launch { // launch a new coroutine and continue
        //    delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
        //    button.isVisible = true
        //    progressBar.isVisible = false
        //    textView.text = editText.text
        //    println("AH")
        //}


        //val button = view.findViewById<Button>(R.id.button)
        //button.setBackgroundColor(ContextCompat.getColor(view.context,R.color.newColor))
        //Toast.makeText(view.context,R.string.toast_message, Toast.LENGTH_LONG).show()
        //Log.v(TAG, "appui")

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
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }




}