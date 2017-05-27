package it.unibo.test1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class ModelLoader {
	
	public static ArrayList<CPMP> loadCPMP(String filepath){
		ArrayList<CPMP> models = new ArrayList<CPMP>();
		
		File file = new File(filepath);
		try{
			
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		int num_models = 0;
		CPMP current_model = null;
		
		String line;
		while( (line=br.readLine()) != null){
			line = line.trim();
			String[] parts = line.split(" ");
			if(parts.length==1){
				num_models = Integer.parseInt(parts[0]);
			} else if(parts.length==2){
				int current_model_id = Integer.parseInt(parts[0]);
				int best_sol_for_current = Integer.parseInt(parts[1]);
				
				current_model = new CPMP(filepath, current_model_id);
				models.add(current_model);
				current_model.setBestKnownSolution(best_sol_for_current);
			} else if(parts.length==3){
				int num_customers = Integer.parseInt(parts[0]);
				int num_medians = Integer.parseInt(parts[1]);
				int medians_capacity = Integer.parseInt(parts[2]);
				
				current_model.setNumOfCustomers(num_customers);
				current_model.setNumOfMedians(num_medians);
				current_model.setCapacityOfMedians(medians_capacity);
			} else if(parts.length==4){
				int customer_id = Integer.parseInt(parts[0]);
				int x = Integer.parseInt(parts[1]);
				int y = Integer.parseInt(parts[2]);
				int demand = Integer.parseInt(parts[3]);
				
				current_model.addCustomer(customer_id, x, y, demand);
			}
		}
		
		}catch(Exception exc){ exc.printStackTrace(); }
		
		return models;
	}

}
