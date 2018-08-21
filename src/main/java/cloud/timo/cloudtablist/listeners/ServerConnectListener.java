package cloud.timo.cloudtablist.listeners;

import cloud.timo.TimoCloud.api.TimoCloudAPI;
import cloud.timo.TimoCloud.api.events.EventHandler;
import cloud.timo.TimoCloud.api.events.Listener;
import cloud.timo.TimoCloud.api.events.PlayerConnectEvent;
import cloud.timo.cloudtablist.CloudTablist;

/**
 * @author Sebastian
 * Created in 21.08.2018
 */
public class ServerConnectListener implements Listener {

    @EventHandler
    public void onServerSwitch(PlayerConnectEvent event) {
        CloudTablist.getInstance().getHelper().setTablist(event.getPlayer());
        TimoCloudAPI.getUniversalAPI().getProxyGroups().forEach(proxyGroupObject -> proxyGroupObject.getProxies().forEach(proxyObject -> {
            proxyObject.getOnlinePlayers().forEach(playerObject -> {
                if (!playerObject.getUuid().equals(event.getPlayer()))
                    CloudTablist.getInstance().getHelper().setTablist(event.getPlayer());
            });
        }));
    }
}
