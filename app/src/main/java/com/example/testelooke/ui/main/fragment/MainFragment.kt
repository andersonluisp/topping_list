package com.example.testelooke.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testelooke.R
import com.example.testelooke.app.gone
import com.example.testelooke.app.visible
import com.example.testelooke.databinding.MainFragmentBinding
import com.example.testelooke.ui.main.adapter.MainAdapter
import com.example.testelooke.ui.main.viewmodel.MainViewModel

class MainFragment : Fragment() {

    private lateinit var b: MainFragmentBinding
    private val vm by lazy { MainViewModel() }

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        b = MainFragmentBinding.inflate(inflater)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupRecyclerview()
    }

    private fun setupObservers() {
        vm.donutsLiveData.observe(viewLifecycleOwner) {
            val toppingList = vm.setToppingList(it)
            (b.rvToppingTypes.adapter as MainAdapter).updateList(toppingList)
            setListViewState()
        }

        vm.responseError.observe(viewLifecycleOwner) {
            setErrorViewState(requireContext().getString(R.string.import_data_error_message))
        }
    }

    private fun setListViewState() {
        b.apply {
            pbProgressBar.gone()
            rvToppingTypes.visible()
        }
    }

    private fun setErrorViewState(errorMessage: String) {
        b.apply {
            pbProgressBar.gone()
            rvToppingTypes.gone()
            labelToppingTypes.gone()
            tvErrorApi.apply {
                text = errorMessage
                visible()
            }
        }
    }

    private fun setupRecyclerview() {
        b.rvToppingTypes.apply {
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            adapter = MainAdapter()
        }
    }
}