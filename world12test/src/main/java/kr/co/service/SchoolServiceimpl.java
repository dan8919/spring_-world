package kr.co.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.persistence.SchoolDAO;

@Service
public class SchoolServiceimpl implements SchoolService {

	@Inject
	private SchoolDAO sDao;
	
}
