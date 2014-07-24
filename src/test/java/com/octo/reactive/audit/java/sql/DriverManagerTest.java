package com.octo.reactive.audit.java.sql;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by pprados on 19/05/2014.
 */
public class DriverManagerTest
{
	@Test(expected = NetworkAuditReactiveException.class)
	public void getConnection() throws InterruptedException, ClassNotFoundException, SQLException
	{
		AuditReactive.strict.commit();
		DriverManager.getConnection("jdbc:derby:derbyDB;create=true");
	}
}
