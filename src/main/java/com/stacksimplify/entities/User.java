package com.stacksimplify.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="This model is to create a user")
@Entity
@Table(name="user")
//@JsonIgnoreProperties({"firstname", "lastname"})  --Static Filtering @JsonIgnore
//@JsonFilter(value = "userFilter")					--Used for MappingJacksonValue filtering section
public class User extends RepresentationModel<User>{
    
    //Primary key
	@ApiModelProperty(notes="Auto generated unique id", required = true, position = 1)
	@Id
	@GeneratedValue
	@JsonView(Views.External.class)
	private Long id;

	@ApiModelProperty(notes="Username should in format flname", example = "pescobar", required = false, position = 2)
	@Size(min=2, max=50)
	@NotEmpty(message="Username is a required field")
	@Column(name="USER_NAME", length=50, nullable=false, unique=true)
	@JsonView(Views.External.class)
	private String username;
	
	@Size(min=2, max=50, message="First name should have at least 2 characters")
	@Column(name="FIRST_NAME", length=50, nullable=false)
	@JsonView(Views.External.class)
	private String firstname;
	
	@Column(name="LAST_NAME", length=50, nullable=false)
	@JsonView(Views.External.class)
	private String lastname;
	
	@Column(name="EMAIL_ADDRESS", length=50, nullable=false)
	@JsonView(Views.External.class)
	private String email;

	@Column(name="ROLE", length=50, nullable=false)
	@JsonView(Views.Internal.class)
	private String role;
	
	@Column(name="SSN", length=50, nullable=false, unique=true)
	//@JsonIgnore --Static Filtering @JsonIgnore
	@JsonView(Views.Internal.class)
	private String ssn;
	
	@OneToMany(mappedBy="user") //Referencing field "user" in Orders table
	@JsonView(Views.Internal.class)
	private List<Order> order;

	public User() {
	}

	public User(Long id, String username, String firstname, String lastname, String email, String role, String ssn) {
		super();
		this.id = id;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
		this.ssn = ssn;
	}
	
	public User(Long id) {
		super();
		this.id = id;
	}

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", email=" + email + ", role=" + role + ", ssn=" + ssn + "]";
	}
	
	
	
	

}
