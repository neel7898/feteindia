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
import com.neel.dao.MenuDao;
import com.neel.dto.Menu;

@Controller
public class MenuController {
	
	@Autowired
	MenuDao dao;
	Gson gson = new Gson();
	
	@RequestMapping(value="/addSingleMenuItem",method = RequestMethod.POST)
	@ResponseBody
	public String addSingleMenuItem(
			/*@RequestParam String feteId,
			@RequestParam String itemName,
			@RequestParam String itemType,
			@RequestParam String category,
			@RequestParam String cost,
			@RequestParam String serving,
			@RequestParam String halfPlate,
			@RequestParam MultipartFile photo*/
			@RequestBody String jsonObj
			) throws JSONException{
		JSONObject obj = new JSONObject(jsonObj);
		String feteId = obj.getString("feteId");
		Menu menu = gson.fromJson(jsonObj, Menu.class);
		dao.addMenuToRestaurant(menu, feteId);
		
		return "{ \"message\" : \"success\"}";
	}
	
	@RequestMapping(value="/getMenuListByFeteId", method = RequestMethod.POST)
	@ResponseBody
	public String getMenuListByFeteId(@RequestBody String feteId){
		List<Menu> menuList = new ArrayList<Menu>();
		menuList = dao.getMenuListByFeteId(feteId);
		String returnStr = "{ \"message\" : \"error\"}";
		if(menuList.size() != 0){
			for(Menu m : menuList){
				m.setRest(null);
			}
			returnStr = gson.toJson(menuList);
		}
		return returnStr;
	}
	
	@RequestMapping(value="/updateSingleMenuItem",method = RequestMethod.POST)
	@ResponseBody
	public void updateSingleMenuItem(@RequestBody String obj){
		Menu menu = gson.fromJson(obj, Menu.class);
		System.out.println("in update menu method");
		dao.updateMenuItem(menu);
	}
	

	@RequestMapping(value="/deleteSingleMenuItem",method = RequestMethod.POST)
	@ResponseBody
	public void deleteSingleMenuItem(@RequestBody String jsonObj) throws JSONException{
		JSONObject obj = new JSONObject(jsonObj);
		String feteId = obj.getString("feteId");
		Menu menu = gson.fromJson(jsonObj, Menu.class);
		
		System.out.println("in update menu method");
		dao.deleteMenuItem(feteId,menu);
	}
	
	@RequestMapping(value="/uploadPhoto",method = RequestMethod.POST)
	@ResponseBody
	public void uploadPhoto(@RequestParam("file") MultipartFile file){
		System.out.println(file.getOriginalFilename());
	}
	
	
}
