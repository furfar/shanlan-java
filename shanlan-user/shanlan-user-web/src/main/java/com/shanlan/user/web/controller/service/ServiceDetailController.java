
package com.shanlan.user.web.controller.service;

import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;

import com.shanlan.opf.service.Service;
import com.shanlan.opf.service.ServiceDetail;
import com.shanlan.user.application.service.ServiceApplication;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.dayatang.querychannel.Page;
import com.shanlan.user.application.service.ServiceDetailApplication;
import com.shanlan.user.application.dto.*;

@Controller
@RequestMapping("/ServiceDetail")
public class ServiceDetailController {
		
	@Inject
	private ServiceDetailApplication serviceDetailApplication;
    @Inject
    private ServiceApplication serviceApplication;
	@ResponseBody
	@RequestMapping("/add")
	public Map<String, Object> add(ServiceDetailDTO serviceDetailDTO) {
		Map<String, Object> result = new HashMap<String, Object>();

        ServiceDetail serviceDetail = new ServiceDetail();
        Service service=new Service();
        ServiceDTO serviceDTO=new ServiceDTO();
        try {
            BeanUtils.copyProperties(service, serviceDetailDTO);
            BeanUtils.copyProperties(serviceDetail, serviceDetailDTO);
            BeanUtils.copyProperties(serviceDTO,serviceDetailDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        serviceApplication.saveService(serviceDTO);
        Page<ServiceDTO> serviceInserted=serviceApplication.pageQueryService(serviceDTO,0,10);
        if (serviceInserted!=null && serviceInserted.getData()!=null && serviceInserted.getData().size()==1){
            serviceDetailDTO.setServiceId(serviceInserted.getData().get(0).getId());
            serviceDetailApplication.saveServiceDetail(serviceDetailDTO);
//            serviceDetailDTO.setId((Integer)serviceDetail.getId());
        }
		result.put("result", "success");
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public Map<String, Object> update(ServiceDetailDTO serviceDetailDTO) {
		Map<String, Object> result = new HashMap<String, Object>();
		serviceDetailApplication.updateServiceDetail(serviceDetailDTO);
		result.put("result", "success");
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/pageJson")
	public Page pageJson(ServiceDetailDTO serviceDetailDTO, @RequestParam int page, @RequestParam int pagesize) {
		Page<ServiceDetailDTO> all = serviceDetailApplication.pageQueryServiceDetail(serviceDetailDTO, page, pagesize);
		return all;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Map<String, Object> delete(@RequestParam String ids) {
		Map<String, Object> result = new HashMap<String, Object>();
		String[] value = ids.split(",");
        Integer[] idArrs = new Integer[value.length];
        for (int i = 0; i < value.length; i ++) {
        	
        	        								idArrs[i] = Integer.parseInt(value[i]);
			        	
        }
        serviceDetailApplication.removeServiceDetails(idArrs);
		result.put("result", "success");
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/get/{id}")
	public Map<String, Object> get(@PathVariable Integer id) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", serviceDetailApplication.getServiceDetail(id));
		return result;
	}
	
		
}
