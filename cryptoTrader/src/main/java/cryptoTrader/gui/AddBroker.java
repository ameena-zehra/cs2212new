package cryptoTrader.gui;

public class AddBroker extends Operations{
	private Broker newBroker;
	private TradingClient currentUser;
	
	public AddBroker(TradingClient currentUser, String traderName, String[] coinNames, String strategyName) {
		this.currentUser = currentUser;
		Broker newBroker = new Broker(traderName, coinNames, strategyName);
		this.newBroker= newBroker;
		//currentUser.addItem(newBroker);
		
	}

	@Override
	public void doAlgorithm(Context context) {
		currentUser.getClientList().add(newBroker);
		return;
	}

}
