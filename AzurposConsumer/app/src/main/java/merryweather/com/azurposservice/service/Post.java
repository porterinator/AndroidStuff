package merryweather.com.azurposservice.service;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by S on 03.04.2018.
 */

public class Post implements Parcelable{

    public long userId;
    public long id;
    public String title;
    public String body;

    public Post(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        userId = in.readLong();
        id = in.readLong();
        title = in.readString();
        body = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(userId);
        parcel.writeLong(id);
        parcel.writeString(title);
        parcel.writeString(body);
    }

    public static final Creator<Post> CREATOR = new
            Creator<Post>() {
                public Post createFromParcel(Parcel in) {
                    return new Post(in);
                }

                public Post[] newArray(int size) {
                    return new Post[size];
                }
            };


}
