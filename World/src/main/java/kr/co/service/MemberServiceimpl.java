package kr.co.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.domain.MemberDTO;
import kr.co.persistence.MemberDAO;

@Service
public class MemberServiceimpl implements MemberService{
@Inject
private MemberDAO memberdao;

@Override
public void insert(MemberDTO dto) {
	// TODO Auto-generated method stub
	memberdao.insert(dto);
	
}

@Override
public List<MemberDTO> list() {
	// TODO Auto-generated method stub
	return memberdao.list();
}

}
