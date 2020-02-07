package pcapp.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="computer_part")
public class ComputerPart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="part_name")
	private String partName;
	
	// one computer part can be included in many requests
	// fetchType = - eager. Can get requests without calling get()
	@ManyToMany(fetch=FetchType.EAGER, cascade= {CascadeType.PERSIST,
			CascadeType.MERGE,
			CascadeType.REFRESH,
			CascadeType.DETACH},
			mappedBy="computerParts")
	private Set<Request> requests = new HashSet<Request>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public Set<Request> getRequests() {
		return requests;
	}

	public void setRequests(Set<Request> requests) {
		this.requests = requests;
	}
	
	public ComputerPart() {
	}
	
	public ComputerPart(String partName) {
		super();
		this.partName = partName;
	}

	public ComputerPart(String partName, Set<Request> requests) {
		super();
		this.partName = partName;
		this.requests = requests;
	}

	@Override
	public String toString() {
		return "ComputerPart [id=" + id + ", partName=" + partName + "]";
	}
	

	
}
