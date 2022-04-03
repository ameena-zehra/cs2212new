package cryptoTrader.gui;
import java.util.ArrayList; // import the ArrayList class
public class Context {
//	private String traderName;
//	private String[] coinNames; 
//	private String strategyName;
//	private TradingClient fullUser; 

	private Operations operation;

	   public Context(Operations userSelectedoperation) {
	      this.operation = userSelectedoperation;
	   }



	   public void execute() {
	      operation.doAlgorithm(this);
	   }


}
