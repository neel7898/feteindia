package com.neel.controllers;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.neel.dao.VenueDao;
import com.neel.dto.Venue;

@Controller
public class VenueController {
	
	
	@Autowired
	VenueDao dao;
	
	@RequestMapping(value="/createPartyHall",method = RequestMethod.POST)
	@ResponseBody
	public void createPartyHall(HttpServletRequest request,HttpServletResponse response) throws IOException{

		Venue venue = new Venue();
		String feteID = "";
		
		if(null != request.getParameter("feteId")){
			feteID = request.getParameter("feteId");
			}
			Long venueCount = dao.getVenueCountFromFeteId(feteID);
			

			venue.setFeteID(feteID);
			venue.setVenueId(feteID+"00"+(venueCount+1));
			
			String photoFolderPath = request.getServletContext().getRealPath("/") +File.separator+ "VenuePhotos";
			
			String venuePhotoFolderPath = request.getServletContext().getRealPath("/") +File.separator+ "VenuePhotos"+ File.separator +feteID+File.separator+venue.getVenueId();
		
/*		// Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);*/
		
		File photoFolder = new File(photoFolderPath);
		if(!photoFolder.isDirectory()){
			photoFolder.mkdir();
		}
		
		File venueFolder = new File(photoFolderPath+File.separator+feteID);
		if(!venueFolder.isDirectory()){
			venueFolder.mkdir();
		}
		
		File folder = new File(venuePhotoFolderPath);
		if(!folder.isDirectory()){
			folder.mkdir();
		}
		
		// Parse the request
/*		try {
			List<FileItem> items = upload.parseRequest(request);
			
			// Process the uploaded items
			Iterator<FileItem> iter = items.iterator();
			int count = 0;
			while(iter.hasNext()){
				FileItem item = iter.next();
				if(!item.isFormField()){
					FileOutputStream fout = new FileOutputStream(new File(venuePhotoFolderPath+File.separator+"Image"+count));
					
					InputStream in = item.getInputStream();
					BufferedInputStream bin = new BufferedInputStream(in);
					int c=0;
					while((c = bin.read())!=-1){
						fout.write(c);
					}
				}
			}
			
			
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in fileuploadexception : "+e.getMessage());
		}*/
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		List<MultipartFile> files = multipartRequest.getFiles("fileupload");
		Iterator<MultipartFile> iter = files.iterator();
		int count = getImageCount(folder);
		String fileType = "";
		while (iter.hasNext()) {
		    MultipartFile file = iter.next();
		    if(file.getOriginalFilename().endsWith(".jpg")){
		    	fileType=".jpg";
		    }else if(file.getOriginalFilename().endsWith(".jpeg")){
		    	fileType=".jpeg";
		    }else if(file.getOriginalFilename().endsWith(".png")){
		    	fileType = ".png";
		    }
		    FileOutputStream fout = new FileOutputStream(new File(venuePhotoFolderPath+File.separator+"Image"+count+fileType));
		    InputStream in = file.getInputStream();
			BufferedInputStream bin = new BufferedInputStream(in);
			int c=0;
			while((c = bin.read())!=-1){
				fout.write(c);
			}
			bin.close();
			in.close();
			fout.close();
		    count++;
		}
		if(count != 0){
		venue.setPhotoPath("VenuePhotos"+ File.separator +feteID+File.separator+venue.getVenueId()+File.separator+"Image"+(count-1)+fileType);
		}else{
			venue.setPhotoPath("images\\partyHall1.jpg");
		}
		
		if(null != request.getParameter("name")){
			venue.setVenueName(request.getParameter("name"));
		}
		
		if(null != request.getParameter("type")){
			venue.setType(request.getParameter("type"));
		}
		
		if(null != request.getParameter("capacity")){
			venue.setCapacity(Integer.parseInt(request.getParameter("capacity")));
		}
		
		if(null != request.getParameter("charges")){
			venue.setCharges(Integer.parseInt(request.getParameter("charges")));
		}
		
		if(null != request.getParameter("parking")){
			venue.setParkingCapacity(Integer.parseInt(request.getParameter("parking")));
		}
		
		if(null != request.getParameter("suitableFor")){
			String[] suitableForArray = request.getParameterValues("suitableFor");
			String suitableFor = "";
			for(String s:suitableForArray){
				suitableFor = suitableFor+s+"~";
			}
			venue.setSuitableFor(suitableFor);
		}
		
		if(null != request.getParameter("facilities")){
			String[] facilitiesArray = request.getParameterValues("facilities");
			String facilities = "";
			for(String s: facilitiesArray){
				facilities = facilities + s +"~";
			}
			venue.setFacilities(facilities);
		}
		
		venue.setPhotoCount(getImageCount(folder));
		
		dao.registerVenue(venue);
		
		response.sendRedirect("/Fete/index.html#/myHall");
	}
	
	@RequestMapping(value="/getPartyHalls",method = RequestMethod.POST)
	@ResponseBody
	public List<Venue> getPartyHalls(@RequestBody String feteId){
		List<Venue> list = new ArrayList<Venue>();
		list = dao.getVenueListFromFeteId(feteId);
		return list;
	}
	
	@RequestMapping(value="/deleteVenueByID", method= RequestMethod.POST)
	@ResponseBody
	public void deleteVenueByID(@RequestParam String id){
		dao.deleteVenueById(id);
	}

	private int getImageCount(File folder) {
		int count = 0;
		if(null != folder.list()){
		 count = folder.list().length;
		}
		return count;
	}
}
