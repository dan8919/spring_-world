package kr.co.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.org.apache.bcel.internal.generic.DDIV;

import kr.co.domain.BoardVO;
import kr.co.domain.PageTO;
import kr.co.persistence.BoardDAO;
import kr.co.persistence.ReplyDAO;


@Service
@Transactional
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAO bDao;
	@Autowired
	private ReplyDAO rDao;
	
	@Override
	public void insert(BoardVO vo) {
		bDao.insert(vo);
	}

	
	@Override
	public BoardVO read(int bno) {
		//조회수 증가
		bDao.increaseViewcnt(bno);
		return bDao.read(bno);
	}

	@Override
	public BoardVO updateUI(int bno) {
		// TODO Auto-generated method stub
		return bDao.updateUI(bno);
	}

	@Override
	public void update(BoardVO vo) {
		// TODO Auto-generated method stub
		bDao.update(vo);
	}

	@Override
	public void delete(int bno) {
		rDao.deleteByBno(bno);
		bDao.delete(bno);
		
	}

	@Override
	public PageTO<BoardVO> list(PageTO<BoardVO> to) {
		// TODO Auto-generated method stub
		return bDao.list(to);
	}


	@Override
	public List<BoardVO> searchlist(String searchType, String keyword) {
		// TODO Auto-generated method stub
		return bDao.searchlist(searchType, keyword);
	}





}
