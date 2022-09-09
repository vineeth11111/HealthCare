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
@Table(name="doctor_tab")
@NoArgsConstructor
public class Doctor {
	
	@Id
	@GeneratedValue()
	@Column(name="doc_id_col")
	private Long id;
	
	@Column(name="doc_addr_col")
	private String address;
	
	@Column(name="doc_mail_col")
	private String email;
	
	@Column(name="doc_fn_col")
	private String firstName;
	
	@Column(name="doc_ls_col")
	private String lastName;
	
	@Column(name="doc_gen_col")
	private String gender;
	
	@Column(name="doc_mob_col")
	private String mobile;
	
	@Column(name="doc_note_col")
	private String note;
	
	@Column(name="doc_img_col")
	private String image;
	
	//-----------Association Mapping
	
		@ManyToOne
		@JoinColumn(name="spec_id_fk_col")
		private Specialization specialization;
	
	

}
