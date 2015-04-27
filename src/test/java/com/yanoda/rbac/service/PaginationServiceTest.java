package com.yanoda.rbac.service;

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

import com.yanoda.rbac.domain.Page;
import com.yanoda.rbac.service.PaginationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class PaginationServiceTest {

	@Autowired
	private PaginationService pageService;
	
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
	public void testPaginationUtil() {
		String condition="WHERE create_user_id = 3 ORDER BY id DESC";
		Page page = this.pageService.PaginationUtil(1, 10, "workflow", condition);
		assertTrue(page.getCountPage() == 3);
	}

}
