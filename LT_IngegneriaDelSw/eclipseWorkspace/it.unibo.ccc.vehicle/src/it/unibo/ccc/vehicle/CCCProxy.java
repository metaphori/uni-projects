package it.unibo.ccc.vehicle;

import it.unibo.ccc.domain.SysKb;
import it.unibo.ccc.domain.interfaces.IConvoyVehicle;
import it.unibo.ccc.system.interfaces.IConvoyVehicleFacade;
import it.unibo.is.interfaces.IBasicEnvAwt;
import it.unibo.is.interfaces.protocols.IConnInteraction;
import it.unibo.supports.FactoryProtocol;

// TODO: turn to IConvoyVehicleFacade
public class CCCProxy /* extends ConvoyCruiseControl */ implements Runnable, IConvoyVehicleFacade {
	private FactoryProtocol factory;
	private IBasicEnvAwt env;
	private String host;
	private int port;
	private IConnInteraction clientConn;
	private IConvoyVehicle v;
	
	public CCCProxy(IBasicEnvAwt env, FactoryProtocol factory, String host, int port) throws Exception{
		super();
		this.env = env;
		this.factory = factory;
		this.host = host;
		this.port = port;
		connect();
		
		Thread t = new Thread(this);
		t.start();
	}
	
	protected void connect() throws Exception{
		try {
			clientConn = factory.createClientProtocolSupport(host, port);			
		} catch (Exception e) {
			env.println("Sorry - " + e.getMessage() );
			throw(e);
		}
	}
	
	@Override
	public int registerToConvoy(IConvoyVehicle vehicle) {
		v = vehicle;
		
		try{
			clientConn.sendALine(SysKb.register, true);
			
			String answer = clientConn.receiveALine();
			env.println("Received answ" + answer);

			return Integer.parseInt(answer);
					
		} catch(Exception exc){
			env.println("Cannot register to convoy: " + exc.getMessage());
			return -1;
		}
	}

	@Override
	public void notifySpeed(int vehicle, double speed) {
		try{
			clientConn.sendALine("v"+vehicle+"//"+speed);
		} catch(Exception exc){
			env.println("Cannot notify speed: " + exc.getMessage());
		}
	}

	@Override
	public void notifyStatus(int vehicle, boolean status) {
		try{
			clientConn.sendALine("v"+vehicle+"//"+ (status? SysKb.working : SysKb.notWorking) );
		} catch(Exception exc){
			env.println("Cannot notify status: " + exc.getMessage());
		}	
	}


	@Override
	public void run() {
		try{
			Thread.sleep(4000);
			while(true){
				String recv = clientConn.receiveALine();
				env.println("Convoy proxy received " + recv);
				if(v!=null){
					
					if( SysKb.getCmdScheme(recv).equals(SysKb.setSpeed)){
						double sp = Double.parseDouble(SysKb.getCmdContent(recv));
						v.setSpeed(sp);
					} else if( recv.equals(SysKb.start)){
						env.println("START");
						v.doStart();
					} else if( recv.equals(SysKb.stop)){
						env.println("STOP");
						v.doStop();
					}
					
				}
			}
		} catch(Exception exc){
			
		}
	}


	
	
}
