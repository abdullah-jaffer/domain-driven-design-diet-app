package com.self.health.user.service

import com.self.health.user.controller.dto.request.UserCreationRequestDto
import com.self.health.user.exception.expected.EmailAlreadyInUseException
import com.self.health.user.exception.unexpected.UserCreationException
import com.self.health.user.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(private val userRepository: UserRepository) {

    @Transactional("userTransactionManager")
    fun createUser(userRequestDto: UserCreationRequestDto) {
        try {
            if (userRepository.findByEmail(userRequestDto.email).isPresent){
                throw EmailAlreadyInUseException(userRequestDto.email)
            }

            userRepository.save(userRequestDto.toUserModel())
        } catch (ex: EmailAlreadyInUseException){
            throw ex
        } catch (ex: Exception){
            throw UserCreationException(ex)
        }
    }

}