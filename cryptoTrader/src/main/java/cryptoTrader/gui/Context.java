package cryptoTrader.gui;

public class Context {
	
	private Operations operation;	// Operations instance variable defined in the constructor
	
	/**
	* Constructor
	* @param Operation to be completed
	*/
	public Context(Operations userSelectedoperation) {
	    this.operation = userSelectedoperation;
	}
	
	/**
	* Execute 
	* Method which forces the execution of the operation specified in the constructor
	*/
	public void execute() {
	   operation.doAlgorithm(this);
	}


}
