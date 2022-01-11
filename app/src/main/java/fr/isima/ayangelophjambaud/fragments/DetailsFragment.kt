package fr.isima.ayangelophjambaud.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.isima.ayangelophjambaud.DetailsViewItemAdapter
import fr.isima.ayangelophjambaud.R
import fr.isima.ayangelophjambaud.viewmodel.DetailsViewModel

private const val GAME_UUID = "game_uuid"

class DetailsFragment : Fragment() {

    private var gameUUID: String? = null
    private var recyclerView: RecyclerView? = null
    private var recyclerviewItemAdapter: DetailsViewItemAdapter? = null
    private val model: DetailsViewModel by viewModels()

    companion object {
        fun newInstance() = DetailsFragment().apply {
            arguments = Bundle().apply {
                putString(GAME_UUID, gameUUID)
            }
        }
    }

    private lateinit var viewModel: DetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.game_fragment, container, false)

        model.items.observe(viewLifecycleOwner, { items ->
            recyclerviewItemAdapter = DetailsViewItemAdapter(items)
            recyclerView = view.findViewById(R.id.recyclerViewDetails)
            recyclerView?.setHasFixedSize(true)
            val layoutManager: RecyclerView.LayoutManager =
                LinearLayoutManager(view.context)

            recyclerView?.layoutManager = layoutManager
            recyclerView?.itemAnimator = DefaultItemAnimator()
            recyclerView?.adapter = recyclerviewItemAdapter
        })

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            gameUUID = it.getString(GAME_UUID)
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}