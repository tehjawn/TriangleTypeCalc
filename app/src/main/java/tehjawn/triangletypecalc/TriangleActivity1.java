package tehjawn.triangletypecalc;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.*;
import android.widget.*;


public class TriangleActivity1 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triangle);


        final Button calculate = (Button) findViewById(R.id.triangleCalcButton);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            //Defines the various views being changed/used
                TextView s1 = (TextView) findViewById(R.id.inputs1);
                TextView s2 = (TextView) findViewById(R.id.inputs2);
                TextView s3 = (TextView) findViewById(R.id.inputs3);

                TextView triTypeText = (TextView) findViewById(R.id.triTypeText);
                TextView isosCheckTV = (TextView) findViewById(R.id.isosCheckText);
                TextView scaleneCheckTV = (TextView) findViewById(R.id.scaleneCheckText);
                ImageView triPicIV = (ImageView) findViewById(R.id.imageViewTriangle);

            //Declares variables and types
                double inputs1, inputs2, inputs3, triPicImageView = 0;
                boolean wasSolved;
                String triangleType;
                boolean isosCheck, scaleneCheck;

            //Constructs TriangleSolver() class
                TriangleSolver tSolver = new TriangleSolver();

            //Receives the user input gathered from the EditText views in the XML and converts them into Strings
                String input1 = s1.getText().toString();
                String input2 = s2.getText().toString();
                String input3 = s3.getText().toString();

            //Logic Check - If any input is empty, return false and create a toast notifying the user
                if  ( ( input1.matches("") ) || ( input2.matches("") )
                        || ( input3.matches("") ) ) {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.inputError), Toast.LENGTH_SHORT).show();
                    wasSolved = false;
            //            - Otherwise, use the inputs as doubles to be processed through the tSolver (TriangleSolver) class
                } else {
                    wasSolved = tSolver.calcTriType(Double.parseDouble(input1), Double.parseDouble(input2), Double.parseDouble(input3));
                }

            //If the triangle type, triangle image, isosceles check, and the scalene check all come back, then it was solved
            //Therefore, allowing new variables to be defined using the values from the TriangleSolver class
                if (wasSolved) {
                    triangleType = tSolver.getTriType();
                    triPicImageView = tSolver.getTriImage();
                    isosCheck = tSolver.checkIsos();
                    scaleneCheck = tSolver.checkScalene();
                } else {
            //If the input was wrong in some way, such as an error in the side lengths, etc., an error message will appear
                    triangleType = "Error!";
                    isosCheck = false;
                    scaleneCheck = false;
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.solveError), Toast.LENGTH_SHORT).show();
                }

            //If the program was successful then the values will be processed and then output to the screen!

                triTypeText.setText(triangleType);      //Sets text that indicates triangle type

                if (isosCheck){     //Sets text that indicates yes/no isosceles type
                    isosCheckTV.setText(getResources().getString(R.string.isosCheckTrue));
                    isosCheckTV.setTextColor(Color.GREEN);
                } else {
                    isosCheckTV.setText(getResources().getString(R.string.isosCheckFalse));
                    isosCheckTV.setTextColor(Color.RED);
                }

                if (scaleneCheck){      //Sets text that indicates yes/no scalene type
                    scaleneCheckTV.setText(getResources().getString(R.string.scaleneCheckTrue));
                    scaleneCheckTV.setTextColor(Color.GREEN);
                } else {
                    scaleneCheckTV.setText(getResources().getString(R.string.scaleneCheckFalse));
                    scaleneCheckTV.setTextColor(Color.RED);
                }

            //Also the picture of the triangle will change based on the triangleType (but not for isosceles/scalene... yet!)
                if(triPicImageView == 1){
                    triPicIV.setImageDrawable(getResources().getDrawable(R.drawable.equilateraltri));
                } else if (triPicImageView == 2){
                    triPicIV.setImageDrawable(getResources().getDrawable(R.drawable.righttri));
                } else if (triPicImageView == 3){
                triPicIV.setImageDrawable(getResources().getDrawable(R.drawable.obtusetri));
                } else if (triPicImageView == 4){
                    triPicIV.setImageDrawable(getResources().getDrawable(R.drawable.acutetri));
                } else {        //Error toast if it fails
                    triPicIV.setImageDrawable(getResources().getDrawable(R.drawable.tcicon));
                    Toast.makeText(getApplicationContext(), "D:", Toast.LENGTH_LONG).show();
                }

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.triangle, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
