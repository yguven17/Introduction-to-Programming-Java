/* File: FlyingMountain.java
 * 
 * Karel is in space and should put beepers on a flying mountain
 */

import stanford.karel.*;

public class FlyingMountain extends SuperKarel {

	public void run() {
		//Your code starts here
		//Get to initial condition
		turnLeft();move();
		for(int k=0;k<4;k++){
			putGrass();paintCorner(ORANGE);
			turnRight();move();paintCorner(ORANGE);
			climbMountain();			
		}
		//go to final position
		turnAround();move();turnLeft();
		//Your code ends here
	}
	//Helper method implementations here
	//Your code starts here
	private void putGrass(){
		while(rightIsBlocked()){
			putBeeper();paintCorner(ORANGE);move();
		}
	}
	private void climbMountain(){
		while(frontIsBlocked()){
			turnLeft();move();paintCorner(ORANGE);
			turnRight();move();paintCorner(ORANGE);
		}
		paintCorner(ORANGE);
	}
	//Your code ends here
}