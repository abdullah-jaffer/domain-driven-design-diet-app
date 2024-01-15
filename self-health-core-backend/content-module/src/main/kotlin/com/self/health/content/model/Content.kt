package com.self.health.content.model

import com.self.health.content.dto.ContentStatus
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType.STRING
import jakarta.persistence.Enumerated
import jakarta.persistence.Table

@Entity
@Table(name = "content")
class Content(
    id:String? = null,
    @Column(name = "title")
    val title: String,
    @Column(name = "image")
    val image: String,
    @Column(name = "link")
    val link: String,
    @Column(name = "status")
    @Enumerated(STRING)
    val status: ContentStatus
): BaseModel(id)