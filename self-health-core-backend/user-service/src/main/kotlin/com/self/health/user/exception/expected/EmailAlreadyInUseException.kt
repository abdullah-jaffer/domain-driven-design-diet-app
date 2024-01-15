package com.self.health.user.exception.expected

class EmailAlreadyInUseException(email: String) : RuntimeException("This email is already in use: $email")