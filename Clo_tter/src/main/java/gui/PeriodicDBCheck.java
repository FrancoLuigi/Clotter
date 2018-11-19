package gui;

import java.awt.Color;
import java.io.IOException;
import java.text.ParseException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import management.Controller;

public class PeriodicDBCheck {

	public PeriodicDBCheck() {

		SimpleRunner r = new SimpleRunner();
		Thread t = new Thread(r);
		t.start();

	}

}

class SimpleRunner implements Runnable {
	public void run()

	{

		try {
			controller = Controller.getInstance();
		} catch (ParseException ex) {
			ex.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (true) {

			if (controller.checkDBConnection()) {

				HomeFrame.lblDBConnection.setText("Attiva");

				Color myGreen = new Color(1, 198, 1);
				HomeFrame.lblDBConnection.setForeground(myGreen);
			} else if (controller.checkDBConnection() == false) {

				HomeFrame.lblDBConnection.setText("Non connesso");
				HomeFrame.lblDBConnection.setForeground(Color.red);
			}

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

	private Controller controller;
}
