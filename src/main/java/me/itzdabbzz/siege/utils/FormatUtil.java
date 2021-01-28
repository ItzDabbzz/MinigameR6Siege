package me.itzdabbzz.siege.utils;

import com.google.gson.JsonObject;
import me.itzdabbzz.siege.config.Lang;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.Random;

public class FormatUtil {

    public static boolean isNullOrEmpty(String str) {
        if(str != null && !str.isEmpty())
            return false;
        return true;
    }

    /**
     * Applies default {@link ChatColor} codes to String and returns it.
     *
     * @param string String that should get default ChatColor applied
     * @return Colored string
     */
    public static String colorString(String string){
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    /**
     * Formats a time in seconds to better looking format, set in configuration.
     *
     * @param sec Time that should be formatted
     * @return Formatted time in String
     */
    public static String formatTime(long sec) {
        long seconds = sec % 60;
        long minutes = sec / 60;
        if (minutes >= 60) {
            long hours = minutes / 60;
            minutes %= 60;
            if (hours >= 24) {
                long days = hours / 24;
                return String.format(Lang.TIME_FORMAT_DAYS.get(), days, hours % 24, minutes, seconds);
            }
            return String.format(Lang.TIME_FORMAT_HOURS.get(), hours, minutes, seconds);
        }
        return String.format(Lang.TIME_FORMAT_MINUTES.get(), minutes, seconds);
    }

    /**
     * Randomly scrambles String.
     *
     * @param inputString String that should be scrambled
     * @return Scrambled inputString
     */
    public static String scramble(String inputString) {
        char[] a = inputString.toCharArray();

        for(int i=0 ; i<a.length ; i++) {
            int j = new Random().nextInt(a.length);
            char temp = a[i]; a[i] = a[j];  a[j] = temp;
        }

        return new String(a);
    }

    /**
     * Returns a {@link String} as a progress bar.
     *
     * @param current Current amount of progress.
     * @param max Maximum amount of progress.
     * @param symbol Symbol, that will represent the bar.
     * @param lockedColor Color of unfinished progress.
     * @param unlockedColor Color of finished progress.
     * @return {@link String} that will represent the progress bar.
     */
    public static String getProgressBar(float current, float max, String symbol, String lockedColor, String unlockedColor){
        float onePercent = max / 100;
        float percent = current / onePercent;
        int unlocked = Math.round(percent / 10);

        StringBuilder progress = new StringBuilder();
        for (int i = 1; i<=10;i++){
            progress.append(symbol);
        }
        progress.insert(unlocked, lockedColor);
        progress.insert(0, unlockedColor);
        return colorString(progress.toString());
    }

    //Translate a Location to a String
    public static String parseToString(Location location) {
        return location.getX() + "," +
                location.getY() + "," +
                location.getZ() + "," +
                location.getYaw() + "," +
                location.getPitch() + "," +
                location.getWorld().getName();
    }

    //Translate a String to a Location
    public static Location parseToLocation(String string) {
        if (string == null) return null;
        String[] data = string.split(",");
        try {
            double x = Double.parseDouble(data[0]);
            double y = Double.parseDouble(data[1]);
            double z = Double.parseDouble(data[2]);
            float pitch = Float.valueOf(data[4]);
            float yaw = Float.valueOf(data[3]);
            World world = Bukkit.getWorld(data[5]);
            Location location = new Location(world, x, y, z, yaw, pitch);
            //location.setPitch((float) pitch);
            //location.setYaw((float) yaw);
            return location;
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static JsonObject parseToJson(Location location) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("x", location.getX());
        jsonObject.addProperty("y", location.getY());
        jsonObject.addProperty("z", location.getZ());
        jsonObject.addProperty("yaw", location.getYaw());
        jsonObject.addProperty("pitch", location.getPitch());
        jsonObject.addProperty("world", location.getWorld().getName());
        return jsonObject;
    }

    public static Location parseToLocation(JsonObject jsonObject) {
        double x = jsonObject.get("x").getAsDouble();
        double y = jsonObject.get("y").getAsDouble();
        double z = jsonObject.get("z").getAsDouble();
        float yaw = jsonObject.get("yaw").getAsFloat();
        float pitch = jsonObject.get("pitch").getAsFloat();
        World world   = Bukkit.getWorld(jsonObject.get("world").getAsString());
        return new Location(world, x, y, z, yaw, pitch);
    }
}
