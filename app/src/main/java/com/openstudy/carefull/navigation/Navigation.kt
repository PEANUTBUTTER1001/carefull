package com.openstudy.carefull.navigation

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.openstudy.carefull.screen.chatbot.ChatBotScreen
import com.openstudy.carefull.screen.hospital.HospitalInfoScreen
import com.openstudy.carefull.screen.hospital.HospitalSearchScreen
import com.openstudy.carefull.screen.medicine.MedicineInfoScreen
import com.openstudy.carefull.screen.medicine.MedicineSearchScreen
import com.openstudy.carefull.screen.Home
import com.openstudy.carefull.screen.Splash
import com.openstudy.carefull.screen.auth.ForgotPassword
import com.openstudy.carefull.screen.auth.Signin
import com.openstudy.carefull.screen.auth.Signup
import com.openstudy.carefull.screen.feed.Ranking
import com.openstudy.carefull.screen.feed.Social
import com.openstudy.carefull.screen.mypage.AccountManagement
import com.openstudy.carefull.screen.mypage.BasalMetabolicRateMeasurement
import com.openstudy.carefull.screen.mypage.MyPage
import com.openstudy.carefull.screen.mypage.PostWrittenManagement
import com.openstudy.carefull.screen.routine.diet_app.DietScreen
import com.openstudy.carefull.screen.routine.Exercise
import com.openstudy.carefull.screen.routine.FoodInformation
import com.openstudy.carefull.screen.routine.SearchFood
import com.openstudy.carefull.ui.theme.CarefullTheme
import com.openstudy.carefull.viewmodel.MedicineViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable


//인자 필요한 경우 object -> class 변경 하여 작성
//예: data class Profile(val id:String) : NavigationRoute()
@Serializable
sealed class NavigationRoute {

    @Serializable
    data object Splash : NavigationRoute()

    @Serializable
    data object Signin : NavigationRoute()

    @Serializable
    data object Signup : NavigationRoute()

    @Serializable
    data object ForgotPassword : NavigationRoute()

    @Serializable
    data object Home : NavigationRoute()

    @Serializable
    data object Exercise : NavigationRoute()

    @Serializable
    data object DietScreen : NavigationRoute()

    @Serializable
    data object SearchFood : NavigationRoute()

    @Serializable
    data object FoodInformation : NavigationRoute()

    @Serializable
    data object ChatBotScreen : NavigationRoute()

    @Serializable
    data object HospitalInfoScreen : NavigationRoute()

    @Serializable
    data object MedicineInfoScreen : NavigationRoute()

    @Serializable
    data object HospitalSearchScreen: NavigationRoute()

    @Serializable
    data object DiseaseSearchScreen : NavigationRoute()

    @Serializable
    data object MedicineSearchScreen : NavigationRoute()

    @Serializable
    data object Social : NavigationRoute()

    @Serializable
    data object Ranking : NavigationRoute()

    @Serializable
    data object MyPage : NavigationRoute()

    @Serializable
    data object AccountManagement : NavigationRoute()

    @Serializable
    data object BasalMetabolicRateMeasurement : NavigationRoute()

    @Serializable
    data object PostWrittenManagement : NavigationRoute()


}

sealed class AuthScreen {
    data object Signin : AuthScreen()
    data object Signup : AuthScreen()
    data object ForgotPassword : AuthScreen()
}

