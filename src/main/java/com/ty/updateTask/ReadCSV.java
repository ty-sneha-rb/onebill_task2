package com.ty.updateTask;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;

public class ReadCSV {

	static int Gitzer_CRM_VOIP_Integrations=0;
	static int Stream_Dynamics=0;
	static int Stream_JetStreamSecure=0;

	public static void main(String[] args) {

		Map<String, String> mapping=new HashMap<String, String>();
		mapping.put("product_Name", "Product_Name");
		mapping.put("product_Code","Product_Code");
		mapping.put("product_Description","Product_Description");
		mapping.put("price_Plan_Name","Price_Plan_Name");
		mapping.put("price_Plan_Code","Price_Plan_Code");
		mapping.put("price_Plan_Description","Price_Plan_Description");
		mapping.put("onetime_Fee","Onetime_Fee");
		mapping.put("recurring_Fee","Recurring_Fee");
		
		HeaderColumnNameTranslateMappingStrategy<Product> strategy=new HeaderColumnNameTranslateMappingStrategy<Product>();
		strategy.setType(Product.class);
		strategy.setColumnMapping(mapping);
		
		CSVReader csvReader = null;
		try {
		csvReader = new CSVReader(new FileReader("./data/product.csv"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
			CsvToBean csvToBean = new CsvToBean();
		
		
      List<Product> prodList = getprodDetails("./doc/product.csv");
		for(Product prod:prodList) {
			System.out.println(prod);
               }
	}

	public static List<Product> getprodDetails(String file) {

		List<Product> prodList =new ArrayList<>();
		Path pathToFile = Paths.get(file);

		try(
				BufferedReader br = Files.newBufferedReader(pathToFile)){
			String row = "";

			while ((row = Product.readLine()) != null ) {
				String[] cols = row.split(","); // array of string


				System.out.println(cols[0]+"   " );
				if(cols[0].equalsIgnoreCase("Gitzer_CRM_VOIP_Integrations")) {
					Gitzer_CRM_VOIP_Integrations++;
				}else if (cols[0].equalsIgnoreCase("Stream Dynamics")) {
					Stream_Dynamics++;
				}else if (cols[0].equalsIgnoreCase("Stream-JetStreamSecure")) {
					Stream_JetStreamSecure++;
				}			

			}
			System.out.println();
		} catch (Exception e) {

			e.printStackTrace();
		}
		System.out.println("Gitzer_CRM_VOIP_Integrations:-"+Gitzer_CRM_VOIP_Integrations);
		System.out.println("Stream_Dynamics:-"+Stream_Dynamics);
		System.out.println("Stream_JetStreamSecure:-"+Stream_JetStreamSecure);
		return prodList;

	}

//	private static Product getOneCustomer(String[] attributes) {
//		int onetime_Fee,recurring_Fee;
//		String product_Name,product_Code,product_Description,price_Plan_Name,price_Plan_Code,price_Plan_Description;
//		onetime_Fee = Integer.parseInt(attributes[0]);
//		product_Name = attributes[1];
//		product_Code = attributes[2].equalsIgnoreCase("")?null:attributes[2];
//		recurring_Fee = Integer.parseInt(attributes[3]);
//		product_Description = attributes[4];
//		price_Plan_Name = attributes[1];
//		price_Plan_Code = attributes[1];
//		price_Plan_Description = attributes[1];
//
//		Product prod = new Product( product_Name,
//				product_Code ,
//				product_Description,
//				price_Plan_Name,
//				price_Plan_Code,
//		price_Plan_Description,
//				onetime_Fee,
//				recurring_Fee	);		 
//		return prod;
//	}
}
