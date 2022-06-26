
//Name:YAKUP ENES G�VEN

import java.awt.Color;
import java.awt.Image;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import acm.graphics.GImage;
import acm.graphics.GPoint;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

public class Comp130_HW4_Fall19 extends GraphicsProgram {

	/**
	 * Initializer. Prints the welcome messages. Do <b>not</b> modify.
	 */
	public void init() {
		setTitle("MakeAMovie");
		println("Welcome to Make a Movie!");
		println("Start by either creating a new project or opening an existing one.");
	}

	/**
	 * Entry method for your implementation.
	 */
	public void run() {
		// your code starts here
		makeAMovie();
		// your code ends here
	}

	// ADDITIONAL HELPER METHODS//
	// your code starts here
	public void makeAMovie() {
		while (true) {
			String chosenOption = readLine(CLI_INPUT_STR);
			switch (chosenOption) {
			case "setback":
				setback();
				break;
			case "setgrammar":
				setgrammar();
				break;
			case "addscene":
				addscene();
				break;
			case "removescene":
				removescene();
				break;
			case "listscene":
				listscene();
				break;
			case "play":
				play();
				break;
			case "exit":
				return;
			default:
				println("Please enter valid input!!!");
				break;
			}
		}
	}

	public void setback() {// set background color depends on what you chose
		String backgroundColor = readLine("Specify the background color: \n(White,Green,Blue,Magenta,Random,Other)\n");
		switch (backgroundColor) {
		case "White":
			setBackground(Color.WHITE);
			println("Background color is White.");
			break;
		case "Green":
			setBackground(Color.GREEN);
			println("Background color is Green.");
			break;
		case "Blue":
			setBackground(Color.BLUE);
			println("Background color is Blue.");
			break;
		case "Magenta":
			setBackground(Color.MAGENTA);
			println("Background color is Magenta.");
			break;
		case "Random": // sets background randomly
			Color random = rgen.nextColor();
			setBackground(random);
			println("Background color is Random.");
			break;
		case "Other": // give you chance to chose background color expect given colors
			String otherColor = readLine("Please specify the background color expect White, Green, Blue, Magenta: \n");
			setBackground(Color.getColor(otherColor));
			println("Background color is " + otherColor + ".");
		default:
			backgroundColor = null;
			break;
		}
	}

	public void setgrammar() { // takes type of grammar to use
		grammar = readLine("Please specify the order of the grammar elemets:\n(scale, image, from, to, time)\n");
		grammarArray = grammar.split(" ");

	}

	public void addscene() { // adds scenes depends on the grammar
		String chosenGrammar = readLine("Enter the rule in an order you chose, \n"
				+ "if you want to chose random image, please just type random instead of image name \n" + grammar
				+ "\n");

		String time = "";
		String scale = "";
		String from = "";
		String to = "";
		String image = "";

		String[] chosenGrammarArray = chosenGrammar.split(" ");

		int counter = 0;

		for (int i = 0; i < grammarArray.length; i++) {
			if (grammarArray[i].equals("time")) {
				time = chosenGrammarArray[counter + 1];
				if (chosenGrammarArray[counter + 2].equals("seconds")) {
					time += "000";
				}
				counter += 3;
			} else if (grammarArray[i].equals("scale")) {
				scale = chosenGrammarArray[counter];
				counter++;
			} else if (grammarArray[i].equals("image")) {
				image = chosenGrammarArray[counter];
				counter++;
			} else if (grammarArray[i].equals("from")) {
				from = chosenGrammarArray[counter + 1];
				counter += 2;
			} else if (grammarArray[i].equals("to")) {
				to = chosenGrammarArray[counter + 1];
				counter += 2;
			}
		}

		String orders = time + " " + image + " " + scale + " " + from + " " + to; // el at
		scenes.add(orders);

	}

	public void removescene() { // removes scenes
		int removescene = readInt("Please enter scene you want to delete :");
		if (scenes.size() >= removescene) {
			scenes.remove(removescene - 1);
			println("Scene number " + removescene + " removed.");
		} else {
			println("Please enter valid scene number!!!");
		}

	}

	public void listscene() { // lists all scenes you have
		int counter = 1;
		println("The scenes are below and in the order you entered.");
		println("order is : miliseconds image scale from to ");
		for (String string : scenes) { // el at
			print(counter + ") ");
			print(string);
			println();
			counter++;
		}

	}

