package spring.auto.rest.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AutoRestDto {

	@NotNull
	String schemaName;
	@NotNull
	String tableName;
	String columnName;
	List<String> columnList;

}
