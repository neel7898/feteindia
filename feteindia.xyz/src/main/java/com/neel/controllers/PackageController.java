package com.neel.controllers;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.neel.dao.PackagesDao;
import com.neel.dto.Packages;

@Controller
public class PackageController {
	
	@Autowired
	PackagesDao dao;
	Gson gson = new Gson();
	
	@RequestMapping(value="/addSinglePackageItem",method = RequestMethod.POST)
	@ResponseBody
	public String addSinglePackageItem(@RequestBody String jsonObj) throws JSONException{
		JSONObject obj = new JSONObject(jsonObj);
		String feteId = obj.getString("feteId");
		Packages packages = gson.fromJson(jsonObj, Packages.class);
		dao.addPackagesToRestaurant(packages, feteId);
		return "{ \"message\" : \"success\"}";
	}
	
	@RequestMapping(value="/getPackagesListByFeteId", method = RequestMethod.POST)
	@ResponseBody
	public String getPackagesListByFeteId(@RequestBody String feteId){
		List<Packages> packagesList = new ArrayList<Packages>();
		packagesList = dao.getPackagesListByFeteId(feteId);
		String returnStr = "{ \"message\" : \"error\"}";
		if(packagesList.size() != 0){
			for(Packages m : packagesList){
				m.setRest(null);
			}
			returnStr = gson.toJson(packagesList);
		}
		return returnStr;
	}
	
	@RequestMapping(value="/updateSinglePackagesItem",method = RequestMethod.POST)
	@ResponseBody
	public void updateSinglePackagesItem(@RequestBody String obj){
		Packages packages = gson.fromJson(obj, Packages.class);
		System.out.println("in update menu method");
		dao.updatePackagesItem(packages);
	}
	

	@RequestMapping(value="/deleteSinglePackagesItem",method = RequestMethod.POST)
	@ResponseBody
	public void deleteSingleMenuItem(@RequestBody String jsonObj) throws JSONException{
		JSONObject obj = new JSONObject(jsonObj);
		String feteId = obj.getString("feteId");
		Packages packages = gson.fromJson(jsonObj, Packages.class);
		
		System.out.println("in update menu method");
		dao.deletePackagesItem(feteId,packages);
	}
	
	@RequestMapping(value="/uploadPackagesPhoto",method = RequestMethod.POST)
	@ResponseBody
	public void uploadPhoto(@RequestParam("file") MultipartFile file){
		System.out.println(file.getOriginalFilename());
	}
	
	
}
