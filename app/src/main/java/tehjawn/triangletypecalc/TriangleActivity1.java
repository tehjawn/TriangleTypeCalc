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
                TextView s1 = (TextView) findViewById(R.id.inputs1);
                TextView s2 = (TextView) findViewById(R.id.inputs2);
                TextView s3 = (TextView) findViewById(R.id.inputs3);

                TextView triTypeText = (TextView) findViewById(R.id.triTypeText);
                TextView isosCheckTV = (TextView) findViewById(R.id.isosCheckText);
                TextView scaleneCheckTV = (TextView) findViewById(R.id.scaleneCheckText);

                double inputs1, inputs2, inputs3;
                boolean wasSolved;
                String triangleType;
                boolean isosCheck, scaleneCheck;

                TriangleSolver tSolver = new TriangleSolver();

                String input1 = s1.getText().toString();
                String input2 = s2.getText().toString();
                String input3 = s3.getText().toString();

                if  ( ( input1.matches("") ) || ( input2.matches("") )
                        || ( input3.matches("") ) ) {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.inputError), Toast.LENGTH_SHORT).show();
                    wasSolved = false;
                } else {
                    wasSolved = tSolver.calcTriType(Double.parseDouble(input1), Double.parseDouble(input2), Double.parseDouble(input3));
                }

                if (wasSolved) {
                    triangleType = tSolver.getTriType();
                    isosCheck = tSolver.checkIsos();
                    scaleneCheck = tSolver.checkScalene();
                } else {
                    triangleType = "Error!";
                    isosCheck = false;
                    scaleneCheck = false;
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.solveError), Toast.LENGTH_SHORT).show();
                }

                triTypeText.setText(triangleType);
                if (isosCheck){
                    isosCheckTV.setText(getResources().getString(R.string.isosCheckTrue));
                } else {
                    isosCheckTV.setText(getResources().getString(R.string.isosCheckFalse));
                }

                if (scaleneCheck){
                    scaleneCheckTV.setText(getResources().getString(R.string.scaleneCheckTrue));
                } else {
                    scaleneCheckTV.setText(getResources().getString(R.string.scaleneCheckFalse));
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
