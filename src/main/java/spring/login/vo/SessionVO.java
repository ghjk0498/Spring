package spring.login.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString(exclude = "password")
public class SessionVO {

	private String email;
	private String password;
	
}
