package mx.pliis.afiliacion.service.job;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import mx.pliis.afiliacion.persistencia.hibernate.entity.AfiliadoEntity;
import mx.pliis.afiliacion.persistencia.hibernate.repository.AfiliadoEntityRepository;
import mx.pliis.afiliacion.persistencia.hibernate.repository.CatalogoEntityRepository;

@Component
public class JobEnvioDatosFunerariaService {

	private AfiliadoEntityRepository afiliadoEntityRepository;

	private CatalogoEntityRepository catalogoEntityRepository;

	// Cron cada dia a las 00:00:01 AM
	// @Scheduled(cron = "0 * * * * ?", zone="America/Mexico_City")
	public void enviaDatosFuneraria() {
		// OBTENEMOS TODOS LOS AFILIADOS DE LA BD
		var list = afiliadoEntityRepository.findAll();

		// VALIDAMOS LA LISTA DE AFILIADOS
		if (!list.isEmpty()) {

			List<String[]> dataCsv = construyeCsv(list);

			// CONSULTAMOS DEL CATALOGO TODOS LAS FUNERARIAS
			var funerarias = catalogoEntityRepository.findByCode("FUNER");
			if (!funerarias.isEmpty()) {
				// ITERAMOS LAS FUNERARIAS
				funerarias.forEach(f -> {

				});
			}
		}
	}

	public List<String[]> construyeCsv(List<AfiliadoEntity> list) {
		List<String[]> lisGen = new ArrayList<>();
		String[] headers = { "IDENTIFICADOR UNICO", "NOMBRE", "APELLIDO PATERNO", "APELLIDO MATERNO",
				"FECHA DE NACIMIENTO", "EDAD", "InicioVigencia", "FinVigencia", "GENERO" };
		lisGen.add(headers);
		String[] data = null;
		for (AfiliadoEntity f : list) {
			data = new String[headers.length];
			data[0] = new String(f.getIdAfiliado() + "");
			data[1] = f.getNombres();
			data[2] = f.getApellidoPaterno();
			data[3] = f.getApellidoMaterno();
			data[4] = f.getFechaNacimiento() == null ? ""
					: f.getFechaNacimiento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

			if (f.getFechaNacimiento() != null) {
				LocalDate d = LocalDate.now();
				Period period = Period.between(f.getFechaNacimiento(), d);
				data[5] = period.getYears() + "";
			} else {
				data[5] = "";
			}
			data[6] = "";
			data[7] = "";
			data[8] = f.getIdSexo() != null ? f.getIdSexo().getNombre() : "";

			lisGen.add(data);
		}
		return lisGen;
	}

	public FileWriter writeCsv(List<String[]> data) throws IOException {
		FileWriter f = new FileWriter("new.csv");
		for (String[] s : data) {
			f.append(String.join(",", s));
			f.append("\n");
		}
		f.flush();
		f.close();
		return f;
	}
	
	public void sendMail() {
		
	}

}
