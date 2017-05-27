The project 
	it.unibo.basicInterfaces (26KB)
defines a set of basic oontracts.

It depends on the internal library
	IObluecove-2.1.0.jar
that is a subset of the library
	bluecove-2.1.0.jar


The package 
	it.unibo.is.interfaces 
defines the operations of common data structures:
	IBasicMessage
	IMessage
	IOutputView
	IPolicy


The package 
	it.unibo.is.interfaces.platforms
defines the operations provided by the contact platform
(see the project it.unibo.platform.medcl).


The package 
	it.unibo.is.interfaces.protocols
defines the operations provided by basic supports related
specific communication protocols.

The package 
	it.unibo.is.interfaces.services
defines the operations of objects acting
as a factory of some specific protocol support.
These objects can be implemented either as conventional objects
or as OSGi services (when the System property named  "osgiContext"
is set by the OSGi activator related to a Contact system).


Communication supports can be related to output operations or to
input operations. The support for a given operation 
is found by the operations:

IOpOut PlatformExpert.findOutSupport(
	String receiver,String msgId, boolean withAnswer,IOutputView view )
IOpIn PlatformExpert.findInSupport(
	String receiver,String msgId,String sender,IOutputView view)

These operations look for the existence of a support by calling
the class SubjectNameService.
If no support exists, a support is created if the operation must be 
performed using some communication protocol. Otherwise the default
communication support is used, which is implemented as a shared space.

The creation of a support related to a communication protocol PPP is performed
by an operation of SubjectNameService called
createMedClPPPInSupport or createMedClPPPOutSupport

The structure of these operations is the following
	protected static IMedCl09 createMedClTcpOutSupport(
			String receiver,String msgId,String sender,IOutputView view) throws Exception{
		if( System.getProperty("osgiContext") != null ) 		
			IS09Util.loadBundle(null,Activator.getContext(),path + "it.unibo.platform.tcp_1.4.0.jar");
		ILindaLike tcpS    = SupportFactory.createTCPOutSupport(receiver, msgId, sender,view);
		IMedCl09 support   = new PlatformContact( tcpS,view );
		SubjectNameService.addSubjectOutConnSupport(sender, receiver+msgId, support,null);
		return support;		
	}

If the system is working under OSGi, the bundle related to the protocol is loaded.
Next the it.unibo.platform.expert.SupportFactory is called to create a low-level support
with interface ILindaLike. This low-level support is wrapped into a high-level support
implementing the Contact interface IMedCl09, which is memoized into the SubjectNameService.


The SupportFactory creates a new object only if no connection is set. To do the job,
SupportFactory calls its internal method
createNewPPPOutSupport or createNewPPPOutSupport

These operations create a low level support implementing the ILindaLike interface by
wrapping a protocol-specific object into a ILindaLike protocol specific support that
implements the out, in, rd, etc operations by using the basic protocol-specific support.
