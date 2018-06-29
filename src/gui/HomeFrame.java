package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.DebugGraphics;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import management.Controller;

public class HomeFrame {

	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					HomeFrame homeFrame = new HomeFrame();
					homeFrame.frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public HomeFrame() throws ParserConfigurationException, SAXException, IOException {
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

	private void initialize() {
		frame = new JFrame();
		frame.setForeground(Color.BLACK);
		frame.setResizable(false);
		frame.setTitle("Clo_tter v1.0");

		frame.setSize(375, 580);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		Color back = new Color(102, 197, 45);
		frame.getContentPane().setBackground(back);

		ImageIcon img = new ImageIcon("images/ovejas1.png");
		JLabel lblImage = new JLabel(img);
		lblImage.setBounds(20, 0, 320, 200);

		frame.getContentPane().add(lblImage);

		JLabel lblNewLabel = new JLabel("Seleziona uno dei seguenti progetti software:");
		lblNewLabel.setBounds(50, 180, 500, 100);
		frame.getContentPane().add(lblNewLabel);

		listModel = new DefaultListModel<>();

		listModel.addElement("Dnsjava");
		listModel.addElement("NoteManager");
		listModel.addElement("Tika");

		list = new JList<String>(listModel);
		list.setVisible(true);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		list.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);
		list.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		

		frame.add(new JScrollPane(list));
		list.setVisible(true);
		list.setBounds(130, 280, 100, 80);

		frame.getContentPane().add(list);
		frame.getContentPane().setVisible(true);

		button = new JButton("Avanti");
		button.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.setSelectedIcon(null);

		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					if (list.getSelectedValue().toString() != null) {

						controller.chiudiFrame(frame);

						controller.visualizzaFormSelection();
						try {
							
							controller.elaboraDati(list.getSelectedValue().toString());
						} catch (ParserConfigurationException | SAXException | IOException e1) {

							e1.printStackTrace();
						}

					}
				} catch (NullPointerException e1) {

				}
			}

		});

		button.setBounds(130, 430, 100, 52);
		frame.getContentPane().add(button);

		JToolBar toolBar = new JToolBar();
		toolBar.setRollover(true);
		toolBar.setFloatable(false);
		toolBar.setToolTipText("");
		toolBar.setBounds(0, 498, 370, 38);
		frame.getContentPane().add(toolBar);

		JLabel lblConnessioneDb = new JLabel("Connessione DB: ");
		toolBar.add(lblConnessioneDb);

		lblDBConnection = new JLabel("Non connesso");
		toolBar.add(lblDBConnection);
		lblDBConnection.setFont(new Font("Lucida Grande", Font.BOLD, 13));

		JSeparator separator_5 = new JSeparator();
		separator_5.setOrientation(SwingConstants.VERTICAL);
		toolBar.add(separator_5);

		JLabel lblImpostazioni = new JLabel("Impostazioni");
		lblImpostazioni.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("unchecked")
			@Override
			public void mouseEntered(MouseEvent e) {
				Font font = lblImpostazioni.getFont();

				@SuppressWarnings("rawtypes")
				Map attributes = font.getAttributes();
				attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
				lblImpostazioni.setFont(font.deriveFont(attributes));
			}

			@SuppressWarnings("unchecked")
			@Override
			public void mouseExited(MouseEvent e) {

				Font font = lblImpostazioni.getFont();
				@SuppressWarnings("rawtypes")
				Map attributes = font.getAttributes();
				attributes.put(TextAttribute.UNDERLINE, -1);
				lblImpostazioni.setFont(font.deriveFont(attributes));

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				controller.visualizzaFrameImpostazioniDB();

			}
		});

		lblImpostazioni.setFont(new Font("Lucida Grande", Font.BOLD, 12));
		lblImpostazioni.setForeground(Color.BLUE);
		toolBar.add(lblImpostazioni);

		JMenuBar menuBar_1 = new JMenuBar();
		frame.setJMenuBar(menuBar_1);

		JMenu mnNewMenu = new JMenu("Clo_tter");
		menuBar_1.add(mnNewMenu);

		JMenuItem mntmChiudi = new JMenuItem("Esci");
		mntmChiudi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		JMenuItem mntmRiguardoTeletaxi = new JMenuItem("Informazioni su Clo_tter v1.0");
		mntmRiguardoTeletaxi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.visualizzaNotifica(
						"Cl_otter versione 1.0 \n\nApplicazione per assocciare cloni ai commiters sviluppata dallo studente\nFranco Luigi con la supervisione del prof. Di Lucca per la tesi di laurea con titolo:\n \"Analisi di progetti software open source per la gestione di cloni\"");
			}
		});
		mnNewMenu.add(mntmRiguardoTeletaxi);

		JSeparator separator_6 = new JSeparator();
		mnNewMenu.add(separator_6);
		mnNewMenu.add(mntmChiudi);
		
		

	}

	public ArrayList<String> getProgetti() {
		return progetti;
	}

	public void setProgetti(ArrayList<String> progetti) {
		this.progetti = progetti;
	}

	private JList<String> list;
	public static JButton button;
	public static JButton btnPrenotaTaxi;
	public static JButton btnCreaUnaCorsa;
	public static JButton btnRegistraUnaCorsa;
	public static JButton btnRegistraUnaCorsa_1;
	private Controller controller;
	public static JLabel lblDBConnection;
	private DefaultListModel<String> listModel;
	private ArrayList<String> progetti;
}
