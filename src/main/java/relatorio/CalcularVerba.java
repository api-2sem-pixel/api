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
			//break;
		case "1602":
			if(isNoturna(dataInicial, dataFinal)) {
				return 0;
			}
			return getQuantidadeHorasTrabalhadas(dataInicial, dataFinal);
			//break;
		case "3000":
			if(!isNoturna(dataInicial, dataFinal) || isFimDeSemana(dataInicial)) {
				return 0;
			}
			if(getQuantidadeHorasTrabalhadas(dataInicial, dataFinal) >= 2) {
				return 2 * 1.1429;
			}
			return 1 * 1.1429;
			//break;	
		case "3001":
			if(!isNoturna(dataInicial, dataFinal)) {
				return 0;
			}
			return getQuantidadeHorasTrabalhadas(dataInicial, dataFinal) * 1.1429; 
			//break;
		default:

		}
		return 0;
	}
	
	/*
	public static void main(String[] args) {
		DateTime dt1 = new DateTime("2023-06-01T17:39:45");
		DateTime dt2 = new DateTime("2023-06-01T21:39:45");
		Period p = new Period(dt1, dt2);

		System.out.println("DAYS: " + p.getDays());
		System.out.println("HOURS: " + p.getHours());
		System.out.println("MINUTES: " + p.getMinutes());
	}
	*/

	public boolean isNoturna(LocalDateTime dataInicial, LocalDateTime dataFinal) {
		if(dataInicial.getHourOfDay() >= 6 && dataFinal.getHourOfDay() <= 22) {
			return false;
		}
		return true;
	}
	
	public boolean isFimDeSemana(LocalDateTime dataInicial) {
		if(dataInicial.getDayOfWeek() >= 2 && dataInicial.getDayOfWeek() <= 6) {
			return false;
		}
		return true;
	}
	
	public int getQuantidadeHorasTrabalhadas(LocalDateTime ini, LocalDateTime fim) {
		Period p = new Period(ini, fim);
		return p.getHours();
		//uma alternativa é meter:
		// fim.getHours() - ini.getHours
	}
}
