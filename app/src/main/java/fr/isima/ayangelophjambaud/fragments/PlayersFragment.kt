package fr.isima.ayangelophjambaud.fragments

import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.isima.ayangelophjambaud.MainActivity
import fr.isima.ayangelophjambaud.R
import fr.isima.ayangelophjambaud.adapters.PlayersViewItemAdapter
import fr.isima.ayangelophjambaud.viewmodel.PlayersViewModel
import java.util.stream.Collectors

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

        viewModel.items.observe(viewLifecycleOwner) { items ->
            recyclerviewItemAdapter = PlayersViewItemAdapter(items)
            recyclerView = view.findViewById(R.id.recyclerViewPlayers)
            recyclerView?.setHasFixedSize(true)
            recyclerView?.layoutManager = LinearLayoutManager(view.context)
            recyclerView?.itemAnimator = DefaultItemAnimator()
            recyclerView?.adapter = recyclerviewItemAdapter
            progressBar.visibility = View.INVISIBLE
        }

        setHasOptionsMenu(true)
        if(activity is AppCompatActivity){
            (activity as AppCompatActivity).setSupportActionBar(view?.findViewById(R.id.player_toolbar))
        }
        showBackButton()

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            gameUUID = it.getString(GAME_UUID)
        }
        viewModel = ViewModelProvider(this, PlayersViewModelFactory(gameUUID))[PlayersViewModel::class.java]



    }

    private fun showBackButton() {
        if (activity is MainActivity) {
            val actionBar = (activity as MainActivity).supportActionBar
            actionBar?.setDisplayHomeAsUpEnabled(true)
            actionBar?.title = getString(R.string.titlePlayers)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.searching_player, menu)
        val searchItem = menu.findItem(R.id.searching)
        val searchView = searchItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(text: String): Boolean {
                return false
            }

            override fun onQueryTextChange(text: String): Boolean {
                refreshRecyclerView(text)
                return true
            }
        })

    }

    private fun refreshRecyclerView(text: String) {
        viewModel.items.observe(viewLifecycleOwner) { items ->
            var items2 = items
            items2 = items2.stream()
                .filter{ it.name.startsWith(text, true)}
                .collect(
                    Collectors.toList()
                )
            recyclerviewItemAdapter = PlayersViewItemAdapter(items2)
            recyclerView?.adapter = recyclerviewItemAdapter
        }
    }

    class PlayersViewModelFactory(private val gameUUID: String?) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(String::class.java).newInstance(gameUUID)
        }

    }
}