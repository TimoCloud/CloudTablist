package cloud.timo.cloudtablist.listeners;

import cloud.timo.TimoCloud.api.TimoCloudAPI;
import cloud.timo.TimoCloud.api.events.EventHandler;
import cloud.timo.TimoCloud.api.events.Listener;
import cloud.timo.TimoCloud.api.events.player.PlayerDisconnectEvent;
import cloud.timo.cloudtablist.CloudTablist;

/**
 * @author Sebastian
 * Created in 20.08.2018
 */
public class PlayerDisconnectListener implements Listener {

    @EventHandler
    public void onPlayerDisconnect(PlayerDisconnectEvent event) {
        TimoCloudAPI.getUniversalAPI().getProxyGroups().forEach(proxyGroupObject -> proxyGroupObject.getProxies().forEach(proxyObject -> {
            proxyObject.getOnlinePlayers().forEach(playerObject -> CloudTablist.getInstance().getHelper().setTablist(playerObject, event));
        }));
    }

}
