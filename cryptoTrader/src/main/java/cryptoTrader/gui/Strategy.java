package cryptoTrader.gui;
/**
* Super class Strategy
* All Strategy classes inherit its method compute to compute the strategy of the current Broker
*/

public abstract class Strategy {
	
	public abstract void compute(Broker currentBroker);

}


