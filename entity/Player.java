package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    int hasKey = 0;
    int relics = 0;
    
    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - gp.tileSize/2;
        screenY = gp.screenHeight/2 - gp.tileSize/2;

        // variables for player collision area
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;
        
        // calls methods
        setDefaultValues();
        getPlayerImg();
    }

    // set the default position of player
    public void setDefaultValues(){
        worldX = gp.tileSize * 9;
        worldY = gp.tileSize * 9;
        speed = 4;
        direction = "down";
    }

    // gets the images from the player folder
    public void getPlayerImg(){
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/back1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/back2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/forward1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/forward2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/left2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/right2.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // updates information while running
    public void update(){
        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true){
             // handles movement using the key handler
            if(keyH.upPressed == true){
                direction = "up";
            }
            else if(keyH.downPressed == true){
                direction = "down";
            }
            else if(keyH.rightPressed == true){
                direction = "right";
            }
            else if(keyH.leftPressed == true){
                direction = "left";
            }
            
            // check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // check object collision
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObjects(objIndex);

            // if false player can move
            if (collisionOn == false) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                
                    default:
                        break;
                }
            }


            // sprite counter, helps in drawing the animation like frame 1 and frame 2.
            spriteCounter = spriteCounter + 1;
            if(spriteCounter > 10){
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void pickUpObjects(int i){
        if (i != 999) {
            
            String objectName = gp.obj[i].name;

            switch (objectName) {
                case "key":
                    hasKey++;
                    gp.obj[i] = null;
                    System.out.println("you've found a key!");
                    break;
                case "door":
                    if (hasKey > 0 ) {
                        gp.obj[i] = null;
                        System.out.println("door opened");
                        hasKey--;
                    }
                    break;
                case "redgem":
                    relics++;
                    gp.obj[i] = null;
                    System.out.println("found the ancient gem of the first queen");
                    System.out.print("you have "+relics+"/3");
                    break;
                    
                case "Talisman":
                    relics++;
                    gp.obj[i] = null;
                    System.out.println("you have found the holy artifact of the old gods");
                    System.out.print("you have "+relics+"/3");
                    break;
                case "brokensword":
                    relics++;
                    gp.obj[i] = null;
                    System.out.println("you have found the remains of the sword that challenged the outsiders");
                    System.out.print("you have "+relics+"/3");
                    break;
                default:
                    break;
            }
        }
    }

    public void draw(Graphics2D g2){
        // draws the character
        BufferedImage image = null;

        switch (direction) {
            case "up":
                if(spriteNum == 1){
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;

                }
                    
                break;
            case "down":
                if(spriteNum == 1){
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1){
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1){
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
                break;
            default:
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
