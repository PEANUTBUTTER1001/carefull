package com.openstudy.carefull.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openstudy.data.model.MedicineItem
import com.openstudy.data.repository.MedicineRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MedicineViewModel @Inject constructor(
    private val repository: MedicineRepository
) : ViewModel() {

    private val _medicineList = MutableStateFlow<List<MedicineItem>>(emptyList())
    val medicineList: StateFlow<List<MedicineItem>> = _medicineList

    private val _selectedItem = MutableStateFlow<MedicineItem?>(null)
    val selectedItem: StateFlow<MedicineItem?> = _selectedItem

    fun searchMedicine(name: String) {
        viewModelScope.launch {
            val result = repository.getMedicineByName(name)
            _medicineList.value = result
        }
    }

    fun setSelectedItem(item: MedicineItem) {
        _selectedItem.value = item
    }
}