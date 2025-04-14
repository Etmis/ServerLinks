package com.etmisthefox.serverLinks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;

public final class ServerLinks extends JavaPlugin {

    @Override
    public void onEnable() {

        String[] banner = {
                "███████╗███████╗██████╗ ██╗   ██╗███████╗██████╗ ██╗     ██╗███╗   ██╗██╗  ██╗███████╗",
                "██╔════╝██╔════╝██╔══██╗██║   ██║██╔════╝██╔══██╗██║     ██║████╗  ██║██║ ██╔╝██╔════╝",
                "███████╗█████╗  ██████╔╝██║   ██║█████╗  ██████╔╝██║     ██║██╔██╗ ██║█████╔╝ ███████╗",
                "╚════██║██╔══╝  ██╔══██╗╚██╗ ██╔╝██╔══╝  ██╔══██╗██║     ██║██║╚██╗██║██╔═██╗ ╚════██║",
                "███████║███████╗██║  ██║ ╚████╔╝ ███████╗██║  ██║███████╗██║██║ ╚████║██║  ██╗███████║",
                "╚══════╝╚══════╝╚═╝  ╚═╝  ╚═══╝  ╚══════╝╚═╝  ╚═╝╚══════╝╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝"
        };

        for (String line : banner) {
            getLogger().info(line);
        }

        saveDefaultConfig();
        FileConfiguration config = getConfig();

        Map<String, Object> commands = config.getConfigurationSection("commands").getValues(false);

        CommandMap commandMap = getCommandMap();
        if (commandMap == null) {
            getLogger().severe("Nepodařilo se získat CommandMap!");
            return;
        }

        for (Map.Entry<String, Object> entry : commands.entrySet()) {
            String commandName = entry.getKey();
            String message = String.valueOf(entry.getValue());

            DynamicCommand cmd = new DynamicCommand(commandName, message);
            commandMap.register(getDescription().getName(), cmd);
        }
    }

    private CommandMap getCommandMap() {
        try {
            java.lang.reflect.Field commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            commandMapField.setAccessible(true);
            return (CommandMap) commandMapField.get(Bukkit.getServer());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player)sender;
        if (command.getName().equalsIgnoreCase("slreload")) {
            if (player.hasPermission("serverlinks.reload")) {
                this.reloadConfig();

                FileConfiguration config = getConfig();
                Map<String, Object> commands = config.getConfigurationSection("commands").getValues(false);
                CommandMap commandMap = getCommandMap();

                if (commandMap == null) {
                    getLogger().severe("Nepodařilo se získat CommandMap při reloadu!");
                    return true;
                }

                for (Map.Entry<String, Object> entry : commands.entrySet()) {
                    String commandName = entry.getKey();
                    String message = String.valueOf(entry.getValue());

                    DynamicCommand cmd = new DynamicCommand(commandName, message);
                    commandMap.register(getDescription().getName(), cmd);
                }

                ServerLinksLogo(player, "Config reloaded and commands updated!");
            } else {
                InsufficientPermissions(player);
            }
        }
        return true;
    }

    public static void InsufficientPermissions(Player player) {
        player.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "[" + ChatColor.BLUE + "" + ChatColor.BOLD + "SERVER" + ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "LINKS" + ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "]" + ChatColor.DARK_RED + " " + ChatColor.DARK_RED + "You don't have permission to use this command");
    }

    public static void ServerLinksLogo(Player player, String message) {
        player.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "[" + ChatColor.BLUE + "" + ChatColor.BOLD + "SERVER" + ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "LINKS" + ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "]" + ChatColor.GREEN + " " + message);
    }
}
