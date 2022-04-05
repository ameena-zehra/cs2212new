package cryptoTrader.gui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class StrategyA extends Strategy{
	
	private double BTCPrice = 0;// Price of Bitcoin initialized to 0 and later updated if trade successful
	private double ETHPrice = 0; // Price of Stellar Coin initialized to 0 and later updated if trade successful

	
	
	/**
	* Public Computes Strategy method
	* @param currentBroker whose strategy is being evaluated
	* If trade is successful then a successful trade is added to the action record
	*/
	@Override
	public void compute(Broker currentBroker) {
		ArrayList<CryptoCoin> coinList = currentBroker.getCoinList();
		boolean success = checkcryptoListandStrategy(coinList);
		
		if (success==true) {
			TradingAction result = new TradingAction(currentBroker.gettraderName(),"Strategy-A", "btc", "Buy", 100, BTCPrice, getDate());
			currentBroker.addActionRecord(result);
			currentBroker.addTrades(100);
			return;
		}
		else {
			TradingAction result = new TradingAction(currentBroker.gettraderName(), "Strategy-A", getDate());
			currentBroker.addActionRecord(result);
			return;
		}
		// TODO Auto-generated method stub
		
	}
	
	/**
	* Private helper method
	* @param coinList
	* Checks if the coin list contains btc and eth and if so evaluates the core strategy
	* @return If both are successful a boolean true is returned, else it is false
	*/
	private boolean checkcryptoListandStrategy(ArrayList<CryptoCoin> coinList){
		int numMatches=0;
		
		for (CryptoCoin coin : coinList) {
			if (coin.getCryptoName().equals("btc")) {
				BTCPrice = coin.getPrice();
				numMatches++;
			}
			if (coin.getCryptoName().equals("eth")) {
				ETHPrice = coin.getPrice();
				numMatches++;
			}
		}
		if (numMatches>=2) {
			if ((BTCPrice)>(ETHPrice)){
				return true;
			}
		}
		return false;
		
	}
	
	/**
	* Private helper method
	* @return returns the date of the trade formatted for the chart used for the trading record
	*/
	private String getDate() {
		LocalDateTime now = LocalDateTime.now(); 
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		String date = dtf.format(now.minusDays(2));

		String month = getMonth(date.substring(0, 2));
		date = date.substring(2,10);
		String newdate = month.concat(date);
		return newdate;
	}
	
	/**
	* Private helper method
	* @return returns the month as a string used in the above method for formatting
	*/
	private String getMonth(String date) {
		if (date.equals("01")) {
			return "January";
		}
		if (date.equals("02")) {
			return "February";
		}
		if (date.equals("03")) {
			return "March";
		}
		if (date.equals("04")) {
			return "April";
		}
		if (date.equals("05")) {
			return "May";
		}
		if (date.equals("06")) {
			return "June";
		}
		if (date.equals("07")) {
			return "July";
		}
		if (date.equals("08")) {
			return "August";
		}
		if (date.equals("09")) {
			return "September";
		}
		if (date.equals("10")) {
			return "October";
		}
		if (date.equals("11")) {
			return "November";
		}
		return "December";
		
		
	}
}