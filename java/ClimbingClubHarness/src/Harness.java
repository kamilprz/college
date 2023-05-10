public class Harness {
	private String make;
	private int modelNumber;
	private int timesUsed;
	private String nameOfLastInspector;
	private boolean isLoaned;
	private String nameOfLoaner;
	final int MAX_NUMBER_OF_USES=25;
	
	
	Harness(String make, int modelNumber, int timesUsed, String nameOfLastInspector, boolean isLoaned, String nameOfLoaner)
	{
		this.make = make;
		this.modelNumber = modelNumber;
		this.timesUsed = timesUsed;
		this.nameOfLastInspector = nameOfLastInspector;
		this.isLoaned = isLoaned;
		if (isLoaned)
		{
			this.nameOfLoaner = nameOfLoaner;
		}
		else this.nameOfLoaner = null;
	}
	
	
	Harness(String make, int modelNumber, String nameOfLastInspector)
	{
		this.make = make;
		this.modelNumber = modelNumber;
		this.timesUsed=0;
		this.nameOfLastInspector = nameOfLastInspector;
		this.isLoaned=false;
		this.nameOfLoaner=null;
	}
	
	
	public String getMake() {
		return make;
	}


	public void setMake(String make) {
		this.make = make;
	}


	public int getModelNumber() {
		return modelNumber;
	}


	public void setModelNumber(int modelNumber) {
		this.modelNumber = modelNumber;
	}


	public int getTimesUsed() {
		return timesUsed;
	}


	public void setTimesUsed(int timesUsed) {
		this.timesUsed = timesUsed;
	}


	public String getNameOfLastInspector() {
		return nameOfLastInspector;
	}


	public void setNameOfLastInspector(String nameOfLastInspector) {
		this.nameOfLastInspector = nameOfLastInspector;
	}


	public boolean isLoaned() {
		return isLoaned;
	}


	public void setLoaned(boolean isLoaned) {
		this.isLoaned = isLoaned;
	}


	public String getNameOfLoaner() {
		return nameOfLoaner;
	}


	public void setNameOfLoaner(String nameOfLoaner) {
		this.nameOfLoaner = nameOfLoaner;
	}
	
	
	public void checkHarness(String climbingInstructorsName)
	{
		if(!isLoaned)
		{
			this.nameOfLastInspector = climbingInstructorsName;
			this.timesUsed = 0;
		}
		else {
			System.out.println("Harness is currently on loan.");
		}
	}
	
	
	public boolean isHarnessOnLoan()
	{
		return isLoaned;
	}
	
	
	public boolean canHarnessBeLoaned()
	{
		if(!isLoaned &&(timesUsed<MAX_NUMBER_OF_USES))
		{
			return true;
		}
		else return false;
	}
	
	
	public void loanHarness(String loaner)
	{
		if(this.canHarnessBeLoaned())
		{
			this.isLoaned = true;
			this.timesUsed++;
			this.nameOfLoaner = loaner;
		}
	}
	
	
	public void returnHarness()
	{
		if(this.isLoaned){
			this.isLoaned= false;
			this.nameOfLoaner = null;
		}
	}
	
	
	public String toString()
	{
		String description = "Brand: " + this.make + " | Model Number: " + this.modelNumber + " | Times Used: " + this.timesUsed + " | Last Inspector: " + this.nameOfLastInspector +  " | Is Borrowed: "
				+this.isLoaned;
		if(this.isLoaned()) {
			description = description + " | Name of loaner: "+this.getNameOfLoaner();
		}
		return description;
	}
}