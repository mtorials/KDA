package de.mtorials.kda.permissions

enum class PermissionLevels(order: Int) {
    USER(0), MODERATOR(1), ADMIN(2), OWNER(3)
}