package com.github.profiiqus.utils;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class representing a simple text formatter.
 * @author ProfiiQus
 */
public class Formatter {

    private static final Pattern HEX_PATTERN = Pattern.compile("<#([A-Fa-f0-9]){6}>");
    private static final Set<String> RGB_VERSIONS = new HashSet<>() {
        {
            add("1.16");
            add("1.17");
        }
    };

    /**
     * Colorizes provided text. If server version supports RGB, it is also applied.
     * @param text Text to be colorized
     * @return Colorized text
     */
    public static String colorize(String text) {
        if(appliesRGB()) {
            Matcher matcher = HEX_PATTERN.matcher(text);
            while (matcher.find()) {
                ChatColor hexColor = ChatColor.of(matcher.group().substring(1, matcher.group().length() - 1));
                String before = text.substring(0, matcher.start());
                String after = text.substring(matcher.end());
                text = before + hexColor + after;
                matcher = HEX_PATTERN.matcher(text);
            }
        }
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    /**
     * Returns information, whether the server version supports RGB.
     * @return Boolean, whether RGB is supported
     */
    private static boolean appliesRGB() {
        for(String version: RGB_VERSIONS) {
            if(Bukkit.getVersion().contains(version)) {
                return true;
            }
        }
        return false;
    }

}
