package de.mtorials.kda.config

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.MessageEmbed
import java.awt.Color

data class GuildConfiguration(
    var prefix: String,
    var messageCommandNotFound: MessageEmbed= EmbedBuilder()
        .setColor(Color.RED)
        .setTitle("Command does not exist")
        .build(),
    var messageCommandNotAllowed: MessageEmbed= EmbedBuilder()
        .setColor(Color.RED)
        .setTitle("You are not allowed to use this command")
        .build()
)