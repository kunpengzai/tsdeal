package com.itee.tsd.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.itee.tsd.dto.PageInfo;
import com.itee.tsd.dto.SearchInfo;
import com.itee.tsd.dto.ShirtDTO;
import com.itee.tsd.dto.ShirtPropertyDTO;
import com.itee.tsd.service.BaseManagerService;
import com.itee.tsd.service.ShirtManagerService;

/**
 * 
 * @author Jia Qianpeng
 * 2015-12-06
 */

@Controller
@RequestMapping ("/sm/")
public class ShirtManagerController {
	
	@Resource
	private BaseManagerService baseManagerService;
	@Resource
	private ShirtManagerService shirtManagerService;

	@RequestMapping ("shirt-state")
	public String shirtState(HttpServletRequest request, Model model) {
		List<ShirtPropertyDTO> brandList = baseManagerService.getBrandList();
		List<ShirtPropertyDTO> sourceList = baseManagerService.getSourceList();
		List<ShirtPropertyDTO> colorList = baseManagerService.getColorList();
		String status = baseManagerService.getWeightScheduler();

		model.addAttribute("brandList", brandList);
		model.addAttribute("sourceList", sourceList);
		model.addAttribute("colorList", colorList);
		model.addAttribute("weightSchedulerStatus", status);

		return "/manager/shirt-state";
	}
	
	@ResponseBody
	@RequestMapping(value="shirt-list", produces="application/json;charset=UTF-8")
	public Map<String, Object> getShirtList(HttpServletRequest request, 
			SearchInfo searchInfo, PageInfo pageInfo) {
		Map<String, Object> m = shirtManagerService.getShirtList(searchInfo, pageInfo);
		return m;
	}
	
	@ResponseBody
	@RequestMapping(value="shirt-detail", produces="application/json;charset=UTF-8", method=RequestMethod.GET)
	public Map<String, Object> shirtDetail(HttpServletRequest request, Long shirtId) throws IOException {
		Map<String, Object> m = shirtManagerService.shirtDetail(shirtId);
		return m;
	}
	
	
	@RequestMapping ("add-shirt")
	public String addShirt(HttpServletRequest request, Model model, ShirtDTO shirt,
			@RequestParam MultipartFile imageFile) throws IOException {
		shirtManagerService.addShirt(shirt, imageFile);
		return "redirect:"+"/sm/shirt-state.htm";
	}
	
	@ResponseBody
	@RequestMapping(value="delete-shirt", produces="application/json;charset=UTF-8", method=RequestMethod.POST)
	public Map<String, Object> deleteShirt(HttpServletRequest request, Long shirtId) throws IOException {
		Map<String, Object> m = shirtManagerService.deleteShirt(shirtId);
		return m;
	}
	
	@RequestMapping ("edit-shirt")
	public String editShirt(HttpServletRequest request, Model model, ShirtDTO shirt, Integer pageNum,
			@RequestParam MultipartFile imageFile) throws IOException {
		shirtManagerService.editShirt(shirt, imageFile);
		if (pageNum == null) {
			pageNum = 1;
		}
		return "redirect:"+"/sm/shirt-state.htm#pageNum="+pageNum;
	}
	
	@ResponseBody
	@RequestMapping(value="change-shirt-status", produces="application/json;charset=UTF-8", method=RequestMethod.POST)
	public Map<String, Object> changeShirtStatus(HttpServletRequest request, 
			Long shirtId, Integer status) {
		Map<String, Object> m = shirtManagerService.changeShirtStatus(shirtId, status);
		return m;
	}
	
	@ResponseBody
	@RequestMapping(value="change-weight-scheduler", produces="application/json;charset=UTF-8", method=RequestMethod.POST)
	public Map<String, Object> changeWeightScheduler(HttpServletRequest request, Integer status) {
		Map<String, Object> m = shirtManagerService.changeWeightScheduler(status);
		return m;
	}

	@ResponseBody
	@RequestMapping(value="compress-img", produces="application/json;charset=UTF-8")
	public Map<String, Object> compressImg() {
		Map<String, Object> m = shirtManagerService.compressImg();
		return m;
	}
}
