/*
RaceCar: Karel decided to practice for a car race.  He needs to go around the race track and
reach one of the beepers in the shortest time possible.  You are free to use any strategy you
would like.  We recommend following one of the walls which should take about 10 lines of code.
*/

import stanford.karel.SuperKarel;

public class RaceCar extends SuperKarel {

	public void run() {
		// Your code starts here
		while(true) {
			while (leftIsBlocked() && frontIsClear() && noBeepersPresent()) {
				move();
			}
			if (beepersPresent()) {
				break;
			} else if (leftIsClear()) {
				turnLeft(); 
				move();
			} else if (frontIsBlocked()) {
				turnRight();
			}			
		}
		// Your code ends here
	}
}
