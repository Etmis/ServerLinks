package com.etmisthefox.serverLinks;

import cz.foresttech.api.ColorAPI;
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
        if (sender instanceof Player) {
            sender.sendMessage(ColorAPI.colorize(message));
            return true;
        } else {
            sender.sendMessage("Tento příkaz je jen pro hráče.");
            return true;
        }
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
        return Collections.emptyList();
    }

}
