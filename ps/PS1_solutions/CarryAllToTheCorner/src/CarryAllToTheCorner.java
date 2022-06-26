/* File: CarryAllToTheCorner.java
 * 
 * Carry all beepers on the way to the right corner 
 */

import stanford.karel.*;

public class CarryAllToTheCorner extends Karel {

	public void run() {
		//Your code starts here
		while(frontIsClear()){
			while(beepersPresent())
				pickBeeper();
			move();
			putBeeper();
			turnAround();
			move();
			turnAround();
			move();
		}
		//Your code ends here
	}
	//Helper method implementations here
	//Your code starts here
	
	private void turnAround(){
		turnLeft();turnLeft();
	}
	//Your code ends here

}