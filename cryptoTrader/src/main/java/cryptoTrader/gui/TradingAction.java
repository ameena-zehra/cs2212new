package cryptoTrader.gui;

public class TradingAction {
	private String traderName;
	private String strategyname;
	private String coinTraded;
	private String BuyorSell; 
	private int quantity=0;
	private double price=0; 
	private String timeStamp;
	public TradingAction(String traderName, String strategyname, String coinTraded, String BuyorSell, int quantity, double price, String timeStamp) {
		this.traderName = traderName;
		this.strategyname = strategyname;
		this.coinTraded = coinTraded;
		this.BuyorSell = BuyorSell;
		this.quantity = quantity;
		this.price = price;
		this.timeStamp = timeStamp;
	}
	public TradingAction(String traderName, String strategyname, String date) {
		this.traderName = traderName;
		this.strategyname = strategyname;
		this.timeStamp= date;
	}

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