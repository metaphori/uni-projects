package it.unibo.ccc.domain.interfaces;

public interface IBasicConvoy /*extends List<IConvoyVehicle>*/ {
	
	public int addVehicle(IConvoyVehicle vehicle) /*throws CannotPerformException*/;
	
	public IConvoyVehicle getVehicle(int position);
	
}
