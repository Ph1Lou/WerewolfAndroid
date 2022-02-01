package fr.isima.ayangelophjambaud.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.isima.ayangelophjambaud.R
import fr.isima.ayangelophjambaud.adapters.PlayersViewItemAdapter
import fr.isima.ayangelophjambaud.viewmodel.PlayersViewModel

private const val GAME_UUID = "game_uuid"

class PlayersFragment : Fragment() {
    private var gameUUID: String? = null
    private var recyclerView: RecyclerView? = null
    private var recyclerviewItemAdapter: PlayersViewItemAdapter? = null
    private lateinit var viewModel: PlayersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.players_fragment, container, false)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBarPlayers)

        viewModel.items.observe(viewLifecycleOwner, { items ->
            recyclerviewItemAdapter = PlayersViewItemAdapter(items)
            recyclerView = view.findViewById(R.id.recyclerViewPlayers)
            recyclerView?.setHasFixedSize(true)
            val layoutManager: RecyclerView.LayoutManager =
                LinearLayoutManager(view.context)

            recyclerView?.layoutManager = layoutManager
            recyclerView?.itemAnimator = DefaultItemAnimator()
            recyclerView?.adapter = recyclerviewItemAdapter
            progressBar.visibility = View.INVISIBLE
        })

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            gameUUID = it.getString(GAME_UUID)
        }
        viewModel = ViewModelProvider(this, PlayersViewModelFactory(gameUUID)).get(PlayersViewModel::class.java)

    }

    class PlayersViewModelFactory(private val gameUUID: String?) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(String::class.java).newInstance(gameUUID)
        }

    }
}