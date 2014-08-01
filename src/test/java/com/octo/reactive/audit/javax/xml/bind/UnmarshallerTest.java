package com.octo.reactive.audit.javax.xml.bind;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.IOTestTools;
import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.FileAuditReactiveException;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.net.MalformedURLException;

public class UnmarshallerTest
{
	private final Unmarshaller x = (Unmarshaller) TestTools.createProxy(Unmarshaller.class);

	@Test(expected = FileAuditReactiveException.class)
	public void unmarshal_File() throws JAXBException
	{
		AuditReactive.strict.commit();
		x.unmarshal(IOTestTools.getTempFile());
	}

	@Test(expected = FileAuditReactiveException.class)
	public void unmarshal_InputStream() throws JAXBException
	{
		AuditReactive.strict.commit();
		x.unmarshal(IOTestTools.getTempFileInputStream());
	}

	@Test(expected = FileAuditReactiveException.class)
	public void unmarshal_Reader() throws JAXBException
	{
		AuditReactive.strict.commit();
		x.unmarshal(IOTestTools.getTempFileReader());
	}

	@Test(expected = FileAuditReactiveException.class)
	public void unmarshal_URL() throws MalformedURLException, JAXBException
	{
		AuditReactive.strict.commit();
		x.unmarshal(IOTestTools.getTempFile().toURI().toURL());
	}


}
