package com.sam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sam.modules.entity.Userinfo;
import com.sam.modules.service.UserinfoService;

@RestController
@RequestMapping(value = "/api")
public class AccountController {
	
	@Autowired
	private UserinfoService userinfoService;
	
	@RequestMapping(value = "/userinfo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE )
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity save(@RequestBody final Userinfo userinfo){
		ResponseEntity response = null;
		try {
			userinfoService.save(userinfo);
			response = new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return response;
	}
	
	
	@RequestMapping(value = "/userinfo/{id}", method = RequestMethod.GET )
    public ResponseEntity get(@PathVariable("id") Long id) {
		ResponseEntity response = null;
		Userinfo userinfo = userinfoService.get(id);
		response = new ResponseEntity<Userinfo>(userinfo, HttpStatus.OK);
		return response;
    }
	
	@RequestMapping(value = "/userinfo/{id}", method = RequestMethod.DELETE )
	@ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
		userinfoService.delete(id);
    }
	
	@RequestMapping(value = "/userinfotest/{id}", method = RequestMethod.DELETE )
	@ResponseStatus(HttpStatus.OK)
    public void deletetest(@PathVariable("id") Long id) throws Exception {
		try {
			userinfoService.cutsdel(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
    }
	
	
}
