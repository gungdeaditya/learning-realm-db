package com.projects.gungde.learningrealm;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.projects.gungde.learningrealm.app.RealmApplication;
import com.projects.gungde.learningrealm.model.Item;

import java.util.UUID;

import io.realm.Realm;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddItemDialog extends DialogFragment implements View.OnClickListener {

    private EditText edtAddItem;
    private Button btnAddItem;

    public AddItemDialog() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AlertDialogStyle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_add_item, container, false);
        initComponents(view);
        return view;
    }

    private void initComponents(View view) {
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        edtAddItem = (EditText) view.findViewById(R.id.edt_item);
        btnAddItem = (Button) view.findViewById(R.id.bt_add_item);
        edtAddItem.requestFocus();
        btnAddItem.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_add_item: {
                if (isItemNotNull()) {
                    Realm realm = Realm.getInstance(RealmApplication.getInstance());
                    realm.beginTransaction();
                    Item u = realm.createObject(Item.class);
                    u.setId(UUID.randomUUID().toString());
                    u.setName(edtAddItem.getText().toString().trim());
                    realm.commitTransaction();
                    AddItemDialog.this.dismiss();
                }
                break;
            }
        }
    }

    private boolean isItemNotNull() {
        return !edtAddItem.getText().toString().isEmpty();
    }
}
