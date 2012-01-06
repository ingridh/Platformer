package com.lelandcs.platformer.gfx;

import com.lelandcs.platformer.PlatformerGame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;

/**
 * This is the panel that gets the graphics rendered on
 * It can be added to a JFrame or made into an Applet
 */
public class PlatformerCanvas extends Canvas {
    public static final int CHEIGHT = 600; // The dimensions of the panel
    public static final int CWIDTH = 800;
    
    /* Keep track of the current state */
    public enum State { INMENU, INGAME, GAMEOVER };
    public static State currentState;
    
    private Graphics2D dbg; 
    private Image dbImage;
    
    public HashMap<String, Font> fonts;
    
    public PlatformerGame master;
    
    public PlatformerCanvas(PlatformerGame master, int fps) {
        this.master = master;
        
        System.out.println("Desired FPS: " + fps);
        setDesiredFPS(fps);
		
	setPreferredSize(new Dimension(CWIDTH, CHEIGHT));
		
	setBackground(Color.white);
		
	setFocusable(true);
	requestFocus();
	addKeyInput();
        
        loadFonts();
        
        currentState = State.INMENU; // set the state to being in the menu
    }
    
    private void addKeyInput() {
        addKeyListener( new KeyAdapter() {
	       public void keyReleased(KeyEvent e) {
	             
	       }
	       public void keyPressed(KeyEvent e) {
	            int keyCode = e.getKeyCode();
	            if (keyCode == KeyEvent.VK_ESCAPE) {
	            	master.exit();
	            }
               }});
    }
    
    private void loadFonts() {
        fonts = new HashMap<String, Font>();
        Font f1 = new Font("SansSerif", Font.PLAIN, 24);
        fonts.put(f1.getFamily(), f1);
    }

    public void update() {
        
    }
    
    public void render() {
        doDoubleBufferedRender();
    }

    /*
     * Uses a double buffering technique
     * First, render the graphics onto a temporary image (dbImage)
     * Then, render that image onto the panel
     * (Instead of rendering the graphics directly on)
     * (Avoids flickering)
     */
    public void doDoubleBufferedRender() {
            if (dbImage == null){
                dbImage = createImage(CWIDTH, CHEIGHT);
                if (dbImage == null) {
                    System.err.println("dbImage is null");
                    return;
                }
                else {
                    dbg = (Graphics2D) dbImage.getGraphics();
                }
	    }
	    dbg.setRenderingHint
        	(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

	    // clear the background
	    dbg.setColor(Color.white);
	    dbg.fillRect(0, 0, CWIDTH, CHEIGHT);
            
            renderGameGraphics(dbg);
	    
	    Graphics g;
	    try {
                g = getGraphics();
                if ((g != null) && (dbImage != null)) {
                    g.drawImage(dbImage, 0, 0, null);
                }
                g.dispose();
	    }
	    catch (Exception e)
	    { 
                e.printStackTrace();
            }
    }

    /*
     * A separate function for rendering the game's graphics
     */
    public void renderGameGraphics(Graphics2D g) {
        // insert respective graphics here
        if (currentState == State.INMENU) {
           dbg.setFont(fonts.get("SansSerif"));
	   dbg.setColor(Color.black);
	   dbg.drawString("In the Game Menu!", 300 , 300);
        }
        else if (currentState == State.INGAME) {
            
        }
        else if (currentState == State.GAMEOVER) {
            
        }
    }
}
