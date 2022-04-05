package cryptoTrader.gui;

public class StrategyFactory {
	/**
	* Constructor Method
	* @param String representing the strategy type
	* Uses the factory design pattern to create a specific strategy based on input provided from the user
	*/
	public Strategy getStrategy(String strategyType) {
	
		if(strategyType == null){  
            return null;  
           }  
         if(strategyType.equalsIgnoreCase("Strategy-A")) {   
           return new StrategyA();  
         }
         else if(strategyType.equalsIgnoreCase("Strategy-B")) {  
           return new StrategyB();  
         }
         else if(strategyType.equalsIgnoreCase("Strategy-C")) {  
           return new StrategyC();  
         }  
         else{  
           return new StrategyD();  
         }
       
       
     
	}

}
