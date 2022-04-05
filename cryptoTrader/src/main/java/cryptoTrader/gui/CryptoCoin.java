package cryptoTrader.gui;

import cryptoTrader.utils.DataFetcher;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;    
public class CryptoCoin {
	private String cryptoName;								// Name of Cryptocoin 
	private double cryptoPrice;								// Price determined through the CoinGeckoAPI
	private DataFetcher coingeckoAPI = new DataFetcher();	// DataFetcher uses to determine the price for the coin
	private String date;									// Date passed into data fetcher
	
	/**
	* Constructor
	* @param name of cryptocoin
	* Sets the date and price of the coin through two helper methods
	*/
	public CryptoCoin(String cryptoName) {
		this.cryptoName = cryptoName;
		setDate();
	}
	
	/**
	* Get Crypto Name
	* @return name of cryptocoin
	*/
	public String getCryptoName() {
		return cryptoName;
	}
	
	/**
	* Private helper function
	* Sets the date using LocalDateTime now
	* Sets it as two days before today
	*/
	private void setDate() {
		LocalDateTime now = LocalDateTime.now(); 
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		date = dtf.format(now.minusDays(2));
	}
	/**
	* Private helper function
	* Sets the price of the cryptocoin using the coinGeckoAPI
	*/
	private void setPrice() {
		this.cryptoPrice= coingeckoAPI.getPriceForCoin(cryptoName, date);
	}
	/**
	* Get Date
	* @return the date as a string to be used by the coinGecko API as input
	*/
	public String getDate() {
		return date;
	}
	
	/**
	* Get Price
	* @return the price of the Cryptocoin based on a call to the coin gecko API
	*/
	public double getPrice() {
		setPrice();
		return cryptoPrice;
	}
	

}
