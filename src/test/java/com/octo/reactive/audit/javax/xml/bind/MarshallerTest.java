package com.octo.reactive.audit.javax.xml.bind;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.IOTestTools;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import javax.transaction.xa.XAException;
import javax.xml.bind.JAXB;
import java.io.IOException;

public class MarshallerTest
{
	JAXB x = null; // FIXME

	@Test(expected = NetworkAuditReactiveException.class)
	public void marshal_File() throws InterruptedException, IOException, XAException
	{
		AuditReactive.strict.commit();
		x.marshal(null, IOTestTools.getTempFile());
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void marshal_OutputStream() throws InterruptedException, IOException, XAException
	{
		AuditReactive.strict.commit();
		x.marshal(null, IOTestTools.getTempFileOutputStream());
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void marshal_Writer() throws InterruptedException, IOException, XAException
	{
		AuditReactive.strict.commit();
		x.marshal(null, IOTestTools.getTempFileWriter());
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void marshal_URL() throws InterruptedException, IOException, XAException
	{
		AuditReactive.strict.commit();
		x.marshal(null, IOTestTools.getTempFile().toURI().toURL());
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void unmarshal_File() throws InterruptedException, IOException, XAException
	{
		AuditReactive.strict.commit();
		x.unmarshal(IOTestTools.getTempFile(), null);
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void unmarshal_InputStream() throws InterruptedException, IOException, XAException
	{
		AuditReactive.strict.commit();
		x.unmarshal(IOTestTools.getTempFile(), null);
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void unmarshal_Reader() throws InterruptedException, IOException, XAException
	{
		AuditReactive.strict.commit();
		x.unmarshal(IOTestTools.getTempFileReader(), null);
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void unmarshal_URL() throws InterruptedException, IOException, XAException
	{
		AuditReactive.strict.commit();
		x.unmarshal(IOTestTools.getTempFile().toURI().toURL(), null);
	}


}
