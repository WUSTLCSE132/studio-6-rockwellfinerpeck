package studio6;

import jssc.*;

public class SerialComm {

	SerialPort port;

	private boolean debug;  // Indicator of "debugging mode"
	
	// This function can be called to enable or disable "debugging mode"
	void setDebug(boolean mode) {
		debug = mode;
	}	
	

	// Constructor for the SerialComm class
	public SerialComm(String name) throws SerialPortException {
		port = new SerialPort(name);		
		port.openPort();
		port.setParams(SerialPort.BAUDRATE_9600,
			SerialPort.DATABITS_8,
			SerialPort.STOPBITS_1,
			SerialPort.PARITY_NONE);
		
		debug = false; // Default is to NOT be in debug mode
	}
		
	// TODO: Add writeByte() method from Studio 5
	public void writeByte(byte b) throws SerialPortException {
		port.writeByte(b);
		if(debug) {
			System.out.println("<0x"+ b + ">");
		}
		
	}
	
	// TODO: Add available() method
	public boolean available() throws SerialPortException {
		return port.getInputBufferBytesCount()>0;
	}
	
	// TODO: Add readByte() method	
	public byte readByte() throws SerialPortException {
		if (this.available()) {
			byte [] byteRead = port.readBytes(1);
			if(debug) {
				String byteName = String.format("%02x", byteRead[0]);
				System.out.print(byteName + "\t");;
			}
			return byteRead[0];
			
		}
		return -1;
		
	}
	
	
	// TODO: Add a main() method
	public static void main(String args[]) throws SerialPortException {
		SerialComm sc = new SerialComm("COM4");
		sc.setDebug(true);
		while (true) {
			if (sc.available()) {
				char byteChar = (char) sc.readByte();
				System.out.println(byteChar);
			}
			
		}
	}
}
