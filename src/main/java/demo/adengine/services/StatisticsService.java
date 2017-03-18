package demo.adengine.services;

/**
 * Service to work with banner statistics.
 * 
 * @author Newman
 *
 */
public interface StatisticsService {

	/**
	 * Loads statistics.
	 */
	public void loadStatistics();
	
	/**
	 * Increase counter of usages for banner.
	 * 
	 * @param bannerId to increase counter
	 */
	public void incCounter(Integer bannerId);
	
	/**
	 * Persists statistics into DB.
	 */
	public void save();

}