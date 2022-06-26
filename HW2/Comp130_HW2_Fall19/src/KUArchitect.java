
/** Student Name:YAKUP ENES GÜVEN
 * This is a console program designed to conduct an audition for an architecture
 * competition. Four contestants are competing against each other given their 
 * education, experience and their conciousness about environment. 
 * 
**/

import acm.program.ConsoleProgram;
import acm.util.RandomGenerator;

public class KUArchitect extends ConsoleProgram {
	public static void main(String[] args) {
		new KUArchitect().start(args);
	}

	public void run() {
		// your code starts here

		for (currentKUArchitectID = 1; currentKUArchitectID <= CONTESTANT_NUM; currentKUArchitectID++) {
			println();
			println();
			double knowledge = askKnowledge();
			double experience = askExperience();
			double awards = askAwards();
			boolean hasRecycledMaterial = hasRecycledMaterial();
			int p = pointCalculator(knowledge, experience, awards, hasRecycledMaterial);
			assignPoint(p);

		}
		comparator();
		finalGame();

		// your code ends here

	}

	//////////// GIVEN HELPER METHODS /////////////////////
	// You need to implement the given helper methods ///
	// You ARE NOT ALLOWED to change the signature of the given methods.

	/**
	 * This methods asks the number of year of education and number of year of
	 * experience. It will keep on asking user input until a valid input is read.
	 * 
	 * @return - returns the knowledge point
	 */
	private double askKnowledge() {
		// this part asks years of education and experience and assigns them as int.
		// your code starts here
		println("NEW CONTESTANT:");

		int yearsEducation = readInt("Years of education of KUArchitect#" + currentKUArchitectID + ": ");
		while (yearsEducation < 4 || yearsEducation > 6) {
			println("Please enter number between 4 and 6.");
			yearsEducation = readInt("Years of education of KUArchitect#" + currentKUArchitectID + ": ");
		}

		int yearsExperience = readInt("Years of experience of KUArchitect#" + currentKUArchitectID + ": ");
		while (yearsExperience < 2 || yearsExperience > 10) {
			println("Please enter number between 2 and 10.");
			yearsExperience = readInt("Years of experience of KUArchitect#" + currentKUArchitectID + ": ");
		}

		return calculateKnowledge(yearsEducation, yearsExperience);
		// your code ends here
	}

	/**
	 * This method calculates the knowledge point given the number of year of
	 * education and the number of year of experience
	 * 
	 * @param yearsEducation  - the number of year of education
	 * @param yearsExperience - the number of year of experience
	 * @return - knowledge point
	 */
	private double calculateKnowledge(int yearsEducation, int yearsExperience) {
		// this part calculate knowledge point and assign it as double.
		// your code starts here
		double knowledgePoint = ((yearsEducation + yearsExperience) / 5.0);
		double product = 1.0;
		for (int i = 1; i < (yearsExperience - 2); i++) {
			product *= knowledgePoint;
		}
		knowledgePoint = product * fibonacci(yearsEducation);

		return knowledgePoint;
		// your code ends here
	}

	/**
	 * This methods asks the number of projects and number of different projects. It
	 * will keep on asking user input until a valid input is read.
	 * 
	 * @return - returns the experience point
	 */
	private int askExperience() {
		// this part asks projects and assign them as int.
		// your code starts here
		int numProjects = readInt("Number of projects completedd by KUArchitect#" + currentKUArchitectID + ": ");
		while (numProjects < 4 || numProjects > 15) {
			println("Please enter number between 4 and 15.");
			numProjects = readInt("Number of projects completedd by KUArchitect#" + currentKUArchitectID + ": ");
		}

		int numDifProjects = readInt("Number of different projects of KUArchitect#" + currentKUArchitectID + ": ");
		while (numDifProjects < 2 || numDifProjects > 5) {
			println("Please enter number between 2 and 5.");
			numDifProjects = readInt("Number of different projects of KUArchitect#" + currentKUArchitectID + ": ");
		}

		return calculateExperience(numProjects, numDifProjects);
		// your code ends here

	}

