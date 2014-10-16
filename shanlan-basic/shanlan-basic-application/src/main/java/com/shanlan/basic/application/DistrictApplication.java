
package com.shanlan.basic.application;

import java.util.List;

import org.dayatang.querychannel.Page;

import com.shanlan.basic.application.dto.DistrictDTO;

public interface DistrictApplication {

	public DistrictDTO getDistrict(Integer id);
	
	public DistrictDTO saveDistrict(DistrictDTO district);
	
	public void updateDistrict(DistrictDTO district);
	
	public void removeDistrict(Integer id);
	
	public void removeDistricts(Integer[] ids);
	
	public List<DistrictDTO> findAllDistrict();
	
	public Page<DistrictDTO> pageQueryDistrict(DistrictDTO district, int currentPage, int pageSize);
	

}

