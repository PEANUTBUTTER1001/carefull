package com.openstudy.carefull.screen.routine.diet_data.repository


import com.openstudy.carefull.screen.routine.diet_shared.model.MealRecord
import com.openstudy.carefull.screen.routine.diet_shared.model.MealType
import com.openstudy.carefull.screen.routine.diet_shared.repository.DietRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Singleton

@Singleton
class DietRepositoryImpl @Inject constructor() : DietRepository {
    // 더미데이터
    private val fakeMealDatabase = MutableStateFlow<List<MealRecord>>(
        listOf(
            MealRecord(1L, "삶은 계란", 155, MealType.BREAKFAST),
            MealRecord(2L, "아몬드", 100, MealType.SNACK),
            MealRecord(3L, "우유", 50, MealType.LUNCH),
            MealRecord(4L, "바나나", 100, MealType.DINNER),
            MealRecord(5L, "김치", 50, MealType.SNACK),
            MealRecord(6L, "라면", 100, MealType.BREAKFAST),
            MealRecord(7L, "피자", 200, MealType.LUNCH),
            MealRecord(8L, "햄버거", 150, MealType.DINNER)
        )
    )

    override fun getAllMeals(): Flow<List<MealRecord>> {
        return fakeMealDatabase
    }

    override suspend fun addMeal(mealRecord: MealRecord) {
        fakeMealDatabase.update { currentList ->
            currentList + mealRecord
        }
    }

    override suspend fun removeMeal(mealRecord: MealRecord) {
        fakeMealDatabase.update { currentList ->
            currentList.filterNot { it.id == mealRecord.id }
        }
    }
}