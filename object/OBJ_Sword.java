package object;

import javax.imageio.ImageIO;

public class OBJ_Sword extends SuperObject{
    public OBJ_Sword(){
        name = "brokensword";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/brokensword.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
