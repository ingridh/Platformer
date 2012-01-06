package com.lelandcs.platformer;

import com.lelandcs.platformer.gfx.PlatformerCanvas;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;

/**
 * The entry point for the game
 */
public class PlatformerGame extends JFrame implements WindowListener {
        
        private static final int FPS = 40;
        
	private PlatformerCanvas canvas;

	public PlatformerGame() {
		super("Platformer Game"); // argument - title of window

		canvas = new PlatformerCanvas(FPS);
		add(canvas); // add the game graphics panel to the JFrame
		
		addWindowListener(this); // keep track of events on the window
		
		pack(); // fixes window size
		
		setResizable(false); // sets the window unresizable
		
		setVisible(true); // sets the window visible
		
		setLocationRelativeTo(null); // centers the window 
	}
        
        /* Start point */
        public static void main(String[] args) {
		System.out.println("Platformer Game");
		PlatformerGame game = new PlatformerGame();
	}

	public void windowActivated(WindowEvent e) {}

	public void windowClosed(WindowEvent e) {}

	public void windowClosing(WindowEvent e) {
		canvas.setRunning(false);
		System.exit(0);
	}

	public void windowDeactivated(WindowEvent e) {}

	public void windowDeiconified(WindowEvent e) {}

	public void windowIconified(WindowEvent e) {}

	public void windowOpened(WindowEvent e) {}
}