	/**
	 * This method calculates the experience point given the number of projects and
	 * the different projects worked on.
	 * 
	 * @param numProjects    - number of projects
	 * @param numDifProjects - number of different projects
	 * @return - experience point
	 */
	private int calculateExperience(int numProjects, int numDifProjects) {
		// this part calculates experience point as int.
		// your code starts here
		int experiencePoint = numProjects * factorial(numDifProjects);
		return experiencePoint;

		// your code ends here
	}

	/**
	 * This methods asks the number of awards received. It will keep on asking user
	 * input until a valid input is read.
	 * 
	 * @return - number of awards
	 */
	private int askAwards() {
		// this part ask awards and assign it as int.
		// your code starts here
		int awards = readInt("Numbers of awards of KUArchitect#" + currentKUArchitectID + ": ");
		while (awards < 1 || awards > 5) {
			println("Please enter number between 1 and 5.");
			awards = readInt("Numbers of awards of KUArchitect#" + currentKUArchitectID + ": ");
		}

		return awards;
		// your code ends here
	}

	/**
	 * This method decides which architect gets the recycled materials.
	 * 
	 * @return - whether the architect received a recycled material or not.
	 */
	private boolean hasRecycledMaterial() {
		// your code starts here

		return rgen.nextBoolean();

		// your code ends here
	}

	/**
	 * This method calculates the total points of a KUArchitect
	 * 
	 * @param knowledge           - the knowledge point of a KUArchietct
	 * @param experience          - the experience point of a KUArchietct
	 * @param awards              - the number of awards of a KUArchietct
	 * @param hasRecycledMaterial - whether KUArchiects received recyled material or
	 *                            not.
	 * @return - the total point of a KUArchitect
	 */
	private int pointCalculator(double knowledge, double experience, double awards, boolean hasRecycledMaterial) {
		// this part calculates total point as int.
		// your code starts here

		int totalPoint;
		if (hasRecycledMaterial()) {
			totalPoint = (int) (Math.sqrt(knowledge * experience) + 1.3 * knowledge);
			if (totalPoint % 1 < 0.5) {
				totalPoint = totalPoint - (totalPoint % 1);
			}
		} else {
			totalPoint = (int) (Math.sqrt(knowledge * experience) + 0.9 * knowledge);
			if (totalPoint % 1 >= 0.5) {
				totalPoint = totalPoint - (totalPoint % 1) + 1;
			}
		}
		return totalPoint;
		// your code ends here
	}

	/**
	 * This method assigns the total point calculatef for a KUArchitects and assigns
	 * to the correct KUArchitect
	 * 
	 * @param p - the total point of any given KUArchitect
	 */
	private void assignPoint(int p) {
		// this part assign each ID to its total points.
		// your code starts here

		if (currentKUArchitectID == 1) {
			totalPointOfKUArchitect1 = p;
			println("KUArchitet#" + currentKUArchitectID + " has reached " + totalPointOfKUArchitect1 + " point.");
		} else if (currentKUArchitectID == 2) {
			totalPointOfKUArchitect2 = p;
			println("KUArchitet#" + currentKUArchitectID + " has reached " + totalPointOfKUArchitect2 + " point.");
		} else if (currentKUArchitectID == 3) {
			totalPointOfKUArchitect3 = p;
			println("KUArchitet#" + currentKUArchitectID + " has reached " + totalPointOfKUArchitect3 + " point.");
		} else if (currentKUArchitectID == 4) {
			totalPointOfKUArchitect4 = p;
			println("KUArchitet#" + currentKUArchitectID + " has reached " + totalPointOfKUArchitect4 + " point.");
		}
	}

