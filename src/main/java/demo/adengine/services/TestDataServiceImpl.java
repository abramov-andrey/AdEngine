package demo.adengine.services;

import demo.adengine.domain.Banner;
import demo.adengine.domain.BannerSize;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TestDataServiceImpl extends HibernateTemplate implements TestDataService {
	
	public void createTestData() {
		insertBannerSizes();
		insertBanners();
	}
	
	@Autowired
	private AdService adService;

	private void insertBannerSizes() {
		insertBannerSize(new BannerSize(100, 100));
		insertBannerSize(new BannerSize(100, 20));
		insertBannerSize(new BannerSize(80, 200));
		insertBannerSize(new BannerSize(80, 201));
	}

	private void insertBannerSize(BannerSize bannerSize) {
		List list = findByExample(bannerSize);
		if (list.isEmpty()) {
			save(bannerSize);
		}
	}

	public AdService getAdService() {
		return adService;
	}

	public void setAdService(AdService adService) {
		this.adService = adService;
	}

	private void insertBanners() {
		insertBanner("100x100_A.GIF", 100, 100);
		insertBanner("100x100_B.GIF", 100, 100);
		insertBanner("100x100_C.GIF", 100, 100);
		insertBanner("100x20.GIF", 100, 20);
		insertBanner("80x200.GIF", 80, 200);
	}	
	
	private void insertBanner(String filename, int width, int height) {
		try {
			File file = ResourceUtils.getFile("classpath:" + filename);
			Banner banner = new Banner();
			banner.setName(filename);
			List List = findByExample(banner);
			if (!List.isEmpty())
				return;
			BannerSize bannerSize = adService.getBannerSize(width, height);
			banner.setBannerSize(bannerSize);
			banner.setImage(FileUtils.readFileToByteArray(file));
			save(banner);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
