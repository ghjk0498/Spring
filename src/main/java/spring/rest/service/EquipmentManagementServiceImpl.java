package spring.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import spring.rest.mapper.EquipmentMapper;
import spring.rest.vo.EquipmentVo;

@Service
@Qualifier("equipmentService")
public class EquipmentManagementServiceImpl implements RestApiService<EquipmentVo> {

	@Autowired
	private EquipmentMapper equipmentMapper;

	@Override
	public List<EquipmentVo> read(EquipmentVo t) {
		return equipmentMapper.select(t);
	}

	@Override
	public int create(EquipmentVo t) {
		return equipmentMapper.insert(t);
	}

	@Override
	public int update(EquipmentVo t) {
		return equipmentMapper.update(t);
	}

	@Override
	public int delete(EquipmentVo t) {
		return equipmentMapper.delete(t);
	}

}
