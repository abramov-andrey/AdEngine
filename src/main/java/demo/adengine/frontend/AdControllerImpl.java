package demo.adengine.frontend;

import demo.adengine.domain.Banner;
import demo.adengine.services.AdService;
import demo.adengine.services.RequestParsed;
import demo.adengine.services.StatisticsService;
import demo.adengine.services.TestDataService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Newman
 * Date: 15.09.11
 * Time: 20:41
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class AdControllerImpl {

	@Autowired
	private AdService adService;

	@Autowired
	public void setStatisticsService(StatisticsService statisticsService) {
		statisticsService.loadStatistics();
	}

	@Autowired
	public void setTestDataService(TestDataService testDataService) {
		testDataService.createTestData();
	}

	@RequestMapping("/*")
	public void doWork(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String reqParams = request.getPathInfo();
		if (reqParams != null) {
			reqParams = reqParams.substring(1);
		} else {
			processError(response);
			return;
		}
		RequestParsed requestParsed = parseRequest(reqParams);
		if (requestParsed == null)
			processError(response);
		else
			processRequest(requestParsed, response);
	}

	public static RequestParsed parseRequest(String requestStr) {
		RequestParsed requestParsed = new RequestParsed();
		int index = requestStr.indexOf("x");
		try {
			if (index >= 0) {
				requestParsed.width = Integer.parseInt(requestStr.substring(0, index));
				requestParsed.height = Integer.parseInt(requestStr.substring(index + 1));
			} else
				requestParsed.id = Integer.parseInt(requestStr);
		} catch (NumberFormatException e) {
			return null;
		}
		return requestParsed;
	}

	private void processRequest(RequestParsed requestParsed, HttpServletResponse response) throws IOException {
		Banner banner;
		if (requestParsed.id == null) {
			banner = loadBySize(requestParsed);
		} else {
			banner = loadById(requestParsed);
		}

		if (banner != null) {
			response.setContentType("image/gif");
			byte[] content = banner.getImage();
			response.getOutputStream().write(content);
		} else {
			processError(response);
		}
	}

	private Banner loadById(RequestParsed requestParsed) {
		return adService.getImageById(requestParsed.id);
	}

	private Banner loadBySize(RequestParsed requestParsed) {
		return adService.getImageBySize(requestParsed.width, requestParsed.height);
	}

	private void processError(HttpServletResponse resp) throws IOException {
		// Process exception
		File file = ResourceUtils.getFile("classpath:404.html");
		resp.setContentType("text/html");
		resp.getOutputStream().write(FileUtils.readFileToByteArray(file));
	}

}
