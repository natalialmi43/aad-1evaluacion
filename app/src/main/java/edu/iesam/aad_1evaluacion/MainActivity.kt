package edu.iesam.aad_1evaluacion

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.iesam.aad_1evaluacion.coche.feature.data.local.xml.CarXmlLocalDataSource
import edu.iesam.aad_1evaluacion.coche.feature.domain.Car
import edu.iesam.aad_1evaluacion.coche.feature.domain.Driver
import edu.iesam.aad_1evaluacion.core.cache.AppCacheTtl
import edu.iesam.aad_1evaluacion.core.serialization.GsonJsonSerializer
import edu.iesam.aad_1evaluacion.core.time.LocalAppTime

class MainActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }

        save()
        delete()
        getById()
        getAll()
        upDate()
    }


    val xml = CarXmlLocalDataSource(
        this,
        GsonJsonSerializer.create(),
        AppCacheTtl(this, LocalAppTime()))

    val mockkCarlist : List <Car> = listOf(
        Car("123A", "Mercedes", "A3", Driver("001", "María", "Sánchez")),
        Car("456B", "Toyota", "A5", Driver ("002", "Paco", "Martín")),
        Car("789B", "Clio", "A6", Driver ("003", "Lucia", "Rodríguez"))
    )

    val mockkCar = Car("789B", "Tesla", "A9", Driver("004", "Azucena", "Encinar"))

    val TAG = MainActivity::class.simpleName


    fun save (){
        xml.save(mockkCarlist)
        Log.d(TAG, "The car:  " + mockkCarlist.toString() + "is saved" )
    }

    fun delete (){
        xml.deleteById("123A")
        Log.d(TAG, "The car with tuituion 123A is delete")
    }

    fun getById (){
        xml.getById("456B")
        Log.d(TAG, "The car with tuition 456B is: " + xml.getById("456B").toString() )
    }

    fun getAll (){
        xml.getAll()
        Log.d(TAG, "The list of car are: " + xml.getAll().toString())

    }

    fun upDate(){
        xml.update(mockkCar)
        Log.d(TAG, "The list of car updating are: " + xml.getAll().toString())

    }






 }






