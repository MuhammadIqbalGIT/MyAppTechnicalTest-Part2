package com.example.myapptechnicaltest_part2.ui.chucknorris

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.data.source.Resource
import com.example.myapptechnicaltest_part2.databinding.FragmentChuckNorrisListBinding
import com.example.myapptechnicaltest_part2.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.getValue

@AndroidEntryPoint
class ChuckNorrisListFragment : BaseFragment<FragmentChuckNorrisListBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentChuckNorrisListBinding
        get() = FragmentChuckNorrisListBinding::inflate

    private val viewModel: ChuckNorrisViewModel by viewModels()
    private lateinit var adapter: ChuckNorrisAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            initUI()
            initObserve()
        }
    }

    override fun FragmentChuckNorrisListBinding.initUI() {
        adapter = ChuckNorrisAdapter()
        rvMeals.layoutManager = LinearLayoutManager(requireContext())
        rvMeals.adapter = adapter

        tilSearch.setEndIconOnClickListener {
            val query = etSearch.text?.toString()?.trim()
            if (!query.isNullOrEmpty()) {
                pbChuckNorris.visibility = View.VISIBLE
                viewModel.getMealsDetailsById(query)
            }
        }
    }


    override fun FragmentChuckNorrisListBinding.initObserve() {
        lifecycleScope.launch {
            viewModel.searchChuckNorris.collect { resource ->
                when (resource) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        pbChuckNorris.visibility = View.GONE
                        adapter.submitList(resource.data)
                    }
                    is Resource.Error -> {
                        pbChuckNorris.visibility = View.GONE
                        adapter.submitList(emptyList())
                    }
                    is Resource.Empty -> {
                        pbChuckNorris.visibility = View.GONE
                        adapter.submitList(emptyList())
                    }
                    is Resource.Standby -> {
                        pbChuckNorris.visibility = View.GONE
                    }
                }
            }
        }
    }
}
