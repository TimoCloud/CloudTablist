package cloud.timo.cloudtablist.listeners;

import cloud.timo.TimoCloud.api.TimoCloudAPI;
import cloud.timo.TimoCloud.api.events.EventHandler;
import cloud.timo.TimoCloud.api.events.Listener;
import cloud.timo.TimoCloud.api.events.player.PlayerConnectEvent;
import cloud.timo.cloudtablist.CloudTablist;

/**
 * @author Sebastian
 * Created in 21.08.2018
 */
public class ServerConnectListener implements Listener {

    @EventHandler
    public void onPlayerDisconnect(PlayerConnectEvent event) {
        TimoCloudAPI.getUniversalAPI().getProxyGroups().forEach(proxyGroupObject -> proxyGroupObject.getProxies().forEach(proxyObject -> {
            proxyObject.getOnlinePlayers().forEach(playerObject -> CloudTablist.getInstance().getHelper().setTablist(playerObject, event));
        }));
    }

}
