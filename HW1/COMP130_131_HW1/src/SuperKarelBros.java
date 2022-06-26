
/* STUDENT NAME:YAKUP ENES GUVEN
 * File: SuperKarelBro.java
 */

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import stanford.karel.SuperKarel;

public class SuperKarelBros extends SuperKarel {

	public void run() {

		playThemeSong(THEME_SONG);

		// Your code starts here.
	
		    clearWay();
			trimmingTheTrees();
			turnLeft();
			clearWay();
			reverseDownStairs();
			
			coinBricks();
			clearWay();
	        theLongPipe();
	        while (!cornerColorIs(PINK)) {
		        move();
	        }
	        thePinkTower();
	        clearWay();
	        turnLeft();
	        clearWay();
	        downStairs();
	        clearWay();
	        flagpole();
	        intoTheFortress();
			 
			
		
		// Your code ends here.

		playVictorySong(VICTORY_SONG);
	}

	/** Helper Methods */
	// Your code starts here.
	private  void trimmingTheTrees() {// take red apples then move
		turnLeft();	
		while (frontIsClear()) {
			move();
			if(cornerColorIs(RED)) {
				paintCorner(CYAN);
			}
			if(!rightIsBlocked()) {
				turnRight();
			}					
			}					
	}
	private void reverseDownStairs() {//climb top of stairs then move down
		turnLeft();
		while(rightIsBlocked()) {
			move();
	    }
		turnRight();
		move();
		move();
		while(rightIsClear()) {
			turnRight();
			move();
			turnLeft();
			move();
		}
	}	
	private void coinBricks() {//berak yellow bricks until got beeper
		while(rightIsBlocked()) {
			move();
		}
		move();
		turnLeft();
		while(cornerColorIs(CYAN)) {
			move();
		}
		
		while(!beepersPresent()) {
			turnAround();
			paintCorner(CYAN);
			clearWay();
			turnLeft();
			move();
			turnLeft();
			while(cornerColorIs(CYAN)) {
				move();
			}
		}
		while(beepersPresent()){
			turnAround();
			pickBeeper();
			paintCorner(CYAN);
			clearWay();
			turnLeft();
			
		}
	}
    private void theLongPipe() {// move into the pipe and take beepers
    	turnLeft();
    	while(rightIsBlocked()) {
    		move();
    	}
    	turnRight();
    	move();
    	turnRight();
    	while(frontIsClear()) {
    		move();
    		if(frontIsBlocked() && rightIsBlocked()) {
    			turnLeft();
    			while(beepersPresent()) {
    				pickBeeper();
    			}
    		}else if(frontIsBlocked() && leftIsBlocked()) {
    			turnRight();
    			while(beepersPresent()) {
    				pickBeeper();
    			}
    		}else if(beepersPresent()) {
    			while(beepersPresent()) {
    				pickBeeper();
    			}
    		}else if(!frontIsBlocked() && !rightIsBlocked() && !leftIsBlocked()) {
    			break;
    		}
     	}
    	turnRight();
    	move();
    	turnRight();
    	clearWay();
    	turnLeft();
    }
    private void thePinkTower() { // collect all beepers and put them black point
    	turnLeft();
    	while(cornerColorIs(PINK)) {
    		if(beepersPresent()) {
    			pickBeeper();
    		}else {
    			move();
    			pickBeeper();
    		}
    		move();
    		putBeeper();
    		turnAround();
    		move();
    		turnAround();
    	}
    	clearWay();
    	turnAround();
    	while(cornerColorIs(PINK)) {
    		if(beepersPresent()&&cornerColorIs(PINK)) {
    			pickBeeper();
    		}else if (cornerColorIs(PINK)){
    			move();
    			pickBeeper();
    		}
    		if(cornerColorIs(BLACK)) break;
    		move();
    		putBeeper();
    		turnAround();
    		move();
    		turnAround();
    	}
    }
    private void downStairs() {  //  goes top of the stairs	
    	while(frontIsBlocked()) {
    		turnLeft();
    		move();
    		turnRight();
    		move();
    	}
    	move();
    	turnRight();
    	clearWay();
    	turnLeft();
	}
    private void flagpole() {//climb flag then take beeper and flage move until below
    	turnLeft();
    	move();
    	turnRight();
    	move();
    	move();
    	turnLeft();
    	move();
    	while(!cornerColorIs(BLUE)) {
    		move();
    	}
    	turnAround();
    	while(rightIsBlocked()) {
    		pickBeeper();
    		paintCorner(CYAN);
    		move();
    		if(rightIsBlocked()) {
    			putBeeper();
        		paintCorner(BLUE);
    		}else
    			break;
    	}
    	turnLeft();
    	move();
    	turnRight();
    	move();
    	turnLeft();	
    }
    private void intoTheFortress() {//mario goes to white end
    	while(!cornerColorIs(WHITE)) {
    		move();
    	}
		
	}
    private void clearWay() {//if way is clear, it makes mario move
    	while(frontIsClear()) {
    		move();
    	}
    }
		
	

	// Your code ends here.

	/** ----- Do not change anything below here. ----- */

	private void playThemeSong(String fileLocation) {
		try {
			inputStream = AudioSystem.getAudioInputStream(new File(fileLocation));
			clip = AudioSystem.getClip();
			clip.open(inputStream);
			clip.loop(Clip.LOOP_CONTINUOUSLY);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void playVictorySong(String fileLocation) {
		try {
			clip.close();
			inputStream.close();
			inputStream = AudioSystem.getAudioInputStream(new File(fileLocation));
			clip.open(inputStream);
			clip.start();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}


	private static final String THEME_SONG = "theme.wav";
	private static final String VICTORY_SONG = "victory.wav";
	private Clip clip;
	private AudioInputStream inputStream;

}
