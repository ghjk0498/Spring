package spring.auto.rest.service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import spring.auto.rest.mapper.AutoRestMapper;
import spring.auto.rest.vo.TableName;

@Slf4j
@Service
@RequiredArgsConstructor
public class AutoRestServerService {

	private final AutoRestMapper autoRestMapper;

	public List<Map<String, Object>> getAllByTableName(String schemaName, String tableName, List<String> columnList) throws NoSuchElementException {
		if (columnList != null) {
			List<String> tableColumnList = autoRestMapper.selectColumnsByTableName(schemaName, tableName);
			columnList = columnList.stream().filter(column -> tableColumnList.indexOf(column) != -1).toList();
		}
		return autoRestMapper.selectAllByTableFullName(TableName.valueOfLabel(schemaName + "." + tableName).get().getLabel(), columnList);
	}

	public List<String> getColumnsByTableName(String schemaName, String tableName) {
		return autoRestMapper.selectColumnsByTableName(schemaName, tableName);
	}

}
