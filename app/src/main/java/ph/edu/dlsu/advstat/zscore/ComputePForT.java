package ph.edu.dlsu.advstat.zscore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class ComputePForT extends AppCompatActivity {
    private static final int LEFT_TAIL = 0;
    private static final int RIGHT_TAIL = 1;
    private static final int TWO_TAIL = 2;

    private RadioButton leftTailRadio;
    private RadioButton rightTailRadio;
    private RadioButton twoTailRadio;
    private EditText tField;
    private EditText dfField;
    private Button computeButton;
    private TextView pLabel;
    private int testType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compute_pfor_t);
        leftTailRadio = (RadioButton)findViewById(R.id.leftTailRadio);
        rightTailRadio = (RadioButton)findViewById(R.id.rightTailRadio);
        twoTailRadio = (RadioButton)findViewById(R.id.twoTailRadio);
        tField = (EditText)findViewById(R.id.tField);
        dfField = (EditText)findViewById(R.id.dfField);
        computeButton = (Button)findViewById(R.id.computeButton);
        pLabel = (TextView)findViewById(R.id.pLabel);

        leftTailRadio.setChecked(true);
        testType = LEFT_TAIL;

        leftTailRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testType = LEFT_TAIL;
            }
        });

        rightTailRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testType = RIGHT_TAIL;
            }
        });

        twoTailRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testType = TWO_TAIL;
            }
        });

        computeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double t = Double.parseDouble(tField.getText().toString());
                    try {
                        double df = Integer.parseInt(dfField.getText().toString());
                        if( df < 1 ) {
                            Toast.makeText(getBaseContext(),"Please input a non-negative integer for the degrees of freedom.",
                                    Toast.LENGTH_LONG).show();
//                        } else if( df < 3 ) {
//                            Toast.makeText(getBaseContext(),"df < 3 is not yet supported.",
//                                    Toast.LENGTH_LONG).show();
                        } else {
                            double p = 0;
                            switch (testType) {
                                case LEFT_TAIL:
                                    p = TScore.computeP(t, df);
                                    break;
                                case RIGHT_TAIL:
                                    p = TScore.computeP(-t, df);
                                    break;
                                case TWO_TAIL:
                                    p = TScore.computeP(t > 0 ? -t : t, df) * 2;
                                    break;
                                default:
                                    Toast.makeText(getBaseContext(), "Invalid test type.",
                                            Toast.LENGTH_LONG).show();
                            }
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
