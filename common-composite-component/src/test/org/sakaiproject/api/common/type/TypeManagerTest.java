/**********************************************************************************
 * $URL$
 * $Id$
 **********************************************************************************
 *
 * Copyright (c) 2003, 2004 The Regents of the University of Michigan, Trustees of Indiana University,
 *                  Board of Trustees of the Leland Stanford, Jr., University, and The MIT Corporation
 * 
 * Licensed under the Educational Community License Version 1.0 (the "License");
 * By obtaining, using and/or copying this Original Work, you agree that you have read,
 * understand, and will comply with the terms and conditions of the Educational Community License.
 * You may obtain a copy of the License at:
 * 
 *      http://cvs.sakaiproject.org/licenses/license_1_0.html
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
 * AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING 
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 **********************************************************************************/

package org.sakaiproject.api.common.type;

import org.sakaiproject.component.junit.spring.ApplicationContextBaseTest;

public class TypeManagerTest extends ApplicationContextBaseTest
{
  private TypeManager typeManager; // dep inj

  public TypeManagerTest()
  {
    super();
    init();
  }

  public TypeManagerTest(String name)
  {
    super(name);
    init();
  }

  private void init()
  {
    typeManager = (TypeManager) getApplicationContext().getBean(
        TypeManager.class.getName());
  }

  protected void setUp() throws Exception
  {
    super.setUp();
  }

  protected void tearDown() throws Exception
  {
    super.tearDown();
  }

  public void testCreateType()
  {
    final String authority = "authority1";
    final String domain = "domain1";
    final String keyword = "keyword1";
    final String displayName = "displayName1";
    final String description = "description1";

    Type type1 = typeManager.createType(authority, domain, keyword,
        displayName, description);
    assertTrue(type1 != null);
    assertTrue(type1.getAuthority().equals(authority));
    assertTrue(type1.getDomain().equals(domain));
    assertTrue(type1.getKeyword().equals(keyword));
    assertTrue(type1.getDisplayName().equals(displayName));
    assertTrue(type1.getDescription().equals(description));
  }

  /*
   * Class under test for Type getType(String)
   */
  public void testGetTypeString()
  {
    final String authority = "authority2";
    final String domain = "domain2";
    final String keyword = "keyword2";
    final String displayName = "displayName2";
    final String description = "description2";

    Type type1 = typeManager.createType(authority, domain, keyword,
        displayName, description);
    assertTrue(type1 != null);
    String uuid = type1.getUuid();
    Type type2 = typeManager.getType(uuid);
    assertTrue(type2 != null);
    assertTrue(type1.equals(type2));
  }

  /*
   * Class under test for Type getType(String, String, String)
   */
  public void testGetTypeStringStringString()
  {
    final String authority = "authority3";
    final String domain = "domain3";
    final String keyword = "keyword3";
    final String displayName = "displayName3";
    final String description = "description3";

    Type type1 = typeManager.createType(authority, domain, keyword,
        displayName, description);
    assertTrue(type1 != null);
    Type type2 = typeManager.getType(authority, domain, keyword);
    assertTrue(type2 != null);
    assertTrue(type1.equals(type2));
  }

  public void testSaveType()
  {
    String authority = "authority4";
    String domain = "domain4";
    String keyword = "keyword4";
    String displayName = "displayName4";
    String description = "description4";

    Type type1 = typeManager.createType(authority, domain, keyword,
        displayName, description);
    assertTrue(type1 != null);
    String uuid = type1.getUuid();

    authority = authority + "-mod";
    domain = domain + "-mod";
    keyword = keyword + "-mod";
    displayName = displayName + "-mod";
    description = description + "-mod";

    type1.setAuthority(authority);
    type1.setDomain(domain);
    type1.setKeyword(keyword);
    type1.setDisplayName(displayName);
    type1.setDescription(description);
    typeManager.saveType(type1);

    Type type2 = typeManager.getType(uuid);
    assertTrue(type2 != null);
    assertTrue(type1.equals(type2));
    assertTrue(type2.getAuthority().equals(authority));
    assertTrue(type2.getDomain().equals(domain));
    assertTrue(type2.getKeyword().equals(keyword));
    assertTrue(type2.getDisplayName().equals(displayName));
    assertTrue(type2.getDescription().equals(description));
  }

  public void testDeleteType()
  {
    final String authority = "authority5";
    final String domain = "domain5";
    final String keyword = "keyword5";
    final String displayName = "displayName5";
    final String description = "description5";

    Type type1 = typeManager.createType(authority, domain, keyword,
        displayName, description);
    assertTrue(type1 != null);
    String uuid = type1.getUuid();

    Type type2 = typeManager.getType(uuid);
    assertTrue(type2 != null);
    assertTrue(type1.equals(type2));

    try
    {
      typeManager.deleteType(type1);
      // if no exception is thrown, let's verify it was indeed deleted
      Type type3 = typeManager.getType(uuid);
      assertTrue(type3 == null);
    }
    catch (UnsupportedOperationException e)
    {
      assertTrue("Callers must support this behavior", e != null);
    }
  }

}



