package cryptoTrader.gui;

import java.util.ArrayList;

public class Broker {
	private String traderName;
	private ArrayList<CryptoCoin> coinList= new ArrayList<CryptoCoin>();
	private String strategyName;
	private ArrayList<TradingAction> ActionRecord= new ArrayList<TradingAction>();
	private int successfulTrades=0;
	

	public Broker(String traderName, String[] coinNames, String strategyName) {
		this.traderName = traderName;
		this.strategyName = strategyName;
		addCoins(coinNames);
		
	}
	
	public void setTraderName(String newName) {
		traderName= newName;
	}
	public void addCoins(String[] coinNames) {
		for (int i=0; i<coinNames.length; i++) {
			
			CryptoCoin newcoin = new CryptoCoin(coinNames[i].toLowerCase());
			
			coinList.add(newcoin);
		}
	}
	
	public void addActionRecord(TradingAction trade) {
		ActionRecord.add(trade);
	}
	public String gettraderName(){
		return traderName;
	}
	public ArrayList<CryptoCoin> getCoinList(){
		return coinList;
	}
	public String strategyName(){
		return strategyName;
	}
	public void setStrategy(String newStrategy){
		strategyName= newStrategy;
	}
	public ArrayList<TradingAction> getactionRecord(){
		return ActionRecord;
	}
	public void addTrades(int quantityTraded) {
		successfulTrades+=quantityTraded;
	}
	public int getnumTrades() {
		return successfulTrades;
	}
//	public static void main(String[] args) {
//		String[] strArray = new String[] {"one", "two"};
//		Broker example = new Broker("Amy", strArray, "Strategy-A");
//		System.out.println(example.gettraderName());
//		
//	}

}
