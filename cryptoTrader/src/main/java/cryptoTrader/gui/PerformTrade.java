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
			Strategy currentStrategy = factory.getStrategy(broker.strategyName());
			currentStrategy.compute(broker);
			
		}
		
		return;
	}

}
