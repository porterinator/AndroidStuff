package merryweather.com.ltech.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import merryweather.com.ltech.R;
import merryweather.com.ltech.model.Entity;

/**
 * Created by S on 19.05.2018.
 */

public class EntitiesAdapter extends RecyclerView.Adapter<EntitiesAdapter.ViewHolder> {


    private ArrayList<Entity> mEntities = null;
    private Context mContext;

    private final PublishSubject<Entity> onClickSubject = PublishSubject.create();

    public void setEntities(Context context, ArrayList<Entity> entities) {
        mContext = context;
        mEntities = entities;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listitem_entity, parent, false);
        ViewHolder vh = new ViewHolder(v);
        vh.mTitle = v.findViewById(R.id.title);
        vh.mDescription = v.findViewById(R.id.desc);
        vh.mTime = v.findViewById(R.id.time);
        vh.mImage = v.findViewById(R.id.image);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTitle.setText(mEntities.get(position).title);
        holder.mDescription.setText(mEntities.get(position).text);
        holder.mTime.setText(mEntities.get(position).date);
        Glide.with(mContext)
                .load(mEntities.get(position).image)
                .asBitmap()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.mImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSubject.onNext(mEntities.get(position));
            }
        });
    }

    public Observable<Entity> getPositionClicks(){
        return onClickSubject;
    }

    @Override
    public int getItemCount() {
        return mEntities == null ? 0 : mEntities.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitle;
        public TextView mDescription;
        public TextView mTime;
        public ImageView mImage;
        public ViewHolder(View v) {
            super(v);
        }
    }

}

