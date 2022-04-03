package cryptoTrader.gui;

import cryptoTrader.utils.DataFetcher;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;    
public class CryptoCoin {
	public String cryptoName;
	private double cryptoPrice;
	private double cryptoMarketCap;
	private DataFetcher example = new DataFetcher();
	private String date;
	public CryptoCoin(String cryptoName) {
		this.cryptoName = cryptoName;
		setDate();
		
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
		
		this.cryptoPrice= example.getPriceForCoin(cryptoName, date);
		
	}
	private void setMarketCap() {
		this.cryptoMarketCap = example.getMarketCapForCoin(cryptoName, date);
	}
	
	public double getPrice() {
		setPrice();
		return cryptoPrice;
	}
	public double getMarketCap() {
		setMarketCap();
		return cryptoMarketCap;
	}
	
//	public static void main(String[] args) {
//		CryptoCoin example = new CryptoCoin("etc");
//		System.out.println(example.getPrice());
//		System.out.println(example.getMarketCap());
//		
//	}

}
