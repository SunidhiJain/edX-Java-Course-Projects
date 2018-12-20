public class Fraction {

    private int numerator;
    private int denominator = 1;


    // Constructor 1: to initialize numerator and denominator
    public Fraction(int num, int den){
        this.numerator = num;
        this.denominator = den;

        // Throws an exception if user enter 0 for the denominator value
        if (den == 0){
            throw new IllegalArgumentException("Denominator cannot be equal to zero.");
        }

        // Makes the fraction +ve, if both numerator and denominator are -ve values
        // Shifts the minus sign to the numerator, if numerator is +ve and denominator is -ve
        if ((numerator < 0 && denominator < 0) || (numerator > 0 && denominator < 0)){
            this.numerator = -num;
            this.denominator = -den;
        }
    }


    // Constructor 2: for natural numbers
    public Fraction(int num){
        this.numerator = num;
        this.denominator = 1;
    }


    // Constructor 3: for 0 as numerator
    public Fraction(){
        this.numerator = 0;
        this.denominator = 1;
    }


    // Accessor method 1: to get numerator
    public int getNumerator() {
        return this.numerator;
    }


    // Accessor method 2: to get denominator
    public int getDenominator() {
        return this.denominator;
    }


    // Method to convert the fraction into a string
    public String toString(){
        String toString;

        if (getDenominator()==1){   // To avoid printing the denominator when it's value is equal to 1
            if (getNumerator()==0){
                toString = Integer.toString(0);
            } else
                toString = Integer.toString(getNumerator());
        } else if (getDenominator()==getNumerator()){   // To print 1 when both numerator and denominator are equal in value
            toString = Integer.toString(1);
        } else
            toString = Integer.toString(getNumerator()) + "/"
                    + Integer.toString(getDenominator());

        return toString;
    }


    // Method to calculate the result of the fraction in decimal points
    public double toDouble(){
        return this.numerator / (double)this.denominator;
    }


    // Method to perform addition between the two fractions
    public Fraction add(Fraction other){
        Fraction addition = new Fraction((this.getNumerator() * other.getDenominator()) + (other.getNumerator() * this.getDenominator()),
                (this.getDenominator() * other.getDenominator()));
        return toLowestTerms(addition);
    }


    // Method to perform subtraction between the two fractions
    public Fraction subtract(Fraction other){
        Fraction subtraction = new Fraction((this.getNumerator() * other.getDenominator()) - (other.getNumerator() * this.getDenominator()),
                (this.getDenominator() * other.getDenominator()));
        return toLowestTerms(subtraction);
    }


    // Method to perform multiplication between the two fractions
    public Fraction multiply(Fraction other){
        Fraction multiplication = new Fraction((this.getNumerator() * other.getNumerator()),
                (this.getDenominator() * other.getDenominator()));
        //System.out.println("multiplication = " + multiplication);
        return toLowestTerms(multiplication);
    }


    // Method to perform division between the two fractions
    public Fraction divide(Fraction other){
        Fraction division = new Fraction();
        if(this.getDenominator() * other.getNumerator()==0){
            System.out.println("Undefined");
        } else
            division = new Fraction((this.getNumerator() * other.getDenominator()), (this.getDenominator() * other.getNumerator()));

        return toLowestTerms(division);
    }


    // Method to perform a check to determine if the two fractions are equal
    public boolean equals(Fraction other){
        double result1 = this.toDouble();
        double result2 = other.toDouble();

        if (result1 == result2){
            return true;
        } else
            return false;
    }


    // Method to simplify the fraction
    public Fraction toLowestTerms(Fraction result){
        int gcd = gcd(result.getNumerator(),result.getDenominator());
        Fraction toLowestTerm = new Fraction(result.getNumerator()/gcd,
                result.getDenominator()/gcd);
        //System.out.println("toLowestTerm = " + toLowestTerm);
        return toLowestTerm;
    }


    // Method to compute GCD of two integers (called by toLowestForm method)
    public static int gcd(int num, int den) {
        int temp;
        //System.out.println(num + ", " + den);
        while (den != 0) {
            temp = num % den;
            num = den;
            den = temp;
        }
        //System.out.println("GCD = " + num);
        return num;
    }

}
