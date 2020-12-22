package com.jamespfluger.roboshield.lists;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jamespfluger.roboshield.R;
import com.jamespfluger.roboshield.utils.Constants;
import com.jamespfluger.roboshield.utils.ExtendedPhoneNumberFormattingTextWatcher;
import com.jamespfluger.roboshield.utils.PrefsUtil;

import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class ItemListFragment extends Fragment {
    private ItemAdapter itemAdapter;

    public ItemListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        String fragmentTypeKey = getArguments().getString(Constants.FRAGMENT_TYPE_KEY);
        List<Item> items = PrefsUtil.getList(fragmentTypeKey);
        itemAdapter = new ItemAdapter(items, fragmentTypeKey);

        final RecyclerView itemList = view.findViewById(R.id.listRecyclerView);
        itemList.setLayoutManager(new LinearLayoutManager(getContext()));
        itemList.setAdapter(itemAdapter);

        initializeFooterContent(view);
        return view;
    }

    private void initializeFooterContent(View view) {
        LinearLayout footer = view.findViewById(R.id.listItemFooter);
        footer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Phone number");

                // Set up the input
                final EditText input = new EditText(getContext());

                //input.addTextChangedListener(new MaskWatcher("(###) ###-####"));
                input.addTextChangedListener(new ExtendedPhoneNumberFormattingTextWatcher());
                input.setHint("(###) ###-####");
                input.setGravity(Gravity.CENTER);
                input.setInputType(InputType.TYPE_CLASS_PHONE);

                builder.setView(input);

                // Set up the buttons
                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Item newItem = new Item(itemAdapter.getItemCount() + 1 + "", input.getText().toString());
                        itemAdapter.addItem(newItem);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });
    }
}