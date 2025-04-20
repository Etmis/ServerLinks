package com.etmisthefox.serverLinks;

import cz.foresttech.api.ColorAPI;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class DynamicCommand extends Command {

    private final String message;

    protected DynamicCommand(String name, String message) {
        super(name);
        this.message = message;
        this.setDescription("Dynamický příkaz z config.yml");
        this.setUsage("/" + name);
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (sender instanceof Player player) {
            String rawUrl = ColorAPI.clear(message);
            String coloredText = ColorAPI.colorize(message);
            BaseComponent[] components = TextComponent.fromLegacyText(coloredText);
            for (BaseComponent comp : components) {
                comp.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, rawUrl));
            }
            player.spigot().sendMessage(components);
        } else {
            sender.sendMessage("Tento příkaz je jen pro hráče.");
        }
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
        return Collections.emptyList();
    }
}
