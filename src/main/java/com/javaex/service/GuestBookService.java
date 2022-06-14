package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.dao.GuestBookDao;
import com.javaex.vo.GuestVo;

@Repository
public class GuestBookService {
	
	@Autowired
	private GuestBookDao gDao;
	
	public static List<GuestVo> getList(){
		List<GuestVo> gList = gDao.getList();
		return gList;
	}
}
