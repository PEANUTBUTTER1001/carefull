package com.openstudy.carefull.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openstudy.carefull.data.network.MedicineItem
import com.openstudy.carefull.data.repository.MedicineRepository
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
//        viewModelScope.launch {
//            val result = repository.getMedicineByName(name)
//            _medicineList.value = result
//        }
        viewModelScope.launch {
            val response = repository.getRawDrugInfo(name)
            if (response.isSuccessful) {
                val rawJson = response.body()?.string()
                Log.d("RAW_RESPONSE", rawJson ?: "null")
            } else {
                Log.e("RAW_RESPONSE", "응답 실패 코드: ${response.code()}")
            }
        }
    }

    fun setSelectedItem(item: MedicineItem) {
        _selectedItem.value = item
    }
}