package de.emir.commons.connection;
import org.testng.Assert;
import org.testng.annotations.Test;

import jssc.SerialPort;
import jssc.SerialPortList;

public class SerialTest {
  @Test
  public void testSerialPorts() {
	  Assert.assertNotNull(SerialPortList.getPortNames(), "No serialports accessible.");
	  Assert.assertTrue(SerialPortList.getPortNames().length > 0, "No serialports accessible.");
	  for (String portName : SerialPortList.getPortNames()) {
			System.out.println("Available SerialPort: " + portName);
	  }
  }
}
