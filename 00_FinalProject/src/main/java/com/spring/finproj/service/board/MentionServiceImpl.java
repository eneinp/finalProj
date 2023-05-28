package com.spring.finproj.service.board;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.spring.finproj.model.board.MentionDAO;
import com.spring.finproj.model.board.MentionDTO;

@Service
public class MentionServiceImpl implements MentionService{
	@Autowired
	private MentionDAO mentionDAO;
	
	@Override
	public void getMentionlist(HttpServletRequest request, Model model, int cm_no) throws Exception {
		List<MentionDTO> list = null;
		
		list = mentionDAO.getMentionList(cm_no);
		
		model.addAttribute("MentionList", list);
	}
	
	@Override
	public List<MentionDTO> addMentionlist(HttpServletRequest request, Model model, int cm_no) throws Exception {
		
		List<MentionDTO> list = new ArrayList<MentionDTO>();
		list = mentionDAO.getMentionList(cm_no);
		
		return list;
	}

	@Override
	public int getMentionInsert(MentionDTO dto) throws Exception {
		
		return this.mentionDAO.insertMentionContent(dto);
		
	}

	@Override
	public int getMentionDelete(int mention_no) throws Exception {

		return this.mentionDAO.deleteMentionContent(mention_no);
		
	}

	@Override
	public void update(MentionDTO dto) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
