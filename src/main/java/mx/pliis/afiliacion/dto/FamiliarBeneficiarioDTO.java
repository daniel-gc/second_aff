package mx.pliis.afiliacion.dto;

import java.util.Date;

import lombok.Data;

@Data
public class FamiliarBeneficiarioDTO {
	
	private Integer idFamBeneficiario;
	private String nombres;
	private String apPaterno;
	private String apMaterno;
    private RelacionFamiliarDTO relacionFamiliar;//PARENTESCO
	private Date fechaNaciomiento;
	private SexoDTO sexo;
	private String curp;
	private String telefono;
	private String email;
	private Integer arqIdUsuario;
	
}
