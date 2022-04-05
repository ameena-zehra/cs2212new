package cryptoTrader.gui;

import java.util.ArrayList;

public class PerformTrade extends Operations{
	TradingClient currentUser;
	public PerformTrade(TradingClient currentUser) {
		this.currentUser= currentUser;
		
	}

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
	private void fetchPrices(ArrayList<CryptoCoin> coinList ) {
		for (CryptoCoin coin: coinList) {
			coin.getPrice();
		}
	}

}
