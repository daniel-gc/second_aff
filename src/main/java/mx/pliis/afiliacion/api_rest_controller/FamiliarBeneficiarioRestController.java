package mx.pliis.afiliacion.api_rest_controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;
import mx.pliis.afiliacion.dto.FamiliarBeneficiarioDTO;
import mx.pliis.afiliacion.service.familiarbeneficiario.FamiliarBeneficiarioService;

@RestController
@RequestMapping("/familiarbeneficiario")
@CrossOrigin(origins = "*")
@Log4j2
public class FamiliarBeneficiarioRestController {

	
	@Autowired
	private FamiliarBeneficiarioService familiarBeneficiarioService;

	@PostMapping(value = "/nuevo")
	public ResponseEntity<Long> guardarNuevoFamiliarBeneficiario(@RequestBody FamiliarBeneficiarioDTO nuevoFamiliarBeneficiarioDTO) {
		Long ret = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		HttpStatus status = HttpStatus.OK;

		try {
			ret = this.familiarBeneficiarioService.saveFamiliarBeneficiario(nuevoFamiliarBeneficiarioDTO);
		} catch (Exception e) {
			responseHeaders.set("Mensaje", "Ocurrió un error al guardar el familiar del beneficiario: " + e.getLocalizedMessage());
			//log.error(e.getLocalizedMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			return new ResponseEntity<>(null, responseHeaders, status);
		}

		return new ResponseEntity<>(ret, status);
	}
	
	@PostMapping(value = "/actualizar")
	public ResponseEntity<Boolean> actualizarFamiliarBeneficiario(@RequestBody FamiliarBeneficiarioDTO nuevoFamiliarBeneficiarioDTO) {
		Boolean ret = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		HttpStatus status = HttpStatus.OK;

		try {
			ret = this.familiarBeneficiarioService.updateFamiliar(nuevoFamiliarBeneficiarioDTO);
		} catch (Exception e) {
			responseHeaders.set("Mensaje", "Ocurrió un error al actualizar el familiar del beneficiario: " + e.getLocalizedMessage());
			//log.error(e.getLocalizedMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			return new ResponseEntity<>(null, responseHeaders, status);
		}

		return new ResponseEntity<>(ret, status);
	}
	
	@GetMapping("/todos")
	public ResponseEntity<List<FamiliarBeneficiarioDTO>> getFamiliaresBeneficiario(@RequestParam("arqIdUsuario") Integer arqIdUsuario) {

		try {
			var dtoList = this.familiarBeneficiarioService.getFamiliaresBeneficiario(arqIdUsuario);
			return new ResponseEntity<>(dtoList, HttpStatus.OK);
		} catch (Exception e) {
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("Mensaje", "Ocurrió un error al obtener los familiares del beneficiario: " + e.getLocalizedMessage());
			//log.error(e.getLocalizedMessage());
			return new ResponseEntity<>(null, responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@DeleteMapping("/eliminar")
	public ResponseEntity<Boolean> deleteFamiliar(@RequestParam("idFamiliarBeneficiario") Integer idFamiliarBeneficiario) {
		Boolean ret = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		HttpStatus status = HttpStatus.OK;

		try {
			ret = familiarBeneficiarioService.deleteFamiliarBeneficiario(idFamiliarBeneficiario);
		} catch (Exception e) {
			responseHeaders.set("Mensaje", "Ocurrió un error al borrar el familiar del beneficiario: " + e.getLocalizedMessage());
			//log.error(e.getLocalizedMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			return new ResponseEntity<>(null, responseHeaders, status);
		}

		return new ResponseEntity<>(ret, status);
	}
	
}
