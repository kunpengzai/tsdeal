package com.itee.tsd.scheduler;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.itee.tsd.dao.ShirtManagerDao;
import com.itee.tsd.dao.SystemParamDao;
import com.itee.tsd.dto.ShirtDTO;
import com.itee.tsd.entity.Shirt;
import com.itee.tsd.entity.ShirtProperty;
import com.itee.tsd.entity.SystemParam;
import com.itee.tsd.utils.Constants;

@Service("shirtWeightService")
public class ShirtWeightServiceImpl implements ShirtWeightService {

	@Resource
	private SystemParamDao systemParamDao;
	@Resource
	private ShirtManagerDao shirtManagerDao;
	
	public void changeShirtWeight() {
		SystemParam sysParam = systemParamDao.getSystemParam(Constants.SYSTEM_NAME_MANAGER, 
				Constants.WEIGHT_SWITCH);
		if (sysParam != null && "1".equals(sysParam.getValue())) {
			Shirt wShirt = new Shirt();
			wShirt.setPageNum(1);
			wShirt.setPageSize(1);
			wShirt.setStatus(0);
			wShirt.setIsActive(1);
			wShirt.setOrderCont("s.weight desc");
			List<ShirtDTO> wShirtList = shirtManagerDao.getShirtList(wShirt);
			if (wShirtList.size() == 0) return;
			Integer weight = wShirtList.get(0).getWeight() + 1;
			SystemParam wSysParam = systemParamDao.getSystemParam(Constants.SYSTEM_NAME_MANAGER, 
					Constants.WEIGHT_CHANGE_SHIRT_NUM);
			List<ShirtProperty> brandIdlist = shirtManagerDao.getBrandList(0);
			for (ShirtProperty sp : brandIdlist) {
				Shirt shirt = new Shirt();
				shirt.setBrandId(sp.getId().intValue());
				shirt.setMaxWeight(0);
				shirt.setStatus(0);
				shirt.setIsActive(1);
				shirt.setPageNum(1);
				shirt.setPageSize(Integer.valueOf(wSysParam.getValue()));
				shirt.setOrderCont("s.createTime");
				List<ShirtDTO> shirtList = shirtManagerDao.getShirtList(shirt);
				for (ShirtDTO dto : shirtList) {
					Shirt updateShirt = new Shirt();
					updateShirt.setId(dto.getShirtId());
					updateShirt.setWeight(weight);
					shirtManagerDao.updateShirt(updateShirt);
				}
			}
		}
	}
}
