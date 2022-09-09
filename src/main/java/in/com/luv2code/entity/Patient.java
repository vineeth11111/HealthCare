package in.com.luv2code.entity;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@Table(name="patient_tab")
@NoArgsConstructor
public class Patient {
	
	@Id
	@GeneratedValue
	@Column(name="pat_id_col")
	private Long id;
	
	@Column(name="pat_comm_addr_col")
	private String communicationAddress;
	
	@Column(name="pat_dob_col")
	//@DateTimeFormat(iso = ISO.DATE)
	@DateTimeFormat
	//@Temporal(temporalType.date)
	private String dob;
	
	@Column(name="pat_mail_col")
	private String email;
	
	@Column(name="pat_fn_col")
	private String firstName;
	
	@Column(name="pat_ln_col")
	private String lastName;
	
	@Column(name="pat_gen_col")
	private String gender;
	
	@Column(name="pat_other_details_col")
	private String otherDetails;
	
	@Column(name="pat_mar_status_col")
	private String maritalStatus;
	
	@Column(name="pat_mobile_col")
	private String mobile;
	
	@Column(name="pat_other_col")
	private String other;
	
	@Column(name="pat_pre_add_col")
	private String presentAddress;
	
	@ElementCollection
	@CollectionTable(name="past_med_his_table",
	joinColumns = @JoinColumn(name="pat_medi_hst_id_fk_col"))
	@Column(name="pat_medi_his_col")
	private Set<String> pastMedicalHistory;
	
	

}
