package me.vlouboos.neteasecheatplus.managements;

import com.darkmagician6.eventapi.EventTarget;
import me.vlouboos.neteasecheatplus.events.EventChatSend;
import me.vlouboos.neteasecheatplus.systems.command.Command;
import me.vlouboos.neteasecheatplus.systems.command.impl.Bind;
import me.vlouboos.neteasecheatplus.utils.PlayerUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class CommandManager {
    private final ArrayList<Command> commands = new ArrayList<>();

    public CommandManager() {
        this.commands.add(new Bind());
        EventManager.register(this);
    }

    @EventTarget
    public void onChatSend(EventChatSend e) {
        if (e.getMessage().startsWith(".") && e.getMessage().length() > 1) {
            e.setCancelled(true);
            String[] arguments = e.getMessage().substring(1).split(" ");
            String commandName = arguments[0];
            Command command = null;
            for (Command commandInList : this.commands) {
                if (commandInList.getName().equalsIgnoreCase(commandName)) {
                    command = commandInList;
                    break;
                }
            }
            if (command == null) {
                PlayerUtil.tellPlayer("\247cUnknown command!");
            } else {
                command.execute(Arrays.copyOfRange(arguments, 1, arguments.length));
            }
        }
    }
}
