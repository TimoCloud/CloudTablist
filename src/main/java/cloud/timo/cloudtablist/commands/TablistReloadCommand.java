package cloud.timo.cloudtablist.commands;

import cloud.timo.cloudtablist.CloudTablist;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

/**
 * @author Sebastian
 * Created in 20.08.2018
 */
public class TablistReloadCommand extends Command {

    public TablistReloadCommand() {
        super("tablist", "cloudtablist.tablist.reload", "tb");
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        CloudTablist.getInstance().getFileManager().load();
        commandSender.sendMessage(new TextComponent("§aSuccessfully reloaded configuration from config.yml. (CloudTablist)"));
    }

}
