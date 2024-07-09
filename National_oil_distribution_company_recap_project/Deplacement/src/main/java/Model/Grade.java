package Model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="grade")
public class Grade {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
		private Long id;
		private String grade;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getGrade() {
			return grade;
		}
		public void setGrade(String grade) {
			this.grade = grade;
		}
		@Override
		public String toString() {
			return "Grade [id=" + id + ", grade=" + grade + "]";
		}
		public Grade(Long id, String grade) {
			super();
			this.id = id;
			this.grade = grade;
		}
		public Grade() {
			super();
			// TODO Auto-generated constructor stub
		}
	
		
		
}
