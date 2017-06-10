package com.projects.gungde.learningrealm;


import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.projects.gungde.learningrealm.app.RealmApplication;
import com.projects.gungde.learningrealm.model.Item;
import com.projects.gungde.learningrealm.realm.table.RealmTable;

import java.util.UUID;

import io.realm.Realm;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddItemDialog extends DialogFragment implements View.OnClickListener {

    private EditText edtAddItem;
    private TextView txAddItem;
    private Button btnAddItem;
    private Realm realm;

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
        setBundle();
        return view;
    }

    private void setBundle() {
        if(getArguments()!=null){
            String id = getArguments().getString(RealmTable.ID);
            Item result = realm.where(Item.class)
                    .equalTo(RealmTable.ID, id)
                    .findFirst();
            txAddItem.setText("Update Item");
            btnAddItem.setText("Update");
            edtAddItem.setText(result.getName());
        }
    }

    private void initComponents(View view) {
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        realm = Realm.getDefaultInstance();
        edtAddItem = (EditText) view.findViewById(R.id.edt_item);
        btnAddItem = (Button) view.findViewById(R.id.bt_add_item);
        txAddItem = (TextView)view.findViewById(R.id.tx_add_item);
        edtAddItem.requestFocus();
        btnAddItem.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_add_item: {
                if (isItemNotNull()) {
                    addItemByName();
                    AddItemDialog.this.dismiss();
                }
                break;
            }
        }
    }

    private void addItemByName() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Item item = new Item();
                item.setName(edtAddItem.getText().toString().trim());
                realm.insert(item);
            }
        });
    }


    private boolean isItemNotNull() {
        return !edtAddItem.getText().toString().isEmpty();
    }
}
