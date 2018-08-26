package cloud.timo.cloudtablist.utils;

import cloud.timo.TimoCloud.api.TimoCloudAPI;
import cloud.timo.TimoCloud.api.objects.PlayerObject;
import cloud.timo.TimoCloud.api.objects.ProxyObject;
import cloud.timo.TimoCloud.api.objects.ServerObject;
import cloud.timo.cloudtablist.CloudTablist;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;

/**
 * @author Sebastian
 * Created in 20.08.2018
 */
public class Helper {

    public void setTablist(PlayerObject playerObject) {
        TextComponent header = new TextComponent();
        header.setText(this.replaceMessage(CloudTablist.getInstance().getFileManager().getMessage("header"), playerObject));

        TextComponent footer = new TextComponent();
        footer.setText(this.replaceMessage(CloudTablist.getInstance().getFileManager().getMessage("footer"), playerObject));

        ProxyServer.getInstance().getPlayer(playerObject.getUuid()).setTabHeader(header, footer);
    }


    private String replaceMessage(String message, PlayerObject playerObject) {
        ServerObject serverObject = playerObject.getServer();
        ProxyObject proxyObject = playerObject.getProxy();
        int[] playerCountGlobal = {0};
        int[] maxPlayerCountGlobal = {0};
        TimoCloudAPI.getUniversalAPI().getProxyGroups().forEach(proxyGroupObject -> maxPlayerCountGlobal[0] += proxyGroupObject.getMaxPlayerCount());
        TimoCloudAPI.getUniversalAPI().getProxyGroups().forEach(proxyGroupObject -> playerCountGlobal[0] += proxyGroupObject.getOnlinePlayerCount());
        return ChatColor.translateAlternateColorCodes('&', message
                .replace("{onlinePlayersCountServer}", String.valueOf(serverObject.getOnlinePlayerCount()))
                .replace("{onlinePlayersCountProxy}", String.valueOf(proxyObject.getOnlinePlayerCount()))
                .replace("{onlinePlayersCountGlobal}", String.valueOf(playerCountGlobal[0]))
                .replace("{serverGroup}", playerObject.getServer().getGroup().getName())
                .replace("{proxyGroup}", playerObject.getProxy().getGroup().getName())
                .replace("{proxyName}", playerObject.getProxy().getName())
                .replace("{serverName}", playerObject.getServer().getName())
                .replace("{playerName}", playerObject.getName())
                .replace("{playerUUID}", playerObject.getUuid().toString())
                .replace("{serverMOTD}", serverObject.getMotd())
                .replace("{proxyMOTD]", proxyObject.getGroup().getMotd())
                .replace("{proxyBASE}", proxyObject.getBase())
                .replace("{serverBASE}", serverObject.getBase())
                .replace("{serverState}", serverObject.getState())
                .replace("{serverPort}", String.valueOf(serverObject.getPort()))
                .replace("{proxyPort}", String.valueOf(proxyObject.getPort()))
                .replace("{serverMap}", serverObject.getMap())
                .replace("{serverExtra}", serverObject.getExtra())
                .replace("{maxPlayerCountServer}", String.valueOf(serverObject.getMaxPlayerCount()))
                .replace("{maxPlayerCountProxy}", String.valueOf(serverObject.getMaxPlayerCount()))
                .replace("{maxPlayerCountGlobal}", String.valueOf(maxPlayerCountGlobal[0])));
    }

}
