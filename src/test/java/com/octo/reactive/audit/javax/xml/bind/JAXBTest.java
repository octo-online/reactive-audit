package com.octo.reactive.audit.javax.xml.bind;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.IOTestTools;
import com.octo.reactive.audit.lib.FileAuditReactiveException;
import org.junit.Test;

import javax.xml.bind.JAXB;
import java.net.MalformedURLException;

public class JAXBTest
{
	@Test(expected = FileAuditReactiveException.class)
	public void marshal_File()
	{
		AuditReactive.strict.commit();
		JAXB.marshal(null, IOTestTools.getTempFile());
	}

	@Test(expected = FileAuditReactiveException.class)
	public void marshal_OutputStream()
	{
		AuditReactive.strict.commit();
		JAXB.marshal(null, IOTestTools.getTempFileOutputStream());
	}

	@Test(expected = FileAuditReactiveException.class)
	public void marshal_Writer()
	{
		AuditReactive.strict.commit();
		JAXB.marshal(null, IOTestTools.getTempFileWriter());
	}

	@Test(expected = FileAuditReactiveException.class)
	public void marshal_URL() throws MalformedURLException
	{
		AuditReactive.strict.commit();
		JAXB.marshal(null, IOTestTools.getTempFile().toURI().toURL());
	}

	@Test(expected = FileAuditReactiveException.class)
	public void unmarshal_File()
	{
		AuditReactive.strict.commit();
		JAXB.unmarshal(IOTestTools.getTempFile(), null);
	}

	@Test(expected = FileAuditReactiveException.class)
	public void unmarshal_InputStream()
	{
		AuditReactive.strict.commit();
		JAXB.unmarshal(IOTestTools.getTempFileInputStream(), null);
	}

	@Test(expected = FileAuditReactiveException.class)
	public void unmarshal_Reader()
	{
		AuditReactive.strict.commit();
		JAXB.unmarshal(IOTestTools.getTempFileReader(), null);
	}

	@Test(expected = FileAuditReactiveException.class)
	public void unmarshal_URL() throws MalformedURLException
	{
		AuditReactive.strict.commit();
		JAXB.unmarshal(IOTestTools.getTempFile().toURI().toURL(), null);
	}
}
