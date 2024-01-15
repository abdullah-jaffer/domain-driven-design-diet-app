package com.self.health.user.exception.unexpected

class UserCreationException(ex: Throwable) : RuntimeException("Unknown exception occurred while creating user", ex)