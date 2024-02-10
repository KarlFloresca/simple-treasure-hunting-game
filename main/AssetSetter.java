package main;

import object.OBJ_Door;
import object.OBJ_Gem;
import object.OBJ_Key;
import object.OBJ_Sword;
import object.OBJ_Talisman;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    // determines where the objects are set
    public void setObject(){
        gp.obj[0] = new OBJ_Talisman();
        gp.obj[0].worldX = 75 * gp.tileSize;
        gp.obj[0].worldY = 2 * gp.tileSize;

        gp.obj[1] = new OBJ_Sword();
        gp.obj[1].worldX = 44 * gp.tileSize;
        gp.obj[1].worldY = 17 * gp.tileSize;

        gp.obj[2] = new OBJ_Gem();
        gp.obj[2].worldX = 57 * gp.tileSize;
        gp.obj[2].worldY = 16 * gp.tileSize;

        gp.obj[3] = new OBJ_Key();
        gp.obj[3].worldX = 2 * gp.tileSize;
        gp.obj[3].worldY = 18 * gp.tileSize;

        gp.obj[4] = new OBJ_Door();
        gp.obj[4].worldX = 34 * gp.tileSize;
        gp.obj[4].worldY = 17 * gp.tileSize;
    }
}
