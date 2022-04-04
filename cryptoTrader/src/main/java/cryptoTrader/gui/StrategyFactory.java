package cryptoTrader.gui;

public class StrategyFactory {
	
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
