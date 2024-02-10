package object;

import javax.imageio.ImageIO;

public class OBJ_Talisman extends SuperObject{
    public OBJ_Talisman(){
        name = "Talisman";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/talisman.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
