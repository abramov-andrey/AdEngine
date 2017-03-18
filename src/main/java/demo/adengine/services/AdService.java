package demo.adengine.services;

import demo.adengine.domain.Banner;
import demo.adengine.domain.BannerSize;

/**
 * 
 * @author Newman
 *
 */
public interface AdService {

	/**
	 * Getting Banner object by Id
	 * @param id
	 * @return
	 */
	public Banner getImageById(int id);

	/**
	 * Getting Banner object by size
	 * @param width
	 * @param height
	 * @return
	 */
	public Banner getImageBySize(int width, int height);
	
	/**
	 * Getting BannerSize object by width & height
	 *
	 * @param width
	 * @param height
	 * @return
	 */
	public BannerSize getBannerSize(int width, int height);

}