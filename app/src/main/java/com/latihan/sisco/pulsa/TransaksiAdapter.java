package com.latihan.sisco.pulsa;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.latihan.sisco.pulsa.dbHelper.TransaksiHelper;

import java.util.ArrayList;

public class TransaksiAdapter extends RecyclerView.Adapter<TransaksiAdapter.CustomViewHolder> {
    //private ArrayList<String> values;


    private LayoutInflater mInflater;
    private ArrayList<TransaksiModel> trans;
    private Context context;
    private TransaksiHelper transaksiHelper;


    public TransaksiAdapter(Context context) {
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        transaksiHelper = new TransaksiHelper(context);


    }

    // Create new views (invoked by the layout manager)
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup,
                                               int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                viewGroup.getContext());
        View v =
                inflater.inflate(R.layout.row_view, viewGroup, false);
        // set the view's size, margins, paddings and layout parameters
        CustomViewHolder vh = new CustomViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final CustomViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final String code = trans.get(position).getCode();
        final String nohp = trans.get(position).getNohp();
        holder.editCode.setText(code);
        holder.editNohp.setText(nohp);


        holder.btnupdate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                trans.get(position).setCode(holder.editCode.getText().toString());
                trans.get(position).setNohp(holder.editNohp.getText().toString());

                transaksiHelper.open();
                transaksiHelper.update(trans.get(position));
                transaksiHelper.close();
                Toast.makeText(context, "Updated", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
        });
        holder.btndelete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                deleteitem(trans.get(position).getId());
                trans.remove(position);
                notifyDataSetChanged();

            }
        });


    }

    private void deleteitem(int id) {

        transaksiHelper.open();
        transaksiHelper.delete(id);
        transaksiHelper.close();

        Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return trans.size();
    }

    public void addItem(ArrayList<TransaksiModel> mData) {
        this.trans = mData;
        notifyDataSetChanged();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class CustomViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private EditText editCode, editNohp;
        private Button btnupdate, btndelete;
        private CardView cv;

        public CustomViewHolder(View itemView) {
            super(itemView);

            editCode = (EditText) itemView.findViewById(R.id.edit_code);
            editNohp = (EditText) itemView.findViewById(R.id.edit_nohp);
            btnupdate = (Button) itemView.findViewById(R.id.btn_update);
            btndelete = (Button) itemView.findViewById(R.id.btn_delete);
            cv = (CardView) itemView.findViewById(R.id.cv);


        }

    }


}