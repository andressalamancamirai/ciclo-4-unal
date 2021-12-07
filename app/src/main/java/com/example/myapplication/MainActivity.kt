package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding
import org.jetbrains.anko.alert
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.jetbrains.anko.yesButton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var binding : ActivityMainBinding
    private lateinit var imagesPuppies : List<String>
    private lateinit var dogsAdapter: DogsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.searchBread.setOnQueryTextListener(this)
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        searchByName(query.toLowerCase())
        return true
    }

    private fun getRetrofil(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun searchByName(query:String){
        doAsync {
            val call = getRetrofil().create(APIService::class.java).getDogsByBreed("$query/images").execute()
            val puppies = call.body() as DogsResponse
            uiThread {
                if(puppies.status == "success"){
                    initDogs(puppies)
                }else{
                    showErrorDialog()
                }
            }
        }
    }

    private fun initDogs(puppies:DogsResponse){
        if(puppies.status == "success"){
            imagesPuppies = puppies.images
        }
        dogsAdapter = DogsAdapter(imagesPuppies)
        binding.rvDogs.setHasFixedSize(true)
        binding.rvDogs.layoutManager = LinearLayoutManager(this)
        binding.rvDogs.adapter = dogsAdapter
    }
    private fun showErrorDialog(){
        alert("Ha ocurrido un error, intetenelo de nuevo"){
            yesButton {  }
        }.show()

    }
}