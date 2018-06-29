package gui;

import java.awt.Color;
import java.awt.Cursor;
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

import javax.swing.DebugGraphics;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import baseClass.Clone;
import baseClass.Committer;
import management.Controller;
import management.ForcedListSelectionModel;
import management.GestoreDB;

public class FrameSelection implements Comparator<Object> {
	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					FrameSelection frameSelection = new FrameSelection();
					frameSelection.frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FrameSelection() throws ParserConfigurationException, SAXException, IOException, ParseException {

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
		frame.setSize(450, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setLayout(new FlowLayout());

		JLabel lblNewLabel_1 = new JLabel("Select: ");
		lblNewLabel_1.setBounds(42, 25, 84, 16);
		frame.getContentPane().add(lblNewLabel_1);

		JComboBox<String> comboBoxTipoRicerca = new JComboBox<String>();
		comboBoxTipoRicerca.setSize(500, 500);
		frame.getContentPane().add(comboBoxTipoRicerca);
		comboBoxTipoRicerca.setModel(new DefaultComboBoxModel<String>(new String[] { "Committer", "Clone" }));

		JButton btnAnnulla = new JButton("Indietro");

		g = GestoreDB.getInstance();
		JTable table = new JTable();
		committers = g.readAssociationsCommitters();
		Object[][] data = new Object[committers.size()][2];
		String col[] = { "Id Committer", "Nome" };
		int row = 0;

		for (Committer c : committers.values()) {
			data[row][0] = c.getEmail();
			data[row][1] = c.getNome();

			row++;
		}

		DefaultTableModel model = new DefaultTableModel(data, col);

		table.setModel(model);

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
		pane.setPreferredSize(new Dimension(400, 200));

		frame.getContentPane().add(pane);

		comboBoxTipoRicerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String selectedItem = (String) comboBoxTipoRicerca.getSelectedItem();
				if (selectedItem.equalsIgnoreCase("Committer")) {

					frame.getContentPane().removeAll();

					frame.getContentPane().add(lblNewLabel_1);
					frame.getContentPane().add(comboBoxTipoRicerca);

					frame.setSize(450, 400);
					frame.setLocationRelativeTo(null);

					String col[] = { "Id Committer", "Nome" };
					int row = 0;
					committers = g.readAssociationsCommitters();
					Object[][] data = new Object[committers.size()][2];
					for (Committer c : committers.values()) {
						data[row][0] = Math.abs(c.getEmail());
						data[row][1] = c.getNome();

						row++;
					}

					DefaultTableModel model = new DefaultTableModel(data, col);

					table.setModel(model);

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
					pane.setPreferredSize(new Dimension(400, 200));

					frame.getContentPane().add(pane);

					table.setSelectionModel(new ForcedListSelectionModel());

					table.setVisible(true);

					table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
					pane.setVisible(true);
					frame.getContentPane().add(pane);
					frame.getContentPane().add(button);
					frame.getContentPane().add(btnAnnulla);
					frame.getContentPane().validate();

				}

				else if (selectedItem.equalsIgnoreCase("Clone")) {

					frame.getContentPane().removeAll();
					frame.getContentPane().add(lblNewLabel_1);
					frame.getContentPane().add(comboBoxTipoRicerca);

					frame.setSize(850, 400);
					frame.setLocationRelativeTo(null);

					String col[] = { "Id Clone", "File", "Id Class", "StartLine", "EndLine", "Version" };

					clones = g.readAssociationsClones();
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

					table.setModel(model);

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

					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					table.getColumnModel().getColumn(1).setPreferredWidth(400);
					table.setShowVerticalLines(true);
					table.setShowHorizontalLines(true);

					table.setSelectionModel(new ForcedListSelectionModel());

					table.setVisible(true);

					JScrollPane pane = new JScrollPane(table);
					pane.setPreferredSize(new Dimension(820, 250));

					pane.setVisible(true);
					frame.getContentPane().add(pane);
					frame.getContentPane().add(button);
					frame.getContentPane().add(btnAnnulla);
					frame.getContentPane().validate();

				}

			}
		});

		button = new JButton("Avanti");
		button.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.setSelectedIcon(null);

		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {

					controller.chiudiFrame(frame);

					controller.setFile(table.getModel().getValueAt(table.getSelectedRow(), 0).toString());
					if (table.getModel().getColumnCount() == 2)
						controller.visualizzaFormAssociationCommit();
					else
						controller.visualizzaFormAssociationClone();

				}

			}

		});

		button.setBounds(100, 430, 100, 52);

		button.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		frame.getContentPane().add(button);

		btnAnnulla.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				frame.dispose();
				controller.visualizzaHomeFrame();
			}
		});
		btnAnnulla.setForeground(Color.RED);
		btnAnnulla.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnAnnulla.setBounds(100, 430, 100, 52);
		frame.getContentPane().add(btnAnnulla);

		JMenuBar menuBar_1 = new JMenuBar();
		frame.setJMenuBar(menuBar_1);

		JMenu mnDatabase = new JMenu("Database");
		menuBar_1.add(mnDatabase);

		JMenuItem mntmPrenotazioni = new JMenuItem("Visualizza commits");
		mntmPrenotazioni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					controller.chiudiFrame(frame);
					controller.visualizzaCommits();
				} catch (ParserConfigurationException | SAXException | IOException | ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		mnDatabase.add(mntmPrenotazioni);

		JMenuItem cloni = new JMenuItem("Visualizza cloni");
		cloni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					controller.chiudiFrame(frame);
					controller.visualizzaCloni();
				} catch (ParserConfigurationException | SAXException | IOException | ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnDatabase.add(cloni);

		JMenuItem mntmClienti = new JMenuItem("Visualizza committers");
		mntmClienti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.chiudiFrame(frame);
				try {
					controller.visualizzaCommitters();
				} catch (ParserConfigurationException | SAXException | IOException | ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		mnDatabase.add(mntmClienti);

		JSeparator separator_4 = new JSeparator();
		mnDatabase.add(separator_4);

		JMenuItem mntmAutisti = new JMenuItem("Visualizza le classi dei cloni");
		mntmAutisti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.chiudiFrame(frame);
				try {
					controller.visualizzaClassClones();
				} catch (ParserConfigurationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SAXException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnDatabase.add(mntmAutisti);

	}

	private Controller controller;
	private GestoreDB g;
	private HashMap<Integer, Committer> committers;
	private HashMap<String, Clone> clones;
	private JButton button;

	@Override
	public int compare(Object arg0, Object arg1) {
		System.out.println("ciccio");
		if (arg0.toString().compareTo(arg1.toString()) > 0)
			return 1;
		else
			return 0;
	}

	/*
	 * @Override public int compare(String source, String target) {
	 * 
	 * 
	 * if(source.compareTo(target)>0) return 1; else return 0; }
	 * 
	 * 
	 * 
	 */

}
