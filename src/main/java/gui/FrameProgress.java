package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import management.Controller;

public class FrameProgress {

	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					FrameProgress frameProgress = new FrameProgress();
					frameProgress.frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FrameProgress() throws ParserConfigurationException, SAXException, IOException, InterruptedException {
		initialize();

		try {

			controller = Controller.getInstance();
		} catch (ParseException ex) {
			ex.printStackTrace();
		}

		if (Controller.USE_DATABASE) {
			new PeriodicDBCheck();
		}

	}

	private void initialize() throws InterruptedException {
		frame = new JFrame("Elaboration date");

		frame.setForeground(Color.BLACK);
		frame.setResizable(false);

		frame.setSize(300, 100);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JProgressBar progressBar = new JProgressBar(JProgressBar.HORIZONTAL);
		progressBar.setStringPainted(true);

		frame.getContentPane().add(progressBar);

		Thread t = new Thread(new Runnable() {
			public void run() {

				for (int i = 0; i <= 100; i++) {

					progressBar.setStringPainted(true);

					progressBar.setValue(i);
					frame.getContentPane().add(progressBar);

					if (progressBar.getValue() == 100) {
						progressBar.setVisible(false);
						controller.chiudiFrame(frame);

						controller.visualizzaFormSelection();

					}

					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		});

		t.start();

	}

	private Controller controller;

}
