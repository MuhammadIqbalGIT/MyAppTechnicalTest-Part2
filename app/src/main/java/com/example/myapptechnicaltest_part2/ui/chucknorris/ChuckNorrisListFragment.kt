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
import com.example.myapptechnicaltest_part2.R


@AndroidEntryPoint
class ChuckNorrisListFragment : BaseFragment<FragmentChuckNorrisListBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentChuckNorrisListBinding
        get() = FragmentChuckNorrisListBinding::inflate

    private val viewModel: ChuckNorrisViewModel by viewModels()
    private lateinit var adapter: ChuckNorrisAdapter
    private var hasSearched = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            initUI()
            initObserve()
        }
    }

    override fun FragmentChuckNorrisListBinding.initUI() {
        adapter = ChuckNorrisAdapter()
        rvChuckNorris.layoutManager = LinearLayoutManager(requireContext())
        rvChuckNorris.adapter = adapter

        tilSearch.setEndIconOnClickListener {
            searchData()
        }
        btnSearch.setOnClickListener {
            searchData()
        }
    }

    private fun searchData() {
        val query = binding.etSearch.text?.toString()?.trim()
        hasSearched = true
        if (!query.isNullOrEmpty()) {
            binding.pbChuckNorris.visibility = View.VISIBLE
            viewModel.searchChuckNorris(query)
        } else {
            binding.tvEmptyState.text = getString(R.string.please_search)
            binding.tvEmptyState.visibility = View.VISIBLE
            adapter.submitList(emptyList())
        }
    }

    override fun FragmentChuckNorrisListBinding.initObserve() {
        lifecycleScope.launch {
            viewModel.searchChuckNorris.collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        pbChuckNorris.visibility = View.VISIBLE
                        tvEmptyState.visibility = View.GONE
                    }
                    is Resource.Success -> {
                        pbChuckNorris.visibility = View.GONE
                        val data = resource.data
                        adapter.submitList(data)

                        tvEmptyState.text = if (data.isNullOrEmpty()) {
                            if (hasSearched) getString(R.string.data_not_found)
                            else getString(R.string.please_search)
                        } else ""

                        tvEmptyState.visibility = if (data.isNullOrEmpty()) View.VISIBLE else View.GONE
                    }
                    is Resource.Error -> {
                        pbChuckNorris.visibility = View.GONE
                        adapter.submitList(emptyList())
                        tvEmptyState.text = getString(R.string.data_not_found)
                        tvEmptyState.visibility = View.VISIBLE
                    }
                    is Resource.Empty -> {
                        pbChuckNorris.visibility = View.GONE
                        adapter.submitList(emptyList())
                        tvEmptyState.text = getString(R.string.data_not_found)
                        tvEmptyState.visibility = View.VISIBLE
                    }
                    is Resource.Standby -> {
                        pbChuckNorris.visibility = View.GONE
                        tvEmptyState.text = getString(R.string.please_search)
                        tvEmptyState.visibility = View.VISIBLE
                    }
                }
            }
        }
    }
}
