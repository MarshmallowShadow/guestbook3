package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.GuestBookService;
import com.javaex.vo.GuestVo;

@Controller
public class GuestBookController {
	
	@Autowired
	private GuestBookService gService;
	
	//리스트 목록
	@RequestMapping(value="/addList", method={RequestMethod.GET, RequestMethod.POST})
	public String addList(Model model) {
		//System.out.println("Controller action: addList");
		
		List<GuestVo> gList = gService.getList();
		
		model.addAttribute("gList", gList);
		
		return "addList";
	}
	
	//글 추가
	@RequestMapping(value="/add", method= {})
	public String add(@ModelAttribute GuestVo gVo) {
		//System.out.println("Controller action: add");
		
		gService.insert(gVo);
		
		return "redirect:/addList";
	}
	
	//삭제 폼
	@RequestMapping(value="/deleteForm/{no}", method= {RequestMethod.GET, RequestMethod.POST})
	public String deleteForm(Model model, @PathVariable("no") int no) {
		//System.out.println("Controller action: deleteForm");
		
		//번호 모델에 저장 (RequestParam쓰면은 이렇게 안해도 된다?)
		model.addAttribute("no", no);
		return "deleteForm";
	}
	
	//삭제
	@RequestMapping(value="/delete", method= {RequestMethod.GET, RequestMethod.POST})
	public String delete(@ModelAttribute GuestVo gVo) {
		//System.out.println("Controller action: delete");
		
		int no = gVo.getNo();
		String password = gVo.getPassword();
		int confirm = gService.delete(no, password);
		
		if(confirm > 0) { //삭제 성공일 경우 메인으로 돌아가기
			return "redirect:/addList";
		}
		else { //틀릴 경우 새로고침하기
			return "redirect:/deleteForm/" + no;
		}
	}
}
