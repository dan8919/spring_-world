package kr.co.persistence;

import java.util.List;

import kr.co.domain.DepartDTO;

public interface DpartDAO {

	List<DepartDTO> list();

	void insert(DepartDTO dto);

	DepartDTO read(String did);

	DepartDTO updateui(String did);

	void update(DepartDTO dto);

	void delete(String did);

}
