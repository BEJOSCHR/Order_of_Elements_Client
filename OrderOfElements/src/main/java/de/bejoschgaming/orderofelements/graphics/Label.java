
package de.bejoschgaming.orderofelements.graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ConcurrentModificationException;

import javax.swing.JLabel;

import de.bejoschgaming.orderofelements.animationsystem.AnimationHandler;
import de.bejoschgaming.orderofelements.animationsystem.animations.Animation;
import de.bejoschgaming.orderofelements.graphics.drawparts.Draw_1Loadingscreen;
import de.bejoschgaming.orderofelements.graphics.drawparts.Draw_2Login;
import de.bejoschgaming.orderofelements.graphics.drawparts.Draw_3Menu;
import de.bejoschgaming.orderofelements.graphics.drawparts.Draw_4Deckbuilder;
import de.bejoschgaming.orderofelements.graphics.drawparts.Draw_5Friendlist;
import de.bejoschgaming.orderofelements.graphics.drawparts.Draw_6Matchhistory;
import de.bejoschgaming.orderofelements.graphics.drawparts.Draw_7Credits;
import de.bejoschgaming.orderofelements.graphics.drawparts.Draw_8Ingame;
import de.bejoschgaming.orderofelements.graphics.drawparts.Draw_9Aftergame;
import de.bejoschgaming.orderofelements.maa.MouseActionArea;
import de.bejoschgaming.orderofelements.maa.MouseActionAreaHandler;


@SuppressWarnings("serial")
public class Label extends JLabel {

    private int displayedFPS = 0;
    private long nextSecond = System.currentTimeMillis() + 1000;
    private int framesInCurrentSecond = 0;
    private int framesInLastSecond = 0;
    private long nextRepaintDelay = 0;
    private int maxFPS = 120;
    private boolean showFPS = true;

    public Label() {

		this.setBounds(0, 0, GraphicsHandler.getWidth(), GraphicsHandler.getHeight());
		this.setVisible(true);
		GraphicsHandler.getFrame().add(this, BorderLayout.CENTER);

    }
    
    @Override
    protected void paintComponent(Graphics g) {

		// MAX FPS GRENZE SCHAFFEN
		long now = System.currentTimeMillis();
		try {
			if(nextRepaintDelay > now) {
				Thread.sleep(nextRepaintDelay - now);
		    }
		    nextRepaintDelay = now + 1000 / (maxFPS - 41);
		}catch (InterruptedException e) {}
		
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		//CONTENT
		
		switch (GraphicsHandler.getDrawState()) {
		case PROGRAMMSTART:
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, GraphicsHandler.getWidth(), GraphicsHandler.getHeight());
			break;
		case LOADINGSCREEN:
			Draw_1Loadingscreen.draw(g);
			break;
		case LOGIN:
			Draw_2Login.draw(g);
			break;
		case MENU:
			Draw_3Menu.draw(g);
			break;
		case DECKBUILDER:
			Draw_4Deckbuilder.draw(g);
			break;
		case FRIENDLIST:
			Draw_5Friendlist.draw(g);
			break;
		case MATCHHISTORY:
			Draw_6Matchhistory.draw(g);
			break;
		case CREDITS:
			Draw_7Credits.draw(g);
			break;
		case INGAME:
			Draw_8Ingame.draw(g);
			break;
		case AFTERGAME:
			Draw_9Aftergame.draw(g);
			break;
		}
		
		// MAA - before animation
		try {
			for(MouseActionArea maa : MouseActionAreaHandler.getMAAs()) {
				if (maa.isActiv() && maa.isOverlapAnimations() == false) {;
					maa.draw(g);
				}
			}
		} catch (ConcurrentModificationException error) {}
		
		// ANIMATIONS
		try {
			for(Animation animation : AnimationHandler.getRunningAnimations()) {
				animation.draw(g);
			}
		} catch (ConcurrentModificationException error) {}
		
		// MAA - after animation
		try {
			for(MouseActionArea maa : MouseActionAreaHandler.getMAAs()) {
				if (maa.isActiv() && maa.isOverlapAnimations() == true) {;
					maa.draw(g);
				}
			}
		} catch (ConcurrentModificationException error) {}
		
		// DRAW FPS
		if(showFPS == true) {
		    g.setColor(Color.WHITE);
		    g.setFont(new Font("Arial", Font.BOLD, (int) (12)));
		    g.drawString("" + getCurrentFPSValue(), 0 + 2, 0 + 13);
		}
		
		// CALCULATE FPS
		calculateFPS();
		
		repaint();

    }

    /**
     * Berechnet und updatet die FPS
     */
    private void calculateFPS() {
		long currentTime = System.currentTimeMillis();
		if (currentTime > nextSecond) {
		    nextSecond += 1000;
		    framesInLastSecond = framesInCurrentSecond;
		    framesInCurrentSecond = 0;
		}
		framesInCurrentSecond++;
		displayedFPS = framesInLastSecond;
    }

    /**
     * Gibt die derzeitigen FPS an
     * 
     * @return {@link Integer}, die derzeitigen FPS
     */
    public int getCurrentFPSValue() {
    	return displayedFPS;
    }

    public void setShowFPS(boolean showFPS) {
    	this.showFPS = showFPS;
    }

    public boolean isShowingFPS() {
    	return showFPS;
    }

}
