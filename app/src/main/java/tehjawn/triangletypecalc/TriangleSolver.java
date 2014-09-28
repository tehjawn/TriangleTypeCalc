// Created by John Nguyen 2014 - Triangle Type Calculator_______________________

package tehjawn.triangletypecalc;

public class TriangleSolver {

//          Constants and Types         //
    private double s1;
    private double s2;
    private double s3;
    private double triPic;
    private double triArea;
    private String triType;
    private boolean isoTri, scaleneTri;
//                                      //

// RESET VALUES of TriangleSolver to avoid conflicts with future calculations //

        public TriangleSolver(){
            resetValues();
        }


// Receives three double values after resetting them to 0, then re-arranges them from lowest to highest value
// in order to use for the Pythagorean Theorem
        public TriangleSolver(double inputs1, double inputs2, double inputs3){
            resetValues();
            s1 = inputs1;
            s2 = inputs2;
            s3 = inputs3;
            orderSidesBigSmall();
        }

    // Defines a class to reset the values of the sides
        private void resetValues() {
            s1 = 0;
            s2 = 0;
            s3 = 0;
            triPic = 0;
            triType = "";
            isoTri = false;
            scaleneTri = false;
        }

//--->                       TRIANGLE TYPE CALCULATOR                             <---//

    public boolean calcTriType(){


//Logic Check___________________________________________________________________
        if ((s1 <= 0.0) || (s2 <= 0.0) || (s3 <= 0.0))      //Checks if sides are greater than 0; if no, program won't work
            return false;                                   //  after all, a side of a triangle can't be less than or equal to zero!
        if (s3>=(s1+s2))                                    //Checks if two smaller sides have a sum that is greater than
            return false;                                   //  the largest side; if true, return false - their sum can't be greater!

//Triangle Type Calculator______________________________________________________
        if ((s1 == s2) && (s2 == s3)){                      //If all three sides are equal, it is an Equilateral Triangle
            triType = "Equilateral Triangle";
            triPic = 1;
        } else if (((s1*s1)+(s2*s2))==(s3*s3)){             //If a^2 + b^2 = c^2, it is a Right Triangle
            triType = "Right Triangle";
            triPic = 2;
        } else if (((s1*s1)+ (s2*s2))<(s3*s3)){             //If a^2 + b^2 > c^2, it is an Obtuse Triangle
            triType = "Obtuse Triangle";
            triPic = 3;
        } else if (((s1*s1)+ (s2*s2) > (s3*s3))){           //If a^2 + b^2 < c^2, it is an Actue Triangle
            triType = "Acute Triangle";
            triPic = 4;
        }
        if ((s1 == s2) || s2 == s3 || s3 == s1){            //Additional check for any two sides being equal; if yes, then it is also an Isoceles Triangle
            isoTri = true;
        }

        if (!(s1 == s2) && !(s2 == s3) && !(s3 == s1)){     //Same as Isosceles check but with scale (checks if all three sides have different values)
            scaleneTri = true;
        }

        double triHPerimeter = ((s1 + s2 + s3) / 2);
        triArea = Math.sqrt(triHPerimeter *((triHPerimeter -s1)*(triHPerimeter -s2)*(triHPerimeter -s3)));

        return true;
    }
//--->                                   ...                                      <---//

// Get true/false from calculation
    public boolean calcTriType(double s1, double s2, double s3){
        resetValues();
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        orderSidesBigSmall();
        return calcTriType();
    }

// Get Values____________________________________________________________________
    public String getTriType(){return triType; }            //Gets triangle type that was calculated

    public double getTriImage(){return triPic; }            //Determines which picture should be shown depending on the triangle type

    public double getTriArea(){return triArea; }            //Gets triangle area that was calculated

    public boolean checkIsos(){return isoTri;  }            //Yes/No for if triangle is also an isosceles triangle

    public boolean checkScalene(){return scaleneTri;  }     //Yes/No for if triangle is also a scalene triangle

// Orders sides from small to large_____________________________________________ (WIP)
    private void orderSidesBigSmall() {                     //If side 1 is larger than sides 2 and 3, side 1 will move to position 3
        double temp;                                        //and sides 2 and 3 shift down one in position
        if ( (s1 >= s2) && (s1 >= s3) ) {
            temp = s1;
        if ( s2 >= s3) {                                    //If side 2 is larger than side 3, then side 2's value moves back up to
                s1 = s3;                                    //the second position
                s3 = temp;
            } else {                                        //If side 2 is not larger than side 3, then the position values are
            s1 = s2;                                        //solidified
                s2 = s3;
                s3 = temp;
            }
        } else if ( (s2 >= s1) && (s2 >= s3) ) {            //If side 2 is larger than both sides 1 and 3, it will move to position 3
        temp = s2;                                          //And then sides 1 and 3 will be compared, etc. etc.
            if ( s1 >= s3) {
                s2 = s1;
                s1 = s3;
                s3 = temp;
            } else {
                s2 = s3;
                s3 = temp;
            }
        } else {                                            //Otherwise, only sides 1 and 2 switch position
            if (s1 > s2) {
                temp = s2;
                s2 = s1;
                s1 = temp;
            }
        }
    }

}
