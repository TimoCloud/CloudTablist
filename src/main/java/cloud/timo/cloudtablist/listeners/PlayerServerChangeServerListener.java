package cloud.timo.cloudtablist.listeners;

import cloud.timo.TimoCloud.api.TimoCloudAPI;
import cloud.timo.TimoCloud.api.events.EventHandler;
import cloud.timo.TimoCloud.api.events.Listener;
import cloud.timo.TimoCloud.api.events.player.PlayerServerChangeEvent;
import cloud.timo.cloudtablist.CloudTablist;

/**
 * @author Sebastian
 * Created in 23.08.2018
 */
public class PlayerServerChangeServerListener implements Listener {
    
    @EventHandler
    public void onPlayerServerChange(PlayerServerChangeEvent event) {
        TimoCloudAPI.getUniversalAPI().getProxyGroups().forEach(proxyGroupObject -> proxyGroupObject.getProxies().forEach(proxyObject -> {
            proxyObject.getOnlinePlayers().forEach(playerObject -> CloudTablist.getInstance().getHelper().setTablist(playerObject, event));
        }));
    }

}
