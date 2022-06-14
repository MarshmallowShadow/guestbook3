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
	//리스트 목록
	@RequestMapping(value="/addList", method={RequestMethod.GET, RequestMethod.POST})
	public String addList(Model model) {
		GuestBookDao gDao = new GuestBookDao();
		List<GuestVo> gLIst = gDao.getList();
		
		model.addAttribute("gList", gLIst);
		
		return "WEB-INF/views/addList.jsp";
	}
	
	//글 추가
	@RequestMapping(value="/add", method= {})
	public String add(@ModelAttribute GuestVo gVo) {
		GuestBookDao gDao = new GuestBookDao();
		gDao.insert(gVo);
		
		return "redirect:/addList";
	}
	
	//삭제 폼
	@RequestMapping(value="/deleteForm/{no}", method= {RequestMethod.GET, RequestMethod.POST})
	public String deleteForm(Model model, @PathVariable("no") int no) {
		//번호 모델에 저장 (RequestParam쓰면은 이렇게 안해도 된다?)
		model.addAttribute("no", no);
		return "/WEB-INF/views/deleteForm.jsp";
	}
	
	//삭제
	@RequestMapping(value="/delete", method= {RequestMethod.GET, RequestMethod.POST})
	public String delete(@ModelAttribute GuestVo gVo) {
		System.out.println("Controller received: delete");
		
		GuestBookDao gDao = new GuestBookDao();
		int no = gVo.getNo();
		String password = gVo.getPassword();
		int confirm = gDao.delete(no, password);
		
		if(confirm > 0) { //삭제 성공일 경우 메인으로 돌아가기
			return "redirect:/addList";
		}
		else { //틀릴 경우 새로고침하기
			return "redirect:/deleteForm/" + no;
		}
	}
}
