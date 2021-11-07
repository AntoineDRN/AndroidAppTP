package com.example.neighbor.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.neighbor.NavigationListener
import com.example.neighbor.R
import com.example.neighbor.adapters.ListNeighborsAdapter
import com.example.neighbor.data.NeighborRepository
import com.example.neighbor.models.Neighbor
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListNeighborsFragment: Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var addNeighbor: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as? NavigationListener)?.updateTitle(R.string.liste_voisins)
        val view = inflater.inflate(R.layout.list_neighbors_fragment, container, false)
        recyclerView = view.findViewById(R.id.neighbors_list)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        addNeighbor = view.findViewById(R.id.add_button)
        addNeighbor.setOnClickListener {
            (activity as? NavigationListener)?.showFragment(AddNeighbourFragment())
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val neighbors: List<Neighbor> = NeighborRepository.getInstance().getNeighbours()
        val adapter = ListNeighborsAdapter(neighbors, this)
        recyclerView.adapter = adapter
    }

    fun onDeleteNeighbor(neighbor: Neighbor) {
        val builder = AlertDialog.Builder(this.context)
        builder.setTitle("Suppression")
        builder.setMessage(getString(R.string.del_user_question))
        builder.setPositiveButton("oui") { dialog, _ ->
            NeighborRepository.getInstance().deleteNeighbor(neighbor)
            dialog.dismiss()
            refreshPage()
        }
        builder.setNegativeButton("Non") { dialog, _ ->
            dialog.dismiss()
        }
        val alertDialog = builder.create()
        alertDialog.show()
    }

    private fun refreshPage () {
        val neighbors: List<Neighbor> = NeighborRepository.getInstance().getNeighbours()
        val adapter = ListNeighborsAdapter(neighbors, this)
        recyclerView.adapter = adapter
    }
}