package sg.edu.rp.c346.id20028828.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText editAmount;
    EditText editPax;
    ToggleButton tbSvc;
    ToggleButton tbGst;
    EditText editDiscount;
    RadioGroup rgPayment;
    Button splitButton;
    Button resetButton;
    TextView totalbill;
    TextView totalSplit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editAmount = findViewById(R.id.editAmount);
        editPax = findViewById(R.id.editPax);
        tbSvc = findViewById(R.id.tbSvc);
        tbGst = findViewById(R.id.tbGst);
        editDiscount = findViewById(R.id.editDiscount);
        splitButton = findViewById(R.id.splitButton);
        resetButton = findViewById(R.id.resetButton);
        rgPayment = findViewById(R.id.rgPayment);
        totalbill = findViewById(R.id.totalbill);
        totalSplit = findViewById(R.id.totalsplit);


        splitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editPax.getText().toString().length() != 0 && editAmount.getText().toString().length() != 0) {
                    double totalBill= 0.0;

                    if (tbSvc.isChecked() && tbGst.isChecked()) {
                        totalBill = Double.parseDouble(editAmount.getText().toString()) * 1.17;
                    } else if (!tbSvc.isChecked() && !tbGst.isChecked()) {
                        totalBill = Double.parseDouble(editAmount.getText().toString()) * 1;
                    } else if (tbGst.isChecked() && !tbSvc.isChecked()) {
                        totalBill = Double.parseDouble(editAmount.getText().toString()) * 1.07;
                    } else {
                        totalBill = Double.parseDouble(editAmount.getText().toString()) * 1.1;
                    }

                    if (editDiscount.getText().toString().length() != 0) {
                        totalBill *= 1 - (Double.parseDouble(editDiscount.getText().toString())/100);
                    }

                    totalbill.setText("Total Bill: $" + String.format("%.2f", totalBill));
                    int ttlPeople = Integer.parseInt(editPax.getText().toString());
                    double perPerson = totalBill/ttlPeople;
                    if (ttlPeople > 1) {
                        totalSplit.setText("Each Pays: $" + String.format("%.2f", perPerson));
                    } else if (ttlPeople > 1) {
                        totalSplit.setText("Each Pays: $" + String.format("%.2f", perPerson));
                    }
                }
            }
        });
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editDiscount.setText("");
                editPax.setText("");
                editAmount.setText("");
            }
        });






    }
}