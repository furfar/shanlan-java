
package com.shanlan.user.application.service;

import java.util.List;
import org.dayatang.querychannel.Page;
import com.shanlan.user.application.dto.*;

public interface ServiceDetailApplication {

	public ServiceDetailDTO getServiceDetail(Integer id);
	
	public ServiceDetailDTO saveServiceDetail(ServiceDetailDTO serviceDetail);
	
	public void updateServiceDetail(ServiceDetailDTO serviceDetail);
	
	public void removeServiceDetail(Integer id);
	
	public void removeServiceDetails(Integer[] ids);
	
	public List<ServiceDetailDTO> findAllServiceDetail();
	
	public Page<ServiceDetailDTO> pageQueryServiceDetail(ServiceDetailDTO serviceDetail, int currentPage, int pageSize);
	

}

