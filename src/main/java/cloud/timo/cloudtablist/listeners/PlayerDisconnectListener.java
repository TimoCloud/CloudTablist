package cloud.timo.cloudtablist.listeners;

import cloud.timo.TimoCloud.api.TimoCloudAPI;
import cloud.timo.TimoCloud.api.events.Listener;
import cloud.timo.TimoCloud.api.events.PlayerDisconnectEvent;
import cloud.timo.cloudtablist.CloudTablist;
import net.md_5.bungee.event.EventHandler;

/**
 * @author Sebastian
 * Created in 20.08.2018
 */
public class PlayerDisconnectListener implements Listener {

    @EventHandler
    public void onPlayerDisconnect(PlayerDisconnectEvent event){
        CloudTablist.getInstance().getHelper().setTablist(event.getPlayer());
        TimoCloudAPI.getUniversalAPI().getProxyGroups().forEach(proxyGroupObject -> proxyGroupObject.getProxies().forEach(proxyObject -> {
            proxyObject.getOnlinePlayers().forEach(playerObject -> {
                if (!playerObject.getUuid().equals(event.getPlayer().getUuid()))
                    CloudTablist.getInstance().getHelper().setTablist(playerObject);
            });
        }));
    }
}
