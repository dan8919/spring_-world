package kr.co.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.org.apache.bcel.internal.generic.DDIV;

import kr.co.domain.BoardVO;
import kr.co.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAO bDao;
	
	@Override
	public void insert(BoardVO vo) {
		bDao.insert(vo);
	}

	@Override
	public List<BoardVO> list() {
		// TODO Auto-generated method stub
		return bDao.list();
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
		bDao.delete(bno);
		
	}
}
