package merryweather.com.easymarkets;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import merryweather.com.easymarkets.model.Appartment;

/**
 * Created by S on 19.05.2018.
 */

public class AppAdapter extends RecyclerView.Adapter<AppAdapter.ViewHolder> {


    private List<Appartment> mEntities = null;
    private Context mContext;

    private final PublishSubject<Appartment> onClickSubject = PublishSubject.create();

    public void setEntities(Context context, List<Appartment> entities) {
        mContext = context;
        mEntities = entities;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listitem_app, parent, false);
        ViewHolder vh = new ViewHolder(v);
        vh.mBedrooms = v.findViewById(R.id.bedrooms);
        vh.mAvailStart = v.findViewById(R.id.availableStart);
        vh.mAvailEnd = v.findViewById(R.id.availableEnd);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mBedrooms.setText(String.valueOf(mEntities.get(position).numberOfBedrooms));
        holder.mAvailStart.setText(String.valueOf(mEntities.get(position).availableStart));
        holder.mAvailEnd.setText(String.valueOf(mEntities.get(position).availableEnd));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSubject.onNext(mEntities.get(position));
            }
        });
    }

    public Observable<Appartment> getPositionClicks(){
        return onClickSubject;
    }

    @Override
    public int getItemCount() {
        return mEntities == null ? 0 : mEntities.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mBedrooms;
        public TextView mAvailStart;
        public TextView mAvailEnd;
        public ViewHolder(View v) {
            super(v);
        }
    }

}

