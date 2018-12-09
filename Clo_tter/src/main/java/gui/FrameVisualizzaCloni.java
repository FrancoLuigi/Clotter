package gui;

import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import base.Clone;
import management.Controller;
import management.ForcedListSelectionModel;
import management.GestoreDB;

public class FrameVisualizzaCloni {

	public FrameVisualizzaCloni() throws ParserConfigurationException, SAXException, IOException, ParseException {

		frame = new JFrame();
		frame.setForeground(Color.BLACK);
		frame.setResizable(false);

		frame.setSize(950, 350);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Clo_tter v1.0");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		try {
			controller = Controller.getInstance();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		String col[] = { "id Clone", "file", "id Class", "startLine", "endLine"};

		controller = Controller.getInstance();
		g = GestoreDB.getInstance();

		clones = g.readClones();

		JLabel lblNewLabel = new JLabel("Cloni individuati nel progetto: " + clones.size());
		lblNewLabel.setBounds(100, 50, 500, 500);
		frame.getContentPane().add(lblNewLabel);

		Object[][] data = new Object[clones.size()][5];

		int row = 0;

		for (Clone c : clones.values()) {
			
				data[row][0] = Integer.parseInt(c.getPcid());
				data[row][1] = c.getFile();
				data[row][2] = Integer.parseInt(c.getClassid());
				data[row][3] = c.getStartLine();
				data[row][4] = c.getEndLine();
			
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

		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(1).setPreferredWidth(550);
		
		table.setSelectionModel(new ForcedListSelectionModel());

		JScrollPane pane = new JScrollPane(table);
		pane.setPreferredSize(new Dimension(900, 200));

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

		JSeparator separator = new JSeparator();
		separator.setBounds(-12, 76, 1009, 12);

		frame.setVisible(true);

	}

	private Controller controller;
	private JFrame frame;
	private HashMap<String, Clone> clones;
	private GestoreDB g;

}
