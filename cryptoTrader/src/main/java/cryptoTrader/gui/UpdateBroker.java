package cryptoTrader.gui;



public class UpdateBroker extends Operations {

	private String traderName;
	private String[] coinNames;
	private String strategyName;
	private int count;
	private TradingClient currentClient;

	public UpdateBroker(TradingClient currentClient, String traderName, String[] coinNames, String strategyName,
			int count) {
		this.currentClient = currentClient;
		this.count =count;
		this.traderName = traderName;
		this.coinNames = coinNames;
		this.strategyName = strategyName;
	}

	@Override
	public void doAlgorithm(Context context) {
		System.out.println("Is this ever reached??");
		currentClient.getClientList().get(count).setTraderName(traderName);
		//currentClient.getClientList().get(count).strategyName().equals(strategyName);
		currentClient.getClientList().get(count).setStrategy(strategyName);
		currentClient.getClientList().get(count).addCoins(coinNames);
		System.out.println("Value at "+ count+" updated");
		System.out.println("New Name at "+ count+currentClient.getClientList().get(count).gettraderName());
		

	}

}
