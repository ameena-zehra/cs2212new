package cryptoTrader.gui;
import java.util.ArrayList; // import the ArrayList class
import java.util.Iterator;
public class TradingClient {
	private ArrayList<Broker> clientList = new ArrayList<Broker>();
	
	
	public ArrayList<Broker> getClientList() {
		return clientList;
	}
	
	public int getSize() {
		return clientList.size();
	}
	public boolean checkList(String brokerName) {
		for (Broker b : clientList) {
			if (b.gettraderName().equals(brokerName)) {
				return true;
			}
		}
		return false;
	}

	
	public int getTotalAllTradingActions () {
		int totalTrades = 0;
		for (Broker B : clientList) {
			totalTrades += B.getactionRecord().size();
			System.out.println(B.gettraderName()+" Size of action record for each broker "+B.getactionRecord().size());
		}
		return totalTrades;
	}
}