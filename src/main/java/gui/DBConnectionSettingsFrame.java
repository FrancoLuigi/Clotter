package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import management.Controller;
import management.DBSettings;

public class DBConnectionSettingsFrame {

	private JFrame frmImpostazioniConnessioneDatabase;
	private JTextField textHost;
	private JTextField textPorta;
	private JTextField textUser;
	private JTextField textPass;
	private JLabel lblPassword;
	private JSeparator separator;
	private JLabel lblNewLabel;
	private JSeparator separator_1;
	private JButton btnNewButton;
	private JButton btnRifiuta;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DBConnectionSettingsFrame window = new DBConnectionSettingsFrame();
					window.frmImpostazioniConnessioneDatabase.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DBConnectionSettingsFrame() throws ParserConfigurationException, SAXException, IOException {
		initialize();
		try {
			controller = Controller.getInstance();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private void initialize() {
		frmImpostazioniConnessioneDatabase = new JFrame();
		frmImpostazioniConnessioneDatabase.setTitle("Impostazioni connessione database");
		frmImpostazioniConnessioneDatabase.setBounds(100, 100, 427, 366);
		frmImpostazioniConnessioneDatabase.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmImpostazioniConnessioneDatabase.getContentPane().setLayout(null);

		textHost = new JTextField();
		textHost.setText(DBConnectionSettingsFrame.host);
		textHost.setColumns(10);
		textHost.setBounds(31, 139, 149, 40);
		frmImpostazioniConnessioneDatabase.getContentPane().add(textHost);

		JLabel lblHost = new JLabel("Host");
		lblHost.setBounds(29, 124, 95, 16);
		frmImpostazioniConnessioneDatabase.getContentPane().add(lblHost);

		textPorta = new JTextField();
		textPorta.setText(DBConnectionSettingsFrame.port);
		textPorta.setColumns(10);
		textPorta.setBounds(31, 206, 149, 40);
		frmImpostazioniConnessioneDatabase.getContentPane().add(textPorta);

		JLabel lblPorta = new JLabel("Porta");
		lblPorta.setBounds(31, 191, 139, 16);
		frmImpostazioniConnessioneDatabase.getContentPane().add(lblPorta);

		textUser = new JTextField();
		textUser.setText(DBConnectionSettingsFrame.user);
		textUser.setColumns(10);
		textUser.setBounds(253, 139, 149, 40);
		frmImpostazioniConnessioneDatabase.getContentPane().add(textUser);

		JLabel lblUser = new JLabel("User");
		lblUser.setBounds(253, 124, 139, 16);
		frmImpostazioniConnessioneDatabase.getContentPane().add(lblUser);

		textPass = new JTextField();
		textPass.setText(DBConnectionSettingsFrame.pass);
		textPass.setColumns(10);
		textPass.setBounds(253, 206, 149, 40);
		frmImpostazioniConnessioneDatabase.getContentPane().add(textPass);

		lblPassword = new JLabel("Password");
		lblPassword.setBounds(253, 191, 139, 16);
		frmImpostazioniConnessioneDatabase.getContentPane().add(lblPassword);

		separator = new JSeparator();
		separator.setBounds(6, 254, 438, 12);
		frmImpostazioniConnessioneDatabase.getContentPane().add(separator);

		lblNewLabel = new JLabel("Impostazioni connessione database");
		lblNewLabel.setBounds(101, 85, 225, 16);
		frmImpostazioniConnessioneDatabase.getContentPane().add(lblNewLabel);

		separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(212, 113, 16, 153);
		frmImpostazioniConnessioneDatabase.getContentPane().add(separator_1);

		btnNewButton = new JButton("Accetta");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DBConnectionSettingsFrame.host = textHost.getText();
				DBConnectionSettingsFrame.port = textPorta.getText();
				DBConnectionSettingsFrame.user = textUser.getText();
				DBConnectionSettingsFrame.pass = textPass.getText();
				controller.setImpostazioniDB(textHost.getText(), textPorta.getText(), textUser.getText(),
						textPass.getText());
				controller.visualizzaNotifica("Impostazioni aggiornate con successo!");
				controller.chiudiFrame(frmImpostazioniConnessioneDatabase);
			}
		});
		btnNewButton.setForeground(new Color(0, 128, 0));
		btnNewButton.setBounds(285, 280, 117, 42);
		frmImpostazioniConnessioneDatabase.getContentPane().add(btnNewButton);

		btnRifiuta = new JButton("Annulla");
		btnRifiuta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.chiudiFrame(frmImpostazioniConnessioneDatabase);
			}
		});
		btnRifiuta.setForeground(new Color(255, 0, 0));
		btnRifiuta.setBounds(156, 280, 117, 42);
		frmImpostazioniConnessioneDatabase.getContentPane().add(btnRifiuta);

		ImageIcon img = new ImageIcon("images/database_icon.png");
		JLabel lblNewLabel_1 = new JLabel(img);
		lblNewLabel_1.setBounds(159, 6, 108, 73);
		frmImpostazioniConnessioneDatabase.getContentPane().add(lblNewLabel_1);
	}

	private Controller controller;
	private static String host = DBSettings.host;
	private static String port = DBSettings.port;
	private static String user = DBSettings.user;
	private static String pass = DBSettings.pass;
}
