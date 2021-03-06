package com.lot.mobile.domain.entities

data class SettingEntity (
    val notificationType: String,
    val name: String,
    val description: String,
    val value: Boolean,
    val userId: Long
        )