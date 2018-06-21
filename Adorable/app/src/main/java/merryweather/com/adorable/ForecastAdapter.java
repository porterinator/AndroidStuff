package merryweather.com.adorable;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import merryweather.com.adorable.model.City;
import merryweather.com.adorable.model.ForecastResponse;
import merryweather.com.adorable.utils.DateConverter;

/**
 * Created by S on 21.06.2018.
 */

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ViewHolder> {

    List<ForecastResponse.ForecastItem> forecastItems;

    private Context mContext;

    public void setItems(Context context, List<ForecastResponse.ForecastItem> mItems) {
        mContext = context;
        this.forecastItems = mItems;
    }

    @Override
    public int getItemCount() {
        return forecastItems == null? 0 : forecastItems.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listitem_forecast, parent, false);
        ViewHolder vh = new ViewHolder(v);
        vh.mDate = v.findViewById(R.id.date);
        vh.mDescription = v.findViewById(R.id.description);
        vh.mHighLowTemp = v.findViewById(R.id.lowHighTemp);
        vh.mImage = v.findViewById(R.id.weatherImage);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mDate.setText(forecastItems.get(position).dt_txt);
        holder.mDescription.setText(forecastItems.get(position).weather.get(0).description);
        holder.mHighLowTemp.setText(String.format("%2.0f° / %2.0f°",
                forecastItems.get(position).main.temp_max,
                forecastItems.get(position).main.temp_min));
        Glide.with(mContext)
                .load(String.format("http://openweathermap.org/img/w/%s.png", forecastItems.get(position).weather.get(0).icon))
                .asBitmap()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.mImage);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mDate;
        public TextView mDescription;
        public TextView mHighLowTemp;
        public ImageView mImage;
        public ViewHolder(View v) {
            super(v);
        }
    }

}
