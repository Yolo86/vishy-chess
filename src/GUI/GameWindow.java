package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GameWindow {
	private JFrame gameWindow;
	private Arena arena;
	
	public GameWindow() {
		gameWindow = new JFrame("Chess");
		gameWindow.setLocation(100, 60);
	    gameWindow.setLayout(new BorderLayout(20,20));
	    
	    arena = new Arena(this);
	    
	    gameWindow.add(arena, BorderLayout.CENTER);
	    
	    gameWindow.setMinimumSize(gameWindow.getPreferredSize());
        gameWindow.setSize(gameWindow.getPreferredSize());
        gameWindow.setResizable(false);
        
        gameWindow.pack();
        gameWindow.setVisible(true);
        gameWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}
