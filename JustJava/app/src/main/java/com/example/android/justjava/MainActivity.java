package com.example.android.justjava;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

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
        if(quantity != 100)
            this.quantity += 1;
        else{
            context = getApplicationContext();
            Toast toast = Toast.makeText(context, "Only 100 coffees can be places at a time", Toast.LENGTH_LONG);
            button = (Button) findViewById(R.id.increment_button);
            button.setEnabled(false);
            toast.show();

        }
    }

    public void decrementQuantity(View view) {
        if(quantity != 0)
            this.quantity -= 1;
        else{

            context = getApplicationContext();
            Toast toast = Toast.makeText(context, "0 coffee isnt an order", Toast.LENGTH_LONG);
            button = (Button) findViewById(R.id.decrement_button);
            button.setEnabled(false);
            toast.show();
        }
    }

    public void sendInvoice(View view) {

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
        ((TextView)findViewById(R.id.totalTextView)).setText(String.valueOf(this.price));

    }

}
