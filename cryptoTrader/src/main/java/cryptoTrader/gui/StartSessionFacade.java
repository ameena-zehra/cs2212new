package cryptoTrader.gui;


public class StartSessionFacade {
	private String lastAddedClient=null;
	private Context context=null;
	
	public void add(TradingClient currentClient, String traderName, String[] coinNames, String strategyName) {
		if (!(traderName.equals(lastAddedClient))) {
			lastAddedClient = traderName;
			context = new Context(new AddBroker(currentClient, traderName, coinNames, strategyName));
			context.execute();
		}
	}
	public void perform(TradingClient currentClient) {
		context = new Context(new PerformTrade(currentClient));
		context.execute();
	}
	public void delete(TradingClient currentClient, int selectedRow) {
		context = new Context(new DeleteBroker(currentClient, selectedRow));
		context.execute();
	}
	public void update(TradingClient currentClient, String traderName, String[] coinNames, String strategyName,
			int count) {
		context = new Context(new UpdateBroker(currentClient, traderName, coinNames, strategyName, count));
		context.execute();
		
	}

}
