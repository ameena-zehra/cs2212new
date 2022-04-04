package cryptoTrader.gui;

import java.util.ArrayList;

public class StrategyD extends Strategy{
	private CryptoCoin BTC = new CryptoCoin("btc");
	private CryptoCoin XLM = new CryptoCoin("xlm");
	private double BTCPrice = BTC.getPrice();
	private double XLMPrice = XLM.getPrice();

	@Override
	public void compute(Broker currentBroker) {
		ArrayList<CryptoCoin> coinList = currentBroker.getCoinList();
		boolean success = checkcryptoListandStrategy(coinList);
		
		if (success==true) {
			TradingAction result = new TradingAction(currentBroker.gettraderName(),"strategy-d", "xlm", "buy", 10, XLM.getPrice(), XLM.getDate());
			currentBroker.addActionRecord(result);
			currentBroker.addTrades();
			return;
		}
		else {
			TradingAction result = new TradingAction(currentBroker.gettraderName(), "strategy-d", BTC.getDate());
			currentBroker.addActionRecord(result);
			return;
		}
		// TODO Auto-generated method stub
		
	}
	private boolean checkcryptoListandStrategy(ArrayList<CryptoCoin> coinList){
		int numMatches=0;
		for (CryptoCoin coin : coinList) {
			System.out.println(coin.getCryptoName());
			if (coin.getCryptoName().equals("btc")) {
				numMatches++;
			}
			if (coin.getCryptoName().equals("xlm")) {
				numMatches++;
			}
		}
		if (numMatches>=2) {
			System.out.println("the list does indeed contain this");
			if ((BTCPrice)>=(XLMPrice)){
				System.out.println("successful trade");
				return true;
			}
		}
		return false;		
	}
	
}