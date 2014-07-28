package com.octo.reactive.audit;

class DetectionUtils
{

	public static String getFullJavaVersion()
	{
		String runtime = System.getProperty("java.version");
		if (runtime == null)
		{
			return System.getProperty("java.runtime.version");
		}
		return runtime;
	}

	public static double getJavaRuntimeVersion()
	{
		String runtime = getFullJavaVersion();
		return getJavaRuntimeVersion(runtime);
	}

	public static double getJavaRuntimeVersion(String runtime)
	{
		int majorVersionPos = runtime.indexOf('.');
		int minorVersionPos = runtime.indexOf('.', majorVersionPos + 1);
		if (minorVersionPos == -1)
		{
			minorVersionPos = runtime.length();
		}
		return Double.parseDouble(runtime.substring(0, minorVersionPos));
	}

}
