package com.example.nikhil.android_01;

/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 */

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.NumberFormat;

import static com.example.nikhil.android_01.R.id.quantity_text_view;
import static com.example.nikhil.android_01.R.string.price;
import static com.example.nikhil.android_01.R.string.quantity;

/**
 * This app displays an order form to order coffee.
 */



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }




    /**
     * This method is called when the order button is clicked.
     */


    /**
     * This method displays the given price on the screen.
     */


    /**
     * This method displays the given text on the screen.
     */



    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayNote(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.note);
        orderSummaryTextView.setText(message);
    }



    public void increment(View view) {
        TextView quantityTextView = (TextView) findViewById(quantity_text_view);
        int  temp=Integer.parseInt(quantityTextView.getText().toString());
        temp= temp +1;
        display(temp);

    }

    public void decrement(View view) {
        TextView quantityTextView = (TextView) findViewById(quantity_text_view);
        int  temp=Integer.parseInt(quantityTextView.getText().toString());
        if (temp>0) {
                        temp = temp - 1;
                    }
        else temp=0;
        display(temp);

    }

    private int totalPrice(){
        CheckBox whippedCreamCheckbox= (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream= whippedCreamCheckbox.isChecked();

        CheckBox chocolateCheckbox= (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate= chocolateCheckbox.isChecked();

        TextView quantityTextView = (TextView) findViewById(quantity_text_view);
        int  quantity=Integer.parseInt(quantityTextView.getText().toString());

        int price=5;

        if(hasChocolate){
            price= price+ 2;
                        }
        if(hasWhippedCream){
            price= price+ 1;
                           }
        int totalPrice= price*quantity;
        return totalPrice;


    }

    private String createOrderSummary(int totalPrice, boolean addWhippedCream, boolean addChocolate, String name){
        TextView quantityTextView = (TextView) findViewById(quantity_text_view);
        int  quantity=Integer.parseInt(quantityTextView.getText().toString());
        String priceMessage= "Name: " + name;
        priceMessage+= "\nAdd Whipped Cream: " + addWhippedCream;
        priceMessage+= "\nAdd Chocolate: " + addChocolate;
        priceMessage+= "\nQuantity: " + quantity;
        priceMessage+= "\nTotal: $ " + totalPrice;
        return priceMessage;

    }


    public void submitOrder(View view) {
        EditText name= (EditText) findViewById(R.id.name);
        String nameString= name.getText().toString();
        CheckBox whippedCreamCheckbox= (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream= whippedCreamCheckbox.isChecked();

        CheckBox chocolateCheckbox= (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate= chocolateCheckbox.isChecked();


        TextView quantityTextView = (TextView) findViewById(quantity_text_view);
        int numberOfCoffees=Integer.parseInt(quantityTextView.getText().toString());;
        int totalPrice= totalPrice();
        display(numberOfCoffees);
        String summary= createOrderSummary(totalPrice,hasWhippedCream, hasChocolate, nameString);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("plain/text");
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"nikhiliated@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Placing order with Nikhil for "+ nameString);
        intent.putExtra(Intent.EXTRA_TEXT, summary);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        displayNote(summary);

    }
}
