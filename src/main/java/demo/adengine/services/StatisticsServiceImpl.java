package demo.adengine.services;

import demo.adengine.domain.BannerStatistics;
import org.springframework.orm.hibernate5.HibernateTemplate;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StatisticsServiceImpl extends HibernateTemplate implements StatisticsService {

	private Map<Integer, BannerStatistics> statisticsMap;
	private final Object lock = new Object();

	public StatisticsServiceImpl() {
		final Thread saveThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						save();
						Thread.sleep(5000);
					} catch (InterruptedException ignored) {
					}
				}
			}
		});
		saveThread.start();
	}

	@SuppressWarnings("unchecked")
	public synchronized void loadStatistics() {
		statisticsMap = new ConcurrentHashMap<>();
		List<BannerStatistics> list = loadAll(BannerStatistics.class);
		for (BannerStatistics bannerStatistics : list) {
			statisticsMap.put(bannerStatistics.getBannerId(), bannerStatistics);
		}
	}

	@Override
	public void incCounter(Integer bannerId) {
		BannerStatistics statistics = statisticsMap.get(bannerId);
		if (statistics == null) {
			statistics = new BannerStatistics(bannerId);
			statisticsMap.put(bannerId, statistics);
		}
		statistics.incCallsNumber();
	}

	@Override
	public void save() {
		synchronized (lock) {
			if (statisticsMap != null) {
				for (BannerStatistics bannerStatistics : statisticsMap.values()) {
					saveOrUpdate(bannerStatistics);
				}
			}
		}
	}

}
