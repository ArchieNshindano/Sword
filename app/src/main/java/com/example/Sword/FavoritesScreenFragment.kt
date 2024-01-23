@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.Sword

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.archie.Sword.screenViews.favouritesScreen.FavoritesScreen
import com.archie.Sword.viewModels.FavouritesScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesScreenFragment : Fragment() {


    private val viewModel: FavouritesScreenViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return ComposeView(requireContext()).apply {

            setContent {

                val state = viewModel.state.collectAsStateWithLifecycle()

                FavoritesScreen(viewModel = viewModel, states = state.value)

            } // SET CONTENT ENDS


        } // COMPOSE VIEW ENDS

    } // onCreateView ENDS

}