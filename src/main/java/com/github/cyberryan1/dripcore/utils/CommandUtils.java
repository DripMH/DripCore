package com.github.cyberryan1.dripcore.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandUtils {

    public static ArrayList<String> getAllOnlinePlayerNames() {
        ArrayList<String> toReturn = new ArrayList<>();
        for ( Player p : Bukkit.getOnlinePlayers() ) {
            toReturn.add( p.getName() );
        }

        return toReturn;
    }

    public static ArrayList<String> matchOnlinePlayers( String startsWith ) {
        ArrayList<String> toReturn = new ArrayList<>();
        for ( Player p : Bukkit.getOnlinePlayers() ) {
            if ( p.getName().toUpperCase().startsWith( startsWith.toUpperCase() ) ) {
                toReturn.add( p.getName() );
            }
        }

        return toReturn;
    }

    public static String combineRemainingArgs( String args[], int startingIndex ) {
        String str = "";
        for ( int i = startingIndex; i < args.length; i++ ) {
            str += args[i] + " ";
        }
        return str;
    }
}