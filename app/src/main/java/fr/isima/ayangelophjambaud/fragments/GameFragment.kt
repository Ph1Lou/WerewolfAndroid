package fr.isima.ayangelophjambaud.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.isima.ayangelophjambaud.MainActivity
import fr.isima.ayangelophjambaud.R
import fr.isima.ayangelophjambaud.adapters.GameViewItemAdapter
import fr.isima.ayangelophjambaud.viewmodel.GamesViewModel


class GameFragment : Fragment() {

    private var recyclerView: RecyclerView? = null
    private var recyclerviewItemAdapter: GameViewItemAdapter? = null
    private val model: GamesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.game_fragment, container, false)

        val progressBar = view.findViewById<ProgressBar>(R.id.progressBarGames)

        model.items.observe(viewLifecycleOwner) { items ->
            recyclerviewItemAdapter = GameViewItemAdapter(items)
            recyclerView = view.findViewById(R.id.recyclerView)
            recyclerView?.setHasFixedSize(true)
            recyclerView?.layoutManager =LinearLayoutManager(view.context)
            recyclerView?.itemAnimator = DefaultItemAnimator()
            recyclerView?.adapter = recyclerviewItemAdapter
            progressBar.visibility = View.INVISIBLE
        }

        removeBackButton()

        return view
    }

    private fun removeBackButton() {
        if (activity is MainActivity) {
            val actionBar = (activity as MainActivity).supportActionBar
            actionBar?.setDisplayHomeAsUpEnabled(false)
            actionBar?.title = getString(R.string.app_name)
        }
    }
}