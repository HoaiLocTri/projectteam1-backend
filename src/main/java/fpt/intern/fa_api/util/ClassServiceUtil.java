package fpt.intern.fa_api.util;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fpt.intern.fa_api.repository.ClassRepository;

@Service
public class ClassServiceUtil {

    @Autowired
    private ClassRepository classRepository;

    public String saveClass() {
       
        String positionCode = "HCM";
        SimpleDateFormat yearFormat = new SimpleDateFormat("yy");
        int maxSequence = 99; 
        String currentYear = yearFormat.format(new Date());
        long lastClassCode=0;
        String classCode="";
        try {
         lastClassCode = classRepository.findLastClassCodeByYear(currentYear);
         
        
        }
        catch (Exception e) {
			
		} 
        if (lastClassCode !=0) 
        {   
			long newcode=lastClassCode+1;
        	 classCode = positionCode + "_" + currentYear + "_" + 0+newcode;        
        } 
        else 
        {
        	classCode = positionCode  + "_" + currentYear  + "_" + "01";
        }

     
        return classCode;
    }
}
