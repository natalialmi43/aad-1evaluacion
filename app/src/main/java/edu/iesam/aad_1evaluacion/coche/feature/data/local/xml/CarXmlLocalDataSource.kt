package edu.iesam.aad_1evaluacion.coche.feature.data.local.xml

import android.content.Context
import edu.iesam.aad_1evaluacion.R
import edu.iesam.aad_1evaluacion.coche.feature.domain.Car
import edu.iesam.aad_1evaluacion.coche.feature.domain.Driver
import edu.iesam.aad_1evaluacion.core.cache.AppCache
import edu.iesam.aad_1evaluacion.core.serialization.JsonSerializer

class CarXmlLocalDataSource (
    private val context: Context,
    private val serializer: JsonSerializer<List<Car>>,
    private val appCache: AppCache
){

    private val keyCar : String = "Car"
    private val ttl : Long = 2*1000

    val sharedPref = context.getSharedPreferences(context.getString(R.string.car_xml_local_source),
        Context.MODE_PRIVATE)


    fun save (car: List<Car>){

        sharedPref.edit().apply{
            putString(keyCar, serializer.toJson(car))
            apply()
        }

        appCache.updateTime(keyCar)
    }

    fun getAll () : List<Car>{

        if(!appCache.isExpired(keyCar, ttl)){

            val jsonList = sharedPref.getString(keyCar, null)

            jsonList?.let {
                return serializer.fromJson(it)
            }
        }
        return emptyList()
    }

    fun deleteById (tuition : String){
        save(getAll().filterNot { it.tuition == tuition })
    }

    fun getById (tuition: String) : Car? {

        return getAll().find { it.tuition == tuition }

    }

    fun update (car: Car){

        val cars = getAll().filterNot { it.tuition == car.tuition }.toMutableList()

        cars.add(car)

        save(cars)
    }

    fun getOwnerDetail (tuition: String) : Driver? {

        val driver = getById(tuition)?.driver

        return driver

    }


}