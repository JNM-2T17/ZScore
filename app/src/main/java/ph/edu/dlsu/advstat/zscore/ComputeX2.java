package ph.edu.dlsu.advstat.zscore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ComputeX2 extends AppCompatActivity {
    private EditText pField;
    private EditText dfField;
    private Button computeButton;
    private TextView x2Label;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compute_x2);

        pField = (EditText)findViewById(R.id.pField);
        dfField = (EditText)findViewById(R.id.dfField);
        computeButton = (Button)findViewById(R.id.computeButton);
        x2Label = (TextView)findViewById(R.id.x2Label);

        computeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double p = Double.parseDouble(pField.getText().toString());
                    if( p < 0 || p > 1 ) {
                        Toast.makeText(getBaseContext(), "Please input a number between 0 and 1.",
                                Toast.LENGTH_LONG).show();
                    } else {
                        try {
                            double df = Integer.parseInt(dfField.getText().toString());
                            if( df < 1 ) {
                                Toast.makeText(getBaseContext(),"Please input a non-negative integer for the degrees of freedom.",
                                        Toast.LENGTH_LONG).show();
//                            } else if( df < 2 ) {
//                                Toast.makeText(getBaseContext(),"df < 2 not supported.",
//                                        Toast.LENGTH_LONG).show();
                            } else {
                                double x2 = 0;
                                x2 = ChiSquare.computeX2(p, df);
                                x2Label.setText(x2 + "");
                            }
                        } catch(NumberFormatException nfe) {
                            Toast.makeText(getBaseContext(),"Please input a non-negative integer for the degrees of freedom.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                } catch(NumberFormatException nfe) {
                    Toast.makeText(getBaseContext(),"Please input a number between 0 and 1.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
