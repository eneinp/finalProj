package com.spring.finproj.controller.board;

import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.spring.finproj.model.board.BoardDTO;
import com.spring.finproj.model.board.MentionDTO;
import com.spring.finproj.service.board.BoardService;
import com.spring.finproj.service.board.MentionService;


@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;
    @Autowired
    private MentionService mentionService;
    
    @RequestMapping("/list")
    public String boardList(@RequestParam(required = false, value = "keyword") String keyword,
    		@RequestParam(required = false) String category, Model model) throws Exception {
    	if(!(keyword==null||keyword=="")) {
    		keyword = URLDecoder.decode(keyword, "UTF-8");
    	}
    	if(!(category==null||category=="")) {
    		category = URLDecoder.decode(category, "UTF-8");
    	}
    	model.addAttribute("Keyword", keyword);
    	model.addAttribute("Category", category);
    	return "board.list";
    }
 
    @RequestMapping("/write")
    public String boardWrite() {
        return "board.write";
    }
    
    @RequestMapping("/writeform")
    public String write(BoardDTO dto, @RequestParam("upfile") MultipartFile[] files, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	System.out.println(dto);
    	
    	int check = boardService.writeBoard(dto, files, request);
    	
    	if(check>0) {
			return "redirect:/board/list";
		}else {
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().println("<script>"
					+ "alert('글 작성 중 오류 발생');"
					+ "history.back();"
					+ "</script>");
			return null;
		}
    }
    
    @RequestMapping("/update")
    public String boardUpdate(HttpServletRequest request, int cm_no, Model model) throws Exception {
    	Map<String, Object> map = boardService.contentBoard(request, cm_no, model);
    	model.addAttribute("Map", map);
    	return "board.update";
  
    }
    
    @RequestMapping("/updateform")
    public String update(BoardDTO dto, @RequestParam("upfile") MultipartFile[] files, 
    		HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	int check = boardService.updateBoard(dto, files, session, request);
    	
    	if(check>0) {
			return "redirect:/board/list";
		}else {
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().println("<script>"
					+ "alert('글 작성 중 오류 발생');"
					+ "history.back();"
					+ "</script>");
			return null;
		}
    }
    
    @RequestMapping("/addlist")
    @ResponseBody
    public Map<String , Object> boardAddList(HttpServletRequest request, 
    		@RequestParam(required = false) int cm_no, 
    		@RequestParam(required = false) String keyword,
    		@RequestParam(required = false) String category) throws Exception{
    	System.out.println(keyword);
    	System.out.println(category);
    	if(!(keyword==null||keyword=="")) {
    		keyword = URLDecoder.decode(keyword, "UTF-8");
    	}
    	if(!(category==null||category=="")) {
    		category = URLDecoder.decode(category, "UTF-8");
    	}
    	return boardService.getBoardAddList(request, cm_no, keyword, category);
    }
    
    @RequestMapping("/addmention")
    @ResponseBody
    public List<MentionDTO> addmentionRequest(MentionDTO dto, HttpServletRequest request, Model model) throws Exception {
    	int check = mentionService.getMentionInsert(dto);
    	
    	List<MentionDTO> list = mentionService.addMentionlist(request, model, dto.getCm_no());
    	return list;
    }
    
    @RequestMapping("/deletemention")
    @ResponseBody
    public int delmentionRequest(int mention_no, HttpServletRequest request) throws Exception {
    	int check = mentionService.getMentionDelete(mention_no);
    	return check;
    }
    
    @RequestMapping(value = "/delete")
    @ResponseBody
    public String boardDelete(int cm_no, HttpServletRequest request) {
    	int re = boardService.deleteBoardCont(cm_no, request);
    	if(re>0) {
    		return "<script>alert('게시글 삭제 성공');location.href='/finproj/index';</script>";
    	}else {
    		return "<script>alert('게시글 삭제 실패');history.back();</script>";
    	}
    }
}