package mx.pliis.afiliacion.service.familiarbeneficiario;

import java.util.List;

import mx.pliis.afiliacion.dto.FamiliarBeneficiarioDTO;

public interface FamiliarBeneficiarioService {
	
	List<FamiliarBeneficiarioDTO> getFamiliaresBeneficiario(Integer idUsuarioArq);
	
	Long saveFamiliarBeneficiario(FamiliarBeneficiarioDTO nuevoFamiiarBeneficiarioDTO);
	
	Boolean updateFamiliar(FamiliarBeneficiarioDTO nuevoFamiiarBeneficiarioDTO);
	
	Boolean deleteFamiliarBeneficiario(Integer idFamiliarBeneficiario);
	
}
