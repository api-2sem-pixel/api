package relatorio;

import java.time.LocalDate;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.joda.time.Period;

public class CalcularVerba {

	
	public double calculaQuantidadeHrsPorVerba(String verba, LocalDateTime dataInicial, LocalDateTime dataFinal) {
		switch (verba) {
		case "1601":
			if(isNoturna(dataInicial, dataFinal) || isFimDeSemana(dataInicial)) {
				return 0;
			}
			if(getQuantidadeHorasTrabalhadas(dataInicial, dataFinal) >= 2) {
				return 2;
			}
			return 1;
		case "1602":
			if(isNoturna(dataInicial, dataFinal)) {
				return 0;
			}
			return getQuantidadeHorasTrabalhadas(dataInicial, dataFinal);
		case "3000":
			if(!isNoturna(dataInicial, dataFinal) || isFimDeSemana(dataInicial)) {
				return 0;
			}
			if(getQuantidadeHorasTrabalhadas(dataInicial, dataFinal) >= 2) {
				return 2 * 1.1429;
			}
			return 1 * 1.1429;
		case "3001":
			if(!isNoturna(dataInicial, dataFinal)) {
				return 0;
			}
			return getQuantidadeHorasTrabalhadas(dataInicial, dataFinal) * 1.1429; 
		default:

		}
		return 0;
	}
	
	public boolean isNoturna(LocalDateTime dataInicial, LocalDateTime dataFinal) {
		if(dataInicial.getHourOfDay() >= 6 && dataInicial.getHourOfDay() <= 21) {
			return false;
		}
		return true;
	}
	
	public boolean isFimDeSemana(LocalDateTime dataInicial) {
		if(dataInicial.getDayOfWeek() > 5) {
			return true;
		}
		return false;
	}
	
	public int getQuantidadeHorasTrabalhadas(LocalDateTime ini, LocalDateTime fim) {
		Period p = new Period(ini, fim);
		return p.getHours();
	}
}
