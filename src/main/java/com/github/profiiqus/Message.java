package com.github.profiiqus;

import com.github.profiiqus.utils.Formatter;
import com.google.common.base.Strings;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.*;

/**
 * A simple object/library for managing plugin's messages.
 * Bears the possibility of coloring and decolorizing messages,
 * setting placeholders and sending them to players.
 * @author ProfiiQus
 */
public class Message {

    private final List<String> lines;

    /**
     * Constructor for single-line messages
     * @param message A single message line
     */
    public Message(String message) {
        this.lines = new ArrayList<>();
        this.lines.add(message);
    }

    /**
     * Constructor for multi-line messages
     * @param lines Message lines
     */
    public Message(List<String> lines) {
        this.lines = lines;
    }

    /**
     * Sets placeholders provided as a map into the message.
     * @param placeholders Map of placeholders
     * @return Instance of this message
     */
    public Message setPlaceholders(HashMap<String, String> placeholders) {
        for(int i = 0; i < lines.size()-1; i++) {
            String line = lines.get(i);
            for(Map.Entry<String, String> entry: placeholders.entrySet()) {
                line = line.replace(entry.getKey(), entry.getValue());
            }
            lines.set(i, line);
        }
        return this;
    }

    /**
     * Colorizes the message (applies RGB if 1.16+)
     * @return Instance of this message
     */
    public Message colorize() {
        for(int i = 0; i < lines.size()-1; i++) {
            lines.set(i, Formatter.colorize(lines.get(i)));
        }
        return this;
    }

    /**
     * Strips colors from the message.
     * @return Instance of this message
     */
    public Message stripColors() {
        for(int i = 0; i < lines.size(); i++) {
            this.lines.set(i, ChatColor.stripColor(this.lines.get(i)));
        }
        return this;
    }

    /**
     * Parses a message from provided configuration path, regardless whether the message is a
     * single-line (String) or multi-line (List<String>).
     * @param config Configuration to parse data from
     * @param path Message's path in the configuration
     * @return Instance of this message
     * @throws NullPointerException If path is empty or message is not set
     * @throws InvalidConfigurationException If object on the provided path is not a message type
     */
    public static Message fromPath(FileConfiguration config, String path) throws NullPointerException, InvalidConfigurationException {
        if(Strings.isNullOrEmpty(path)) {
            throw new NullPointerException("Message path is null/empty");
        }
        if(config.getString(path) == null) {
            throw new NullPointerException("Message on " + path + "is empty");
        }

        Object msg = config.get(path);
        if(msg instanceof String) {
            return new Message((String) msg);
        }

        if(msg instanceof List) {
            return new Message((ArrayList<String>) msg);
        }

        throw new InvalidConfigurationException("Invalid message type on: " + path);
    }

    /**
     * Sends the message to a player.
     * @param player The player to send the message to.
     */
    public void send(Player player) {
        for(String line: this.lines) {
            player.sendMessage(line);
        }
    }
}
