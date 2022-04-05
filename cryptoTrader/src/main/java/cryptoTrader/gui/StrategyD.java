package cryptoTrader.gui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class StrategyD extends Strategy{
	
	private double BTCPrice = 0;
	private double XLMPrice = 0;

	@Override
	public void compute(Broker currentBroker) {
		ArrayList<CryptoCoin> coinList = currentBroker.getCoinList();
		boolean success = checkcryptoListandStrategy(coinList);
		
		if (success==true) {
			TradingAction result = new TradingAction(currentBroker.gettraderName(),"strategy-d", "xlm", "buy", 300, XLMPrice, getDate());
			currentBroker.addActionRecord(result);
			currentBroker.addTrades(300);
			return;
		}
		else {
			TradingAction result = new TradingAction(currentBroker.gettraderName(), "strategy-d", getDate());
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
				BTCPrice = coin.getPrice();
				numMatches++;
			}
			if (coin.getCryptoName().equals("xlm")) {
				XLMPrice = coin.getPrice();
				numMatches++;
			}
		}
		if (numMatches>=2) {
			if ((BTCPrice)>(XLMPrice)||(XLMPrice>5)){
				return true;
			}
		}
		return false;
		
	}
	private String getDate() {
		LocalDateTime now = LocalDateTime.now(); 
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		String date = dtf.format(now.minusDays(2));

		String month = getMonth(date.substring(0, 2));
		date = date.substring(2,10);
		String newdate = month.concat(date);
		return newdate;
	}
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