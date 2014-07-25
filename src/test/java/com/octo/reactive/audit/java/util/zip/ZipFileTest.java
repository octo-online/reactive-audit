package com.octo.reactive.audit.java.util.zip;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.IOTestTools;
import com.octo.reactive.audit.lib.FileAuditReactiveException;
import org.junit.Test;

import java.io.IOException;
import java.util.zip.ZipFile;

/**
 * Created by pprados on 19/05/2014.
 */
public class ZipFileTest
{
	@Test(expected = FileAuditReactiveException.class)
	public void New() throws IOException
	{
		AuditReactive.strict.commit();
		new ZipFile(IOTestTools.getTempFile());
	}

}
