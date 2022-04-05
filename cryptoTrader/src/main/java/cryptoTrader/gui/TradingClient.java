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
		for (Broker currentBroker : clientList) {
			if (currentBroker.gettraderName().equals(brokerName)) {
				return true;
			}
		}
		return false;
	}

	
	public int getTotalAllTradingActions () {
		int totalTrades = 0;
		for (Broker currentBroker : clientList) {
			totalTrades += currentBroker.getactionRecord().size();
			System.out.println(currentBroker.gettraderName()+" Size of action record for each broker "+currentBroker.getactionRecord().size());
		}
		return totalTrades;
	}
}