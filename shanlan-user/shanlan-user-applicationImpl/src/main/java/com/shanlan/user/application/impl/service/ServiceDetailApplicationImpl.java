
package com.shanlan.user.application.impl.service;

import java.util.List;
import java.util.ArrayList;
import java.text.MessageFormat;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import org.dayatang.domain.InstanceFactory;
import org.dayatang.querychannel.Page;
import org.dayatang.querychannel.QueryChannelService;
import com.shanlan.user.application.dto.*;
import com.shanlan.user.application.service.ServiceDetailApplication;
import com.shanlan.opf.service.*;

@Named
@Transactional
public class ServiceDetailApplicationImpl implements ServiceDetailApplication {


	private QueryChannelService queryChannel;

    private QueryChannelService getQueryChannelService(){
       if(queryChannel==null){
          queryChannel = InstanceFactory.getInstance(QueryChannelService.class,"queryChannel");
       }
     return queryChannel;
    }
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ServiceDetailDTO getServiceDetail(Integer id) {
		ServiceDetail serviceDetail = ServiceDetail.load(ServiceDetail.class, id);
		ServiceDetailDTO serviceDetailDTO = new ServiceDetailDTO();
		// 将domain转成VO
		try {
			BeanUtils.copyProperties(serviceDetailDTO, serviceDetail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		serviceDetailDTO.setId((Integer)serviceDetail.getId());
		return serviceDetailDTO;
	}
	
	public ServiceDetailDTO saveServiceDetail(ServiceDetailDTO serviceDetailDTO) {
		ServiceDetail serviceDetail = new ServiceDetail();
		try {
        	BeanUtils.copyProperties(serviceDetail, serviceDetailDTO);
        } catch (Exception e) {
        	e.printStackTrace();
        }
		serviceDetail.save();
		serviceDetailDTO.setId((Integer)serviceDetail.getId());
		return serviceDetailDTO;
	}
	
	public void updateServiceDetail(ServiceDetailDTO serviceDetailDTO) {
		ServiceDetail serviceDetail = ServiceDetail.get(ServiceDetail.class, serviceDetailDTO.getId());
		// 设置要更新的值
		try {
			BeanUtils.copyProperties(serviceDetail, serviceDetailDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removeServiceDetail(Integer id) {
		this.removeServiceDetails(new Integer[] { id });
	}
	
	public void removeServiceDetails(Integer[] ids) {
		for (int i = 0; i < ids.length; i++) {
			ServiceDetail serviceDetail = ServiceDetail.load(ServiceDetail.class, ids[i]);
			serviceDetail.remove();
		}
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ServiceDetailDTO> findAllServiceDetail() {
		List<ServiceDetailDTO> list = new ArrayList<ServiceDetailDTO>();
		List<ServiceDetail> all = ServiceDetail.findAll(ServiceDetail.class);
		for (ServiceDetail serviceDetail : all) {
			ServiceDetailDTO serviceDetailDTO = new ServiceDetailDTO();
			// 将domain转成VO
			try {
				BeanUtils.copyProperties(serviceDetailDTO, serviceDetail);
			} catch (Exception e) {
				e.printStackTrace();
			}
			list.add(serviceDetailDTO);
		}
		return list;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Page<ServiceDetailDTO> pageQueryServiceDetail(ServiceDetailDTO queryVo, int currentPage, int pageSize) {
		List<ServiceDetailDTO> result = new ArrayList<ServiceDetailDTO>();
		List<Object> conditionVals = new ArrayList<Object>();
	   	StringBuilder jpql = new StringBuilder("select _serviceDetail from ServiceDetail _serviceDetail   where 1=1 ");
	
	   	if (queryVo.getServiceId() != null) {
	   		jpql.append(" and _serviceDetail.serviceId=?");
	   		conditionVals.add(queryVo.getServiceId());
	   	}	
	
	
	   	if (queryVo.getScenario() != null && !"".equals(queryVo.getScenario())) {
	   		jpql.append(" and _serviceDetail.scenario like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getScenario()));
	   	}		
	
	   	if (queryVo.getBusinessParam() != null && !"".equals(queryVo.getBusinessParam())) {
	   		jpql.append(" and _serviceDetail.businessParam like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getBusinessParam()));
	   	}		
	
	   	if (queryVo.getReponse() != null && !"".equals(queryVo.getReponse())) {
	   		jpql.append(" and _serviceDetail.reponse like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getReponse()));
	   	}		
	
	   	if (queryVo.getErrorCode() != null && !"".equals(queryVo.getErrorCode())) {
	   		jpql.append(" and _serviceDetail.errorCode like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getErrorCode()));
	   	}		
	
	   	if (queryVo.getResponseSample() != null && !"".equals(queryVo.getResponseSample())) {
	   		jpql.append(" and _serviceDetail.responseSample like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getResponseSample()));
	   	}		
        Page<ServiceDetail> pages = getQueryChannelService().createJpqlQuery(jpql.toString()).setParameters(conditionVals).setPage(currentPage, pageSize).pagedList();
        for (ServiceDetail serviceDetail : pages.getData()) {
            ServiceDetailDTO serviceDetailDTO = new ServiceDetailDTO();
            
             // 将domain转成VO
            try {
            	BeanUtils.copyProperties(serviceDetailDTO, serviceDetail);
            } catch (Exception e) {
            	e.printStackTrace();
            } 
            
                                                                                                            result.add(serviceDetailDTO);
        }
        return new Page<ServiceDetailDTO>(pages.getStart(), pages.getResultCount(), pageSize, result);
	}
	
	
}
