package pcapp.entities;

import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnTransformer;

import pcapp.validators.TextLength;

@Entity
@Table(name = "request")
public class Request {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "status")
	@ColumnTransformer(read = "UPPER(status)")
	@Enumerated(EnumType.STRING)
	@NotNull(message="Are you submitting now or leaving for later submission?")
	private RequestStatus requestStatus;

	@Temporal(TemporalType.DATE)
	@Column(name="date_subm")
	Date dateSubmitted;

	@NotNull(message="You have to explain the reason for your request")
	@TextLength
	String text;

	// Uni-directional mapping
	// One user may have many requests
	@Column(name="user_id")
	Long userId;

	// one request can have many computer parts
	// fetchType = - eager. Can get pc parts without calling get()
	@ManyToMany(fetch = FetchType.EAGER, 
			cascade= {CascadeType.PERSIST,
			CascadeType.MERGE,
			CascadeType.REFRESH,
			CascadeType.DETACH})
	@JoinTable(name = "requests_computer_parts", 
				joinColumns = @JoinColumn(name = "request_id"), 
				inverseJoinColumns = @JoinColumn(name = "computer_part_id"))
	private Set<ComputerPart> computerParts = new HashSet<>();
	
	// List of computer parts - for conversion to HashSet when request form filled
	// this field is not used for entity mapping
	@Transient
	private List<String> cpList;

	public Request() {
	}

	public Request(RequestStatus requestStatus, Date dateSubmitted, String text) throws ParseException {
		this.text = text;
		this.requestStatus = requestStatus;
		this.dateSubmitted = dateSubmitted;
	}
	
	


	public Request(RequestStatus requestStatus, Date dateSubmitted, String text, Long userId,
			Set<ComputerPart> computerParts) {
		super();
		this.requestStatus = requestStatus;
		this.dateSubmitted = dateSubmitted;
		this.text = text;
		this.userId = userId;
		this.computerParts = computerParts;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Enumerated(EnumType.STRING)
	public RequestStatus getRequestStatus() {
		return requestStatus;
	}

	@Enumerated(EnumType.STRING)
	public void setRequestStatus(RequestStatus requestStatus) {
		this.requestStatus = requestStatus;
	}

	public Date getDateSubmitted() {
		return dateSubmitted;
	}

	public void setDateSubmitted(Date dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	
	public Set<ComputerPart> getComputerParts() {
		return computerParts;
	}

	public void setComputerParts(Set<ComputerPart> computerParts) {
		this.computerParts = computerParts;
	}
	
	
	public List<String> getCpList() {
		return cpList;
	}

	public void setCpList(List<String> cpList) {
		this.cpList = cpList;
	}

	@Override
	public String toString() {
		return "Request [id=" + id + ", requestStatus=" + requestStatus + ", dateSubmitted=" + DateUtils.dateToString(dateSubmitted) + ", text="
				+ text + ", userId=" + userId + ", computerParts=" + computerParts + "]";
	}



}
