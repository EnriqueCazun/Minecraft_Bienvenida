package com.Bienvenida;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.Listener;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler; // <-- Este es el importante

import java.io.File;

public class Bienvenida extends JavaPlugin implements Listener {
    
    private FileConfiguration config;
    
    @Override
    public void onEnable() {
        createConfig();
        
        getLogger().info("¡Plugin activado!");
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin desactivado.");
    }
    
    private void createConfig() {
        try {
            if (!getDataFolder().exists()) {
                getDataFolder().mkdirs();
            }
            
            File configFile = new File(getDataFolder(), "config.yml");
            
            if (!configFile.exists()) {
                getLogger().info("Creando config.yml...");
                saveDefaultConfig();
            } else {
                getLogger().info("Cargando config.yml...");
            }
            
            reloadConfig();
            config = getConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String nombreJugador = player.getName();

        String welcomeMsg = config.getString("welcome-message", "&a¡Bienvenido al servidor, {player}!")
            .replace("{player}", nombreJugador);
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', welcomeMsg));

        String broadcastMsg = config.getString("welcome-broadcast", "&e¡Todos den la bienvenida a {player} que se ha unido al servidor!")
            .replace("{player}", nombreJugador);
        event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', broadcastMsg));
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("bienvenida")) {
            if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission("bienvenida.reload") || sender.isOp()) {
                    reloadConfig();
                    config = getConfig();
                    
                    String reloadMsg = config.getString("reload-message", "&aConfiguración recargada correctamente");
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', reloadMsg));
                    return true;
                } else {
                    sender.sendMessage(ChatColor.RED + "No tienes permiso para ejecutar este comando.");
                    return true;
                }
            }

            sender.sendMessage(ChatColor.YELLOW + "Uso: /bienvenida reload - Recarga la configuración");
            return true;
        }
        return false;
    }

    @Override
    public java.util.List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        if (cmd.getName().equalsIgnoreCase("bienvenida")) {
            if (args.length == 1) {
                return java.util.Arrays.asList("reload");
            }
        }
        return null;
    }
}