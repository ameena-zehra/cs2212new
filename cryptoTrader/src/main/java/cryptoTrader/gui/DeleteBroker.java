package cryptoTrader.gui;

public class DeleteBroker extends Operations{
	private int indextoDelete;
	private TradingClient currentUser;
	
	public DeleteBroker(TradingClient currentUser, int index) {
		this.currentUser = currentUser;
		this.indextoDelete = index;
		
	}

	@Override
	public void doAlgorithm(Context context) {
		currentUser.getClientList().remove(indextoDelete);
		return;
	}
}