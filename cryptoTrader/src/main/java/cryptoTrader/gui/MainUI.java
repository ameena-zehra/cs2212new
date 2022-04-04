package cryptoTrader.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import cryptoTrader.utils.DataVisualizationCreator;

public class MainUI extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static MainUI instance;
	private JPanel stats, chartPanel, tablePanel;

	// Should be a reference to a separate object in actual implementation
	private List<String> selectedList;

	private JTextArea selectedTickerList;
//	private JTextArea tickerList;
	private JTextArea tickerText;
	private JTextArea BrokerText;
	private JComboBox<String> strategyList;
	private Map<String, List<String>> brokersTickers = new HashMap<>();
	private Map<String, String> brokersStrategies = new HashMap<>();
	private List<String> selectedTickers = new ArrayList<>();
	private String selectedStrategy = "";
	private DefaultTableModel dtm;
	private JTable table;
	private TradingClient example = new TradingClient();

	public static MainUI getInstance() {
		if (instance == null)
			instance = new MainUI();

		return instance;
	}

	private MainUI() {

		// Set window title
		super("Crypto Trading Tool");

		// Set top bar


		JPanel north = new JPanel();

//		north.add(strategyList);

		// Set bottom bar
//		JLabel from = new JLabel("From");
//		UtilDateModel dateModel = new UtilDateModel();
//		Properties p = new Properties();
//		p.put("text.today", "Today");
//		p.put("text.month", "Month");
//		p.put("text.year", "Year");
//		JDatePanelImpl datePanel = new JDatePanelImpl(dateModel, p);
//		@SuppressWarnings("serial")
//		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new AbstractFormatter() {
//			private String datePatern = "dd/MM/yyyy";
//
//			private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePatern);
//
//			@Override
//			public Object stringToValue(String text) throws ParseException {
//				return dateFormatter.parseObject(text);
//			}
//
//			@Override
//			public String valueToString(Object value) throws ParseException {
//				if (value != null) {
//					Calendar cal = (Calendar) value;
//					return dateFormatter.format(cal.getTime());
//				}
//
//				return "";
//			}
//		});
		
		JButton trade = new JButton("Perform Trade");
		
		trade.setActionCommand("refresh");
		trade.addActionListener(this);



		JPanel south = new JPanel();
		
		south.add(trade);

		dtm = new DefaultTableModel(new Object[] { "Trading Client", "Coin List", "Strategy Name" }, 1);
		table = new JTable(dtm);
		// table.setPreferredSize(new Dimension(600, 300));
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Trading Client Actions",
				TitledBorder.CENTER, TitledBorder.TOP));
		Vector<String> strategyNames = new Vector<String>();
		strategyNames.add("None");
		strategyNames.add("Strategy-A");
		strategyNames.add("Strategy-B");
		strategyNames.add("Strategy-C");
		strategyNames.add("Strategy-D");
		TableColumn strategyColumn = table.getColumnModel().getColumn(2);
		JComboBox comboBox = new JComboBox(strategyNames);
		strategyColumn.setCellEditor(new DefaultCellEditor(comboBox));
		JButton addRow = new JButton("Add Row");
		JButton remRow = new JButton("Remove Row");
		addRow.setActionCommand("addTableRow");
		addRow.addActionListener(this);
		remRow.setActionCommand("remTableRow");
		remRow.addActionListener(this);

		scrollPane.setPreferredSize(new Dimension(800, 300));
		table.setFillsViewportHeight(true);
		

		JPanel east = new JPanel();
//		east.setLayout();
		east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS));
//		east.add(table);
		east.add(scrollPane);
		JPanel buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
		buttons.add(addRow);
		buttons.add(remRow);
		east.add(buttons);
//		east.add(selectedTickerListLabel);
//		east.add(selectedTickersScrollPane);

		// Set charts region
		JPanel west = new JPanel();
		west.setPreferredSize(new Dimension(1250, 650));
		stats = new JPanel();
		stats.setLayout(new GridLayout(2, 2));

		west.add(stats);

		getContentPane().add(north, BorderLayout.NORTH);
		getContentPane().add(east, BorderLayout.EAST);
		getContentPane().add(west, BorderLayout.CENTER);
		getContentPane().add(south, BorderLayout.SOUTH);
