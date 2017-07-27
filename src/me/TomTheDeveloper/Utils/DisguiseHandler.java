package me.TomTheDeveloper.Utils;


import me.TomTheDeveloper.GameAPI;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.PlayerDisguise;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;

import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 * Created by Tom on 2/08/2014.
 */
public class DisguiseHandler {


    public static GameAPI plugin;

    public DisguiseHandler() {
    }


    public static void disguiseEntity(Entity entity, EntityType entityType){
        Entity disguiseentity = entity.getWorld().spawnEntity(entity.getLocation(), entityType);
        Disguise disguise = DisguiseAPI.constructDisguise(disguiseentity, true, true, true);
        DisguiseAPI.disguiseEntity(entity, disguise);
        disguiseentity.remove();
    }

    public static void disguiseEntity(Entity entity, EntityType entityType, List<Player> playerList){
        Entity disguiseentity = entity.getWorld().spawnEntity(entity.getLocation(), entityType);
        Disguise disguise = DisguiseAPI.constructDisguise(disguiseentity, true, true, true);
        DisguiseAPI.disguiseToPlayers(entity, disguise, (Collection) playerList);
        disguiseentity.remove();
    }

    public static void undisguiseEntity(Entity entity){
        if(DisguiseAPI.isDisguised(entity))
        DisguiseAPI.undisguiseToAll(entity);

    }

    public static void disguiseAsPlayer(Entity entity, String name){
        PlayerDisguise playerDisguise = new PlayerDisguise(name);
        DisguiseAPI.disguiseEntity(entity, playerDisguise);
    }

    public static void disguiseAsPlayer(Entity entity, String name, List<Player> players){
        PlayerDisguise playerDisguise = new PlayerDisguise(name);
        DisguiseAPI.disguiseToPlayers(entity, playerDisguise, (Collection) players);
    }

    public static void disguiseRandom(Entity entity){
        EntityType[] types = EntityType.values();
        Random random = new Random();
        EntityType entityType = types[random.nextInt(types.length-1)];
        disguiseEntity(entity, entityType);
    }

    public static void disguiseRandom(Entity entity, MOBTYPE mobtype){
        Random random = new Random();
        switch (mobtype){
            case FRIENDLY:
                EntityType[] friendlys = new EntityType[]{
                        EntityType.COW, EntityType.WOLF, EntityType.HORSE, EntityType.SHEEP, EntityType.OCELOT, EntityType.PIG,
                };
                disguiseEntity(entity, friendlys[random.nextInt(friendlys.length-1)]);
                break;
            case HOSTILE:
                EntityType[] hostiles = new EntityType[]{
                        EntityType.CREEPER, EntityType.SPIDER, EntityType.CAVE_SPIDER, EntityType.SKELETON, EntityType.ZOMBIE, EntityType.PIG_ZOMBIE,
                };
                disguiseEntity(entity, hostiles[random.nextInt(hostiles.length-1)]);
                break;
        }
    }

    public enum MOBTYPE {
      FRIENDLY, HOSTILE
    }

    public static Disguise getDisguise(Entity entity){

        if(DisguiseAPI.isDisguised(entity))
            return DisguiseAPI.getDisguise(entity);
        return null;
    }


}
