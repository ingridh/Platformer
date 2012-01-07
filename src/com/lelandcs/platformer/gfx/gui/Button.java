package com.lelandcs.platformer.gfx.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * A basic button
 */
public class Button {
    /* The offset between button's left edge and the start of the text */
    public int xoffset = 10;
    /* The offset between button's bottom edge and the bottom of the text */
    public int yoffset = 10;
    
    /* The position and dimensions of the button */
    public int x;
    public int y;
    public int height;
    public int width;
    
    /* The clickable region */
    private Rectangle rec;
    
    /* The colors of the button */
    public Color buttonColor;
    public Color highlightColor;
    public Color textColor;

    /* The text of the button */
    public String text;
    public Font font;
    
    /* indicates if the mouse is over the button */
    private boolean focused = false;
    
    
    public Button(int x, int y, int width, int height, Color buttonColor, 
            Color textColor, String text, Font font) {
        this.x = x;
        this.y = y;
        
        this.width = width;
        this.height = height;
        
        this.buttonColor = buttonColor;
        highlightColor = buttonColor.brighter();
        this.textColor = textColor;
        this.text = text;
        this.font = font;
        
        rec = new Rectangle(x, y, width, height);
    }
    
    public void update(int mousex, int mousey) {
        if (rec.contains(mousex, mousey)) {
            focused = true;
        }
        else {
            focused = false;
        }
    }
    
    public void render(Graphics g) {
        Color initial = g.getColor(); // preserve old color
        
        if (focused) {
            g.setColor(highlightColor);
        }
        else {
            g.setColor(buttonColor);
        }
        g.fillRect(x, y, width, height);
       
        g.setColor(textColor);
        g.setFont(font);
        g.drawString(text, x + xoffset, y + height - yoffset);
        
        g.setColor(initial);
    }
}
