package com.example.finalproject.Agent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.finalproject.R

/**
 * A simple [Fragment] subclass.
 * Use the [WasteDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WasteDetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_waste_details, container, false)
    }


}