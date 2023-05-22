package com.spring.finproj.service.board;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.spring.finproj.model.board.BoardDTO;
import com.spring.finproj.model.chat.ChatDTO;

public interface BoardService {

	public void getBoardList(HttpServletRequest request, Model model, String keyword) throws Exception;
	public String writeBoard(BoardDTO boardDTO, MultipartFile[] files, 
			Model model, String[] category, String hashtags, HttpSession session, HttpServletRequest request) throws Exception;
	public Map<String, List<BoardDTO>> getBoardAddList(HttpServletRequest request, int cm_no, String keyword) throws Exception;
}