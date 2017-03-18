package demo.adengine.test;

import demo.adengine.domain.Banner;
import demo.adengine.frontend.AdControllerImpl;
import demo.adengine.services.AdService;
import demo.adengine.services.RequestParsed;
import demo.adengine.services.StatisticsService;
import demo.adengine.services.TestDataService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

@ContextConfiguration(locations = {"classpath:/applicationContext.xml"})
@TransactionConfiguration(transactionManager = "txManager")
public class AdServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private AdService adService;

	@Autowired
	TestDataService testDataService;

	@Autowired
	StatisticsService statisticsService;

	/**
	 * Test for method getImageBySize
	 */
	@Test
	public void testGetImageBySizeTest() {
		testDataService.createTestData();
		statisticsService.loadStatistics();
		Banner banner = adService.getImageBySize(100, 100);
		Assert.assertNotNull(banner);
	}

	@Test
	public void testGetImageBySizeTest1() {
		testDataService.createTestData();
		statisticsService.loadStatistics();
		Banner banner = adService.getImageBySize(80, 200);
		Assert.assertNotNull(banner);
	}

	@Test
	public void testParseNull() {
		RequestParsed requestParsed = AdControllerImpl.parseRequest("100xa");
		Assert.assertNull(requestParsed);
	}

	@Test
	public void testParseNotNull() {
		RequestParsed requestParsed = AdControllerImpl.parseRequest("100x101");
		Assert.assertNotNull(requestParsed);
	}

}
