package spring.rest.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class EquipmentVo {

	@Schema(description = "설비 id", defaultValue="EQxxx", example="EQ123")
	private String eqId;
	@Schema(description = "설비 이름", defaultValue="x#x", example="AFP#9")
	private String eqName;
	@Schema(description = "공장 위치", defaultValue="SITE_COM", example="SITE_COM")
	private String site;
	@Schema(description = "설치일자", defaultValue="", example="")
	private String setupTime;
	@Schema(description = "등록일자", accessMode = Schema.AccessMode.READ_ONLY)
	private String regDt;
	@Schema(description = "수정일자", accessMode = Schema.AccessMode.READ_ONLY)
	private String modDt;
	@Schema(description = "비고", defaultValue="", example="")
	private String remart;
	@Schema(description = "공장 위치명", defaultValue="복합재동", example="복합재동", accessMode = Schema.AccessMode.READ_ONLY)
	private String siteName;

}
