package com.ezatpanah.mvi_themealdb_api.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import com.ezatpanah.mvi_themealdb_api.databinding.FragmentHomeBinding
import com.ezatpanah.mvi_themealdb_api.intent.home.HomeIntent
import com.ezatpanah.mvi_themealdb_api.state.HomeState
import com.ezatpanah.mvi_themealdb_api.utils.setupListWithAdapter
import com.ezatpanah.mvi_themealdb_api.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleScope.launch {
                //send
                viewModel.intentChannel.send(HomeIntent.GetFilterLetters)
                viewModel.intentChannel.send(HomeIntent.GetRandom)
                viewModel.intentChannel.send(HomeIntent.GetCategoriesList)

                //get
                viewModel.state.collect { state ->
                    when (state) {
                        is HomeState.Idle -> {}
                        is HomeState.FilterLetters -> {
                            filterSpinner.setupListWithAdapter(state.letters) {
                                //
                            }
                        }
                        is HomeState.RandomFood -> {
                            if (state.food != null) {
                                headerImg.load(state.food.strMealThumb){
                                    crossfade(true)
                                    crossfade(500)
                                }

                            }
                        }
                        is HomeState.Error -> {
                            Toast.makeText(requireContext(), state.error, Toast.LENGTH_SHORT).show()
                        }
                    }
                }


            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}