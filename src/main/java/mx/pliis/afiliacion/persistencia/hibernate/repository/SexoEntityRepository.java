package mx.pliis.afiliacion.persistencia.hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.pliis.afiliacion.persistencia.hibernate.entity.SexoEntity;

@Repository
public interface SexoEntityRepository extends JpaRepository<SexoEntity, Integer> {

}
