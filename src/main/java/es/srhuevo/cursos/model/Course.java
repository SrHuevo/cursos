package es.srhuevo.cursos.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@Entity
@Table(name = "course")
public class Course {

	@Id
	@Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id = -1;
	
	@Column(name="enable")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean enable = true;
	
	@Column(name="teacher")
	private String teacher;
	
	@Column(name="title")
	private String title;
	
	@Column(name="level")
	private Integer level;
	
	@Column(name="time")
	private Integer time;
	
	@Column(name="filepath")
	private String filepath;
	
	public StreamedContent getFileDownload() {
		try {
			if(this.getFilepath() != null){
				InputStream ins = new FileInputStream(this.getFilepath());
				DefaultStreamedContent d = new DefaultStreamedContent(ins);
				d.setName(this.getFilepath().substring(this.getFilepath().lastIndexOf("\\")+1));
				d.getName();
				return d;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}
	
	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

}
