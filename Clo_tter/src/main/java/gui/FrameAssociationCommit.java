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

import base.Clone;
import management.Controller;
import management.GestoreDB;

public class FrameAssociationCommit {

	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					FrameAssociationCommit frameAssociations = new FrameAssociationCommit();
					frameAssociations.frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FrameAssociationCommit() throws ParserConfigurationException, SAXException, IOException, ParseException {
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

		frame.setSize(1500, 550);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());

		String col[] = { "Id Clone", "File", "Id Class", "StartLine", "EndLine", "Version" };

		controller = Controller.getInstance();
		g = GestoreDB.getInstance();

		clones = g.readAssociationsClones(controller.getFile());

		JLabel lblNewLabel = new JLabel("Committer: " + controller.getFile());
		lblNewLabel.setBounds(40, 10, 500, 100);
		JLabel l = new JLabel(" Clones: " + clones.size());

		frame.getContentPane().add(lblNewLabel);
		frame.getContentPane().add(l);

		Object[][] data = new Object[clones.size()][6];

		int row = 0;

		for (Clone c : clones.values()) {

			data[row][0] = Integer.parseInt(c.getPcid());

			data[row][1] = c.getFile();
			data[row][2] = Integer.parseInt(c.getClassid());
			data[row][3] = c.getStartLine();
			data[row][4] = c.getEndLine();
			data[row][5] = c.getVersion();
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

		sorter.setComparator(2, new Comparator<Integer>() {

			@Override
			public int compare(Integer name1, Integer name2) {
				return name1.compareTo(name2);
			}
		});

		sorter.setComparator(3, new Comparator<Integer>() {

			@Override
			public int compare(Integer name1, Integer name2) {
				return name1.compareTo(name2);
			}
		});

		sorter.setComparator(4, new Comparator<Integer>() {

			@Override
			public int compare(Integer name1, Integer name2) {
				return name1.compareTo(name2);
			}
		});
		table.setShowVerticalLines(true);
		table.setShowHorizontalLines(true);
		table.setGridColor(new Color(209, 209, 209));

		JScrollPane pane = new JScrollPane(table);

		pane.setPreferredSize(new Dimension(1450, 450));
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
		btnAnnulla.setBounds(800, 10, 20, 20);
		frame.getContentPane().add(btnAnnulla);

	}

	private HashMap<String, Clone> clones;
	private Controller controller;
	private GestoreDB g;
}
