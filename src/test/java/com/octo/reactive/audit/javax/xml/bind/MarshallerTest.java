package com.octo.reactive.audit.javax.xml.bind;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.IOTestTools;
import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.FileAuditReactiveException;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class MarshallerTest
{
	private final Marshaller x = (Marshaller) TestTools.createProxy(Marshaller.class);

	@Test(expected = FileAuditReactiveException.class)
	public void marshal_File() throws JAXBException
	{
		AuditReactive.strict.commit();
		x.marshal(null, IOTestTools.getTempFile());
	}

	@Test(expected = FileAuditReactiveException.class)
	public void marshal_OutputStream() throws JAXBException
	{
		AuditReactive.strict.commit();
		x.marshal(null, IOTestTools.getTempFileOutputStream());
	}

	@Test(expected = FileAuditReactiveException.class)
	public void marshal_Writer() throws JAXBException
	{
		AuditReactive.strict.commit();
		x.marshal(null, IOTestTools.getTempFileWriter());
	}

}
