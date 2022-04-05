package cryptoTrader.gui;

public class AddBroker extends Operations{
	private Broker newBroker; 			// newBroker to be added
	private TradingClient currentUser;	// the current Trading client where the broker is being added to
	
	/**
	* Constructor
	* @param Current TradingClient, coinList, name of trader,  strategy name
	* Creates a new broker to be added to the array list through the do algorithm method
	*/
	public AddBroker(TradingClient currentUser, String traderName, String[] coinNames, String strategyName) {
		this.currentUser = currentUser;
		Broker newBroker = new Broker(traderName, coinNames, strategyName);
		this.newBroker= newBroker;
	}

	/**
	* Do Algorithm Method
	* @param Uses strategy design pattern through the context variable 
	* Completes the algorithm of adding a new broker to the current user
	*/
	@Override
	public void doAlgorithm(Context context) {
		currentUser.getClientList().add(newBroker);
		return;
	}

}
