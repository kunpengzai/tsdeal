package com.itee.tsd.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itee.tsd.dto.PageInfo;
import com.itee.tsd.dto.SearchInfo;
import com.itee.tsd.dto.ShirtPropertyDTO;
import com.itee.tsd.service.BaseService;
import com.itee.tsd.service.ShirtService;

/**
 * 
 * @author Jia Qianpeng
 * 2015-12-06
 */

@Controller
@RequestMapping ("/shirt/")
public class ShirtController {
	
	@Resource
	private BaseService baseService;
	@Resource
	private ShirtService shirtService;
	
	@RequestMapping ("shirt-list")
	public String shirtState(HttpServletRequest request, Model model) {
		List<ShirtPropertyDTO> brandList = baseService.getBrandList();
		List<ShirtPropertyDTO> sourceList = baseService.getSourceList();
		List<ShirtPropertyDTO> colorList = baseService.getColorList();
		List<ShirtPropertyDTO> priceRangeList = baseService.getPriceRangeList();
		
		model.addAttribute("brandList", brandList);
		model.addAttribute("sourceList", sourceList);
		model.addAttribute("colorList", colorList);
		model.addAttribute("priceRangeList", priceRangeList);
		
		return "/shirt/shirt-list";
	}
	
	@ResponseBody
	@RequestMapping(value="get-more-shirt", produces="application/json;charset=UTF-8")
	public Map<String, Object> getMoreShirt(HttpServletRequest request, 
			SearchInfo searchInfo, PageInfo pageInfo) throws Exception {
		Map<String, Object> m = shirtService.getMoreShirt(searchInfo, pageInfo);
		return m;
	}
}
