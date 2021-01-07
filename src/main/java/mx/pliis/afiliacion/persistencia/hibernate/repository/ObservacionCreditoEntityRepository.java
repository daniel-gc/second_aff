package mx.pliis.afiliacion.persistencia.hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.pliis.afiliacion.persistencia.hibernate.entity.ObservacionCreditoEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.TipoArchivoEntity;

@Repository
public interface ObservacionCreditoEntityRepository extends JpaRepository<ObservacionCreditoEntity, Integer> {

}