@Composable
fun NavigationControl() {
    val navController = rememberNavController()
    val viewModel: MedicineViewModel = hiltViewModel()
    val context = LocalContext.current
    val activity = (context as? Activity)
    val scope = rememberCoroutineScope()
    var backPressedOnce by remember { mutableStateOf(false) }


    // 홈화면에서 뒤로가기 두번(2초안에) 눌렀을 때 앱 종료
    BackHandler(enabled = true) {
        if (backPressedOnce) {
            activity?.finish()
        } else {
            backPressedOnce = true
            Toast.makeText(context, "한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
            scope.launch {
                delay(2000L)
                backPressedOnce = false
            }
        }
    }

    AppScaffold(navController = navController) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route


            val subTopBarType = when (currentRoute) {

                NavigationRoute.ChatBotScreen::class.qualifiedName,
                NavigationRoute.HospitalInfoScreen::class.qualifiedName,
                NavigationRoute.MedicineInfoScreen::class.qualifiedName -> SubTopBarType.SubDiagnosis

                NavigationRoute.HospitalSearchScreen::class.qualifiedName,
                NavigationRoute.DiseaseSearchScreen::class.qualifiedName,
                NavigationRoute.MedicineSearchScreen::class.qualifiedName -> SubTopBarType.SubSearch

                else -> SubTopBarType.None
            }
            if (subTopBarType !is SubTopBarType.None) {
                SubTopNavigationBar(
                    navController = navController,
                    currentRoute = currentRoute,
                    subTopBarType = subTopBarType
                )
            }
// 카테고리 탭 구현 예정
//            val subFeedTopBarType = when (currentRoute) {
//                NavigationRoute.Social::class.qualifiedName -> SubFeedTopBarType.SubSocial
//                NavigationRoute.Ranking::class.qualifiedName -> SubFeedTopBarType.SubRanking
//
//
//                else -> SubFeedTopBarType.None
//            }
//            if (subFeedTopBarType !is SubFeedTopBarType.None) {
//                SubFeedTopNavigationBar(
//                    navController = navController,
//                    currentRoute = currentRoute,
//                    subFeedTopBarType = subFeedTopBarType
//                )
//            }
            NavHost(
                navController = navController,
                startDestination = NavigationRoute.Splash
            ) {
                composable<NavigationRoute.Splash> {
                    Splash(
                        shift = {
                            navController.navigate(NavigationRoute.Signin) {
                                popUpTo(NavigationRoute.Splash) {
                                    inclusive = true
                                }
                            }
                        }
                    )
                }

                composable<NavigationRoute.Signin> {
                    Signin(
                        select = { it ->
                            when (it) {
                                is AuthScreen.Signin -> {
                                    navController.navigate(NavigationRoute.Home) {
                                        popUpTo(NavigationRoute.Signin) {
                                            inclusive = true
                                        }
                                    }
                                }

                                is AuthScreen.Signup -> {
                                    navController.navigate(NavigationRoute.Signup)
                                }

                                is AuthScreen.ForgotPassword -> {
                                    navController.navigate(NavigationRoute.ForgotPassword)
                                }
                            }
                        }
                    )
                }

                composable<NavigationRoute.Signup> {
                    Signup()
                }

                composable<NavigationRoute.ForgotPassword> {
                    ForgotPassword()
                }

                composable<NavigationRoute.Home> {
                    Home()
                }

                composable<NavigationRoute.Exercise> {
                    Exercise()
                }

                composable<NavigationRoute.DietScreen> {
                    DietScreen()
                }

                composable<NavigationRoute.SearchFood> {
                    SearchFood()
                }

                composable<NavigationRoute.FoodInformation> {
                    FoodInformation()
                }

                composable<NavigationRoute.ChatBotScreen> { // 진료 - 챗봇
                    ChatBotScreen()
                }

                composable<NavigationRoute.HospitalInfoScreen> { // 진료 - 병원
                    HospitalInfoScreen()
                }

                composable<NavigationRoute.MedicineInfoScreen> { // 진료 - 약
                    MedicineInfoScreen(viewModel = viewModel)
                }

                composable<NavigationRoute.HospitalSearchScreen> { // 검색 - 병원
                    HospitalSearchScreen()
                }

                composable<NavigationRoute.DiseaseSearchScreen> { // 검색 - 질환
                    DiseaseSearchScreen()
                }

                composable<NavigationRoute.MedicineSearchScreen> { // 검색 - 약
                    MedicineSearchScreen(
                        viewModel = viewModel,
                        onNavigateToMedicineInfo = {
                            navController.navigate(NavigationRoute.MedicineInfoScreen)
                        }
                    )
                }

                composable<NavigationRoute.Social> {
                    Social()
                }

                composable<NavigationRoute.Ranking> {
                    Ranking()
                }

                composable<NavigationRoute.MyPage> {
                    MyPage()
                }

                composable<NavigationRoute.AccountManagement> {
                    AccountManagement()
                }

                composable<NavigationRoute.BasalMetabolicRateMeasurement> {
                    BasalMetabolicRateMeasurement()
                }

                composable<NavigationRoute.PostWrittenManagement> {
                    PostWrittenManagement()
                }
            }
        }
    }
}


@Composable
fun AppScaffold(
    navController: NavController,
    content: @Composable (PaddingValues) -> Unit
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val topBar: @Composable () -> Unit = {
        val topBarType = when (currentRoute) {

            NavigationRoute.Exercise::class.qualifiedName,
            NavigationRoute.DietScreen::class.qualifiedName -> TopBarType.Routine

            NavigationRoute.ChatBotScreen::class.qualifiedName,
            NavigationRoute.HospitalInfoScreen::class.qualifiedName,
            NavigationRoute.MedicineInfoScreen::class.qualifiedName,
            NavigationRoute.HospitalSearchScreen::class.qualifiedName,
            NavigationRoute.DiseaseSearchScreen::class.qualifiedName,
            NavigationRoute.MedicineSearchScreen::class.qualifiedName -> TopBarType.Diagnosis


            NavigationRoute.Social::class.qualifiedName,
            NavigationRoute.Ranking::class.qualifiedName -> TopBarType.Feed

            else -> TopBarType.None
        }
        TopNavigationBar(
            navController = navController,
            topAppBarType = topBarType
        )
    }
    val bottomBar: @Composable () -> Unit = {
        val bottomBarType = when (currentRoute) {
            NavigationRoute.Home::class.qualifiedName,
            NavigationRoute.Exercise::class.qualifiedName,
            NavigationRoute.DietScreen::class.qualifiedName,
            NavigationRoute.ChatBotScreen::class.qualifiedName,
            NavigationRoute.HospitalInfoScreen::class.qualifiedName,
            NavigationRoute.MedicineInfoScreen::class.qualifiedName,
            NavigationRoute.HospitalSearchScreen::class.qualifiedName,
            NavigationRoute.DiseaseSearchScreen::class.qualifiedName,
            NavigationRoute.MedicineSearchScreen::class.qualifiedName,
            NavigationRoute.Social::class.qualifiedName,
            NavigationRoute.Ranking::class.qualifiedName,
            NavigationRoute.MyPage::class.qualifiedName
                -> true

            else -> false
        }
        if (bottomBarType) {
            BottomNavigationBar(navController = navController)
        }
    }

    Scaffold(
        topBar = topBar,
        bottomBar = bottomBar

    ) { innerPadding ->
        content(innerPadding)
    }
}


@Preview(showBackground = true)
@Composable
fun EntryScreenPreview() {
    CarefullTheme {
        BottomNavigationBar(navController = rememberNavController())
    }
}
