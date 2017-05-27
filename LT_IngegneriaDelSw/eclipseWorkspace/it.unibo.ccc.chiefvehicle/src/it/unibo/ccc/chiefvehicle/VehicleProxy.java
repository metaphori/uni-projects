package it.unibo.ccc.chiefvehicle;

import it.unibo.ccc.domain.SysKb;
import it.unibo.ccc.domain.interfaces.IConvoyVehicle;
import it.unibo.ccc.exceptions.CannotPerformException;
import it.unibo.ccc.exceptions.InvalidArgumentException;
import it.unibo.is.interfaces.IBasicEnvAwt;
import it.unibo.is.interfaces.protocols.IConnInteraction;
import it.unibo.platform.tcp.SocketTcpConnSupport;

public class VehicleProxy implements IConvoyVehicle {

	private SocketTcpConnSupport conn;
	private IBasicEnvAwt env;
	private int ID;
	
	public VehicleProxy(IBasicEnvAwt env, SocketTcpConnSupport conn){
		this.conn = conn;
		this.env = env;
		this.ID = -1;
	}
	
	@Override
	public void setSpeed(double speed) throws InvalidArgumentException {
		try{
			conn.sendACmdLine( SysKb.setSpeed + "//" + speed );
			env.println("Vehicle proxy send speed set ok");
		} catch(Exception exc){
			env.println("Vehicle proxy set speed error");
		}
	}

	@Override
	public double getSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getID() {
		return this.ID;
	}
	
	public void setID(int id){
		this.ID = id;
	}

	@Override
	public boolean isWorking() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void doStart() throws CannotPerformException {
		try{
			conn.sendALine( SysKb.start );
			env.println("Vehicle proxy send start ok");
		} catch(Exception exc){
			env.println("Vehicle proxy set speed error");
		}
	}

	@Override
	public void doStop() {
		try{
			conn.sendALine( SysKb.stop );
			env.println("Vehicle proxy send stop ok");
		} catch(Exception exc){
			env.println("Vehicle proxy set speed error");
		}
	}

}
