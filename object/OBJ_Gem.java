package object;

import javax.imageio.ImageIO;

public class OBJ_Gem extends SuperObject{
    public OBJ_Gem(){
        name = "redgem";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/redgem.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
