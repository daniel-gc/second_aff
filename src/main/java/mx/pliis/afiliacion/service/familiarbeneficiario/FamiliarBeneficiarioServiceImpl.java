package mx.pliis.afiliacion.service.familiarbeneficiario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mx.pliis.afiliacion.dto.FamiliarBeneficiarioDTO;
import mx.pliis.afiliacion.persistencia.hibernate.entity.FamiliarBeneficiarioEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.RelacionFamiliarEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.SexoEntity;
import mx.pliis.afiliacion.persistencia.hibernate.repository.AfiliadoEntityRepository;
import mx.pliis.afiliacion.persistencia.hibernate.repository.FamiliarBeneficiarioRepository;
import mx.pliis.afiliacion.persistencia.hibernate.repository.RelacionFamiliarEntityRepository;
import mx.pliis.afiliacion.persistencia.hibernate.repository.SexoEntityRepository;
import mx.pliis.afiliacion.persistencia.hibernate.repository.UsuarioEntityRepository;
import mx.pliis.afiliacion.utils.familiarbeneficiario.FamiliarBeneficiarioUtilComponent;

@Service
@RequiredArgsConstructor
@Log4j2
public class FamiliarBeneficiarioServiceImpl implements FamiliarBeneficiarioService{
	
	@Autowired
	private SexoEntityRepository sexoEntityRepository;

		@Autowired
	    private  RelacionFamiliarEntityRepository relacionFamiliarEntityRepository;
		
	    @Autowired
	    private  UsuarioEntityRepository usuarioEntityRepository;
		
	    @Autowired
	    private  FamiliarBeneficiarioRepository familiarBeneficiarioRepository;
		
	    @Autowired
	    private  AfiliadoEntityRepository afiliadoEntityRepository;
		
	    @Autowired
	    private  FamiliarBeneficiarioUtilComponent familiarBeneficiarioUtilComponent;
	    

	@Override
	public List<FamiliarBeneficiarioDTO> getFamiliaresBeneficiario(Integer idUsuarioArq) {

		var usEntOpt = this.usuarioEntityRepository.findByArqIdUsuario(idUsuarioArq);
		if (usEntOpt.isEmpty()) {
			//log.error("El usuario con arqIdUsuario = {} no existe en la BD de afiliados.", idUsuarioArq);
			throw new IllegalArgumentException("El usuario especificado no existe en la BD de afiliados.");
		}
		var usuarioEntity = usEntOpt.get();
		
		var familiaresBeneficiario = this.familiarBeneficiarioRepository
				.findFamBeneficiarioByIdUsuario(usuarioEntity);
		
		if(familiaresBeneficiario.isEmpty()) {
			return new ArrayList<>();
		}
		
		var familiaresBeneficiarios = familiaresBeneficiario.get();
		var familiaresBeneficiarioDTO = this.familiarBeneficiarioUtilComponent.copyFromEntityList(familiaresBeneficiarios);

		return familiaresBeneficiarioDTO;
	}

