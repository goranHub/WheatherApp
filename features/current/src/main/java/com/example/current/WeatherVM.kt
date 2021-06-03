package com.example.current

import android.location.Location
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.common.base.BaseViewModel
import com.example.common.toEntity
import com.example.common.toModel
import com.example.local.database.MyLocationEntity
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import com.example.repository.Repository

/**
 * @author lllhr
 * @date 5/28/2021
 */

private val TAG = WeatherVM::class.java.simpleName

class WeatherVM @ViewModelInject constructor(
    private val repository: Repository,
    val adapter: WeatherAdapter
) : BaseViewModel() {


/*    fun byName(disposable: CompositeDisposable) {
        disposable.add(
            repository.observableCityEntity("zagreb")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        //add to DB
                        repository.weatherDao.insertCityWeather(it.toModel().toEntity())
                        //add to adapter
                        adapter.add(it.weather)
                    },
                    { error ->
                        Log.e(TAG, "Unable to get CityWeather ", error)
                    }
                )
        )
    }*/


    fun byLocation(location : Location, disposable : CompositeDisposable) {
        disposable.add(
            repository.searchByCurrentLocation(location.latitude, location.longitude)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        //add to DB
                        repository.insertCityWeather(it.toModel().toEntity())
                        //add to adapter
                        adapter.add(it.weather)
                    },
                    { error ->
                        Log.e(TAG, "Unable to get CityWeather ", error)
                    }
                )
        )
    }


    fun locationListFromDB() {
        repository
            .getLocations()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<MyLocationEntity>> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(locations: List<MyLocationEntity>) {
                    for (location in locations) {
                        Log.d(TAG, location.latitude.toString())
                    }
                }

                override fun onError(e: Throwable) {
                }

                override fun onComplete() {
                }
            })
    }





}
