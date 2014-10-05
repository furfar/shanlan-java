
package com.shanlan.user.application.impl.service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.apache.commons.beanutils.BeanUtils;
import org.dayatang.domain.InstanceFactory;
import org.dayatang.querychannel.Page;
import org.dayatang.querychannel.QueryChannelService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shanlan.opf.service.Service;
import com.shanlan.user.application.dto.ServiceDTO;
import com.shanlan.user.application.service.ServiceApplication;

@Named
@Transactional
public class ServiceApplicationImpl implements ServiceApplication {

    private QueryChannelService queryChannel;

    private QueryChannelService getQueryChannelService() {
        if (queryChannel == null) {
            queryChannel = InstanceFactory.getInstance(QueryChannelService.class, "queryChannel");
        }
        return queryChannel;
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public ServiceDTO getService(Integer id) {
        Service service = Service.load(Service.class, id);
        ServiceDTO serviceDTO = new ServiceDTO();
        // 将domain转成VO
        try {
            BeanUtils.copyProperties(serviceDTO, service);
        } catch (Exception e) {
            e.printStackTrace();
        }
        serviceDTO.setId((Integer) service.getId());
        return serviceDTO;
    }

    public ServiceDTO saveService(ServiceDTO serviceDTO) {
        Service service = new Service();
        try {
            BeanUtils.copyProperties(service, serviceDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        service.save();
        serviceDTO.setId((Integer) service.getId());
        return serviceDTO;
    }

    public void updateService(ServiceDTO serviceDTO) {
        Service service = Service.get(Service.class, serviceDTO.getId());
        // 设置要更新的值
        try {
            BeanUtils.copyProperties(service, serviceDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeService(Integer id) {
        this.removeServices(new Integer[]{id});
    }

    public void removeServices(Integer[] ids) {
        for (int i = 0; i < ids.length; i++) {
            Service service = Service.load(Service.class, ids[i]);
            service.remove();
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<ServiceDTO> findAllService() {
        List<ServiceDTO> list = new ArrayList<ServiceDTO>();
        List<Service> all = Service.findAll(Service.class);
        for (Service service : all) {
            ServiceDTO serviceDTO = new ServiceDTO();
            // 将domain转成VO
            try {
                BeanUtils.copyProperties(serviceDTO, service);
            } catch (Exception e) {
                e.printStackTrace();
            }
            list.add(serviceDTO);
        }
        return list;
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<ServiceDTO> pageQueryService(ServiceDTO queryVo, int currentPage, int pageSize) {
        List<ServiceDTO> result = new ArrayList<ServiceDTO>();
        List<Object> conditionVals = new ArrayList<Object>();
        StringBuilder jpql = new StringBuilder("select _service from Service _service   where 1=1 ");

        if (queryVo.getServiceName() != null && !"".equals(queryVo.getServiceName())) {
            jpql.append(" and _service.serviceName like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getServiceName()));
        }

        if (queryVo.getServiceVersion() != null && !"".equals(queryVo.getServiceVersion())) {
            jpql.append(" and _service.serviceVersion like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getServiceVersion()));
        }

        if (queryVo.getUrl() != null && !"".equals(queryVo.getUrl())) {
            jpql.append(" and _service.url like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getUrl()));
        }

        if (queryVo.getEnable() != null) {
            jpql.append(" and _service.enable = ?");
            conditionVals.add(queryVo.getEnable());
        }

        if (queryVo.getType() != null && !"".equals(queryVo.getType())) {
            jpql.append(" and _service.type like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getType()));
        }

        if (queryVo.getServiceGroup() != null && !"".equals(queryVo.getServiceGroup())) {
            jpql.append(" and _service.serviceGroup like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getServiceGroup()));
        }
        if (queryVo.getIsLocal() != null) {
            jpql.append(" and _service.isLocal=?");
            conditionVals.add(queryVo.getIsLocal());
        }

        Page<Service> pages = getQueryChannelService().createJpqlQuery(jpql.toString()).setParameters(conditionVals).setPage(currentPage, pageSize).pagedList();
        List<Service> services = pages.getData();
        for (Service service : services) {
            ServiceDTO serviceDTO = new ServiceDTO();

            // 将domain转成VO
            try {
                BeanUtils.copyProperties(serviceDTO, service);
            } catch (Exception e) {
                e.printStackTrace();
            }

            result.add(serviceDTO);
        }
        return new Page<ServiceDTO>(pages.getStart(), pages.getResultCount(), pageSize, result);
    }


}
