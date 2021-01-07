package mx.pliis.afiliacion.persistencia.hibernate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.pliis.afiliacion.persistencia.hibernate.entity.CatalogoEntity;

@Repository
public interface CatalogoEntityRepository extends JpaRepository<CatalogoEntity, Integer> {

	/**
	 * @author Daniel
	 * @descripciòn: Método para consultar catalogo por codigo
	 * @return CatalogoEntity
	 */
	public List<CatalogoEntity> findByCode(String code);

	/**
	 * @author Daniel
	 * @descripciòn: Método para consultar catalogo por valor
	 * @return CatalogoEntity
	 */
	public CatalogoEntity findByValue(String value);
}
