package de.mtorials.kda.message

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.MessageEmbed
import java.awt.Color
import javax.management.monitor.StringMonitor

class InfixEmbedBuilder(
    block: InfixEmbedBuilder.() -> Unit
) {

    val message = this

    private val builder: EmbedBuilder = EmbedBuilder()

    init {
        this.block()
    }

    fun build() : MessageEmbed = this.builder.build()

    infix fun InfixEmbedBuilder.title(title: String) = this.builder.setTitle(title)
    infix fun InfixEmbedBuilder.author(author: String) = this.builder.setAuthor(author)
    infix fun InfixEmbedBuilder.description(desc: String) = this.builder.setDescription(desc)
    infix fun InfixEmbedBuilder.footer(footer: String) = this.builder.setFooter(footer)
    infix fun InfixEmbedBuilder.thumbnail(url: String) = this.builder.setThumbnail(url)
    infix fun InfixEmbedBuilder.image(url: String) = this.builder.setImage(url)
    infix fun InfixEmbedBuilder.color(color: Color) = this.builder.setColor(color)

    fun field(block: InfixFieldBuilder.() -> Unit) {
        val fieldBuilder = InfixFieldBuilder(false)
        fieldBuilder.block()
        builder.addField(fieldBuilder.build())
    }

    fun inlineField(block: InfixFieldBuilder.() -> Unit) {
        val fieldBuilder = InfixFieldBuilder(true)
        fieldBuilder.block()
        builder.addField(fieldBuilder.build())
    }

    class InfixFieldBuilder(
        private val inline: Boolean
    ) {
        val field = this

        private var name: String = ""
        private var value: String = ""

        fun build(): MessageEmbed.Field = MessageEmbed.Field(name, value, inline)

        infix fun nameIs(name: String) {
            this.name = name
        }

        infix fun valueIs(value: String) {
            this.value = value
        }
    }
}