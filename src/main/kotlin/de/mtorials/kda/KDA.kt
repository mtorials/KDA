package de.mtorials.kda

import de.mtorials.kda.adapter.CommandAdapter
import de.mtorials.kda.adapter.KDAListenerAdapter
import de.mtorials.kda.command.*
import de.mtorials.kda.config.GuildConfiguration
import de.mtorials.kda.permissions.PermissionLevels
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.MessageEmbed

class KDA(
    val name: String,
    val prefix: String,
    token: String
) {
    private val jda: JDA = JDABuilder(token).build()
    private val commands: MutableList<Command> = mutableListOf()
    private var help: Boolean = false
    private val globalConfig = GuildConfiguration(prefix)
    private var commandContext: CommandContext? = null

    private val listenerContext = ListenerContext()

    inline fun configure(block: KDA.() -> Unit) = this.block()

    fun listeners(block: ListenerContext.() -> Unit) = listenerContext.block()

    fun setCommandNotFoundMessage(block: () -> MessageEmbed) {
        globalConfig.messageCommandNotFound = block()
    }
    fun setCommandNotAllowedMessages(block: () -> MessageEmbed) {
        globalConfig.messageCommandNotAllowed = block()
    }
    fun addCommandContext(block: () -> CommandContext) {
        this.commandContext = block()
    }

    fun addCommand(invoke: String, onInvoke: CommandEvent.() -> Unit) {
        this.commands.add(
            Command(
                invoke = invoke,
                onInvoke = onInvoke
            )
        )
    }
    fun addCommand(invoke: String, description: String, onInvoke: CommandEvent.() -> Unit) {
        this.commands.add(
            Command(
                invoke = invoke,
                description = description,
                onInvoke = onInvoke
            )
        )
    }
    fun addCommand(invoke: String, description: String, permissionLevel: PermissionLevels, onInvoke: CommandEvent.() -> Unit) {
        this.commands.add(
            Command(
                invoke = invoke,
                description = description,
                onInvoke = onInvoke,
                permission = permissionLevel
            )
        )
    }
    fun addCommands(commands: Collection<Command>) = this.commands.addAll(commands)

    fun installPing() {
        commands.add(StandardCommands.ping)
    }
    fun installHelp() {
        help = true
    }

    fun init() {
        // TODO sort commands by invoke
        if (help) commands.add(HelpCommand(commands))
        jda.addEventListener(KDAListenerAdapter(listenerContext))
        jda.addEventListener(CommandAdapter(
            listenerContext = listenerContext,
            commands = commands,
            config = globalConfig,
            commandContext = commandContext
        ))
    }
}