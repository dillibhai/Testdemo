package com.example.onlinestore;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentCategory extends Fragment{

    LinearLayout layoutLaptop,layoutDesktop,layoutAccessories,layoutHardware,layoutSoftware;
    //preferences
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public FragmentCategory() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        layoutLaptop = view.findViewById(R.id.linearLaptop);
        layoutDesktop = view.findViewById(R.id.linearDesktop);
        layoutHardware = view.findViewById(R.id.linearHardware);
        layoutSoftware = view.findViewById(R.id.linearSoftware);
        layoutAccessories = view.findViewById(R.id.linearAccessories);

        layoutLaptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pt = "laptop";
                preferences = (getContext()).getSharedPreferences("productType",0);
                editor = preferences.edit();
                editor.putString("product_type",pt);
                editor.commit();
                Intent intent = new Intent(getActivity(),ProductType.class);
                startActivity(intent);
            }
        });
        layoutDesktop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pt = "desktop";
                preferences = (getContext()).getSharedPreferences("productType",0);
                editor = preferences.edit();
                editor.putString("product_type",pt);
                editor.commit();
                Intent intent = new Intent(getActivity(),ProductType.class);
                startActivity(intent);
            }
        });
        layoutAccessories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pt = "accessories";
                preferences = (getContext()).getSharedPreferences("productType",0);
                editor = preferences.edit();
                editor.putString("product_type",pt);
                editor.commit();
                Intent intent = new Intent(getActivity(),ProductType.class);
                startActivity(intent);
            }
        });
        layoutHardware.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pt = "hardware";
                preferences = (getContext()).getSharedPreferences("productType",0);
                editor = preferences.edit();
                editor.putString("product_type",pt);
                editor.commit();
                Intent intent = new Intent(getActivity(),ProductType.class);
                startActivity(intent);
            }
        });
        layoutSoftware.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pt = "software";
                preferences = (getContext()).getSharedPreferences("productType",0);
                editor = preferences.edit();
                editor.putString("product_type",pt);
                editor.commit();

                Intent intent = new Intent(getActivity(),ProductType.class);
                startActivity(intent);
            }
        });
        return view;
    }


}
