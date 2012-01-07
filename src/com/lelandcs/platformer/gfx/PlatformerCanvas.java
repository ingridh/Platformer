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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
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
		
	setPreferredSize(new Dimension(CWIDTH-10, CHEIGHT-10));
		
	setFocusable(true);
	requestFocus();
        
        currentState = State.INMENU; // set the state to being in the menu
        
        init();
    }
    
    /* Initialize components */
    private void init() {
        addInput();
        loadFonts();
    }
    
    private void addInput() {
        addKeyListener( new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
	            
            }
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_ESCAPE) {
                    master.exit();
                }
            }
        });
        addMouseMotionListener( new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                // input handling here
            }
        });
        addMouseListener( new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                // input handling here
            }
        });
    }
    
    private void loadFonts() {
        fonts = new HashMap<String, Font>();
        Font f1 = new Font("Arial", Font.ITALIC, 24);
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
	    dbg.setColor(Color.black);
	    dbg.fillRect(0, 0, CWIDTH, CHEIGHT);
            
            renderGameGraphics();
	    
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
    public void renderGameGraphics() {
        if (currentState == State.INMENU) {
           dbg.setFont(fonts.get("Arial"));
	   dbg.setColor(Color.white);
	   dbg.drawString("In the Game Menu!", 300 , 300);
        }
        else if (currentState == State.INGAME) {
            
        }
        else if (currentState == State.GAMEOVER) {
            
        }
    }
}
