package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import java.io.Serializable

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ExampleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ExampleFragment : Fragment() {

    private var title: String =""
    lateinit private var animals: ArrayList<Animal>
    lateinit var adapter: AnimalsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureView()
    }

    private fun configureView(){
        textTitle.text = title
        setUpRecyclerView(animals)
    }

    private fun setUpRecyclerView(animals: ArrayList<Animal>){
        rvAnimals.setHasfixedSize(true)
        rvAnimals.layoutManager = LinearLayoutManager(activity)
        rvAnimals.adapter = adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if( arguments != null){
            title = arguments.getString(ARG_TITLE)
            animals = arguments.getSerializable(ARG_ANIMALS) as ArrayList<Animal>
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_example, container, false)
    }

    companion object {
        private val ARG_TITLE = "ARG_TITLE"
        private val ARG_ANIMALS = "ARG_ANIMALS"

        fun newInstance(title:String, animals: ArrayList<Animal>) : ExampleFragment{
            val fragment = ExampleFragment()
            val args = Bundle()
            args.putString(ARG_TITLE,title)
            args.putSerializable(ARG_ANIMALS, animals as Serializable)
            fragment.arguments = args
            return fragment
        }
    }
}