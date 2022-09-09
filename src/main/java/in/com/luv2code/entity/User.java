package in.com.luv2code.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="user_tab")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	@Column(name="usr_id_col")
	@GeneratedValue
	@Id
	private Long id;
	
	@Column(name="usr_dname_col")
	private String displayName;
	
	@Column(name="usr_uname_col")
	private String username;
	
	@Column(name="usr_pwd_col")
	private String password;
	
	@Column(name="usr_role_col")
	private String role;
	
	

}
