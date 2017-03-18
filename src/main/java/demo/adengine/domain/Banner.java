package demo.adengine.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

/**
 * 
 * @author Newman
 *
 */
@Entity
@NamedQuery(name = Banner.RandBySizeQuery, query = "FROM Banner WHERE bannerSize_Id = ?")
public class Banner extends BaseDomainObject {

	private String name;
	private byte[] image;
	private BannerSize bannerSize;
	public static final String RandBySizeQuery = "BannerSize.RandBySize";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Lob
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	public BannerSize getBannerSize() {
		return bannerSize;
	}

	public void setBannerSize(BannerSize bannerSize) {
		this.bannerSize = bannerSize;
	}
}
