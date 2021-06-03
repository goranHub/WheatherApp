package com.example.repository


import com.example.local.database.CityEntity
import com.example.local.database.MyLocationEntity
import com.example.model.response.ResponseByCity
import io.reactivex.Observable
import java.util.concurrent.Executors

/**
 * @author lllhr
 * @date 5/28/2021
 */
class Repository (
    private val  databdatabaseSource: com.example.local.database.DatabaseSource,
    private val weatherApi : com.example.remote.NetworkDataSource,
) {

    private val executor = Executors.newSingleThreadExecutor()

    fun searchByCurrentLocation(lati : Double, longi: Double): Observable<ResponseByCity> {
        return weatherApi.searchByCurrentLocation(lati, longi)
    }

    fun observableCityEntity(name : String): Observable<ResponseByCity> {
        return weatherApi.searchByCity(name)
    }

    fun getLocations() = databdatabaseSource.getLocations()

    //fun getLocation(id: UUID) = weatherDao.getLocation(id)

    fun updateLocation(myLocationEntity: MyLocationEntity) {
        executor.execute {
            databdatabaseSource.updateLocation(myLocationEntity)
        }
    }

    fun addLocation(myLocationEntity: MyLocationEntity) {
        executor.execute {
            databdatabaseSource.addLocation(myLocationEntity)
        }
    }

    fun insertCityWeather(cityEntity: CityEntity) {
        executor.execute {
            databdatabaseSource.insertCityWeather(cityEntity)
        }
    }

    fun addLocations(myLocationEntities: List<MyLocationEntity>) {
        executor.execute {
            databdatabaseSource.addLocations(myLocationEntities)
        }
    }

}