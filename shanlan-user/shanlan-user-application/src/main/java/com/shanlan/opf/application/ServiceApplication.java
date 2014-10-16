
package com.shanlan.opf.application;

import java.util.List;

import org.dayatang.querychannel.Page;

import com.shanlan.opf.application.dto.*;

public interface ServiceApplication {

	public ServiceDTO getService(Integer id);
	
	public ServiceDTO saveService(ServiceDTO service);
	
	public void updateService(ServiceDTO service);
	
	public void removeService(Integer id);
	
	public void removeServices(Integer[] ids);
	
	public List<ServiceDTO> findAllService();
	
	public Page<ServiceDTO> pageQueryService(ServiceDTO service, int currentPage, int pageSize);
	

}
