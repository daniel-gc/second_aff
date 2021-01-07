package mx.pliis.afiliacion.persistencia.hibernate.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_funerarias")
public class UserFunerariasEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2250648820732706948L;

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_user_funerarias")
    private Integer idUserFunerarias;
	
	@JoinColumn(name = "id_cat", referencedColumnName = "id_cat", insertable = false, updatable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private CatalogoEntity catalogoEntity;
		
	@JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", insertable = false, updatable = false)
	@ManyToOne(fetch = FetchType.LAZY)
    private UsuarioEntity usuarioEntity;

	/**
	 * @return the idUserFunerarias
	 */
	public Integer getIdUserFunerarias() {
		return idUserFunerarias;
	}

	/**
	 * @param idUserFunerarias the idUserFunerarias to set
	 */
	public void setIdUserFunerarias(Integer idUserFunerarias) {
		this.idUserFunerarias = idUserFunerarias;
	}

	/**
	 * @return the catalogoEntity
	 */
	public CatalogoEntity getCatalogoEntity() {
		return catalogoEntity;
	}

	/**
	 * @param catalogoEntity the catalogoEntity to set
	 */
	public void setCatalogoEntity(CatalogoEntity catalogoEntity) {
		this.catalogoEntity = catalogoEntity;
	}

	/**
	 * @return the usuarioEntity
	 */
	public UsuarioEntity getUsuarioEntity() {
		return usuarioEntity;
	}

	/**
	 * @param usuarioEntity the usuarioEntity to set
	 */
	public void setUsuarioEntity(UsuarioEntity usuarioEntity) {
		this.usuarioEntity = usuarioEntity;
	}

	
}
