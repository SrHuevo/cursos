package es.srhuevo.cursos.model;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import es.srhuevo.cursos.jpa.service.CourseJPAService;

public class CourseLazy extends LazyDataModel<Course> {
	
	private static final long serialVersionUID = 2172717016464850747L;

	private CourseJPAService jpaService;
	
	public CourseLazy() {}
	
	public CourseLazy(CourseJPAService jpaService) {
		this.jpaService = jpaService;
	}

    
    @Override
    public Course getRowData(String rowKey) {
        return null;
    }
 
    @Override
    public Object getRowKey(Course car) {
        return car.getId();
    }
    
	@Override
    public List<Course> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
    	return jpaService.getOrderPaginatorList(first, pageSize, sortField, sortOrder);
    }
}
