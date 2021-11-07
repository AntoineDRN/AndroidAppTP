package com.example.neighbor.data

import com.example.neighbor.data.service.DummyNeighborApiService
import com.example.neighbor.data.service.NeighborApiService
import com.example.neighbor.models.Neighbor

class NeighborRepository {
    private val apiService: NeighborApiService

    init {
        apiService = DummyNeighborApiService()
    }

    fun deleteNeighbor(neighbor: Neighbor) = apiService.deleteNeighbour(neighbor)
    fun getNeighbours(): List<Neighbor> = apiService.neighbours
    fun createNeighbor(neighbor: Neighbor) = apiService.createNeighbour(neighbor)


    companion object {
        private var instance: NeighborRepository? = null
        fun getInstance(): NeighborRepository {
            if (instance == null) {
                instance = NeighborRepository()
            }
            return instance!!
        }
    }
}