package cryptoTrader.gui;

import java.util.ArrayList;

public class Broker {
	private String traderName;												// Name of Broker in trading list
	private ArrayList<CryptoCoin> coinList= new ArrayList<CryptoCoin>();	// All the coins that the broker is interested in
	private String strategyName;											// Holds strategy name
	private ArrayList<TradingAction> ActionRecord= new ArrayList<TradingAction>(); // Holds all trading actions committed by broker
	private int successfulTrades=0;											// Holds total number of successful trades
	
	/**
	* Constructor
	* @param coinList, name of trader,  strategy name
	* Creates a new broker to be added to the array list of the trading clients
	*/
	public Broker(String traderName, String[] coinNames, String strategyName) {
		this.traderName = traderName;
		this.strategyName = strategyName;
		addCoins(coinNames);
		
	}
	
	/**
	* Add Coin List Method
	* @param String[] array of all coin names
	* Added to the list through first changing all cryptocoins to lowercase
	* Then appended to the list
	*/
	public void addCoins(String[] coinNames) {
		for (int i=0; i<coinNames.length; i++) {
			CryptoCoin newcoin = new CryptoCoin(coinNames[i].toLowerCase());
			coinList.add(newcoin);
		}
	}
	
	/**
	* Add Action Record
	* @param trade committed by the broker
	* Adds a trading action completed by a broker to the array of all trading actions
	*/
	public void addActionRecord(TradingAction trade) {
		ActionRecord.add(trade);
	}
	
	/**
	* Get Trader Name
	* @return Name of Trader as String
	*/
	public String gettraderName(){
		return traderName;
	}
	
	/**
	* Get Coin List
	* @return all coins as an array list
	*/
	public ArrayList<CryptoCoin> getCoinList(){
		return coinList;
	}
	
	/**
	* Get Coin List
	* @return Name of Strategy as String
	*/
	public String getStrategy(){
		return strategyName;
	}
	
	/**
	* Get Trading Actions
	* @return all trading actions as an array list
	*/
	public ArrayList<TradingAction> getactionRecord(){
		return ActionRecord;
	}
	
	/**
	* Add to Trades
	* @param Quantity traded, differing in each strategy
	* @return Adds the quantity traded from the parameter to the instance variable successful trades
	*/
	public void addTrades(int quantityTraded) {
		successfulTrades+=quantityTraded;
	}
	/**
	* Get Trades
	* @return number of successful trades
	*/
	public int getnumTrades() {
		return successfulTrades;
	}


}
