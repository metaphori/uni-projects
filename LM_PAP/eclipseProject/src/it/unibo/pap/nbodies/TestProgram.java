package it.unibo.pap.nbodies;

import it.unibo.pap.nbodies.*;
import it.unibo.pap.nbodies.gui.Controller;
import it.unibo.pap.nbodies.gui.ControllerWithActors;
import it.unibo.pap.nbodies.gui.NBodyGUI;
import it.unibo.pap.nbodies.physics.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class TestProgram {

	public static void main(String[] args) {
			
		Controller controller = new Controller();
		NBodyGUI gui = new NBodyGUI(controller);		
		controller.setView(gui);

	}

}