	/**
	 * This method compares the total point of all contestants and prints the first,
	 * secong and third order of the KUArchitect according to their total points.
	 */
	private void comparator() {
		// this part compare total points and finds first, second and thirds.
		// your code starts here
		int max = 50000;
		int min = 0;

		for (int i = max; i >= min; i--) {

			if (i == totalPointOfKUArchitect1) {
				firstID = totalPointOfKUArchitect1;
				break;
			} else if (i == totalPointOfKUArchitect2) {
				firstID = totalPointOfKUArchitect2;
				break;
			} else if (i == totalPointOfKUArchitect3) {
				firstID = totalPointOfKUArchitect3;
				break;
			} else if (i == totalPointOfKUArchitect4) {
				firstID = totalPointOfKUArchitect4;
				break;
			}
		}
		for (int i = firstID - 1; i >= min; i--) {

			if (i == totalPointOfKUArchitect1) {
				secondID = totalPointOfKUArchitect1;
				break;
			} else if (i == totalPointOfKUArchitect2) {
				secondID = totalPointOfKUArchitect2;
				break;
			} else if (i == totalPointOfKUArchitect3) {
				secondID = totalPointOfKUArchitect3;
				break;
			} else if (i == totalPointOfKUArchitect3) {
				secondID = totalPointOfKUArchitect4;
				break;
			}
		}
		for (int i = secondID - 1; i >= min; i--) {

			if (i == totalPointOfKUArchitect1) {
				thirdID = totalPointOfKUArchitect1;
				break;
			} else if (i == totalPointOfKUArchitect2) {
				thirdID = totalPointOfKUArchitect2;
				break;
			} else if (i == totalPointOfKUArchitect3) {
				thirdID = totalPointOfKUArchitect3;
				break;
			} else if (i == totalPointOfKUArchitect3) {
				thirdID = totalPointOfKUArchitect4;
				break;
			}
		}

		resultOfComparator();

		// your code ends here
	}

	//////////// ADDITIONAL HELPER METHODS /////////////////////
	// Feel free to add additional helper methods
	// your code starts here
	private int fibonacci(int yearsEducation) { // fibonacci series
		int a = 0, b = 1, c;
		if (yearsEducation == 0)
			return a;

		for (int i = 2; i <= (yearsEducation); i++) {
			c = a + b;
			a = b;
			b = c;

		}
		return b;

	}

	private int factorial(int numDifProjects) { // this part calculates factorial.
		int fact = 1;
		for (int i = 1; i <= numDifProjects; i++) {
			fact *= i;
		}
		return fact;

	}

	private void resultOfComparator() {
		// this part prints results.
		println();
		println();

		if (firstID == totalPointOfKUArchitect1) {
			println("KUArchitect#1 becomes #1 with " + totalPointOfKUArchitect1 + " points.");
		} else if (firstID == totalPointOfKUArchitect2) {
			println("KUArchitect#2 becomes #1 with " + totalPointOfKUArchitect2 + " points.");
		} else if (firstID == totalPointOfKUArchitect3) {
			println("KUArchitect#3 becomes #1 with " + totalPointOfKUArchitect3 + " points.");
		} else if (firstID == totalPointOfKUArchitect4) {
			println("KUArchitect#4 becomes #1 with " + totalPointOfKUArchitect4 + " points.");
		}

		if (secondID == totalPointOfKUArchitect1) {
			println("KUArchitect#1 becomes #2 with " + totalPointOfKUArchitect1 + " points.");
		} else if (secondID == totalPointOfKUArchitect2) {
			println("KUArchitect#2 becomes #2 with " + totalPointOfKUArchitect2 + " points.");
		} else if (secondID == totalPointOfKUArchitect3) {
			println("KUArchitect#3 becomes #2 with " + totalPointOfKUArchitect3 + " points.");
		} else if (secondID == totalPointOfKUArchitect4) {
			println("KUArchitect#4 becomes #2 with " + totalPointOfKUArchitect4 + " points.");
		}

		if (thirdID == totalPointOfKUArchitect1) {
			println("KUArchitect#1 becomes #3 with " + totalPointOfKUArchitect1 + " points.");
		} else if (thirdID == totalPointOfKUArchitect2) {
			println("KUArchitect#2 becomes #3 with " + totalPointOfKUArchitect2 + " points.");
		} else if (thirdID == totalPointOfKUArchitect3) {
			println("KUArchitect#3 becomes #3 with " + totalPointOfKUArchitect3 + " points.");
		} else if (thirdID == totalPointOfKUArchitect4) {
			println("KUArchitect#4 becomes #3 with " + totalPointOfKUArchitect4 + " points.");
		}
	}

