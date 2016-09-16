package ph.edu.dlsu.advstat.zscore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button pValueButton;
    private Button zScoreButton;
    private Button pValueTButton;
    private Button tScoreButton;
    private Button pValueX2Button;
    private Button x2Button;
    private Button pValueFButton;
    private Button fButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pValueButton = (Button)findViewById(R.id.pValueButton);
        zScoreButton = (Button)findViewById(R.id.zScoreButton);
        pValueTButton = (Button)findViewById(R.id.pValueTButton);
        tScoreButton = (Button)findViewById(R.id.tScoreButton);
        pValueX2Button = (Button)findViewById(R.id.pValueX2Button);
        x2Button = (Button)findViewById(R.id.x2Button);
        pValueFButton = (Button)findViewById(R.id.pValueFButton);
        fButton = (Button)findViewById(R.id.fButton);

        pValueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kanga = new Intent(getBaseContext(),ComputePForZ.class);
                startActivity(kanga);
            }
        });

        zScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kanga = new Intent(getBaseContext(),ComputeZ.class);
                startActivity(kanga);
            }
        });

        pValueTButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kanga = new Intent(getBaseContext(),ComputePForT.class);
                startActivity(kanga);
            }
        });

        tScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kanga = new Intent(getBaseContext(),ComputeT.class);
                startActivity(kanga);
            }
        });

        pValueX2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kanga = new Intent(getBaseContext(),ComputePForX2.class);
                startActivity(kanga);
            }
        });

        x2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kanga = new Intent(getBaseContext(),ComputeX2.class);
                startActivity(kanga);
            }
        });

        pValueFButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kanga = new Intent(getBaseContext(),ComputePForF.class);
                startActivity(kanga);
            }
        });

        fButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kanga = new Intent(getBaseContext(),ComputeF.class);
                startActivity(kanga);
            }
        });
    }
}
