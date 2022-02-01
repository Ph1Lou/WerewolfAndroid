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
import fr.isima.ayangelophjambaud.adapters.DetailsViewItemAdapter
import fr.isima.ayangelophjambaud.R
import fr.isima.ayangelophjambaud.viewmodel.DetailsViewModel

private const val GAME_UUID = "game_uuid"

class DetailsFragment : Fragment() {

    private var gameUUID: String? = null
    private var recyclerView: RecyclerView? = null
    private var recyclerviewItemAdapter: DetailsViewItemAdapter? = null


    private lateinit var viewModel: DetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.details_fragment, container, false)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBarDetails)

        viewModel.items.observe(viewLifecycleOwner, { items ->
            recyclerviewItemAdapter = DetailsViewItemAdapter(items)
            recyclerView = view.findViewById(R.id.recyclerViewDetails)
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
        viewModel = ViewModelProvider(this, DetailsViewModelFactory(gameUUID)).get(DetailsViewModel::class.java)


    }

    class DetailsViewModelFactory(private val gameUUID: String?) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(String::class.java).newInstance(gameUUID)
        }

    }
}