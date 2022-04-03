package cryptoTrader.gui;

import java.util.ArrayList;

public class StrategyB extends Strategy{
	CryptoCoin BTC = new CryptoCoin("btc");
	CryptoCoin ETH = new CryptoCoin("eth");

	@Override
	public void compute(Broker currentBroker) {
		ArrayList<CryptoCoin> coinList = currentBroker.getCoinList();
		boolean success = checkcryptoListandStrategy(coinList);
		
		if (success=true) {
			TradingAction result = new TradingAction(currentBroker.gettraderName(), "strategy-b", "btc", "buy", 10, BTC.getPrice(), BTC.getDate());
			currentBroker.addActionRecord(result);
			return;
		}
		else {
			TradingAction result = new TradingAction(currentBroker.gettraderName(), "strategy-b", BTC.getDate());
			currentBroker.addActionRecord(result);
			return;
		}
		// TODO Auto-generated method stub
		
	}
	private boolean checkcryptoListandStrategy(ArrayList<CryptoCoin> coinList){
//		if (((coinList.contains(BTC))&&(coinList.contains(ETH)))&&((BTC.getPrice())>(ETH.getPrice()))){
//			return true;
//		}
		return true;
		
	}
	
}