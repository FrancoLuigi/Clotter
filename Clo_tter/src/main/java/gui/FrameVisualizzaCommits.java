package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import base.Commit;
import management.Controller;
import management.GestoreDB;

public class FrameVisualizzaCommits {

	public FrameVisualizzaCommits() throws ParserConfigurationException, SAXException, IOException, ParseException {

		frame = new JFrame();
		frame.setForeground(Color.BLACK);
		frame.setResizable(false);

		frame.setSize(1000, 550);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Clo_tter v1.0");
	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		try {
			controller = Controller.getInstance();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		JPanel panel = new JPanel();

		String col[] = { "Id", "Data", "Descrizione", "Nome", "Id Committer", "Version"};

		controller = Controller.getInstance();
		g = GestoreDB.getInstance();

		commits = g.readCommits();
		
		JLabel lblNewLabel = new JLabel("Commits individuati nel progetto: "+ commits.size());
		lblNewLabel.setBounds(100, 50, 500, 500);
		frame.getContentPane().add(lblNewLabel);

		Object[][] data = new Object[commits.size()][6];
		
		SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyy HH:mm:ss");

		int row = 0;

		for (Commit c : commits.values()) {
			data[row][0] = c.getId();
			data[row][1] = sd.format(c.getData());
			data[row][2] = c.getDescrizione();
			data[row][3] = c.getCommitter().getNome();
			data[row][4] =  Integer.parseInt(c.getCommitter().getEmail());
			data[row][5] = c.getVersion();
			row++;
		}

		DefaultTableModel model = new DefaultTableModel(data, col);

		JTable table = new JTable(model);

		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);

		table.setRowSorter(sorter);
		
		
		sorter.setComparator(4, new Comparator<Integer>() {

			@Override
			public int compare(Integer name1, Integer name2) {
				return name1.compareTo(name2);
			}
		});
		
		sorter.setComparator(1, new Comparator<String>() {

			@Override
			public int compare(String name1, String name2) {
				Date date1=null ;
				Date date2=null; 
				try {
				date1 = sd.parse(name1);
				date2 = sd.parse(name2);
				}
				catch(ParseException e) {
				}
				
				return date1.compareTo(date2);
			}
		});
		
		
		table.setShowVerticalLines(true);
		table.setShowHorizontalLines(true);
		table.setGridColor(new Color(209, 209, 209));

		table.getColumnModel().getColumn(0).setPreferredWidth(300);
		JScrollPane pane = new JScrollPane(table);
		pane.setPreferredSize(new Dimension(950, 450));

		frame.getContentPane().add(pane);

		JButton btnAnnulla = new JButton("Indietro");
		btnAnnulla.addActionListener(new ActionListener() {

	public void actionPerformed(ActionEvent e) {

		frame.dispose();
		controller.visualizzaFormSelection();
	}});btnAnnulla.setForeground(Color.RED);btnAnnulla.setFont(new Font("Lucida Grande",Font.PLAIN,15));btnAnnulla.setBounds(800,10,20,20);frame.getContentPane().add(btnAnnulla);

	JSeparator separator = new JSeparator();separator.setBounds(-12,76,1009,12);panel.add(separator);

	frame.setVisible(true);

	}

	private Controller controller;
	private JFrame frame;
	private HashMap<String, Commit> commits;
	private GestoreDB g;

}