	public void play() { // play the movie
		int counter = 0;
		println("Your movie is playing now.");

		for (String string : scenes) {

			String[] arrayS = string.split(" ");
			int time = Integer.parseInt(arrayS[0]);

			if (arrayS[1].equals("random")) { // gives chance to random image
				String[] randomImage = new String[10];
				randomImage[0] = "cactus";
				randomImage[1] = "camera";
				randomImage[2] = "crown";
				randomImage[3] = "dna";
				randomImage[4] = "duck";
				randomImage[5] = "fish";
				randomImage[6] = "flower";
				randomImage[7] = "pizza";
				randomImage[8] = "strawberry";
				randomImage[9] = "zeppelin";
				int randomImageNumber = rgen.nextInt(0, 9);
				imagePath = LIBRARY_PATH + randomImage[randomImageNumber] + IMAGE_TYPE;
			} else {
				imagePath = LIBRARY_PATH + arrayS[1] + IMAGE_TYPE;
			}

			double scale = Double.parseDouble(arrayS[2]);
			String from = arrayS[3];
			String to = arrayS[4];

			GImage image = new GImage(imagePath);
			image.scale(scale);

			int xSpeed = 0;
			int ySpeed = 0;

			switch (from) { // add image to the ground
			case "left":
				add(image, getWidth() - image.getWidth(), (getHeight() - image.getHeight()) / 2);
				break;
			case "right":
				add(image, 0, (getHeight() - image.getHeight()) / 2);
				break;
			case "top":
				add(image, (getWidth() - image.getWidth()) / 2, 0);
				break;
			case "bottom":
				add(image, (getWidth() - image.getWidth() / 2), getHeight() - image.getHeight());
				break;
			case "center":
				add(image, (getWidth() - image.getWidth()) / 2, (getHeight() - image.getHeight()) / 2);
				break;
			}

			switch (to) { // speed of the objects depends on first and last place
			case "left":
				if (from.equals("center")) {
					xSpeed = 1;
					ySpeed = 0;
				} else if (from.equals("right")) {
					xSpeed = 1;
					ySpeed = 0;
				} else if (from.equals("top")) {
					xSpeed = 1;
					ySpeed = 1;
				} else if (from.equals("bottom")) {
					xSpeed = 1;
					ySpeed = -1;
				}
				break;
			case "right":
				if (from.equals("left")) {
					xSpeed = -1;
					ySpeed = 0;
				} else if (from.equals("center")) {
					xSpeed = -1;
					ySpeed = 0;
				} else if (from.equals("top")) {
					xSpeed = -1;
					ySpeed = 1;
				} else if (from.equals("bottom")) {
					xSpeed = -1;
					ySpeed = -1;
				}
				break;
			case "top":
				if (from.equals("left")) {
					xSpeed = -1;
					ySpeed = -1;
				} else if (from.equals("right")) {
					xSpeed = +1;
					ySpeed = -1;
				} else if (from.equals("center")) {
					xSpeed = 0;
					ySpeed = -1;
				} else if (from.equals("bottom")) {
					xSpeed = 0;
					ySpeed = -1;
				}
				break;
			case "bottom":
				if (from.equals("left")) {
					xSpeed = -1;
					ySpeed = +1;
				} else if (from.equals("right")) {
					xSpeed = +1;
					ySpeed = +1;
				} else if (from.equals("top")) {
					xSpeed = 0;
					ySpeed = 1;
				} else if (from.equals("center")) {
					xSpeed = 0;
					ySpeed = 1;
				}
				break;
			case "center":
				if (from.equals("left")) {
					xSpeed = -1;
					ySpeed = 0;
				} else if (from.equals("right")) {
					xSpeed = 1;
					ySpeed = 0;
				} else if (from.equals("top")) {
					xSpeed = 0;
					ySpeed = 1;
				} else if (from.equals("bottom")) {
					xSpeed = 0;
					ySpeed = -1;
				}
				break;
				default:
					throw new java.lang.IllegalStateException("Unexpected value: " + to);
			}

			while (time >= counter) {
				image.move(xSpeed, ySpeed);
				pause(PAUSE_TIME);
				counter++;
			}

			counter = 0;
			remove(image);
		}
		println("Your movie is finished.");
	}

	// your code ends here

	// ADDITIONAL INSTANCE AND CONSTANT VARIABLES//
	// your code starts here
	String[] grammarArray = new String[9];
	String string = null;
	static RandomGenerator rgen = new RandomGenerator();
	String imagePath = null;
	// your code ends here

	// DO NOT REMOVE THIS SECTION//

	private String grammar = "";
	private List<String> scenes = new ArrayList<String>();
	private static int PAUSE_TIME = 1;

	// INSTANCE VARIABLES AND CONSTANTS
	/**
	 * Constant <code>String</code> used to prompt user for commands.
	 */
	public static String CLI_INPUT_STR = "MakeAMovie -> ";

	/**
	 * Path to the folder enclosing the images. Read images from this path.
	 */
	public static String LIBRARY_PATH = "../lib/";

	/**
	 * File extension of image files.
	 */
	public static String IMAGE_TYPE = ".png";

}
