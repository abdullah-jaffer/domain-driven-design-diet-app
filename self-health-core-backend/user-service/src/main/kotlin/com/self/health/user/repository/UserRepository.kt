package com.self.health.user.repository

import com.self.health.user.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository: JpaRepository<User, String>{
    fun findByEmail(email: String): Optional<User>
}