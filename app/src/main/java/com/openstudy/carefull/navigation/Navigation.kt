package com.openstudy.carefull.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.openstudy.carefull.screen.ForgotPassword
import com.openstudy.carefull.screen.Home
import com.openstudy.carefull.screen.MyPage
import com.openstudy.carefull.screen.Signin
import com.openstudy.carefull.screen.Signup
import com.openstudy.carefull.screen.Splash
import kotlinx.serialization.Serializable


//인자 필요한 경우 object -> class 변경 하여 작성
//예: data class Profile(val id:String) : NavigationRoute()
@Serializable
sealed class NavigationRoute {

    @Serializable
    data object Auth : NavigationRoute()

    @Serializable
    data object Main : NavigationRoute()

    @Serializable
    data object Splash : NavigationRoute()

    @Serializable
    data object Signin : NavigationRoute()

    @Serializable
    data object ForgotPassword : NavigationRoute()

    @Serializable
    data object Signup : NavigationRoute()

    @Serializable
    data object Home : NavigationRoute()

    @Serializable
    data object MyPage : NavigationRoute()

}

sealed class AuthScreen {
    data object Signin : AuthScreen()
    data object Signup : AuthScreen()
    data object ForgotPassword : AuthScreen()
}

@Composable
fun NavigationControl() {
    val navController = rememberNavController()

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
        composable<NavigationRoute.MyPage> {
            MyPage()
        }
    }
}
