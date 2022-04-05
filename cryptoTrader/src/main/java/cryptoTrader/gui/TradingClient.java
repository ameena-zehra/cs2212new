package cryptoTrader.gui;
import java.util.ArrayList; // import the ArrayList class
import java.util.Iterator;
public class TradingClient {
	private ArrayList<Broker> clientList = new ArrayList<Broker>();		// an array list containing all the brokers linked to the user
	
	/**
	* Get Client List Method
	* @return gives the client list linked to the user trading client
	*/
	public ArrayList<Broker> getClientList() {
		return clientList;
	}
	
	/**
	* Get Size method
	* @return the size of the array client list
	*/
	public int getSize() {
		return clientList.size();
	}
	
	/**
	* Check List method
	* @return boolean determining if a name already exists in the list
	*/
	public boolean checkList(String brokerName) {
		for (Broker currentBroker : clientList) {
			if (currentBroker.gettraderName().equals(brokerName)) {
				return true;
			}
		}
		return false;
	}

	/**
	* Check List method
	* @return int specifying all the trading actions committed by all the brokers in the trading client list
	*/
	public int getTotalAllTradingActions () {
		int totalTrades = 0;
		for (Broker currentBroker : clientList) {
			totalTrades += currentBroker.getactionRecord().size();
		}
		return totalTrades;
	}
}