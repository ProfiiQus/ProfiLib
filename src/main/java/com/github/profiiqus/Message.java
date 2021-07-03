package com.github.profiiqus;

import com.github.profiiqus.utils.Formatter;

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
}
