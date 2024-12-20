package com.example.cinema_app.ui.state

data class RegistrationContent(
    val name: String = "",
    val nameError: String? = null,
    val email: String = "",
    val emailError: String? = null,
    val birthDate: String = "",
    val password: String = "",
    val passwordError: String? = null,
    val login: String = "",
    val gender: Int = 0,
    val loginError: String? = null,
    val confirmPassword: String = "",
    val confirmPasswordError: String? = null,
    val birthDateError: String? = null,
    val uniqueLoginError: String? = null
)

fun RegistrationContent.clearAllParams(): RegistrationContent {
    return RegistrationContent(
        login = "",
        name = "",
        password = "",
        email = "",
        confirmPassword = "",
        birthDate = "",
        emailError = null,
        loginError = null,
        passwordError = null,
        confirmPasswordError = null,
        birthDateError = null,
        nameError = null,
    )
}