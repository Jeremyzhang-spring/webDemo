package controllers;

import java.util.List;

import controllers.response.PhoneNumberCombinationVO;
import org.springframework.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import services.PhoneNumberService;


@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class HelloWebController {
	
		@Autowired
		private PhoneNumberService phonenumberservice;
	
		@RequestMapping(value="/phone-number", method = RequestMethod.GET)
		public PhoneNumberCombinationVO getNumber(@RequestParam("number") String number,
												  @RequestParam("page") Integer pagenumber,
												  @RequestParam("records") Integer pagesize){
			return phonenumberservice.getPhoneNumber(pagenumber, pagesize, number);
		}
}
