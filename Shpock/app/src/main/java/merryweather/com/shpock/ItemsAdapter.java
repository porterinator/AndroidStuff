package merryweather.com.shpock;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import merryweather.com.shpock.model.Item;

/**
 * Created by S on 19.04.2018.
 */

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {



    private List<Item> mItems;

    private Context mContext;

    public void setItems(Context context, List<Item> mItems) {
        mContext = context;
        this.mItems = mItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listitem_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        vh.mTitle = v.findViewById(R.id.title);
        vh.mDescription = v.findViewById(R.id.desc);
        vh.mPrice = v.findViewById(R.id.price);
        vh.mImage = v.findViewById(R.id.image);
        return vh;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTitle.setText(mItems.get(position).getTitle());
        holder.mDescription.setText(mItems.get(position).getDescription());
        holder.mPrice.setText(String.valueOf(mItems.get(position).getPrice()));
        Glide.with(mContext)
                .load(mItems.get(position).getImage())
                .asBitmap()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.mImage);
    }

    @Override
    public int getItemCount() {
        return mItems == null? 0 : mItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitle;
        public TextView mDescription;
        public TextView mPrice;
        public ImageView mImage;
        public ViewHolder(View v) {
            super(v);
        }
    }

}
