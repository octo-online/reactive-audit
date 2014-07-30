package com.octo.reactive.audit.javax.xml.parsers;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.IOTestTools;
import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import javax.transaction.xa.XAException;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;

public class DocumentBuilderTest
{
	Unmarshaller x = (Unmarshaller) TestTools.createProxy(Unmarshaller.class);

	@Test(expected = NetworkAuditReactiveException.class)
	public void unmarshal_File() throws InterruptedException, IOException, XAException, JAXBException
	{
		AuditReactive.strict.commit();
		x.unmarshal(IOTestTools.getTempFile());
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void unmarshal_InputStream() throws InterruptedException, IOException, XAException, JAXBException
	{
		AuditReactive.strict.commit();
		x.unmarshal(IOTestTools.getTempFileInputStream());
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void unmarshal_Reader() throws InterruptedException, IOException, XAException, JAXBException
	{
		AuditReactive.strict.commit();
		x.unmarshal(IOTestTools.getTempFileReader());
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void unmarshal_URL() throws InterruptedException, IOException, XAException, JAXBException
	{
		AuditReactive.strict.commit();
		x.unmarshal(IOTestTools.getTempFile().toURI().toURL());
	}


}
