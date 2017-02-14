package css.cis3334.pizzaorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements updateViewInterface {

    RadioButton rbSmall;
    RadioButton rbMedium;
    RadioButton rbLarge;
    CheckBox chkbxCheese;
    CheckBox chkbxDelivery;
    TextView txtTotal;
    TextView txtStatus;
    PizzaOrderInterface pizzaOrderSystem;
    Spinner spinnerToppings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rbSmall = (RadioButton) findViewById(R.id.radioButtonSmall);
        rbMedium = (RadioButton) findViewById(R.id.radioButtonMedium);
        rbLarge = (RadioButton) findViewById(R.id.radioButtonLarge);

        chkbxCheese = (CheckBox) findViewById(R.id.checkBoxCheese);
        chkbxDelivery = (CheckBox) findViewById(R.id.checkBoxDeluvery);

        txtTotal = (TextView) findViewById(R.id.textViewTotal);
        txtStatus = (TextView) findViewById(R.id.textViewStatus);

        pizzaOrderSystem = new PizzaOrder(this);
    }

    @Override
    public void updateView(String orderStatus) {
        txtStatus.setText("Order Status" + orderStatus);
    }

    public void onClickOrder(View view) {
        String size = null;
        Boolean extraCheese;

        if (rbSmall.isChecked()) {
            size = "Small";
        }
        else if (rbMedium.isChecked()) {
            size = "Medium";
        }
        else if (rbLarge.isChecked()) {
            size = "Large";
        }

        //-----------------------------------

        if (chkbxCheese.isChecked()) {
            extraCheese = true;
        }
        else { extraCheese = false;}

        //-----------------------------------

        String orderDescription = pizzaOrderSystem.OrderPizza(spinnerToppings.getSelectedItem().toString(),size, extraCheese);
        //display a pop up message for a long period of time
        Toast.makeText(getApplicationContext(), "You have ordered a "+orderDescription , Toast.LENGTH_LONG).show();
        txtTotal.setText("Total Due: " + pizzaOrderSystem.getTotalBill().toString());
    }
}
