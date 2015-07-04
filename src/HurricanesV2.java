/**
 * 
 * @author timothyroejr
 * @version 7/3/15
 * 
 */
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
public class HurricanesV2 {

	//public static String[] month = new String[12];
	public static String[] year;
	public static String[] name;
	public static int[] category;
	public static int[] pressure;
	public static double[] windSpeed;
	public static double categoryAverage;
	public static double pressureAverage;
	public static double windAverage;
	public static double maxCategory;
	public static double maxPressure;
	public static double maxWindSpeed;
	public static double minCategory;
	public static double minPressure;
	public static double minWindSpeed;
	public static int cat1;
	public static int cat2;
	public static int cat3;
	public static int cat4;
	public static int cat5;
	static class Data {
		public void readHurricaneData() throws IOException {
			File hurricaneData = new File("/Users/timothyroejr/Documents/workspace/6.04 Hurricane Data/src/hurcdata2.txt");
			Scanner inFile = new Scanner(hurricaneData);
			int count = 0;
			while (inFile.hasNextLine()) {
				count++;
				inFile.nextLine();
			}
			year = new String[count];
			name = new String[count];
			category = new int[count];
			pressure = new int[count];
			windSpeed = new double[count];
			int index = 0;
			Scanner inFileRedeclaration = new Scanner(hurricaneData);
			for (int i = 0; i < 59; i++) {
				year[index] = inFileRedeclaration.next();
				String month = inFileRedeclaration.next();
				int pressureData = Integer.parseInt(inFileRedeclaration.next());
				pressure[index] = pressureData;
				double windData = Double.parseDouble(inFileRedeclaration.next());
				windData = windData*1.15077945;
				if (windData < 95.0) {
					category[index] = 1;
					cat1++;
				} else if (windData <= 110) {
					category[index] = 2;
					cat2++;
				} else if (windData <= 129) {
					category[index] = 3;
					cat3++;
				} else if (windData <= 156 ) {
					category[index] = 4;
					cat4++;
				} else if (windData > 156) {
					category[index] = 5;
					cat5++;
				}
				categoryAverage += category[index];
				windSpeed[index] = windData;
				pressureAverage += pressure[index];
				windAverage += windSpeed[index];
				name[index] = inFileRedeclaration.next();
				int categoryValue = category[index];
				if  (maxCategory < categoryValue) {
					maxCategory = category[index];
					minCategory = category[index];
				}
				if (minCategory > categoryValue) {
					minCategory = category[index];
				}
				int pressureValue = pressure[index];
				if (maxPressure < pressureValue) {
					maxPressure = pressure[index];
					minPressure = pressure[index];
				}
				if (minPressure > pressureValue) {
					minPressure = pressure[index];
				}
				double windSpeedValue = windSpeed[index];
				if (maxWindSpeed < windSpeedValue) {
					maxWindSpeed = windSpeed[index];
					minWindSpeed = windSpeed[index];
				}
				if (minWindSpeed > windSpeedValue) {
					minWindSpeed = windSpeed[index];
				}
				index++;
			}
			categoryAverage = categoryAverage/count;
			pressureAverage = pressureAverage/count;
			windAverage = windAverage/count;
		}
	}
	public static void exportData() throws IOException {
		PrintWriter output = new PrintWriter(new File("Hurricane_Data_From_1980_to_2006.txt"));
		output.printf("%s%14s%14s%18s%20s", "Year", "Hurricane", "Category", "Pressure (mb)", "Wind Speed (mph)");
		output.println();
		output.println("**********************************************************************");
		int index = 0;
		for (int i = 0; i < 59; i++){
			output.printf("%s%14s%14d%18d%20.2f\n", year[index], name[index], category[index], pressure[index], windSpeed[index]);
			index++;
		}
		output.println("**********************************************************************");
		output.print("Average: ");
		output.printf("%23.1f%18.2f%20.2f\n", categoryAverage, pressureAverage, windAverage);
		output.print("Maximum: ");
		output.printf("%23.0f%18.0f%20.2f\n", maxCategory, maxPressure, maxWindSpeed);
		output.print("Minimum: ");
		output.printf("%23.0f%18.0f%20.2f\n\n", minCategory, minPressure, minWindSpeed);
		output.println("Number of Category 1: " + cat1);
		output.println("Number of Category 2: " + cat2);
		output.println("Number of Category 3: " + cat3);
		output.println("Number of Category 4: " + cat4);
		output.println("Number of Category 5: " + cat5);
		output.close();
		System.out.println("Created File: Hurricane_Data_From_1980_to_2006.txt");
	}
	
	public static void main(String[] args) throws IOException {
		Data d = new Data();
		d.readHurricaneData();
		System.out.printf("%s%14s%14s%18s%20s", "Year", "Hurricane", "Category", "Pressure (mb)", "Wind Speed (mph)");
		System.out.println();
		System.out.println("**********************************************************************");
		int index = 0;
		for (int i = 0; i < 59; i++){
			System.out.printf("%s%14s%14d%18d%20.2f\n", year[index], name[index], category[index], pressure[index], windSpeed[index]);
			index++;
		}
		System.out.println("**********************************************************************");
		System.out.print("Average: ");
		System.out.printf("%23.1f%18.2f%20.2f\n", categoryAverage, pressureAverage, windAverage);
		System.out.print("Maximum: ");
		System.out.printf("%23.0f%18.0f%20.2f\n", maxCategory, maxPressure, maxWindSpeed);
		System.out.print("Minimum: ");
		System.out.printf("%23.0f%18.0f%20.2f\n\n", minCategory, minPressure, minWindSpeed);
		System.out.println("Number of Category 1: " + cat1);
		System.out.println("Number of Category 2: " + cat2);
		System.out.println("Number of Category 3: " + cat3);
		System.out.println("Number of Category 4: " + cat4);
		System.out.println("Number of Category 5: " + cat5);
		System.out.println();
		exportData();
	}

}
