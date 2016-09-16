package ph.edu.dlsu.advstat.zscore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ComputeF extends AppCompatActivity {
    private EditText pField;
    private EditText df1Field;
    private EditText df2Field;
    private Button computeButton;
    private TextView fLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compute_f);
        pField = (EditText)findViewById(R.id.pField);
        df1Field = (EditText)findViewById(R.id.df1Field);
        df2Field = (EditText)findViewById(R.id.df2Field);
        computeButton = (Button)findViewById(R.id.computeButton);
        fLabel = (TextView)findViewById(R.id.fLabel);

        computeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double p = Double.parseDouble(pField.getText().toString());
                    if (p < 0 || p > 1) {
                        Toast.makeText(getBaseContext(), "Please input a number between 0 and 1.",
                                Toast.LENGTH_LONG).show();
                    } else {
                        try {
                            double df = Integer.parseInt(df1Field.getText().toString());
                            if (df < 1) {
                                Toast.makeText(getBaseContext(), "Please input a non-negative integer for the degrees of freedom.",
                                        Toast.LENGTH_LONG).show();
                            } else if( df < 2 ) {
                                Toast.makeText(getBaseContext(),"df < 2 not supported.",
                                        Toast.LENGTH_LONG).show();
                            } else {
                                try {
                                    double df2 = Integer.parseInt(df2Field.getText().toString());
                                    if( df2 < 1 ) {
                                        Toast.makeText(getBaseContext(), "Please input a non-negative integer for the degrees of freedom.",
                                                Toast.LENGTH_LONG).show();
                                    } else if( df2 < 1 ) {
                                        Toast.makeText(getBaseContext(),"df < 2 not supported.",
                                                Toast.LENGTH_LONG).show();
                                    } else {
                                        double x2 = 0;
                                        x2 = FScore.computeF(p, df,df2);
                                        fLabel.setText(x2 + "");
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
                    }
                } catch (NumberFormatException nfe) {
                    Toast.makeText(getBaseContext(), "Please input a number between 0 and 1.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
