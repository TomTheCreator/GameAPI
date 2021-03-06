package me.TomTheDeveloper.Handlers;

import me.TomTheDeveloper.Game.GameInstance;
import me.TomTheDeveloper.GameAPI;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tom on 27/07/2014.
 */
public class GameInstanceManager {

    private List<GameInstance> gameInstances = new ArrayList<GameInstance>();
    private static GameInstanceManager gameInstanceManager;

    public List<GameInstance> getGameInstances() {
        return gameInstances;
    }

    public GameInstanceManager() {

    }



    public boolean isInGameInstance(Player p){
        boolean b = false;
        for(GameInstance gameInstance: gameInstances){
            if(gameInstance.getPlayers().contains(p)){
                b = true;
                break;
            }
        }
        return b;
    }

    public GameInstance getGameInstance(Player p){
        GameInstance gameInst = null;
        if(p == null)
            return  null;
        if(!p.isOnline())
            return  null;

           for(GameInstance gameInstance:gameInstances){
               for(Player player: gameInstance.getPlayers()){
                   if(player.getUniqueId() == p.getUniqueId()) {
                       gameInst = gameInstance;
                       break;
                   }
               }



           }


        return gameInst;
    }

    public void registerGameInstance(GameInstance gameInstance){
        gameInstances.add(gameInstance);
    }

    public GameInstance getGameInstance(String ID) {
        GameInstance GameInst = null;
        for (GameInstance GameInstance : gameInstances) {
            if (GameInstance.getID().equalsIgnoreCase(ID)) {
                GameInst = GameInstance;
                break;
            }
        }


            return GameInst;

    }



}
