package exercici1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.opencsv.CSVWriter;

@SuppressWarnings("resource")
public class App {

	public static void main(String[] args) {
		boolean sortir = false;
		//Cal indicar ruta!!
		String path = "/Users/adriamarticomas/git/S01T03N03MartiComasAdria/S01T03N03MartiComasAdria/data.csv";

		//Crear archiu csv si no esta creat
		//newCsv(path);
		
		ArrayList<Persona> llistaPersones = new ArrayList<Persona>();
		parseCsv(path, llistaPersones);

		System.out.println("DADES FITXER CSV");
		llistaPersones.forEach(System.out::println);

		do {

			switch (menu()) {
			case 0:
				System.out.println("Gracies per utilitzar l'aplicació");
				sortir = true;
				break;
			case 1:
				// INTRODUIR PERSONA
				introduirPersona(path);
				llistaPersones.removeAll(llistaPersones);
				parseCsv(path, llistaPersones);
				break;
			case 2:
				// MOSTRAR PERSONES PER NOM A-Z
				llistaPersones.sort((p1, p2) -> p1.getNom().compareTo(p2.getNom()));
				llistaPersones.forEach(System.out::println);

				break;
			case 3:
				// MOSTRAR PERSONES PER NOM Z-A
				llistaPersones.sort((p1, p2) -> p2.getNom().compareTo(p1.getNom()));
				llistaPersones.forEach(System.out::println);

				break;
			case 4:
				// MOSTRAR PERSONES PER COGNOM A-Z
				llistaPersones.sort((p1, p2) -> p1.getCognoms().compareTo(p2.getCognoms()));
				llistaPersones.forEach(System.out::println);

				break;
			case 5:
				// MOSTRAR PERSONES PER COGNOM Z-A
				llistaPersones.sort((p1, p2) -> p2.getCognoms().compareTo(p1.getCognoms()));
				llistaPersones.forEach(System.out::println);

				break;
			case 6:
				// MOSTRAR PERSONES PER DNI 1-9
				llistaPersones.sort((p1, p2) -> p1.getDni().compareTo(p2.getDni()));
				llistaPersones.forEach(System.out::println);

				break;
			case 7:
				// MOSTRAR PERSONES PER DNI 9-1
				llistaPersones.sort((p1, p2) -> p2.getDni().compareTo(p1.getDni()));
				llistaPersones.forEach(System.out::println);

			}
		} while (!sortir);

	}

	private static int menu() {

		Scanner entrada = new Scanner(System.in);
		byte opcio;
		final byte MINIMO = 0;
		final byte MAXIMO = 7;

		do {
			System.out.println("\nMENú PRINCIPAL");
			System.out.println("1. Introduir persona ");
			System.out.println("2. Mostrar persones ordenades per nom (A-Z) ");
			System.out.println("3. Mostrar persones ordenades per nom (Z-A) ");
			System.out.println("4. Mostrar les persones ordenades per cognoms (A-Z).");
			System.out.println("5. Mostrar les persones ordenades per cognoms (Z-A).");
			System.out.println("6. Mostrar les persones ordenades per DNI (1-9)");
			System.out.println("7. Mostrar les persones ordenades per DNI (9-1)");

			System.out.println("0. Sortir de l'aplicacio");

			opcio = entrada.nextByte();
			if (opcio < MINIMO || opcio > MAXIMO) {
				System.out.println("Escull una opció vàlida");
			}
		} while (opcio < MINIMO || opcio > MAXIMO);

		return opcio;

	}

	public static void parseCsv(String path, ArrayList<Persona> llistaPersones) {
		String line = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			br.readLine();

			while ((line = br.readLine()) != null) {
				String split[] = line.split(",");
				llistaPersones.add(new Persona(split[0], split[1], split[2]));
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		}

	}

	public static void newCsv(String path) {

		File file = new File(path);
		try {
			// create FileWriter object with file as parameter
			FileWriter outputfile = new FileWriter(file);

			// create CSVWriter object filewriter object as parameter
			CSVWriter writer = new CSVWriter(outputfile);

			// adding header to csv
			String[] header = { "Nom", "Cognoms", "DNI" };
			writer.writeNext(header);

			// add data to csv
			String[] data1 = { "Ona", "Lascorz Jane", "12345678A" };
			writer.writeNext(data1);

			String[] data2 = { "Blai", "Juanet Cases", "22345678A" };
			writer.writeNext(data2);

			String[] data3 = { "Andrea", "Ramirez Lopez", "32345678A" };
			writer.writeNext(data3);

			String[] data4 = { "Anna", "Cristobal Bassaganyes", "42345678A" };
			writer.writeNext(data4);

			// closing writer connection
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void writeCsv(String path, Persona persona) {
		String[] newCsvLine = { "", "", "" };

		newCsvLine[0] = persona.getNom();
		newCsvLine[1] = persona.getCognoms();
		newCsvLine[2] = persona.getDni();

		try {
			FileWriter outputfile = new FileWriter(path, true);
			CSVWriter writer = new CSVWriter(outputfile);

			writer.writeNext(newCsvLine);

			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(
				"S'ha introduit correctament a: " + newCsvLine[0] + " " + newCsvLine[1] + " amb DNI: " + newCsvLine[2]);

	}

	public static void introduirPersona(String path) {
		Scanner sc = new Scanner(System.in);

		System.out.println("INTRODUIR PERSONA:");
		System.out.println("Introdueix el nom:");
		String nom = sc.nextLine();
		System.out.println("Introdueix els cognoms:");
		String cognoms = sc.nextLine();
		System.out.println("Introdueix el DNI:");
		String dni = sc.nextLine();

		Persona persona = new Persona(nom, cognoms, dni);

		writeCsv(path, persona);
	}

}
