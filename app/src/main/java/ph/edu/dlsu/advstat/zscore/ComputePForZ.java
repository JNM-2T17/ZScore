package ph.edu.dlsu.advstat.zscore;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class ComputePForZ extends AppCompatActivity {
    private static final int LEFT_TAIL = 0;
    private static final int RIGHT_TAIL = 1;
    private static final int TWO_TAIL = 2;

    private RadioButton leftTailRadio;
    private RadioButton rightTailRadio;
    private RadioButton twoTailRadio;
    private EditText zField;
    private Button computeButton;
    private TextView pLabel;
    private int testType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compute_pfor_z);
        leftTailRadio = (RadioButton)findViewById(R.id.leftTailRadio);
        rightTailRadio = (RadioButton)findViewById(R.id.rightTailRadio);
        twoTailRadio = (RadioButton)findViewById(R.id.twoTailRadio);
        zField = (EditText)findViewById(R.id.zField);
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
                    double z = Double.parseDouble(zField.getText().toString());
                    double p = 0;
                    switch(testType) {
                        case LEFT_TAIL:
                            p = ZScore.computeP(z);
                            break;
                        case RIGHT_TAIL:
                            p = ZScore.computeP(-z);
                            break;
                        case TWO_TAIL:
                            p = ZScore.computeP(z > 0 ? -z : z) * 2;
                            break;
                        default:
                            Toast.makeText(getBaseContext(),"Invalid test type.",
                                    Toast.LENGTH_LONG).show();
                    }
                    pLabel.setText(p + "");
                } catch(NumberFormatException nfe) {
                    Toast.makeText(getBaseContext(),"Please input a number for the z-score.",
                                    Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
