package com.yanoda.rbac.mapper;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class RbacMapperTest {

	@Autowired
	private RbacMapper rbacMapper;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	
	@Test
	public void testDoRbacActionInit() {
		System.out.println(this.rbacMapper.doRbacActionInit().get(1).getId());
		System.out.println(this.rbacMapper.doRbacActionInit().get(0).getPermissionList().size());
		assertTrue(this.rbacMapper.doRbacActionInit().get(0).getPermissionList().size() == 1);
	}

}
