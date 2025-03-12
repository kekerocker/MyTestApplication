package com.dsoft.mytestapplication.ui.news

import androidx.lifecycle.viewModelScope
import com.dsoft.mytestapplication.domain.usecase.GetLatestNewsUseCase
import com.dsoft.mytestapplication.ui.news.state.NewsState
import com.dsoft.mytestapplication.util.BaseViewModel
import com.dsoft.mytestapplication.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getLatestNewsUseCase: GetLatestNewsUseCase
) : BaseViewModel() {

    private val mData: MutableStateFlow<Response<NewsState>> = MutableStateFlow(Response.Success(NewsState()))
    val articles: StateFlow<Response<NewsState>> = mData.asStateFlow()

    override val coroutineExceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, t ->
            viewModelScope.launch {
                val previous = mData.value.data ?: NewsState()
                sendMessage(t.message.toString())
                mData.value = Response.Success(previous)
            }
        }

    init {
        loadLatestNews()
    }

    private fun loadLatestNews() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            mData.value = Response.Loading()

            val result = getLatestNewsUseCase()
            mData.value = Response.Success(NewsState(result))
        }
    }
}