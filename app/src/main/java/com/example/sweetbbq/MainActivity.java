package com.example.sweetbbq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private int total = 0;

    private int numberWings=0;
    private int numberSteaks=0;
    private int numberSausages=0;
    private int numberRibs=0;
    private int numberWine=0;
    private int numberBeer=0;
    private int numberCoke=0;

    public final int unitPriceWing=10;
    public final int unitPriceSteak=25;
    public final int unitPriceSausage=7;
    public final int unitPriceRib=15;
    public final int unitPriceWine=12;
    public final int unitPriceBeer=8;
    public final int unitPriceCoke=5;

    public HashMap<Integer,Integer> button2Quantity=new HashMap<Integer,Integer>(){{
        put(R.id.decrementWings,R.id.wings);
        put(R.id.incrementWings,R.id.wings);
        put(R.id.decrementSausages, R.id.sausages);
        put(R.id.incrementSausages, R.id.sausages);
        put(R.id.decrementRibs, R.id.ribs);
        put(R.id.incrementRibs, R.id.ribs);
        put(R.id.decrementSteaks, R.id.steaks);
        put(R.id.incrementSteaks, R.id.steaks);
        put(R.id.decrementWine, R.id.wine);
        put(R.id.incrementWine, R.id.wine);
        put(R.id.decrementBeer, R.id.beer);
        put(R.id.incrementBeer, R.id.beer);
        put(R.id.decrementCoke, R.id.coke);
        put(R.id.incrementCoke, R.id.coke);
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void order(View view){
        // get total price
        int total = getTotal();
        // update the total price in screen
        updateTotalPrice(total);
        // send a confirmation email with gmail
        sendEmail();
    }
    public int getTotal(){
        int total=0;
        total+=numberWings*unitPriceWing;
        total+=numberSausages*unitPriceSausage;
        total+=numberRibs*unitPriceRib;
        total+=numberSteaks*unitPriceSteak;
        total+=numberWine*unitPriceWine;
        total+=numberBeer*unitPriceBeer;
        total+=numberCoke*unitPriceCoke;
        return total;
    }

    public void updateTotalPrice(int total){
        TextView totalPrice=findViewById(R.id.total);
        totalPrice.setText(getString(R.string.total, total));
    }

    public void sendEmail(){
        String orderList=getString(R.string.total, total);
        if(numberWings>0){
            orderList+=String.format("\n Wings[$%d]: %d", unitPriceWing, numberWings);
        }
        if(numberSausages>0){
            orderList+=String.format("\n Sausages[$%d]: %d", unitPriceSausage,numberSausages);
        }
        if(numberRibs>0){
            orderList+=String.format("\n Ribs[$%d]: %d", unitPriceRib, numberRibs);
        }
        if(numberSteaks>0){
            orderList+=String.format("\n Steaks[$%d]: %d", unitPriceSteak, numberSteaks);
        }
        if(numberWine>0){
            orderList+=String.format("\n Wine[%d]: %d", unitPriceWine, numberWine);
        }
        if(numberBeer>0){
            orderList+=String.format("\n Beer[%d]: %d", unitPriceBeer, numberBeer);
        }
        if(numberCoke>0){
            orderList+=String.format("\n Coke[%d]: %d", unitPriceCoke, numberCoke);
        }
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, "test@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Order Confirmation from Sweet Barbecue");
        intent.putExtra(Intent.EXTRA_TEXT, orderList);
        startActivity(intent);
    }

    public void increment(View view){
        // update the quantity
        int itemId = button2Quantity.get(view.getId());
        TextView quantityView = findViewById(itemId);
        int quantity = Integer.valueOf(quantityView.getText().toString());
        if(quantity==50){
            return;
        }
        switch(itemId){
            case R.id.wings:
                numberWings+=1;
                break;
            case R.id.sausages:
                numberSausages+=1;
                break;
            case R.id.ribs:
                numberRibs+=1;
                break;
            case R.id.steaks:
                numberSteaks+=1;
                break;
            case R.id.wine:
                numberWine+=1;
                break;
            case R.id.beer:
                numberBeer+=1;
                break;
            case R.id.coke:
                numberCoke+=1;
                break;
            default:
                return;
        }
        quantity+=1;
        // display the updated quantity
        quantityView.setText(String.valueOf(quantity));
        // display the updated total price
        displayTotalPrice(getTotal());
    }

    public void displayTotalPrice(int total){
        TextView view = findViewById(R.id.total);
        view.setText(getString(R.string.total,total));
    }

    public void decrement(View view){
        int itemId = button2Quantity.get(view.getId());
        TextView quantityView = findViewById(itemId);
        int quantity = Integer.parseInt(quantityView.getText().toString());
        if(quantity==0){
            return;
        }
        switch(itemId){
            case R.id.wings:
                numberWings-=1;
                break;
            case R.id.sausages:
                numberSausages-=1;
                break;
            case R.id.ribs:
                numberRibs-=1;
                break;
            case R.id.steaks:
                numberSteaks-=1;
                break;
            case R.id.wine:
                numberWine-=1;
                break;
            case R.id.beer:
                numberBeer-=1;
                break;
            case R.id.coke:
                numberCoke-=1;
                break;
            default:
                return;
        }
        quantity-=1;
        // display the updated quantity
        quantityView.setText(String.valueOf(quantity));
        // display the updated total price
        displayTotalPrice(getTotal());
    }
}
