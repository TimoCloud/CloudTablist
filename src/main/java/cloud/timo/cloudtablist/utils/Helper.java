package cloud.timo.cloudtablist.utils;

import cloud.timo.TimoCloud.api.TimoCloudAPI;
import cloud.timo.TimoCloud.api.events.Event;
import cloud.timo.TimoCloud.api.events.EventType;
import cloud.timo.TimoCloud.api.objects.PlayerObject;
import cloud.timo.TimoCloud.api.objects.ProxyGroupObject;
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

    public void setTablist(PlayerObject playerObject, Event event) {
        TextComponent header = new TextComponent();
        header.setText(this.formatMessage(CloudTablist.getInstance().getFileManager().getMessage("header"), playerObject, event));

        TextComponent footer = new TextComponent();
        footer.setText(this.formatMessage(CloudTablist.getInstance().getFileManager().getMessage("footer"), playerObject, event));

        ProxyServer.getInstance().getPlayer(playerObject.getUuid()).setTabHeader(header, footer);
    }


    private String formatMessage(String message, PlayerObject playerObject, Event event) {
        ServerObject serverObject = playerObject.getServer();
        ProxyObject proxyObject = playerObject.getProxy();

        int playerCountGlobal = formatPlayerCount(TimoCloudAPI.getUniversalAPI().getProxyGroups().stream().mapToInt(ProxyGroupObject::getOnlinePlayerCount).sum(), event);
        int maxPlayerCountGlobal = TimoCloudAPI.getUniversalAPI().getProxyGroups().stream().mapToInt(ProxyGroupObject::getMaxPlayerCount).sum();
        int playerCountServer = formatPlayerCount(serverObject.getOnlinePlayerCount(), event);
        int playerCountProxy = formatPlayerCount(proxyObject.getOnlinePlayerCount(), event);

        return ChatColor.translateAlternateColorCodes('&', message
                .replace("{onlinePlayersCountServer}", String.valueOf(playerCountServer))
                .replace("{onlinePlayersCountProxy}", String.valueOf(playerCountProxy)))
                .replace("{onlinePlayersCountGlobal}", String.valueOf(playerCountGlobal))
                .replace("{serverGroup}", serverObject.getName())
                .replace("{proxyGroup}", proxyObject.getGroup().getName())
                .replace("{proxyName}", proxyObject.getName())
                .replace("{serverName}", serverObject.getName())
                .replace("{playerName}", playerObject.getName())
                .replace("{playerUUID}", playerObject.getUuid().toString())
                .replace("{serverMOTD}", serverObject.getMotd())
                .replace("{proxyMOTD]", proxyObject.getGroup().getMotd())
                .replace("{proxyBASE}", proxyObject.getBase().getName())
                .replace("{serverBASE}", serverObject.getBase().getName())
                .replace("{serverState}", serverObject.getState())
                .replace("{serverPort}", String.valueOf(serverObject.getPort()))
                .replace("{proxyPort}", String.valueOf(proxyObject.getPort()))
                .replace("{serverMap}", serverObject.getMap())
                .replace("{serverExtra}", serverObject.getExtra())
                .replace("{maxPlayerCountServer}", String.valueOf(serverObject.getMaxPlayerCount()))
                .replace("{maxPlayerCountProxy}", String.valueOf(proxyObject.getGroup().getMaxPlayerCountPerProxy()))
                .replace("{maxPlayerCountGlobal}", String.valueOf(maxPlayerCountGlobal));
    }

    private int formatPlayerCount(int i, Event event){
        return (event.getType() == EventType.PLAYER_DISCONNECT) ? i -1 : i;
    }

}
