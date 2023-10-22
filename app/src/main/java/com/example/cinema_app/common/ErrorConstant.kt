package com.example.cinema_app.common

object ErrorConstant {
    const val PASSWORD_LENGTH_ERROR = "Пароль должен содержать не менее 6 символов."
    const val EMAIL_ERROR = "Email должен соотвествовать стандарту."
    const val LOGIN_ERROR = "Логин не должен быть пустым."
    const val NAME_ERROR = "Имя не должно быть пустым."
    const val DATE_ERROR = "Дата не должна быть пустой."
    const val CONFIRMATION_ERROR = "Пароли должны совпадать."
    const val EMAIL_PATTERN = "[a-zA-Z0-9._-]{4,}+@[a-z]+\\.+[a-z]{2,}+"
}