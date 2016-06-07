package es.srhuevo.cursos.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import es.srhuevo.cursos.jpa.service.CourseJPAService;
import es.srhuevo.cursos.model.Course;
import es.srhuevo.cursos.model.CourseLazy;

@ManagedBean(name="courseList")
@ViewScoped
public class CourseList implements Serializable {

	private static final long serialVersionUID = 6885000539948269566L;

	@ManagedProperty("#{courseJPAService}")
	private CourseJPAService courseService;
	
	private LazyDataModel<Course> courseLazyModel;
	
    @PostConstruct
    public void init() {
    	courseLazyModel = new CourseLazy(courseService);
    }
    
	public CourseJPAService getCourseService() {
		return courseService;
	}

	public void setCourseService(CourseJPAService courseService) {
		this.courseService = courseService;
	}

	public List<Course> getCourseList(){
		return courseService.getOrderPaginatorList(0, -1, null, null);
	}

	public LazyDataModel<Course> getCourseLazyModel() {
		return courseLazyModel;
	}

	public void setCourseLazyModel(LazyDataModel<Course> courseLazyModel) {
		this.courseLazyModel = courseLazyModel;
	}
    
    public String getPage(){
    	return "courselist?faces-redirect=true";
    }

}
