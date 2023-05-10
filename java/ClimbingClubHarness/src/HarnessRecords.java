
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class HarnessRecords {
	
	ArrayList<Harness> harnessList;

	HarnessRecords(){
		harnessList = new ArrayList<Harness>(0);
	}
	
	
	HarnessRecords(FileInputStream inputStream) throws IOException{		
		harnessList = new ArrayList<Harness>(0);
		BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
		String line;
		int numberOfHarnesses=Integer.parseInt(bf.readLine());
		for(int counter=0; counter<numberOfHarnesses; counter++) {
			line=bf.readLine();
			String[] harnessValues = line.split(", ",-1);
			Harness harness = new Harness(harnessValues[0],Integer.parseInt(harnessValues[1]),Integer.parseInt(harnessValues[2]),harnessValues[3]
								,Boolean.parseBoolean(harnessValues[4]) ,harnessValues[5]);
			this.harnessList.add(harness);
		}
		bf.close();
	}
	
	public boolean isEmpty(ArrayList<HarnessRecords>harnessList){
		boolean isEmpty=false;
		if(harnessList.size()==0){
			isEmpty=true;
		}
		return isEmpty;
	}
	
	
	public void addHarness(Harness harness){
		this.harnessList.add(harness);
	}
	
	
	public Harness findHarness(String make, int modelNumber){
		for(int i=0; i<this.harnessList.size();i++){
			if(harnessList.get(i).getMake().equals(make) && harnessList.get(i).getModelNumber()==(modelNumber)){
				return this.harnessList.get(i);
			}
		}
		return null;
	}
	
	
	public Harness checkHarness(String nameOfLastInspector,String make, int modelNumber){
		Harness harness = new Harness(make, modelNumber, nameOfLastInspector);
		if(!harness.isHarnessOnLoan()){
			harness.setTimesUsed(0);
			harness.setNameOfLastInspector(nameOfLastInspector);
			return harness;
		}
		return null;
	}
	
	
	public Harness loanHarness(String nameOfLoaner) {
		for(Harness harness : harnessList) {
			if(harness.canHarnessBeLoaned()) {
				harness.loanHarness(nameOfLoaner);
				return harness;
			}
		}
		return null;
	}
	
	
	public Harness returnHarness(String make, int modelNumber) {
		for(Harness harness : harnessList) {
			if(harness.isLoaned()) {
				if(harness.getMake().equals(make) && harness.getModelNumber()==modelNumber) {
					harness.returnHarness();
					return harness;
				}
			}
		}		
		return null;
	}
	
	public Harness removeHarness(String make, int modelNumber) {
		for(Harness harness : harnessList) {
			if(harness.getMake().equals(make) && harness.getModelNumber()==modelNumber) {
				harnessList.remove(harness);
				return harness;
			}
		}		
		return null;
	}
	
	public ArrayList<Harness> getList(){
		return harnessList;
	}
}
