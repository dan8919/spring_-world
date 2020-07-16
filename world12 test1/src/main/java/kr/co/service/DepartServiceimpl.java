package kr.co.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.domain.DepartDTO;
import kr.co.persistence.DpartDAO;
@Service
public class DepartServiceimpl implements DepartService{

	@Inject
	private DpartDAO dpartdao;
	
	
	@Override
	public void insert(DepartDTO dto) {
		
		dpartdao.insert(dto);
	}


	@Override
	public List<DepartDTO> list() {
		// TODO Auto-generated method stub
		return dpartdao.list();
	}


	@Override
	public DepartDTO read(String did) {
		// TODO Auto-generated method stub
		return dpartdao.read(did);
	}


	@Override
	public DepartDTO updateui(String did) {
		// TODO Auto-generated method stub
		return dpartdao.updateui(did);
	}


	@Override
	public void update(DepartDTO dto) {
		dpartdao.update(dto);
		
	}


	@Override
	public void delete(String did) {
		dpartdao.delete(did);
		
	}



}
