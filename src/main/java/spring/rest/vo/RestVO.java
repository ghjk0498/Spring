package spring.rest.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class RestVO {

	@NonNull
	private Integer id;
	@NonNull
	private String title;
	@NonNull
	private String text;
	private String imageUrl;

}
