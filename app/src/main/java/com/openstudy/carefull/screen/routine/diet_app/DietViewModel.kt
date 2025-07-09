package com.openstudy.carefull.screen.routine.diet_app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openstudy.carefull.screen.routine.diet_shared.model.MealRecord
import com.openstudy.carefull.screen.routine.diet_shared.model.MealType
import com.openstudy.carefull.screen.routine.diet_shared.repository.DietRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

// UI에 보여줄 상태를 정의하는 데이터 클래스

data class DietUiState(
    val mealsByTime: Map<MealType, List<MealRecord>> = emptyMap(),
    val totalCalories: Int = 0,
    val isLoading: Boolean = true // 초기 로딩 상태를 표시
)

@HiltViewModel
class DietViewModel @Inject constructor(
    private val dietRepository: DietRepository
) : ViewModel() {

    val uiState: StateFlow<DietUiState> = dietRepository.getAllMeals()
        .map { meals ->
            DietUiState(
                mealsByTime = meals.groupBy { it.mealType },
                totalCalories = meals.sumOf { it.calories },
                isLoading = false
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = DietUiState()
        )

    fun onAddMeal(mealRecord: MealRecord) {
        viewModelScope.launch {
            dietRepository.addMeal(mealRecord)
        }
    }

    fun onRemoveFood(mealRecord: MealRecord) {
        viewModelScope.launch {
            dietRepository.removeMeal(mealRecord)
        }
    }
}