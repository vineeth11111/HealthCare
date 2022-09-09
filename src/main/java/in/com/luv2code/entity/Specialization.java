package in.com.luv2code.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@Table(name="specilization_tab")
@NoArgsConstructor
public class Specialization {
	
	@Id
	@GeneratedValue()
	@Column(name="spec_id_col")
	private Long id;
	
	@Column(name="spec_code_col",length=10,nullable=false)
	private String code;
	
	@Column(name="spec_name_col",length=20,nullable=false)
	private String name;
	
	@Column(name="spec_note_col",length=250)
	private String note;
	
	
	
	

}
