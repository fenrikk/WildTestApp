package com.myapp.wildtestapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.myapp.wildtestapp.databinding.FragmentStartBinding
import com.myapp.wildtestapp.viewmodel.StartViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding
    private val viewModel by viewModel<StartViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel
    }
}