package main;

import java.io.IOException;
import java.text.ParseException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import management.Controller;

public class Main {
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

		Controller controller = null;
		try {
			Controller.USE_DATABASE = true;
			controller = Controller.getInstance();
		} catch (ParseException ex) {
			ex.printStackTrace();
		}

		 try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		controller.visualizzaHomeFrame();

	}
	

}