//		getContentPane().add(west, BorderLayout.WEST);
	}

	public void updateStats(JComponent component) {
		stats.add(component);
		stats.revalidate();
	}

	public static void main(String[] args) {
		JFrame frame = MainUI.getInstance();
		frame.setSize(900, 600);
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		String traderName = null;
		String[] coinNames = null;
		String strategyName = null;
		Context context;
		if ("refresh".equals(command)) {
			// Add last
			Object traderObject = dtm.getValueAt(dtm.getRowCount()-1, 0);
			if (traderObject == null) {
				JOptionPane.showMessageDialog(this, "please fill in Trader name on line "  );
							
				return;
			}
			traderName = traderObject.toString();
			Object coinObject = dtm.getValueAt(dtm.getRowCount()-1, 1);
			if (coinObject == null) {
				JOptionPane.showMessageDialog(this, "please fill in cryptocoin list on line "  );
				return;
			}
			coinNames = coinObject.toString().split(",");
			Object strategyObject = dtm.getValueAt(dtm.getRowCount()-1, 2);
			if (strategyObject == null) {
				JOptionPane.showMessageDialog(this, "please fill in strategy name on line "  );
				return;
			}
			strategyName = strategyObject.toString();
			context = new Context(new AddBroker(example, traderName, coinNames, strategyName));
			context.execute();
			for (int count = 0; count < dtm.getRowCount(); count++){
					traderObject = dtm.getValueAt(count, 0);
					if (traderObject == null) {
						JOptionPane.showMessageDialog(this, "please fill in Trader name on line " + (count + 1) );
						
						return;
					}
					traderName = traderObject.toString();
					coinObject = dtm.getValueAt(count, 1);
					if (coinObject == null) {
						JOptionPane.showMessageDialog(this, "please fill in cryptocoin list on line " + (count + 1) );
						return;
					}
					coinNames = coinObject.toString().split(",");
					strategyObject = dtm.getValueAt(count, 2);
					if (strategyObject == null) {
						JOptionPane.showMessageDialog(this, "please fill in strategy name on line " + (count + 1) );
						return;
					}
					strategyName = strategyObject.toString();
					System.out.println(traderName + " " + Arrays.toString(coinNames) + " " + strategyName);
					
//				   if(example.getSize()==0) {
//					   context = new Context(new AddBroker(example, traderName, coinNames, strategyName));
//					   context.execute();
//				   }
//					ListIterator <Broker> iter = example.getClientList().listIterator();
//					while (iter.hasNext()) {
//						System.out.println(iter.next().gettraderName());
//					}
//					context = new Context(new AddBroker(example, traderName, coinNames, strategyName));
//				    context.execute();
					context = new Context(new PerformTrade(example));
					context.execute();

					
					
	        }
			stats.removeAll();
			DataVisualizationCreator creator = new DataVisualizationCreator();
			creator.createCharts();
		} else if ("addTableRow".equals(command)) {
			
			// Add previous
			Object traderObject = dtm.getValueAt(dtm.getRowCount()-1, 0);
			if (traderObject == null) {
				JOptionPane.showMessageDialog(this, "please fill in Trader name on line "  );
				
				return;
			}
			traderName = traderObject.toString();
			Object coinObject = dtm.getValueAt(dtm.getRowCount()-1, 1);
			if (coinObject == null) {
				JOptionPane.showMessageDialog(this, "please fill in cryptocoin list on line "  );
				return;
			}
			coinNames = coinObject.toString().split(",");
			Object strategyObject = dtm.getValueAt(dtm.getRowCount()-1, 2);
			if (strategyObject == null) {
				JOptionPane.showMessageDialog(this, "please fill in strategy name on line "  );
				return;
			}
			dtm.addRow(new String[3]);
			strategyName = strategyObject.toString();
//			traderName = (String) dtm.getValueAt(dtm.getRowCount()-1, 0);
//			Object coinObject = dtm.getValueAt(dtm.getRowCount()-1, 1);
//			coinNames = coinObject.toString().split(",");
//			strategyName = (String) dtm.getValueAt(dtm.getRowCount()-1, 2);
			context = new Context(new AddBroker(example, traderName, coinNames, strategyName));
		    context.execute();
			
		} else if ("remTableRow".equals(command)) {
			int selectedRow = table.getSelectedRow();
			context = new Context(new DeleteBroker(example, selectedRow));
		    
			
			if (selectedRow != -1)
				dtm.removeRow(selectedRow);
		}
		
	
		System.out.println(example.getSize());
		ListIterator <Broker> iter = example.getClientList().listIterator();
		while (iter.hasNext()) {
			System.out.println(iter.next().gettraderName());
		}
//		context = new Context(new PerformTrade(example));
//		context.execute();
		
		
	}

}
