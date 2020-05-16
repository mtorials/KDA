package de.mtorials.kda.command

import de.mtorials.kda.permissions.PermissionLevels

open class Command(
    val invoke: String,
    val description: String = "There is no description for this command...",
    val permission: PermissionLevels = PermissionLevels.USER,
    private val onInvoke: CommandEvent.() -> Unit = {}
) {
    open fun execute(event: CommandEvent) = event.onInvoke()
}