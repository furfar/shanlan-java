
package com.shanlan.basic.application.impl;

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

import com.shanlan.basic.application.DistrictApplication;
import com.shanlan.basic.application.dto.DistrictDTO;
import com.shanlan.basic.core.domain.District;

@Named
@Transactional
public class DistrictApplicationImpl implements DistrictApplication {


	private QueryChannelService queryChannel;

    private QueryChannelService getQueryChannelService(){
       if(queryChannel==null){
          queryChannel = InstanceFactory.getInstance(QueryChannelService.class,"queryChannel");
       }
     return queryChannel;
    }
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DistrictDTO getDistrict(Integer id) {
		District district = District.load(District.class, id);
		DistrictDTO districtDTO = new DistrictDTO();
		// 将domain转成VO
		try {
			BeanUtils.copyProperties(districtDTO, district);
		} catch (Exception e) {
			e.printStackTrace();
		}
		districtDTO.setId((Integer)district.getId());
		return districtDTO;
	}
	
	public DistrictDTO saveDistrict(DistrictDTO districtDTO) {
		District district = new District();
		try {
        	BeanUtils.copyProperties(district, districtDTO);
        } catch (Exception e) {
        	e.printStackTrace();
        }
		district.save();
		districtDTO.setId((Integer)district.getId());
		return districtDTO;
	}
	
	public void updateDistrict(DistrictDTO districtDTO) {
		District district = District.get(District.class, districtDTO.getId());
		// 设置要更新的值
		try {
			BeanUtils.copyProperties(district, districtDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removeDistrict(Integer id) {
		this.removeDistricts(new Integer[] { id });
	}
	
	public void removeDistricts(Integer[] ids) {
		for (int i = 0; i < ids.length; i++) {
			District district = District.load(District.class, ids[i]);
			district.remove();
		}
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DistrictDTO> findAllDistrict() {
		List<DistrictDTO> list = new ArrayList<DistrictDTO>();
		List<District> all = District.findAll(District.class);
		for (District district : all) {
			DistrictDTO districtDTO = new DistrictDTO();
			// 将domain转成VO
			try {
				BeanUtils.copyProperties(districtDTO, district);
			} catch (Exception e) {
				e.printStackTrace();
			}
			list.add(districtDTO);
		}
		return list;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Page<DistrictDTO> pageQueryDistrict(DistrictDTO queryVo, int currentPage, int pageSize) {
		List<DistrictDTO> result = new ArrayList<DistrictDTO>();
		List<Object> conditionVals = new ArrayList<Object>();
	   	StringBuilder jpql = new StringBuilder("select _district from District _district   where 1=1 ");
	
	
	   	if (queryVo.getName() != null && !"".equals(queryVo.getName())) {
	   		jpql.append(" and _district.name like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getName()));
	   	}		
	
	   	if (queryVo.getLevel() != null) {
		   	jpql.append(" and _district.level is ?");
		   	conditionVals.add(queryVo.getLevel());
	   	}	
	   	if (queryVo.getUpid() != null) {
	   		jpql.append(" and _district.upid=?");
	   		conditionVals.add(queryVo.getUpid());
	   	}	
	
        Page<District> pages = getQueryChannelService().createJpqlQuery(jpql.toString()).setParameters(conditionVals).setPage(currentPage, pageSize).pagedList();
        for (District district : pages.getData()) {
            DistrictDTO districtDTO = new DistrictDTO();
            
             // 将domain转成VO
            try {
            	BeanUtils.copyProperties(districtDTO, district);
            } catch (Exception e) {
            	e.printStackTrace();
            } 
            
                                                               result.add(districtDTO);
        }
        return new Page<DistrictDTO>(pages.getStart(), pages.getResultCount(), pageSize, result);
	}
	
	
}
