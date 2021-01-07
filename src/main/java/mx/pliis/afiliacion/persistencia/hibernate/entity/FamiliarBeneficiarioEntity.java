package mx.pliis.afiliacion.persistencia.hibernate.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "familiar_beneficiario")
@NamedQueries({
    @NamedQuery(name = "FamiliarBeneficiarioEntity.findAll", query = "SELECT f FROM FamiliarBeneficiarioEntity f"),
    @NamedQuery(name = "FamiliarBeneficiarioEntity.findByIdFamBeneficiario", query = "SELECT f FROM FamiliarBeneficiarioEntity f WHERE f.idFamiliarBeneficiario = :idFamiliarBeneficiario"),
    @NamedQuery(name = "FamiliarBeneficiarioEntity.findByNombres", query = "SELECT f FROM FamiliarBeneficiarioEntity f WHERE f.nombres = :nombres"),
    @NamedQuery(name = "FamiliarBeneficiarioEntity.findByApellidoPaterno", query = "SELECT f FROM FamiliarBeneficiarioEntity f WHERE f.apPaterno = :apellidoPaterno"),
    @NamedQuery(name = "FamiliarBeneficiarioEntity.findByApellidoMaterno", query = "SELECT f FROM FamiliarBeneficiarioEntity f WHERE f.apMaterno = :apellidoMaterno"),
    @NamedQuery(name = "FamiliarBeneficiarioEntity.findByEmail", query = "SELECT f FROM FamiliarBeneficiarioEntity f WHERE f.email = :email"),
    @NamedQuery(name = "FamiliarBeneficiarioEntity.findByFechaNacimiento", query = "SELECT f FROM FamiliarBeneficiarioEntity f WHERE f.fechaNacimiento = :fechaNacimiento")})
public class FamiliarBeneficiarioEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3091112950881285779L;
	

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_fam_beneficiario")
    private Integer idFamiliarBeneficiario;
	
	@Basic(optional = false)
    @NotNull
    @Column(name = "nombres")
	private String nombres;
	
	@Basic(optional = false)
    @NotNull
    @Column(name = "ap_paterno")
	private String apPaterno;
	
	@Basic(optional = false)
    @NotNull
    @Column(name = "ap_materno")
	private String apMaterno;
	
	@JoinColumn(name = "id_relacion_familiar", referencedColumnName = "id_relacion_familiar")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
    private RelacionFamiliarEntity relacionFamiliar;
	
	@Basic(optional = false)
    @Column(name = "fecha_nacimiento")
	private Date fechaNacimiento;
	
	@JoinColumn(name = "id_sexo", referencedColumnName = "id_sexo")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private SexoEntity sexo;
	
	@Basic(optional = true)
    @Column(name = "curp")
	private String curp;
	
	@Basic(optional = true)
    @Column(name = "telefono")
	private String telefono;
	
	@Basic(optional = true)
    @Column(name = "email")
	private String email;
	
	@JoinColumn(name = "id_arq_usuario", referencedColumnName = "id_usuario")
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private UsuarioEntity idUsuario;

	public Integer getIdFamiliarBeneficiario() {
		return idFamiliarBeneficiario;
	}

	public void setIdFamiliarBeneficiario(Integer idFamiliarBeneficiario) {
		this.idFamiliarBeneficiario = idFamiliarBeneficiario;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApPaterno() {
		return apPaterno;
	}

	public void setApPaterno(String apPaterno) {
		this.apPaterno = apPaterno;
	}

	public String getApMaterno() {
		return apMaterno;
	}

	public void setApMaterno(String apMaterno) {
		this.apMaterno = apMaterno;
	}

	public RelacionFamiliarEntity getRelacionFamiliar() {
		return relacionFamiliar;
	}

	public void setRelacionFamiliar(RelacionFamiliarEntity relacionFamiliar) {
		this.relacionFamiliar = relacionFamiliar;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public SexoEntity getSexo() {
		return sexo;
	}

	public void setSexo(SexoEntity sexo) {
		this.sexo = sexo;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UsuarioEntity getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(UsuarioEntity idUsuario) {
		this.idUsuario = idUsuario;
	}
	
}
