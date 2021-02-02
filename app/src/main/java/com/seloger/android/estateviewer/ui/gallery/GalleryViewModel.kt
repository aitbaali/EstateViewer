package com.seloger.android.estateviewer.ui.gallery

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seloger.android.estateviewer.data.DataSource
import com.seloger.android.estateviewer.data.entity.Estate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GalleryViewModel @ViewModelInject constructor(
    private val repository: DataSource
) : ViewModel() {

    private var _estates = MutableLiveData<List<Estate>>()
    val estates: LiveData<List<Estate>> = _estates

    init {
        viewModelScope.launch {
            _estates.value = repository.getEstates().value
        }
    }
}