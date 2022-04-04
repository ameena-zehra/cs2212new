package cryptoTrader.gui;

import cryptoTrader.utils.DataFetcher;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;    
public class CryptoCoin {
	private String cryptoName;
	private double cryptoPrice;
	private DataFetcher coingeckoAPI = new DataFetcher();
	private String date;
	
	public CryptoCoin(String cryptoName) {
		this.cryptoName = cryptoName;
		setDate();
	}
	public String getCryptoName() {
		return cryptoName;
	}
	private void setDate() {
		LocalDateTime now = LocalDateTime.now(); 
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		date = dtf.format(now.minusDays(2));
	}
	public String getDate() {
		return date;
	}
	private void setPrice() {
		this.cryptoPrice= coingeckoAPI.getPriceForCoin(cryptoName, date);
	}
	public double getPrice() {
		setPrice();
		return cryptoPrice;
	}
	

}
