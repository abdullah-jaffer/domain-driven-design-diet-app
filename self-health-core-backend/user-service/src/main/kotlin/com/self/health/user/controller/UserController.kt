package com.self.health.user.controller

import com.self.health.user.controller.dto.request.UserCreationRequestDto
import com.self.health.user.service.UserService
import org.springframework.http.HttpStatus.CREATED
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/user")
class UserController(private val userService: UserService) {

    @PostMapping
    @ResponseStatus(CREATED)
    fun fetchUserDetails(@RequestBody userDto: UserCreationRequestDto) {
        userService.createUser(userDto)
    }

}