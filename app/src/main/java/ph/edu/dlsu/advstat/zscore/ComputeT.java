package ph.edu.dlsu.advstat.zscore;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class ComputeT extends AppCompatActivity {
    private static final int LEFT_TAIL = 0;
    private static final int RIGHT_TAIL = 1;
    private static final int TWO_TAIL = 2;

    private RadioButton leftTailRadio;
    private RadioButton rightTailRadio;
    private RadioButton twoTailRadio;
    private EditText pField;
    private EditText dfField;
    private Button computeButton;
    private TextView tLabel;
    private int testType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compute_t);

        leftTailRadio = (RadioButton)findViewById(R.id.leftTailRadio);
        rightTailRadio = (RadioButton)findViewById(R.id.rightTailRadio);
        twoTailRadio = (RadioButton)findViewById(R.id.twoTailRadio);
        pField = (EditText)findViewById(R.id.pField);
        dfField = (EditText)findViewById(R.id.dfField);
        computeButton = (Button)findViewById(R.id.computeButton);
        tLabel = (TextView)findViewById(R.id.tLabel);

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
                    double p = Double.parseDouble(pField.getText().toString());
                    if( p < 0 || p > 1 ) {
                        Toast.makeText(getBaseContext(), getString(R.string.pValueError),
                                Toast.LENGTH_LONG).show();
                    } else {
                        try {
                            double df = Integer.parseInt(dfField.getText().toString());
                            if( df < 1 ) {
                                Toast.makeText(getBaseContext(),getString(R.string.dfError),
                                        Toast.LENGTH_LONG).show();
                            } else {
                                double t = 0;
                                switch (testType) {
                                    case LEFT_TAIL:
                                        t = TScore.computeT(p, df);
                                        break;
                                    case RIGHT_TAIL:
                                        t = TScore.computeT(1 - p,df);
                                        break;
                                    case TWO_TAIL:
                                        t = TScore.computeT(p / 2.0,df);
                                        break;
                                    default:
                                        Toast.makeText(getBaseContext(),
                                                getString(R.string.testTypeError),
                                                Toast.LENGTH_LONG).show();
                                }
//                                Log.i("ComputeT:", "p = " + p + ", t = " + t);
                                tLabel.setText(testType == TWO_TAIL ?
                                                String.format(getString(R.string.range),t,-t) :
                                        getString(R.string.value,t));
                            }
                        } catch(NumberFormatException nfe) {
                            Toast.makeText(getBaseContext(),getString(R.string.dfError),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                } catch(NumberFormatException nfe) {
                    Toast.makeText(getBaseContext(),getString(R.string.pValueError),
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
