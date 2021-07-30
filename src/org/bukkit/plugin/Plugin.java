package org.bukkit.plugin;

import com.avaje.ebean.EbeanServer;
import org.bukkit.Server;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.config.Configuration;

import java.io.File;
import java.io.InputStream;

/**
 * Represents a Plugin
 */
public interface Plugin extends CommandExecutor {

    /**
     * Returns the folder that the plugin data's files are located in. The
     * folder may not yet exist.
     *
     * @return
     */
    public File getDataFolder();

    /**
     * Returns the plugin.yaml file containing the details for this plugin
     *
     * @return Contents of the plugin.yaml file
     */
    public PluginDescriptionFile getDescription();

    /**
     * Returns the main configuration file. It should be loaded.
     *
     * @return
     */
    public Configuration getConfiguration();

    /**
     * Gets the associated PluginLoader responsible for this plugin
     *
     * @return PluginLoader that controls this plugin
     */
    public PluginLoader getPluginLoader();

    /**
     * Returns the Server instance currently running this plugin
     *
     * @return Server running this plugin
     */
    public Server getServer();

    /**
     * Returns a value indicating whether or not this plugin is currently enabled
     *
     * @return true if this plugin is enabled, otherwise false
     */
    public boolean isEnabled();

    /**
     * Called when this plugin is disabled
     */
    public void onDisable();

    /**
     * Called after a plugin is loaded but before it has been enabled.
     * When mulitple plugins are loaded, the onLoad() for all plugins is called before any onEnable() is called.
     */
    public void onLoad();

    /**
     * Called when this plugin is enabled
     */
    public void onEnable();

    /**
     * Simple boolean if we can still nag to the logs about things
     * @return boolean whether we can nag
     */
    public boolean isNaggable();

    /**
     * Set naggable state
     * @param canNag is this plugin still naggable?
     */
    public void setNaggable(boolean canNag);

    /**
     * Gets the {@link EbeanServer} tied to this plugin
     *
     * @return Ebean server instance
     */
    public EbeanServer getDatabase();

    /**
     * Gets a {@link ChunkGenerator} for use in a default world, as specified in the server configuration
     *
     * @param worldName Name of the world that this will be applied to
     * @param id Unique ID, if any, that was specified to indicate which generator was requested
     * @return ChunkGenerator for use in the default world generation
     */
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id);

    //Modern Configuration Backport - Start


    /**
     * Gets a {@link FileConfiguration} for this plugin, read through "config.yml"
     * <p>
     * If there is a default config.yml embedded in this plugin, it will be provided
     * as a default for this Configuration.
     *
     * @return Plugin configuration
     */
    public FileConfiguration getConfig();

    /**
     * Gets an embedded resource in this plugin
     *
     * @param filename Filename of the resource
     * @return File if found, otherwise null
     */
    public InputStream getResource(String filename);

    /**
     * Saves the {@link FileConfiguration} retrievable by {@link #getConfig()}.
     */
    public void saveConfig();

    /**
     * Saves the raw contents of the default config.yml file to the location retrievable by {@link #getConfig()}.
     * If there is no default config.yml embedded in the plugin, an empty config.yml file is saved.
     */
    public void saveDefaultConfig();

    /**
     * Saves the raw contents of any resource embedded with a plugin's .jar file assuming it can be found using
     * {@link #getResource(String)}. The resource is saved into the plugin's data folder using the same hierarchy
     * as the .jar file (subdirectories are preserved).
     *
     * @param resourcePath the embedded resource path to look for within the plugin's .jar file. (No preceding slash).
     * @param replace if true, the embedded resource will overwrite the contents of an existing file.
     * @throws IllegalArgumentException if the resource path is null, empty, or points to a nonexistent resource.
     */
    public void saveResource(String resourcePath, boolean replace);

    /**
     * Discards any data in {@link #getConfig()} and reloads from disk.
     */
    public void reloadConfig();
}
