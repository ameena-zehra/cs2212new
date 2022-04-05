package cryptoTrader.gui;

import java.util.ArrayList;

public class PerformTrade extends Operations{
	TradingClient currentUser;					// current TradingClient for which trade is to be performed
	
	/**
	* Constructor
	* @param Current TradingClient
	* Provides the trading client from which trade is to be performed
	*/
	public PerformTrade(TradingClient currentUser) {
		this.currentUser= currentUser;
		
	}

	/**
	* Do Algorithm Method
	* @param Uses strategy design pattern through the context variable 
	* Completes the algorithm of performing broker from the current user through the helper function getPrices
	* Then uses the factory method and creates a different strategy based on user specifications
	*/
	@Override
	public void doAlgorithm(Context context) {
		ArrayList<Broker>  clientList = currentUser.getClientList();
		StrategyFactory factory = new StrategyFactory();
		for (Broker broker : clientList) {
			Strategy currentStrategy = factory.getStrategy(broker.getStrategy());
			fetchPrices(broker.getCoinList());
			currentStrategy.compute(broker);
			
		}
		
		return;
	}
	
	/**
	* Private helper function fetch prices
	* @param contains the coins specified by all brokers
	* Attaches the price to the coin through calling the coinGecko API
	*/
	private void fetchPrices(ArrayList<CryptoCoin> coinList ) {
		for (CryptoCoin coin: coinList) {
			coin.getPrice();
		}
	}

}
