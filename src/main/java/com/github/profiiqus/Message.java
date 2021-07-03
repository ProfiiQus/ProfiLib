package com.github.profiiqus;

import com.github.profiiqus.utils.Formatter;
import com.google.common.base.Strings;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.*;

public class Message {

    private final List<String> lines;

    public Message(String message) {
        this.lines = new ArrayList<>();
        this.lines.add(message);
    }

    public Message(List<String> lines) {
        this.lines = lines;
    }

    public void setPlaceholders(HashMap<String, String> placeholders) {
        for(int i = 0; i < lines.size()-1; i++) {
            String line = lines.get(i);
            for(Map.Entry<String, String> entry: placeholders.entrySet()) {
                line = line.replace(entry.getKey(), entry.getValue());
            }
            lines.set(i, line);
        }
    }

    public void colorize() {
        for(int i = 0; i < lines.size()-1; i++) {
            lines.set(i, Formatter.colorize(lines.get(i)));
        }
    }

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
}
