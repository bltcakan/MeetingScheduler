package com.n11.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MeetingSchedulerWebController {
	@RequestMapping(value={"/","/index.html","/index"})
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
 		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping(value={"/time-line.html","/time-line"})
	public ModelAndView timeTable() {
		ModelAndView mav = new ModelAndView();
 		mav.setViewName("time-line");
		return mav;
	}
}
