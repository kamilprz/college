
public class Rational {
	int numerator;
	int denominator;

	Rational(int numerator, int denominator){
		this.numerator = numerator;
		this.denominator = denominator;
	}

	Rational(int aNumerator){
		numerator=aNumerator;
		denominator=1;
	}


	public Rational addRationals(Rational rationalNum){
		Rational rationalSum= new Rational(0,0);
		rationalSum.numerator = this.numerator*rationalNum.denominator+
				rationalNum.numerator*this.denominator;
		rationalSum.denominator = this.denominator*rationalNum.denominator;
		rationalSum.simplifyRational();
		return rationalSum;
	}

	public Rational subtractRationals(Rational rationalNum){
		Rational rationalDifference= new Rational(0,0);
		rationalDifference.numerator = this.numerator*rationalNum.denominator-
				rationalNum.numerator*this.denominator;
		rationalDifference.denominator = this.denominator*rationalNum.denominator;
		rationalDifference.simplifyRational();
		return rationalDifference;
	}

	public Rational multiplyRationals(Rational rationalNum){
		Rational rationalProduct= new Rational(0,0);
		rationalProduct.numerator = this.numerator*rationalNum.numerator;							
		rationalProduct.denominator = this.denominator*rationalNum.denominator;
		rationalProduct.simplifyRational();
		return rationalProduct;
	}

	public Rational divideRationals(Rational rationalNum){
		Rational rationalQuotient= new Rational(0,0);
		rationalQuotient.numerator = this.numerator*rationalNum.denominator;							
		rationalQuotient.denominator = this.denominator*rationalNum.numerator;
		rationalQuotient.simplifyRational();
		return rationalQuotient;
	}
	
	public boolean areEqual (Rational rationalNum) {
		boolean areEqual=false;
		this.simplifyRational();
		rationalNum.simplifyRational();
		if(this.numerator==rationalNum.numerator && this.denominator==rationalNum.denominator) {
			areEqual=true;
			System.out.println("The rationals are equal.");
		}
		if(!areEqual) {
			System.out.println("The rationals are not equal");
		}	
		return areEqual;
	}
	
	public boolean isLessThan (Rational rationalNum) {
		boolean isLessThan=false;
		this.simplifyRational();
		rationalNum.simplifyRational();
		if(this.subtractRationals(rationalNum).numerator<0) {
			isLessThan=true;
			System.out.println("The rational "+this.rationalToString()+" is not greater than "+rationalNum.rationalToString());
		}
		if(!isLessThan) {
			System.out.println("The rational "+this.rationalToString()+" is not less than "+rationalNum.rationalToString());
		}
		return isLessThan;
	}
	
	
	public Rational simplifyRational() {
		//gcd stands for greatest common divisor
		int gcd=1;
		if(this.numerator>this.denominator) {
			for(int counter=1; counter<=this.denominator; counter++)
			{
				if(this.numerator%counter==0 && this.denominator%counter==0)
				{
					gcd=counter;
				}
			}
			this.numerator=this.numerator/gcd;
			this.denominator=this.denominator/gcd;

		}

		else if(this.numerator<this.denominator) {
			for(int counter=1; counter<=this.numerator; counter++) 
			{
				if(this.numerator%counter==0 && this.denominator%counter==0) 
				{
					gcd=counter;
				}			
			}
			this.numerator=this.numerator/gcd;
			this.denominator=this.denominator/gcd;
		}
		return this;
	}
	
	public String rationalToString() {
		String rational="";
		rational=Integer.toString(this.numerator) + "/" + Integer.toString(this.denominator);
		//System.out.println(rational);
		return rational;
	}
}


