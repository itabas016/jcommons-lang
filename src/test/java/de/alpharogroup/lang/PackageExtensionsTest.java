/**
 * The MIT License
 *
 * Copyright (C) 2015 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package de.alpharogroup.lang;

import java.util.Set;

import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test class for the class {@link de.alpharogroup.lang.PackageExtensions}.
 * 
 * @version 1.0
 * @author Asterios Raptis
 */
public class PackageExtensionsTest
{

	/** The result. */
	private boolean result;

	/**
	 * Sets the up method.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@BeforeMethod
	public void setUp() throws Exception
	{
	}

	/**
	 * Tear down method.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@AfterMethod
	public void tearDown() throws Exception
	{
	}

	/**
	 * Test method for.
	 *
	 * {@link de.alpharogroup.lang.PackageExtensions#getPackagePath(java.lang.Object)}.
	 */
	@Test
	public void testGetPackagePath()
	{
		final String expected = "de/alpharogroup/lang/";
		final String packagePath = PackageExtensions.getPackagePath(this);
		this.result = expected.equals(packagePath);
		AssertJUnit.assertTrue("", this.result);

	}

	/**
	 * Test method for.
	 *
	 * {@link de.alpharogroup.lang.PackageExtensions#getPackagePathWithSlash(java.lang.Object)}.
	 */
	@Test
	public void testGetPackagePathWithSlash()
	{
		final String expected = "/de/alpharogroup/lang/";
		final String packagePath = PackageExtensions.getPackagePathWithSlash(this);
		this.result = expected.equals(packagePath);
		AssertJUnit.assertTrue("", this.result);
	}

	@Test
	public void testScanNames() throws Exception
	{
		Set<String> list = PackageExtensions.scanClassNames("de.alpharogroup.lang", false, true);
		AssertJUnit.assertTrue("Result should contain classes from same package.",
			list.contains("de.alpharogroup.lang.PackageExtensionsTest"));
		AssertJUnit.assertFalse("Result should not contain classes from subpackages.",
			list.contains("de.alpharogroup.lang.sub.SubPackageClass"));

		list = PackageExtensions.scanClassNames("de.alpharogroup.lang", true, true);
		AssertJUnit.assertTrue("Result should contain classes from subpackages.",
			list.contains("de.alpharogroup.lang.sub.SubPackageClass"));

		list = PackageExtensions.scanClassNames("org.apache.commons.beanutils", false, true);
		AssertJUnit.assertTrue("Result should contain classes from same package.",
			list.contains("org.apache.commons.beanutils.BeanMap"));
		AssertJUnit.assertFalse("Result should not contain classes from subpackages.",
			list.contains("org.apache.commons.beanutils.locale.BaseLocaleConverter"));
		AssertJUnit.assertFalse("Result should not contain classes from subpackages.", list
			.contains("org.apache.commons.beanutils.locale.converters.BigDecimalLocaleConverter"));

		list = PackageExtensions.scanClassNames("org.apache.commons.beanutils.locale", true, true);
		AssertJUnit.assertTrue("Result should contain classes from same package.",
			list.contains("org.apache.commons.beanutils.locale.BaseLocaleConverter"));
		AssertJUnit.assertTrue("Result should contain classes from subpackages.", list
			.contains("org.apache.commons.beanutils.locale.converters.BigDecimalLocaleConverter"));

		// ......
		list = PackageExtensions.scanClassNames("de.alpharogroup.lang", false, false);
		AssertJUnit.assertTrue("Result should contain classes from same package.",
			list.contains("PackageExtensionsTest"));
		AssertJUnit.assertFalse("Result should not contain classes from subpackages.",
			list.contains("SubPackageClass"));

		list = PackageExtensions.scanClassNames("de.alpharogroup.lang", true, false);
		AssertJUnit.assertTrue("Result should contain classes from subpackages.",
			list.contains("SubPackageClass"));

		list = PackageExtensions.scanClassNames("org.apache.commons.beanutils", false, false);
		AssertJUnit.assertTrue("Result should contain classes from same package.",
			list.contains("BeanMap"));
		AssertJUnit.assertFalse("Result should not contain classes from subpackages.",
			list.contains("BaseLocaleConverter"));
		AssertJUnit.assertFalse("Result should not contain classes from subpackages.",
			list.contains("BigDecimalLocaleConverter"));

		list = PackageExtensions.scanClassNames("org.apache.commons.beanutils.locale", true, false);
		AssertJUnit.assertTrue("Result should contain classes from same package.",
			list.contains("BaseLocaleConverter"));
		AssertJUnit.assertTrue("Result should contain classes from subpackages.",
			list.contains("BigDecimalLocaleConverter"));

		list = PackageExtensions.scanClassNames("de.alpharogroup", true, true);
		for (final String string : list)
		{
			System.out.println("<class name=\"" + string + "\"/>");
		}

	}


}
