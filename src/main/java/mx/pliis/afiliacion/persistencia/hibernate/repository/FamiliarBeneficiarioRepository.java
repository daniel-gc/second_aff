package mx.pliis.afiliacion.persistencia.hibernate.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.pliis.afiliacion.persistencia.hibernate.entity.FamiliarBeneficiarioEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.UsuarioEntity;

@Repository
public interface FamiliarBeneficiarioRepository extends JpaRepository<FamiliarBeneficiarioEntity, Integer>{

	Optional<FamiliarBeneficiarioEntity> findByIdFamiliarBeneficiario(Integer idFamiliarBeneficiario);
	
	Optional<List<FamiliarBeneficiarioEntity>> findFamBeneficiarioByIdUsuario(UsuarioEntity idUsuario);
	
}
