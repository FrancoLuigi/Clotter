package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import base.Commit;
import management.Controller;
import management.GestoreDB;

public class FrameAssociationClone {

	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					FrameAssociationClone frameAssociations = new FrameAssociationClone();
					frameAssociations.frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FrameAssociationClone() throws ParserConfigurationException, SAXException, IOException, ParseException {
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

	private void initialize() throws ParseException, ParserConfigurationException, SAXException, IOException {
		frame = new JFrame();
		frame.setForeground(Color.BLACK);
		frame.setResizable(false);
		frame.setTitle("Clo_tter v1.0");

		frame.setSize(1000, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setLayout(new FlowLayout());

		String col[] = { "Id", "Data", "Descrizione", "Nome", "Email" };

		g = GestoreDB.getInstance();

		controller = Controller.getInstance();
		commits = g.readAssociationsCommits(controller.getFile());

		JLabel lblNewLabel = new JLabel("Clone: " + controller.getFile() + " Committer: " + commits.size());
		lblNewLabel.setBounds(100, 50, 500, 500);

		frame.getContentPane().add(lblNewLabel);

		Object[][] data = new Object[commits.size()][5];
		SimpleDateFormat sd = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
		int row = 0;

		for (Commit c : commits.values()) {
			data[row][0] = c.getId();
			data[row][1] = sd.format(c.getData());
			data[row][2] = c.getDescrizione();
			data[row][3] = c.getCommitter().getNome();
			data[row][4] = Integer.parseInt(c.getCommitter().getEmail());
			row++;
		}

		DefaultTableModel model = new DefaultTableModel(data, col);

		JTable table = new JTable(model);
		
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);

		table.setRowSorter(sorter);

		sorter.setComparator(0, new Comparator<Integer>() {

			@Override
			public int compare(Integer name1, Integer name2) {
				return name1.compareTo(name2);
			}
		});
		
		table.setShowVerticalLines(true);
		table.setShowHorizontalLines(true);
		table.setGridColor(new Color(209, 209, 209));

		JScrollPane pane = new JScrollPane(table);

		pane.setPreferredSize(new Dimension(950, 200));

		frame.getContentPane().add(pane);

		JButton btnAnnulla = new JButton("Indietro");
		btnAnnulla.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				frame.dispose();
				controller.visualizzaFormSelection();
			}
		});
		btnAnnulla.setForeground(Color.RED);
		btnAnnulla.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnAnnulla.setBounds(10, 10, 20, 20);
		frame.getContentPane().add(btnAnnulla);

	}

	private HashMap<String, Commit> commits;
	private Controller controller;
	private GestoreDB g;
}
