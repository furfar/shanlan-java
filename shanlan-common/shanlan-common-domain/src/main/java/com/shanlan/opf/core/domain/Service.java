package com.shanlan.opf.core.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.shanlan.common.constant.ConstantNumber;
import org.apache.commons.collections.CollectionUtils;
import org.openkoala.koala.commons.domain.KoalaLegacyEntity;

import com.shanlan.common.exception.sub.business.OPFBaseException;
import com.shanlan.common.exception.sub.business.RequestCheckingException;
import com.shanlan.common.exception.sub.business.ServiceDisableException;

/**
 * Auto Generated Entity
 *
 * @author Koala
 */
@Entity
@Table(name = "service")
public class Service extends KoalaLegacyEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "service_version")
    private String serviceVersion;

    @Column(name = "url")
    private String url;

    @Column(name = "enable")
    private Integer enable;

    @Column(name = "type")
    private String type;

    @Column(name = "service_group")
    private String serviceGroup;

    @Column(name = "is_local")
    private Integer isLocal;

    public void setId(int id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String name) {
        this.serviceName = name;
    }

    public String getServiceVersion() {
        return serviceVersion;
    }

    public void setServiceVersion(String serviceVersion) {
        this.serviceVersion = serviceVersion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getServiceGroup() {
        return serviceGroup;
    }

    public void setServiceGroup(String serviceGroup) {
        this.serviceGroup = serviceGroup;
    }

    public Integer getIsLocal() {
        return isLocal;
    }

    public void setIsLocal(Integer isLocal) {
        this.isLocal = isLocal;
    }

    public Integer getId() {
        return id;
    }

    public boolean existed() {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean notExisted() {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean existed(String propertyName, Object propertyValue) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String[] businessKeys() {
        // TODO Auto-generated method stub
        return null;
    }

    public static Service getServiceByServiceNameAndVersion(String serviceName,
                                                            String serviceVersion) throws OPFBaseException {
        Map<String, Object> propValues = new HashMap<String, Object>();
        propValues.put("serviceName", serviceName);
        propValues.put("serviceVersion", serviceVersion);
        List<Service> services = findByProperties(Service.class, propValues);
        if (services != null && services.size() > 0) {
            Service service = services.get(0);
            if (service != null) {

                if (ConstantNumber.IS_LOCAL_SERVICE_TRUE != service.getEnable()) {// 如果服务不可用
                    throw new ServiceDisableException("the service name '"
                            + serviceName + "' version '"+serviceVersion+"' was disable");
                }

            } else {
                throw new RequestCheckingException("the service name '"
                        + serviceName + "' version '"+serviceVersion+"' did not exist,please check the input service name and version");
            }
            return service;
        }else{
            throw new RequestCheckingException("the service name '"
                    + serviceName + "' version '"+serviceVersion+"' did not exist,please check the input service name and version");
        }
    }

    @Override
    public String toString() {
        return "Service{" + "id=" + id + ", serviceName='" + serviceName + '\''
                + ", serviceVersion='" + serviceVersion + '\'' + ", url='"
                + url + '\'' + ", enable=" + enable + ", type='" + type + '\''
                + ", serviceGroup='" + serviceGroup + '\'' + ", isLocal="
                + isLocal + '}';
    }
}