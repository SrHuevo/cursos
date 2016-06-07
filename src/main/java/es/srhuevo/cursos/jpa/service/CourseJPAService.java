package es.srhuevo.cursos.jpa.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.primefaces.model.SortOrder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import es.srhuevo.cursos.model.Course;

@Component
public class CourseJPAService {
	
	@PersistenceContext
	private EntityManager em;

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Transactional
	public void insert(Course course) {
		this.em.persist(course);
	}

	@Transactional
	public List<Course> getList() {
	    return getOrderPaginatorList(0, -1, null, null);
	}
	
	@Transactional
	public List<Course> getOrderPaginatorList(int first, int pageSize, String sortField, SortOrder sortOrder) {
		CriteriaBuilder cb = this.em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		Root<Course> from = cq.from(Course.class);
		cq.select(from);
		if(sortField != null){
			if(sortOrder.equals(SortOrder.ASCENDING)){
				cq.orderBy(cb.asc(from.get(sortField)));
			} else {
				cq.orderBy(cb.desc(from.get(sortField)));
			}
		}
		ParameterExpression<Integer> enable = cb.parameter(Integer.class);
		Predicate predicate = cb.gt(from.<Integer> get("enable"), enable);
		cq.where(predicate);
		TypedQuery<Course> typedQuery = this.em.createQuery(cq);
		typedQuery.setFirstResult(first);
		if(pageSize != -1){
			typedQuery.setMaxResults(pageSize * first);
		}
		typedQuery.setParameter(enable, 0);
	    return typedQuery.getResultList();
	}
}
