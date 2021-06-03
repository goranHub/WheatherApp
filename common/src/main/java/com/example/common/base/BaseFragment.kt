package com.example.common.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.common.extension.setupSnackbar
import com.example.navigation.NavCommand
import com.google.android.material.snackbar.Snackbar


abstract class BaseFragment: Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeNavigation(getViewModel())
        setupSnackbar(this, getViewModel().snackBarError, Snackbar.LENGTH_LONG)
    }

    abstract fun getViewModel(): BaseViewModel


    private fun observeNavigation(viewModel: BaseViewModel) {
        viewModel.navigation.observe(viewLifecycleOwner, Observer {
            it?.getContentIfNotHandled()?.let { command ->
                when (command) {
                    is NavCommand.To -> findNavController().navigate(command.directions, getExtras())
                    is NavCommand.Back -> findNavController().navigateUp()
                }
            }
        })
    }


    open fun getExtras(): FragmentNavigator.Extras = FragmentNavigatorExtras()
}