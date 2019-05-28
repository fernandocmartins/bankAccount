package com.br.k2testesantander.account;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.br.k2testesantander.R;
import com.br.k2testesantander.helper.HelperFormat;
import com.br.k2testesantander.network.model.LoginResponse;

import java.util.Objects;


public class AccountHeader extends ConstraintLayout {

    private View view;
    private ImageView logout;
    private TextView clientName;
    private TextView accountNumber;
    private TextView balance;

    public interface OnItemClickListener {
        void onItemClick(ImageView item);
    }

    public AccountHeader(Context context) {
        super(context);
        inflateHeader(context);
    }

    public AccountHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflateHeader(context);
    }

    public AccountHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflateHeader(context);
    }

    private void bindViews() {
        clientName = view.findViewById(R.id.client_name_text);
        logout = view.findViewById(R.id.logout_img);
        accountNumber = view.findViewById(R.id.account_number);
        balance = view.findViewById(R.id.balance_value);
    }

    public void setDataClient(LoginResponse loginResponse){
        clientName.setText(Objects.requireNonNull(loginResponse.getUserAccount()).getName());
        accountNumber.setText(HelperFormat.Companion.accountFormat(Objects.requireNonNull(loginResponse.getUserAccount().getBankAccount()),
                Objects.requireNonNull(loginResponse.getUserAccount().getAgency())));
        balance.setText(HelperFormat.Companion.moneyCheck(loginResponse.getUserAccount().getBalance()));
    }

    public void setListener(final OnItemClickListener listener) {
        logout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(logout);
            }
        });
    }

    private void inflateHeader(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.header_account, this, true);
        bindViews();
    }
}