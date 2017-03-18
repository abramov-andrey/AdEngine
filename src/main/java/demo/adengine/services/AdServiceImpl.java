package demo.adengine.services;

import demo.adengine.domain.Banner;
import demo.adengine.domain.BannerSize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;

import java.util.List;
import java.util.Random;

public class AdServiceImpl extends HibernateTemplate implements AdService {

	@Autowired
	private StatisticsService statisticsService;

	@Override
	public Banner getImageById(int id) {
		final Banner banner = getSessionFactory().getCurrentSession().get(Banner.class, id);
		if (banner != null) {
			statisticsService.incCounter(id);
		}
		return banner;
	}

	
	@Override
	public BannerSize getBannerSize(int width, int height) {
		List list = findByExample(new BannerSize(width, height));
		return list.isEmpty() ? null : (BannerSize) list.get(0);
	}

	@Override
	public Banner getImageBySize(int width, int height) {
		BannerSize bannerSize = getBannerSize(width, height);
		if (bannerSize == null) {
			return null;
		} else {
			final List<?> list = findByNamedQuery(Banner.RandBySizeQuery, bannerSize.getId());
			if (list.isEmpty()) {
				return null;
			}
			Random random = new Random();
			final Banner result = (Banner) list.get(random.nextInt(list.size()));
			statisticsService.incCounter(result.getId());
			return result;
		}
	}
}
