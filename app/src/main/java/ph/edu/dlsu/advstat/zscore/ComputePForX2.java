package ph.edu.dlsu.advstat.zscore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ComputePForX2 extends AppCompatActivity {
    private EditText x2Field;
    private EditText dfField;
    private Button computeButton;
    private TextView pLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compute_pfor_x2);

        x2Field = (EditText)findViewById(R.id.x2Field);
        dfField = (EditText)findViewById(R.id.dfField);
        computeButton = (Button)findViewById(R.id.computeButton);
        pLabel = (TextView)findViewById(R.id.pLabel);

        computeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double x2 = Double.parseDouble(x2Field.getText().toString());
                    try {
                        double df = Integer.parseInt(dfField.getText().toString());
                        if( df < 1 ) {
                            Toast.makeText(getBaseContext(), "Please input a non-negative integer for the degrees of freedom.",
                                    Toast.LENGTH_LONG).show();
                        } else if( df < 2 && x2 > 100 ) {
                            Toast.makeText(getBaseContext(), "x2 > 100 is not supported for df = 1.",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            double p = 0;
                            p = ChiSquare.computeP(x2, df);
                            pLabel.setText(p + "");
                        }
                    } catch(NumberFormatException nfe) {
                        Toast.makeText(getBaseContext(),"Please input a non-negative integer for the degrees of freedom.",
                                Toast.LENGTH_LONG).show();
                    }
                } catch(NumberFormatException nfe) {
                    Toast.makeText(getBaseContext(),"Please input a number for the z-score.",
                            Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
