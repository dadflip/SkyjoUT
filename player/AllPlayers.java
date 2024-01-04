package player;

import java.util.ArrayList;

public class AllPlayers {
    private int index;
    private static int playersnumber;
    public static ArrayList<Player> allplayers;

    public AllPlayers(ArrayList<Player> allp, int pnum){
        AllPlayers.allplayers = allp;
        AllPlayers.playersnumber = pnum;
    }

    public void setPlayerIndex(int i){
        this.index = i;
    }

    public int getPlayerIndex(){
        return index;
    }

    public void setPlayerNumber(int n){
        AllPlayers.playersnumber = n;
    }
    
    public static int getPlayerNumber(){
        return AllPlayers.playersnumber;
    }

    public static ArrayList<Player> getAllPlayers() {
        return AllPlayers.allplayers;
    }

    public void addToAllPlayer(Player p){
        allplayers.add(p);
    }

    public void removeFromAllPlayer(Player p){
        allplayers.remove(p);
    }

    public static Player getPlayerAtIndex(int i){
        if(allplayers.size() != 0){System.out.println("vide !");}
        return allplayers.get(i);
    }

}
