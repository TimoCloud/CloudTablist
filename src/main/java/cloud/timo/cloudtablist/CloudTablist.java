package cloud.timo.cloudtablist;

import cloud.timo.TimoCloud.api.TimoCloudAPI;
import cloud.timo.cloudtablist.commands.TablistReloadCommand;
import cloud.timo.cloudtablist.listeners.PlayerDisconnectListener;
import cloud.timo.cloudtablist.listeners.PlayerServerChangeServerListener;
import cloud.timo.cloudtablist.listeners.ServerConnectListener;
import cloud.timo.cloudtablist.managers.FileManager;
import cloud.timo.cloudtablist.utils.Helper;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

/**
 * @author Sebastian
 * Created in 20.08.2018
 */
public class CloudTablist extends Plugin {
    private static CloudTablist instance;
    private FileManager fileManager;
    private Helper helper;

    @Override
    public void onEnable() {
        this.makeInstances();
        this.registerListeners();
        this.registerCommands();
        this.getLogger().info("CloudTablist by " + getDescription().getAuthor() + " (Version: " + getDescription().getVersion() + ") has been enabled.");
    }

    private void registerCommands() {
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new TablistReloadCommand());
    }

    private void registerListeners() {
        TimoCloudAPI.getEventAPI().registerListener(new PlayerDisconnectListener());
        TimoCloudAPI.getEventAPI().registerListener(new ServerConnectListener());
        TimoCloudAPI.getEventAPI().registerListener(new PlayerServerChangeServerListener());
    }

    private void makeInstances() {
        setInstance(this);
        setFileManager(new FileManager());
        fileManager.load();
        setHelper(new Helper());
    }

    @Override
    public void onDisable() {
        this.getLogger().info("CloudTablist by " + getDescription().getAuthor() + " (Version: " + getDescription().getVersion() + ") has been disabled.");
    }

    public static CloudTablist getInstance() {
        return instance;
    }

    public static void setInstance(CloudTablist instance) {
        CloudTablist.instance = instance;
    }

    public Helper getHelper() {
        return helper;
    }

    public void setHelper(Helper helper) {
        this.helper = helper;
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    public void setFileManager(FileManager fileManager) {
        this.fileManager = fileManager;
    }
}
