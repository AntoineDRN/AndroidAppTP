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
import com.example.neighbor.R
import com.example.neighbor.adapters.ListNeighborHandler
import com.example.neighbor.adapters.ListNeighborsAdapter
import com.example.neighbor.data.NeighborRepository
import com.example.neighbor.models.Neighbor

class ListNeighborsFragment: Fragment(), ListNeighborHandler {

    private lateinit var recyclerView: RecyclerView

    /**
     * Fonction permettant de définir une vue à attacher à un fragment
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.list_neighbors_fragment, container, false)
        recyclerView = view.findViewById(R.id.neighbors_list)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val neighbors: List<Neighbor> = NeighborRepository.getInstance().getNeighbours()
        val adapter = ListNeighborsAdapter(neighbors, this)
        recyclerView.adapter = adapter
    }

    override fun onDeleteNeighbor(neighbor: Neighbor) {
        val builder = AlertDialog.Builder(this.context)
        builder.setTitle("Suppression")
        builder.setMessage("Voulez-vous supprimer cet utilisateur?")
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