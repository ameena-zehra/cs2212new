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
			if (b.gettraderName().equals(b)) {
				return true;
			}
		}
		return false;
	}

	
	

}
