package demo.adengine.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 
 * @author Newman
 *
 */
@Entity
@Table (name="BannerSize", uniqueConstraints = @UniqueConstraint(name="ix_BannerSize",columnNames={"width", "height"}))
public class BannerSize extends BaseDomainObject {

	private int width;

	private int height;

	public BannerSize() {
	}

	public BannerSize(int width, int height) {
		super();
		this.width = width;
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
