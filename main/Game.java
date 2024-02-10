package main;

import javax.swing.JFrame;


public class Game {
    public static void main(String[] args){
        
        // instanciations of classes
        GamePanel gamePanel = new GamePanel();
        JFrame window = new JFrame();
        
        
        // set the display and settings of the game screen
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Journey Through Prelic");
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
        gamePanel.setUpGame();
        // initializes the gamepanel
        gamePanel.startGameThread();
    }
}