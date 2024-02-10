package object;

import javax.imageio.ImageIO;

public class OBJ_Key extends SuperObject{
    public OBJ_Key(){
        name = "key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/key.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
