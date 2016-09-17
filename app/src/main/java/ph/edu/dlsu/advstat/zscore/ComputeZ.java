package ph.edu.dlsu.advstat.zscore;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class ComputeZ extends AppCompatActivity {
    private static final int LEFT_TAIL = 0;
    private static final int RIGHT_TAIL = 1;
    private static final int TWO_TAIL = 2;

    private RadioButton leftTailRadio;
    private RadioButton rightTailRadio;
    private RadioButton twoTailRadio;
    private EditText pField;
    private Button computeButton;
    private TextView zLabel;
    private int testType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compute_z);

        leftTailRadio = (RadioButton)findViewById(R.id.leftTailRadio);
        rightTailRadio = (RadioButton)findViewById(R.id.rightTailRadio);
        twoTailRadio = (RadioButton)findViewById(R.id.twoTailRadio);
        pField = (EditText)findViewById(R.id.pField);
        computeButton = (Button)findViewById(R.id.computeButton);
        zLabel = (TextView)findViewById(R.id.zLabel);

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
                        Toast.makeText(getBaseContext(),getString(R.string.pValueError),
                                Toast.LENGTH_LONG).show();
                    } else {
                        double z = 0;
                        switch (testType) {
                            case LEFT_TAIL:
                                z = ZScore.computeZ(p);
                                break;
                            case RIGHT_TAIL:
                                z = ZScore.computeZ(1 - p);
                                break;
                            case TWO_TAIL:
                                z = ZScore.computeZ(p / 2.0);
                                break;
                            default:
                                Toast.makeText(getBaseContext(), getString(R.string.testTypeError),
                                        Toast.LENGTH_LONG).show();
                        }
                        zLabel.setText(testType == TWO_TAIL ?
                                String.format(getString(R.string.range),z,-z) :
                                getString(R.string.value,z));
                    }
                } catch(NumberFormatException nfe) {
                    Toast.makeText(getBaseContext(),getString(R.string.pValueError),
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
