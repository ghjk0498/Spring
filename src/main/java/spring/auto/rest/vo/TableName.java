package spring.auto.rest.vo;

import java.util.Arrays;
import java.util.Optional;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TableName {
	// SCHEMA이름_TABLE이름
	PUBLIC_PLAYERS("public.players");

	private final String label;

	public static Optional<TableName> valueOfLabel(String label) {
		return Optional.ofNullable(Arrays.stream(values())
				.filter(tableName -> tableName.getLabel().equals(label))
				.findAny()
				.get());
	}

}
