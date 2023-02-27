package pojo_MySql;

import javax.persistence.*;

@Entity
@Table(name = "admin")
@NamedQueries({
		@NamedQuery(name = "Admin.findById", query = "select a from Admin a where a.idAdmin = :id"),
        @NamedQuery(name = "Admin.findByLoginAndPassword", query = "select a from Admin a where a.login = :login and a.motDePasse = :mdp"),
})
public class Admin {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_admin")
    private Integer idAdmin;

    @Column(name = "login", unique = true)
    private String login;
    
    @Column(name = "mot_de_passe")
    private String motDePasse;

	public Integer getIdAdmin() {
		return idAdmin;
	}

	public void setIdAdmin(Integer idAdmin) {
		this.idAdmin = idAdmin;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
    
    
}
