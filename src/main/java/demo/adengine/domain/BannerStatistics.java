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
@Table (name="BannerStatistics", uniqueConstraints = @UniqueConstraint(name="ix_bannerId",columnNames={"bannerId"}))
public class BannerStatistics extends BaseDomainObject {

	private Integer bannerId;
	
	private Long callsNumber = 0L;
	
	public BannerStatistics() {
	}
	
	public BannerStatistics(Integer bannerId) {
		this.bannerId = bannerId;
	}

	public Integer getBannerId() {
		return bannerId;
	}

	public void setBannerId(Integer bannerId) {
		this.bannerId = bannerId;
	}

	public Long getCallsNumber() {
		return callsNumber;
	}

	public void setCallsNumber(Long callsNumber) {
		this.callsNumber = callsNumber;
	}
	
	public void incCallsNumber() {
		callsNumber++;
	}

}
