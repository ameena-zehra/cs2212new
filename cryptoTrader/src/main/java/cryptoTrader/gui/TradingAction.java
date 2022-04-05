package cryptoTrader.gui;

public class TradingAction {
	private String traderName;				// Name of Broker who committed trade
	private String strategyname;			// Name of strategy used by broker
	private String coinTraded;				// Name of coin traded by the broker
	private String BuyorSell; 				// Buy or sell was committed 
	private int quantity=0;					// Quantity of stock bought or sold
	private double price=0; 				// Price of stock
	private String timeStamp;				// Time stamp of each trade
	
	/**
	* Constructor
	* @param traderName String, strategyName String, coinTraded String, buy or sell string, quantity, price
	* Sets all the instance variables associated with the trading action class
	* Used if the trade is successful
	*/
	public TradingAction(String traderName, String strategyname, String coinTraded, String BuyorSell, int quantity, double price, String timeStamp) {
		this.traderName = traderName;
		this.strategyname = strategyname;
		this.coinTraded = coinTraded;
		this.BuyorSell = BuyorSell;
		this.quantity = quantity;
		this.price = price;
		this.timeStamp = timeStamp;
	}
	
	/**
	* Alternative Constructor
	* @param traderName String, strategyName String, coinTraded String, buy or sell string, quantity, price
	* Sets all the instance variables associated with the trading action class
	* Used if the trade has failed and the majority of variables are set to null
	*/
	public TradingAction(String traderName, String strategyname, String date) {
		this.traderName = traderName;
		this.strategyname = strategyname;
		this.timeStamp= date;
	}

	/**
	* Outputs Trade as an array object
	* @return an array representing the total traded action to be used by the data visualization creator
	*/
	public Object[] outputTradeArray() {
		Object[] result = new Object[7];
		result[0]= traderName;
		result[1] = strategyname;
		result[2] = coinTraded;
		result[3] = BuyorSell;
		result[4] = String.valueOf(quantity);
		result[5] = String.valueOf(price);
		result[6] = timeStamp;
		return result;
	}
}