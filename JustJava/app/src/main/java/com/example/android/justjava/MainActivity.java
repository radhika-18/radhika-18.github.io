package com.example.android.justjava;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MainActivity extends AppCompatActivity {

    private final double standardPrice = 40.25;
    private final double whippingCreamCost = 10.0;
    private final double chocolateCost = 15.0;
    int quantity;
    double price;
    String name;
    Context context;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitializeVariables();
    }

    private void InitializeVariables(){
        quantity=0;
        name="";
        price = 0.0;
    }

    public void incrementQuantity(View view) {
        if(quantity != 100){
            this.quantity += 1;
            ((TextView)findViewById(R.id.quantityViewTextView)).setText(this.quantity);
        }

        else{
            context = getApplicationContext();
            Toast toast = Toast.makeText(context, "Only 100 coffees can be places at a time", Toast.LENGTH_LONG);
            button = (Button) findViewById(R.id.increment_button);
            button.setEnabled(false);
            toast.show();

        }
    }

    public void decrementQuantity(View view) {
        if(quantity != 0) {
            this.quantity -= 1;
            ((TextView)findViewById(R.id.quantityViewTextView)).setText(this.quantity);
        }
        else{

            context = getApplicationContext();
            Toast toast = Toast.makeText(context, "0 coffee isnt an order", Toast.LENGTH_LONG);
            button = (Button) findViewById(R.id.decrement_button);
            button.setEnabled(false);
            toast.show();
        }
    }

    public void sendInvoice(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        String emailId = ((EditText)findViewById(R.id.inputnameEditText)).getText().toString()+"@gmail.com";
        intent.putExtra(Intent.EXTRA_EMAIL, emailId);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();
        String subject = "Invoice for your coffee at Cafe N Crisp on" + (dtf.format(localDate));
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        String text = "<table><tr><td>Name :</td><td>" + R.id.inputnameEditText + "</td></tr><tr><td>Choice :</td><td>Whipped Cream : "
                +(findViewById(R.id.checkBox_whippedCream)).isSelected() + "<br>Chocolate: " +(findViewById(R.id.checkBox_chocolate)).isSelected() +"</td>"
                +"</tr><tr><td>Quantity :</td><td>" +this.quantity +"</td></tr><tr><td>Total</td><td>" + (R.string.Rs)+ this.price
                +"</td></tr><tr><td><b></b>Tanks fr Ordering . Do visit again</b></td></tr></table>";
        intent.putExtra(Intent.EXTRA_TEXT, text);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void CalculateCost(boolean whippingCreamPresent, boolean chocolatePresent){
        if(whippingCreamPresent){
            this.price = this.quantity * this.standardPrice * this.whippingCreamCost;
        }
        if(chocolatePresent){
            this.price = this.quantity * this.standardPrice * this.chocolateCost;
        }
    }
    public void orderSubmit(View view) {
        boolean whippingCreamCheckBox = (findViewById(R.id.checkBox_whippedCream)).isSelected();
        boolean chocolateCheckBox = (findViewById(R.id.checkBox_chocolate)).isSelected();
        CalculateCost(whippingCreamCheckBox,chocolateCheckBox);
        ((TextView)findViewById(R.id.nameTextView)).setText(((EditText)findViewById(R.id.inputnameEditText)).getText().toString());
        ((TextView)findViewById(R.id.quantityTextView)).setText(this.quantity);
        String choiceText = "Whipped Cream: " + (String.valueOf(whippingCreamCheckBox)) +"\n Chocolate: " + (String.valueOf(chocolateCheckBox));
        ((TextView)findViewById(R.id.choiceTextView)).setText(choiceText);
        ((TextView)findViewById(R.id.totalTextView)).setText(R.string.Rs+". "+String.valueOf(this.price));
    }

}
