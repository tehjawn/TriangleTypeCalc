// Created by John Nguyen 2014 - Triangle Type Calculator_______________________

package tehjawn.triangletypecalc;

public class TriangleSolver {

    // Constants and Types
    private double s1, s2, s3;
    private String triType;
    private boolean isoTri, scaleneTri;

    public TriangleSolver(){
        resetValues();
    }

    public TriangleSolver(double inputs1, double inputs2, double inputs3){
        resetValues();
        s1 = inputs1;
        s2 = inputs2;
        s3 = inputs3;
        orderSidesBigSmall();
    }

    //Defines a class to reset the values of the sides
    private void resetValues() {
        s1 = 0;
        s2 = 0;
        s3 = 0;
        triType = "";
        isoTri = false;
        scaleneTri = false;
    }

    //>__________________________________________________________________________<//
    public boolean calcTriType(){

//Logic Check___________________________________________________________________
        if ((s1 <= 0.0) || (s2 <= 0.0) || (s3 <= 0.0))
            return false;
        if (s3>(s1+s2))
            return false;

//Triangle Type Calculator______________________________________________________
        if ((s1 == s2) && (s2 == s3)){
            triType = "Equilateral Triangle";
        } else if (((s1*s1)+(s2*s2))==(s3*s3)){
            triType = "Right Triangle";
        } else if (((s1*s1)+ (s2*s2))<(s3*s3)){
            triType = "Obtuse Triangle";
        } else if (((s1*s1)+ (s2*s2) > (s3*s3))){
            triType = "Acute Triangle";
        }
        if ((s1 == s2) || s2 == s3 || s3 == s1){
            isoTri = true;
        }

        if (!(s1 == s2) || !(s2 == s3) || !(s3 == s1)){
            scaleneTri = true;
        }
        return true;
    }
//>__________________________________________________________________________<//

    public boolean calcTriType(double s1, double s2, double s3){
        resetValues();
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        orderSidesBigSmall();
        return calcTriType();
    }

    //Get Values____________________________________________________________________
    public String getTriType(){
        return triType;
    }

    public boolean checkIsos(){
        return isoTri;
    }

    public boolean checkScalene(){
        return scaleneTri;
    }

    private void orderSidesBigSmall() {
        double temp;
        if ( (s1 >= s2) && (s1 >= s3) ) {
            temp = s1;
            if ( s2 >= s3) {
                s1 = s3;
                s3 = temp;
            } else {
                s1 = s2;
                s2 = s3;
                s3 = temp;
            }
        } else if ( (s2 >= s1) && (s2 >= s3) ) {
            temp = s2;
            if ( s1 >= s3) {
                s2 = s1;
                s1 = s3;
                s3 = temp;
            } else {
                s2 = s3;
                s3 = temp;
            }
        } else {
            if (s1 > s2) {
                temp = s2;
                s2 = s1;
                s1 = temp;
            }
        }
    }

}
