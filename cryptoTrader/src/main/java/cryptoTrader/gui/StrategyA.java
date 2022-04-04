package cryptoTrader.gui;

import java.util.ArrayList;

public class StrategyA extends Strategy{
	private CryptoCoin BTC = new CryptoCoin("btc");
	private CryptoCoin ETH = new CryptoCoin("eth");
	private double BTCPrice = BTC.getPrice();
	private double ETHPrice = ETH.getPrice();

	@Override
	public void compute(Broker currentBroker) {
		ArrayList<CryptoCoin> coinList = currentBroker.getCoinList();
		boolean success = checkcryptoListandStrategy(coinList);
		
		if (success==true) {
			TradingAction result = new TradingAction(currentBroker.gettraderName(),"strategy-a", "btc", "buy", 10, BTC.getPrice(), BTC.getDate());
			currentBroker.addActionRecord(result);
			currentBroker.addTrades();
			return;
		}
		else {
			TradingAction result = new TradingAction(currentBroker.gettraderName(), "strategy-a", BTC.getDate());
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
			if (coin.getCryptoName().equals("eth")) {
				numMatches++;
			}
		}
		if (numMatches>=2) {
			System.out.println("the list does indeed contain this");
			if ((BTCPrice)>(ETHPrice)){
				return true;
			}
		}
		
		return false;
		
	}
	
}