	private void beforeResult() {
		// this part prints title of result.
		println();
		println("The game is now complated and the scores are as below:");
		println();

	}

	private void printResultOfDuos(int f, int s, int t) {
		// this part prints the final result.
		if (firstID == totalPointOfKUArchitect1) {
			println("KuArchitech#1 won " + f + " times in 100 games.");
		} else if (firstID == totalPointOfKUArchitect2) {
			println("KuArchitech#2 won " + f + " times in 100 games.");
		} else if (firstID == totalPointOfKUArchitect3) {
			println("KuArchitech#3 won " + f + " times in 100 games.");
		} else if (firstID == totalPointOfKUArchitect4) {
			println("KuArchitech#4 won " + f + " times in 100 games.");
		}

		if (secondID == totalPointOfKUArchitect1) {
			println("KuArchitech#1 won " + s + " times in 100 games.");
		} else if (secondID == totalPointOfKUArchitect2) {
			println("KuArchitech#2 won " + s + " times in 100 games.");
		} else if (secondID == totalPointOfKUArchitect3) {
			println("KuArchitech#3 won " + s + " times in 100 games.");
		} else if (secondID == totalPointOfKUArchitect4) {
			println("KuArchitech#4 won " + s + " times in 100 games.");
		}

		if (thirdID == totalPointOfKUArchitect1) {
			println("KuArchitech#1 won " + t + " times in 100 games.");
		} else if (thirdID == totalPointOfKUArchitect2) {
			println("KuArchitech#2 won " + t + " times in 100 games.");
		} else if (thirdID == totalPointOfKUArchitect3) {
			println("KuArchitech#3 won " + t + " times in 100 games.");
		} else if (thirdID == totalPointOfKUArchitect4) {
			println("KuArchitech#4 won " + t + " times in 100 games.");
		}

	}

	private void finalGame() {
		// this part make duos for one to one.
		beforeResult();

		int f = 0;
		int s = 0;
		int t = 0;

		for (int i = 0; i <= NTIMES; i++) {
			for (int j = 1; j <= 3; j++) {
				switch (j) {
				case 1:
					int max12 = firstID + secondID;
					int combat12 = rgen.nextInt(0, max12);
					if (combat12 < firstID) {
						f++;
					} else {
						s++;
					}
				case 2:
					int max23 = secondID + thirdID;
					int combat23 = rgen.nextInt(0, max23);
					if (combat23 < secondID) {
						s++;
					} else {
						t++;
					}
				case 3:
					int max13 = firstID + thirdID;
					int combat13 = rgen.nextInt(0, max13);
					if (combat13 < firstID) {
						f++;
					} else {
						t++;
					}
				}

			}

		}
		printResultOfDuos(f, s, t);
		duosComparator(f, s, t);

	}

	private void duosComparator(int f, int s, int t) {
		int max = 50000;
		int min = 0;

		for (int i = max; i >= min; i--) {

			if (i == f) {
				println("CONGRATULATIONS KUArchitect#" + firstID + "!! YOU ARE THE WINNER OF KUArchitect.");
				break;

			} else if (i == s) {
				println("CONGRATULATIONS KUArchitect#" + secondID + "!! YOU ARE THE WINNER OF KUArchitect.");
				break;

			} else if (i == t) {
				println("CONGRATULATIONS KUArchitect#" + thirdID + "!! YOU ARE THE WINNER OF KUArchitect.");
				break;

			}
		}
	}
	// your code ends here

	//////////// GIVEN VARIABLES and CONSTANTS /////////////////////
	int currentKUArchitectID = 1;

	int totalPointOfKUArchitect1;
	int totalPointOfKUArchitect2;
	int totalPointOfKUArchitect3;
	int totalPointOfKUArchitect4;

	int firstID;
	int secondID;
	int thirdID;

	int firstPoint;
	int secondPoint;
	int thirdPoint;

	private final int CONTESTANT_NUM = 4;
	private final int NTIMES = 100;
	static RandomGenerator rgen = new RandomGenerator();

	//////////// ADDITIONAL VARIABLES and CONSTANTS /////////////////////
	// Feel free to add additional variables and constants
	// your code starts here

	// your code ends here

}