	@Override
	public Long saveFamiliarBeneficiario(FamiliarBeneficiarioDTO nuevoFamiiarBeneficiarioDTO) {
			FamiliarBeneficiarioEntity famBeneficiarioEnt = this.familiarBeneficiarioUtilComponent.copyFromFamimiliarDTO(nuevoFamiiarBeneficiarioDTO);
			
			var usEntOpt = this.usuarioEntityRepository.findByArqIdUsuario(nuevoFamiiarBeneficiarioDTO.getArqIdUsuario());
			if (usEntOpt.isEmpty()) {
				log.error("El usuario con arqIdUsuario = {} no existe en la BD de afiliados.", nuevoFamiiarBeneficiarioDTO.getArqIdUsuario());
				throw new IllegalArgumentException("El usuario especificado no existe en la BD de afiliados.");
			}
			
			if (sexoEntityRepository.findById(nuevoFamiiarBeneficiarioDTO.getSexo().getIdSexo()).isPresent()) {
				SexoEntity sexoEntity = sexoEntityRepository.findById(nuevoFamiiarBeneficiarioDTO.getSexo().getIdSexo()).get();
				famBeneficiarioEnt.setSexo(sexoEntity);
			} else {
				return -1L;
			}
			
			RelacionFamiliarEntity relacionFamiliarEntity = null;
			if (relacionFamiliarEntityRepository.findById(nuevoFamiiarBeneficiarioDTO.getRelacionFamiliar().getIdRelacionFamiliar()).isPresent()) {
				relacionFamiliarEntity = relacionFamiliarEntityRepository.findById(nuevoFamiiarBeneficiarioDTO.getRelacionFamiliar().getIdRelacionFamiliar())
						.get();
				famBeneficiarioEnt.setRelacionFamiliar(relacionFamiliarEntity);
			} else {
				return -1L;
			}
			
			var usrEntity = usEntOpt.get();
			
			famBeneficiarioEnt.setIdUsuario(usrEntity);
			try {
				famBeneficiarioEnt = familiarBeneficiarioRepository.save(famBeneficiarioEnt);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		
		
		return famBeneficiarioEnt.getIdFamiliarBeneficiario().longValue();
	}

	@Override
	public Boolean updateFamiliar(FamiliarBeneficiarioDTO updateFamiiarBeneficiarioDTO) {
		
		var usEntOpt = this.usuarioEntityRepository.findByArqIdUsuario(updateFamiiarBeneficiarioDTO.getArqIdUsuario());
		if (usEntOpt.isEmpty()) {
			log.error("El usuario con arqIdUsuario = {} no existe en la BD de afiliados.", updateFamiiarBeneficiarioDTO.getArqIdUsuario());
			throw new IllegalArgumentException("El usuario especificado no existe en la BD de afiliados.");
		}
		
		var beneficiario = this.familiarBeneficiarioRepository.findById(updateFamiiarBeneficiarioDTO.getIdFamBeneficiario());
		if(beneficiario.isEmpty()) {
			//log.error("El familiar del beneficiario no se ecnontro.");
			throw new IllegalArgumentException("El familiar del beneficiario no se ecnontro.");
		}
		
		FamiliarBeneficiarioEntity famBeneficiarioEnt = this.familiarBeneficiarioUtilComponent.copyFromFamimiliarDTO(updateFamiiarBeneficiarioDTO);
		
		if (sexoEntityRepository.findById(updateFamiiarBeneficiarioDTO.getSexo().getIdSexo()).isPresent()) {
			SexoEntity sexoEntity = sexoEntityRepository.findById(updateFamiiarBeneficiarioDTO.getSexo().getIdSexo()).get();
			famBeneficiarioEnt.setSexo(sexoEntity);
		} else {
			return false;
		}
		
		RelacionFamiliarEntity relacionFamiliarEntity = null;
		if (relacionFamiliarEntityRepository.findById(updateFamiiarBeneficiarioDTO.getRelacionFamiliar().getIdRelacionFamiliar()).isPresent()) {
			relacionFamiliarEntity = relacionFamiliarEntityRepository.findById(updateFamiiarBeneficiarioDTO.getRelacionFamiliar().getIdRelacionFamiliar())
					.get();
			famBeneficiarioEnt.setRelacionFamiliar(relacionFamiliarEntity);
		} else {
			return false;
		}
		
		var usrEntity = usEntOpt.get();
		famBeneficiarioEnt.setIdUsuario(usrEntity);
		famBeneficiarioEnt = familiarBeneficiarioRepository.save(famBeneficiarioEnt);
		
		
		return true;
	}

	@Override
	public Boolean deleteFamiliarBeneficiario(Integer idFamiliarBeneficiario) {

		Optional<FamiliarBeneficiarioEntity> opcionalCreditoEntity = familiarBeneficiarioRepository.findById(idFamiliarBeneficiario.intValue());
		FamiliarBeneficiarioEntity famBeneficiarioEntity = opcionalCreditoEntity.get();
		
		if (opcionalCreditoEntity.isPresent()) {
			familiarBeneficiarioRepository.delete(famBeneficiarioEntity);
			return true;
		}
		return false;
	}
	


}
