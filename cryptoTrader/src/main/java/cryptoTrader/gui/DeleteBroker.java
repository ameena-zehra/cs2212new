package cryptoTrader.gui;

public class DeleteBroker extends Operations{
	private int indextoDelete;			// int indextoDelete
	private TradingClient currentUser;	// current TradingClient from which the broker will be deleted
	
	/**
	* Constructor
	* @param Current TradingClient, index to delete
	* Specifies the index for the broker to be deleted from the array list using the do algorithm method
	*/
	public DeleteBroker(TradingClient currentUser, int index) {
		this.currentUser = currentUser;
		this.indextoDelete = index;
		
	}

	/**
	* Do Algorithm Method
	* @param Uses strategy design pattern through the context variable 
	* Completes the algorithm of adding a broker from the current user through editing the client list
	*/
	@Override
	public void doAlgorithm(Context context) {
		currentUser.getClientList().remove(indextoDelete);
		return;
	}
}