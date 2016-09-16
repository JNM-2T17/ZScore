package ph.edu.dlsu.advstat.zscore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ComputePForF extends AppCompatActivity {
    private EditText fField;
    private EditText df1Field;
    private EditText df2Field;
    private Button computeButton;
    private TextView pLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compute_pfor_f);

        fField = (EditText)findViewById(R.id.fField);
        df1Field = (EditText)findViewById(R.id.df1Field);
        df2Field = (EditText)findViewById(R.id.df2Field);
        computeButton = (Button)findViewById(R.id.computeButton);
        pLabel = (TextView)findViewById(R.id.pLabel);

        computeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double f = Double.parseDouble(fField.getText().toString());
                    try {
                        double df = Integer.parseInt(df1Field.getText().toString());
                        if (df < 1) {
                            Toast.makeText(getBaseContext(), "Please input a non-negative integer for the degrees of freedom.",
                                    Toast.LENGTH_LONG).show();
                        } else if (df < 2) {
                            Toast.makeText(getBaseContext(), "df < 2 is not supported.",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            try {
                                double df2 = Integer.parseInt(df2Field.getText().toString());
                                if( df2 < 1) {
                                    Toast.makeText(getBaseContext(), "Please input a non-negative integer for the degrees of freedom.",
                                            Toast.LENGTH_LONG).show();
                                } else if( df2 < 2) {
                                    Toast.makeText(getBaseContext(), "df < 2 is not supported.",
                                            Toast.LENGTH_LONG).show();
                                } else {
                                    double p = 0;
                                    p = FScore.computeP(f, df, df2);
                                    pLabel.setText(p + "");
                                }
                            } catch(NumberFormatException nfe) {
                                Toast.makeText(getBaseContext(), "Please input a non-negative integer for the degrees of freedom.",
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    } catch (NumberFormatException nfe) {
                        Toast.makeText(getBaseContext(), "Please input a non-negative integer for the degrees of freedom.",
                                Toast.LENGTH_LONG).show();
                    }
                } catch (NumberFormatException nfe) {
                    Toast.makeText(getBaseContext(), "Please input a number for the z-score.",
                            Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
