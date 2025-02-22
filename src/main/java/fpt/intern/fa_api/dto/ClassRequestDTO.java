package fpt.intern.fa_api.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import fpt.intern.fa_api.model.entity.User;
import fpt.intern.fa_api.model.enums.ClassStatus;

@Setter
@Getter
@Data
public class ClassRequestDTO implements Serializable {

	

	    private String name;
	   
	    private String code;
	    
//	    private String Duration;
	    
	    private ClassStatus status;
	    
	    private String location;
	    
	    private String fsu;
	    
	    private Date start_date;
	    
	    private Date end_date;
	    
	    private int created_by;
	    
	    private Date created_date;
	    
	    private int modified_by;
	    
	    private Date modified_date;


	 
	   

			
	   
	

}
