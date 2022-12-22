package com.tickethandling.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.tickethandling.dto.BookMarkDTO;
import com.tickethandling.repositories.BookmarkReository;

@Service
public class BookmarkService {

	@Autowired
	private BookmarkReository bookmarkReository;

	public boolean bookMarkTicket(Long userid, Long ticketid) {
		try {
			List<BookMarkDTO> ls = bookmarkReository.findByUseridAndTicketid(userid, ticketid);
			if (ObjectUtils.isEmpty(ls)) {
				BookMarkDTO bookMarkDTO = new BookMarkDTO();
				bookMarkDTO.setUserid(userid);
				bookMarkDTO.setTicketid(ticketid);
				bookmarkReository.save(bookMarkDTO);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return true;
	}

	public boolean removeBookMarkTicket(Long userid, Long ticketid) {
		try {
			bookmarkReository.deleteByUseridAndTicketid(userid, ticketid);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return true;
	}
}
