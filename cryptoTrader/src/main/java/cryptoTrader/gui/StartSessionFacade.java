package cryptoTrader.gui;


public class StartSessionFacade {
	
	
	private String lastAddedClient=null;			// prevents instances of double adding
	private Context context=null;					// used to implement strategy design pattern
	
	/**
	* Facade Add Class
	* @param current client, name of trader, array of coin names, strategy name
	* Adds the broker through using the context and operations class (implementing strategy design pattern)
	*/
	public void add(TradingClient currentClient, String traderName, String[] coinNames, String strategyName) {
		if (!(traderName.equals(lastAddedClient))) {
			lastAddedClient = traderName;
			context = new Context(new AddBroker(currentClient, traderName, coinNames, strategyName));
			context.execute();
		}
	}
	
	/**
	* Facade Perform Class
	* @param current client
	* Performs trade through using the context and operations class (implementing strategy design pattern)
	*/
	public void perform(TradingClient currentClient) {
		context = new Context(new PerformTrade(currentClient));
		context.execute();
	}
	
	/**
	* Facade Delete Class
	* @param current client, int row to delete
	* Deletes the broker through using the context and operations class (implementing strategy design pattern)
	*/
	public void delete(TradingClient currentClient, int selectedRow) {
		context = new Context(new DeleteBroker(currentClient, selectedRow));
		context.execute();
	}
	

}
