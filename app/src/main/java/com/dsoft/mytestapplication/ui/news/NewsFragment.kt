package com.dsoft.mytestapplication.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.dsoft.mytestapplication.R
import com.dsoft.mytestapplication.databinding.NewsFragmentBinding
import com.dsoft.mytestapplication.ui.news.adapter.NewsAdapter
import com.dsoft.mytestapplication.util.EmptyDividerDecoration
import com.dsoft.mytestapplication.util.Response
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private var _binding: NewsFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<NewsViewModel>()

    private lateinit var rvAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = NewsFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindRecyclerView()
        subscribeToArticles()
    }

    private fun subscribeToArticles() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.articles.collectLatest {
                    when (it) {
                        is Response.Loading -> showProgress()
                        is Response.Success -> {
                            hideProgress()
                            it.let { item ->
                                rvAdapter.setData(item.data?.list.orEmpty())
                            }
                        }
                    }

                }
            }
        }
    }

    private fun showProgress() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        binding.progressBar.visibility = View.GONE
    }

    private fun bindRecyclerView() {
        rvAdapter = NewsAdapter()
        binding.recyclerView.apply {
            adapter = rvAdapter

            if (itemDecorationCount == 0) {
                addItemDecoration(EmptyDividerDecoration(requireContext(), R.dimen.baseline_8dp))
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}