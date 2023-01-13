package spring.auto.rest.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AutoRestMapper {
	public List<Map<String, Object>> selectAllByTableFullName(String tableFullName, List<String> columnList);
	public List<String> selectColumnsByTableName(String schemaName, String tableName);
}
