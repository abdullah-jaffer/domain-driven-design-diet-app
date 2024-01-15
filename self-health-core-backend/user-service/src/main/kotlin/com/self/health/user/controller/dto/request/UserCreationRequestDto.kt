package com.self.health.user.controller.dto.request

import com.self.health.user.model.User

data class UserCreationRequestDto(
    val userName: String,
    val email: String,
    val dateOfBirth: String,
    val externalId: String) {

    fun toUserModel(): User {
        return User(name = userName, email = email, dateOfBirth = dateOfBirth, externalId = externalId)
    }
}
