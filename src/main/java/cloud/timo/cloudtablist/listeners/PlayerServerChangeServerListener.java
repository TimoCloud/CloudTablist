package cloud.timo.cloudtablist.listeners;

import cloud.timo.TimoCloud.api.TimoCloudAPI;
import cloud.timo.TimoCloud.api.events.EventHandler;
import cloud.timo.TimoCloud.api.events.Listener;
import cloud.timo.TimoCloud.api.events.player.PlayerServerChangeEvent;
import cloud.timo.cloudtablist.CloudTablist;

/**
 * @author Sebastian
 * Created in 23.08.2018
 * Copyright (c) 2015 - 2018 by CraftMal.de to present. All rights reserved.
 */
public class PlayerServerChangeServerListener implements Listener {


    @EventHandler
    public void onPlayerServerChange(PlayerServerChangeEvent event) {
        CloudTablist.getInstance().getHelper().setTablist(event.getPlayer());
        TimoCloudAPI.getUniversalAPI().getProxyGroups().forEach(proxyGroupObject -> proxyGroupObject.getProxies().forEach(proxyObject -> {
            proxyObject.getOnlinePlayers().forEach(playerObject -> {
                if (!playerObject.getUuid().equals(event.getPlayer().getUuid()))
                    CloudTablist.getInstance().getHelper().setTablist(playerObject);
            });
        }));
    }

}
