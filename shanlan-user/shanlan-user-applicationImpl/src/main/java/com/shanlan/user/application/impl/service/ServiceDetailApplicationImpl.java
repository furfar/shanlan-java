
package com.shanlan.user.application.impl.service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import com.shanlan.user.application.dto.ServiceDTO;
import com.shanlan.user.application.service.ServiceApplication;
import org.apache.commons.beanutils.BeanUtils;
import org.dayatang.domain.InstanceFactory;
import org.dayatang.querychannel.Page;
import org.dayatang.querychannel.QueryChannelService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shanlan.opf.service.Service;
import com.shanlan.opf.service.ServiceDetail;
import com.shanlan.user.application.dto.ServiceDetailDTO;
import com.shanlan.user.application.service.ServiceDetailApplication;

@Named
@Transactional
public class ServiceDetailApplicationImpl implements ServiceDetailApplication {


	private QueryChannelService queryChannel;
    @Inject
    private ServiceApplication serviceApplication;

    private QueryChannelService getQueryChannelService(){
       if(queryChannel==null){
          queryChannel = InstanceFactory.getInstance(QueryChannelService.class,"queryChannel");
       }
     return queryChannel;
    }
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ServiceDetailDTO getServiceDetail(Integer id) {
        Service service=Service.load(Service.class,id);
		ServiceDetail serviceDetail = ServiceDetail.findByServiceId(service.getId());
		ServiceDetailDTO serviceDetailDTO = new ServiceDetailDTO();
		// 将domain转成VO
		try {
            BeanUtils.copyProperties(serviceDetailDTO,service);
			BeanUtils.copyProperties(serviceDetailDTO, serviceDetail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		serviceDetailDTO.setId(id);
		return serviceDetailDTO;
	}

	public ServiceDetailDTO saveServiceDetail(ServiceDetailDTO serviceDetailDTO) {
		ServiceDetail serviceDetail = new ServiceDetail();
//        Service service=new Service();
//        ServiceDTO serviceDTO=new ServiceDTO();
		try {
//            BeanUtils.copyProperties(service,serviceDetailDTO);
        	BeanUtils.copyProperties(serviceDetail, serviceDetailDTO);
//            BeanUtils.copyProperties(serviceDTO,serviceDetailDTO);
        } catch (Exception e) {
        	e.printStackTrace();
        }
//        service.save();
//        Page<ServiceDTO> serviceInserted=serviceApplication.pageQueryService(serviceDTO,1,10);
//        if (serviceInserted!=null && serviceInserted.getData()!=null && serviceInserted.getData().size()==1){
//            serviceDetail.setServiceId(serviceInserted.getData().get(0).getId());
//            serviceDetail.save();
//            serviceDetailDTO.setId((Integer)serviceDetail.getId());
//        }
        serviceDetail.save();
        serviceDetailDTO.setId((Integer)serviceDetail.getId());

//        Map<String,Object> keyValue=new HashMap<String,Object>();
//        keyValue.put("serviceName",service.getServiceName());
//        keyValue.put("serviceVersion",service.getServiceVersion());
//        keyValue.put("serviceGroup",service.getServiceGroup());
//        List<Service> insertServiceList= (List<Service>)Service.findByProperties(Service.class,keyValue);



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
	
	   	if (queryVo.getResponse() != null && !"".equals(queryVo.getResponse())) {
	   		jpql.append(" and _serviceDetail.response like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getResponse()));
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
