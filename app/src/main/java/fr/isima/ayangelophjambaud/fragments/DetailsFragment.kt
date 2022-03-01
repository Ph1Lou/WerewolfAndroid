package fr.isima.ayangelophjambaud.fragments

import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.isima.ayangelophjambaud.MainActivity
import fr.isima.ayangelophjambaud.R
import fr.isima.ayangelophjambaud.adapters.DetailsViewItemAdapter
import fr.isima.ayangelophjambaud.viewmodel.DetailsViewModel
import java.util.stream.Collectors


private const val GAME_UUID = "game_uuid"

class DetailsFragment : Fragment() {

    private var gameUUID: String? = null
    private var recyclerView: RecyclerView? = null
    private var recyclerviewItemAdapter: DetailsViewItemAdapter? = null
    private var order = true
    private lateinit var viewModel: DetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.details_fragment, container, false)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBarDetails)

        viewModel.items.observe(viewLifecycleOwner) { items ->
            recyclerviewItemAdapter = DetailsViewItemAdapter(items)
            recyclerView = view.findViewById(R.id.recyclerViewDetails)
            recyclerView?.setHasFixedSize(true)
            recyclerView?.layoutManager = LinearLayoutManager(view.context)
            recyclerView?.itemAnimator = DefaultItemAnimator()
            recyclerView?.adapter = recyclerviewItemAdapter
            progressBar.visibility = View.INVISIBLE
        }
        setHasOptionsMenu(true)
        if(activity is AppCompatActivity){
            (activity as AppCompatActivity).setSupportActionBar(view.findViewById(R.id.details_toolbar))
        }
        showBackButton()

        return view
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            gameUUID = it.getString(GAME_UUID)
        }
        viewModel = ViewModelProvider(this, DetailsViewModelFactory(gameUUID))[DetailsViewModel::class.java]


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action_item_sort, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.up -> {
            order=!order
            refreshRecyclerViewOrder()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    private fun refreshRecyclerViewOrder() {
        viewModel.items.observe(viewLifecycleOwner) { items ->
            var items2 = items
            if (!order) {
                items2 = items2.stream()
                    .sorted { prettyEvent1, prettyEvent2 -> prettyEvent2.timer - prettyEvent1.timer }
                    .collect(
                        Collectors.toList()
                    )
            }
            recyclerviewItemAdapter = DetailsViewItemAdapter(items2)
            recyclerView?.adapter = recyclerviewItemAdapter
        }
    }

    private fun showBackButton() {
        if (activity is MainActivity) {
            val actionBar = (activity as MainActivity).supportActionBar
            actionBar?.setDisplayHomeAsUpEnabled(true)
            actionBar?.title = getString(R.string.titleDetails)
        }
    }

    class DetailsViewModelFactory(private val gameUUID: String?) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(String::class.java).newInstance(gameUUID)
        }

    }
}