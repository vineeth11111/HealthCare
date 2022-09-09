package in.com.luv2code.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@Table(name="appointment_tab")
@NoArgsConstructor
public class Appointment {
	
	@Id
	@GeneratedValue()
	@Column(name="app_id_col")
	private Long id;
	
	@Column(name="app_date_col")
	@DateTimeFormat
	private String date;
	
	@Column(name="app_details_col")
	private String details;
	
	@Column(name="app_amount_col")
	private double amount;
	
	@Column(name="app_slots_col")
	private int slots;
	
	
	
	
	

}
