package android.example.illumismart;

import android.content.Context;
import android.content.Intent;
import android.example.illumismart.entity.dataItem;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;


public class DataAdapter extends ListAdapter<dataItem, DataAdapter.DataViewHolder> {

    public DataAdapter(@NonNull DiffUtil.ItemCallback<dataItem> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_item_view, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        dataItem current = getItem(position);
        // timestamp reformatting: 20210226236 into 2021-02-26, 12:36:03
        String time = current.getTimestamp().substring(0, 4) + "-" + current.getTimestamp().substring(4, 6) + "-" +
                current.getTimestamp().substring(6, 8) + ", " + current.getTimestamp().substring(8, 10) + ":" +
                current.getTimestamp().substring(10, 12) + ":" +
                current.getTimestamp().substring(12, 14);
        holder.mtimeStamp.setText(time);
        holder.mdataItem.setText(current.getDataItem());
        // set data item color
        if (current.getDataItem().equals("Illuminance")) {
            holder.mdataItem.setTextColor(Color.parseColor("#92A6CB"));
        } else if (current.getDataItem().equals("Flicker")) {
            holder.mdataItem.setTextColor(Color.parseColor("#B49EBC"));
        }
    }

    static class DataDiff extends DiffUtil.ItemCallback<dataItem> {

        @Override
        public boolean areItemsTheSame(@NonNull dataItem oldItem, @NonNull dataItem newItem) {
            return oldItem.getTimestamp().equals(newItem.getTimestamp());
        }

        @Override
        public boolean areContentsTheSame(@NonNull dataItem oldItem, @NonNull dataItem newItem) {
            return oldItem.getDataItem().equals(newItem.getDataItem());
        }
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {
        TextView mtimeStamp;
        TextView mdataItem;

        public DataViewHolder(View itemView) {
            super(itemView);
            mdataItem = itemView.findViewById(R.id.data_item);
            mtimeStamp = itemView.findViewById(R.id.time_stamp);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: if data_item == xxx, start responding activity with intent extra timestamp
                    int mPosition = getLayoutPosition();
                    dataItem click = getItem(mPosition);
                    Log.d("jinlaimei1", String.valueOf(mPosition));
                    String click_timestamp = click.getTimestamp();
                    String click_item = click.getDataItem();
                    Context ctx = v.getContext();
                    Log.d("jinlaimei2", click_timestamp);
                    Log.d("jinlaimei3", click_item);

                    if (click_item.equals("Illuminance")) {
                        Intent intent = new Intent(ctx,  IlluminanceRecordActivity.class);
                        intent.putExtra("timestamp", click_timestamp);
                        ctx.startActivity(intent);
                    }
                    /* else if (click_item.equals("Flicker")) {
                        Intent intent = new Intent(ctx,  FlickerRecordActivity.class);
                        intent.putExtra("timestamp", click_timestamp);
                        ctx.startActivity(intent);
                    } */
                }
            });
        }
    }
}
