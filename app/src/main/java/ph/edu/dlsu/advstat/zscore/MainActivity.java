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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pValueButton = (Button)findViewById(R.id.pValueButton);
        zScoreButton = (Button)findViewById(R.id.zScoreButton);
        pValueTButton = (Button)findViewById(R.id.pValueTButton);
        tScoreButton = (Button)findViewById(R.id.tScoreButton);

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
    }
}
