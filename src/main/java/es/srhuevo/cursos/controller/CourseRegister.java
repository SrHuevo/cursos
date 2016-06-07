package es.srhuevo.cursos.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;

import es.srhuevo.cursos.config.CourseConfig;
import es.srhuevo.cursos.jpa.service.CourseJPAService;
import es.srhuevo.cursos.model.Course;
import es.srhuevo.cursos.util.FileFn;

@ManagedBean
@SessionScoped
public class CourseRegister implements Serializable {

	private static final long serialVersionUID = -3230609693409820565L;


	@ManagedProperty("#{courseJPAService}")
    private CourseJPAService courseService;
    

	private UploadedFile file;	
 
    private Course course = new Course();

    public CourseJPAService getCourseService() {
        return courseService;
    }
 
    public void setCourseService(CourseJPAService courseService) {
        this.courseService = courseService;
    }
 
    public Course getCourse() {
        return course;
    }
 
    public void setCourse(Course course) {
        this.course = course;
    }

    public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}
	
    public String register() {
    	if(file.getSize()!=0){
    		String fName = (new StringBuilder(CourseConfig.PATH_FILE_DOCUMENTATION).append(file.getFileName())).toString();
    		try {
				FileFn.saveFile(fName, file.getInputstream());
	    		course.setFilepath(fName);
			} catch (IOException e) {
				e.printStackTrace();

		        FacesContext.getCurrentInstance().addMessage(null,
		                new FacesMessage("No se ha podido subir el fichero"));
		        return "";
			}
    	}
    	
        // Calling Business Service
    	courseService.insert(course);

        // Add message
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("El curso "+this.course.getTitle()+" ha sido añadido correctamente"));
        return "";
    }
    
    public String getPage(){
    	return "courseregister?faces-redirect=true";
    }
    
}
