package com.example.cinema_app.common

object ErrorConstant {
    const val PASSWORD_LENGTH_ERROR = "Пароль должен содержать не менее 6 символов."
    const val EMAIL_ERROR = "Email должен соотвествовать стандарту."
    const val UNAUTHORIZED = "Неверный логин или пароль."
    const val UNEXPECTED_ERROR = "Непредвиденная ошибка, пожалуйста проверьте свои интернет-соединение и попробуйте снова"
    const val BAD_REQUEST = "Неверный запрос."
    const val ANONYMOUS_ERROR = "Нельзя изменить публичный отзыв на анонимный."
    const val LOGIN_ERROR = "Логин не должен быть пустым и содержать не менее двух латинских символов"
    const val UNIQUE_EMAIL = "Такой email уже используется другим пользователем"
    const val UNIQUE_LOGIN = "Такой логин уже используется другим пользователем"
    const val AUTHORIZATION_ERROR = "Неверный логин или пароль."
    const val UNKNOWN_ERROR = "Неизвестная ошибка."
    const val NAME_ERROR = "Имя не должно быть пустым и содержать не менее двух латинских символов"
    const val DATE_ERROR = "Дата не должна быть пустой."
    const val CONFIRMATION_ERROR = "Пароли должны совпадать."
    const val EMAIL_PATTERN = "[a-zA-Z0-9._-]{4,}+@[a-z]+\\.+[a-z]{2,}+"
}