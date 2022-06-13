package com.javaex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.dao.GuestBookDao;
import com.javaex.vo.GuestVo;

@Controller
public class GuestBookController {
	@RequestMapping(value="/addList", method={RequestMethod.GET, RequestMethod.POST})
	public String addList(Model model) {
		GuestBookDao gDao = new GuestBookDao();
		List<GuestVo> gLIst = gDao.getList();
		
		model.addAttribute("gList", gLIst);
		
		return "WEB-INF/views/addList.jsp";
	}
	
	@RequestMapping(value="/add", method= {})
	public String add(@ModelAttribute GuestVo gVo) {
		GuestBookDao gDao = new GuestBookDao();
		gDao.insert(gVo);
		
		return "redirect:/addList";
	}
	
	@RequestMapping(value="/deleteForm/{no}", method= {RequestMethod.GET, RequestMethod.POST})
	public String deleteForm(Model model, @PathVariable("no") int no) {
		model.addAttribute("no", no);
		return "/WEB-INF/views/deleteForm.jsp";
	}
	
	@RequestMapping(value="/delete", method= {RequestMethod.GET, RequestMethod.POST})
	public String delete(@ModelAttribute GuestVo gVo) {
		System.out.println("Controller received: delete");
		
		GuestBookDao gDao = new GuestBookDao();
		int no = gVo.getNo();
		String password = gVo.getPassword();
		gDao.delete(no, password);
		
		return "redirect:/addList";
	}
}
