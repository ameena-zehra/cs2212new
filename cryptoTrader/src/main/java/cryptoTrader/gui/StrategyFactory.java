package cryptoTrader.gui;

public class StrategyFactory {
	
	public Strategy getStrategy(String strategyType) {
	
		if(strategyType == null){  
            return null;  
           }  
         if(strategyType.equalsIgnoreCase("Strategy-A")) {  
        	 
                return new StrategyA();  
              }   
          else { 
        	 // System.out.println("is this computed?");
               return new StrategyB();  
           }   
//         else if(strategyType.equalsIgnoreCase("Strategy-C")) {  
//               return new StrategyC();  
//         }  
//         else if(strategyType.equalsIgnoreCase("Strategy-D")) {  
//             return new StrategyD();  
//         }
//         else if(strategyType.equalsIgnoreCase("Strategy-E")) {  
//             return new StrategyE();  
//         }
       
     
	}

}
