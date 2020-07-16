/*
 * package kr.co.service;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Service; import
 * org.springframework.transaction.annotation.Transactional;
 * 
 * import kr.co.domain.BoardVO; import kr.co.persistence.BoardDAO;
 * 
 * @Service
 * 
 * @Transactional public class TranTestServiceImpl implements TranTestService {
 * 
 * @Autowired private BoardDAO bDAO;
 * 
 * 
 * @Override public void insertNupdate1(BoardVO vo) { bDAO.insert(vo);
 * //System.out.println(vo.getBno()+":::::::::::::::::::::::::::::::::");
 * System.out.println(0/4); bDAO.update(vo);
 * 
 * }
 * 
 * 
 * 
 * 
 * }
 */




package kr.co.service;

 

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

 

import kr.co.domain.BoardVO;

import kr.co.persistence.BoardDAO;

 

@Service

@Transactional

public class TranTestServiceImpl implements TranTestService{

	

	@Autowired

	private BoardDAO bDao;

 

	@Override

	public void insertNupdate1(BoardVO vo) {

		bDao.insert(vo);

		System.out.println(4/0);

		

		bDao.update(vo);

		

	}

}