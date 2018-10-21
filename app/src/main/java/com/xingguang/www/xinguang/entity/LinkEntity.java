package com.xingguang.www.xinguang.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @创建者 pengbo
 * @创建时间 2018/10/20 10:19
 * @描述 TODO
 */
public class LinkEntity implements Parcelable {
    public String  id;
    public String  title;
    public String  website;
    public boolean isCollection;

    public LinkEntity() {
    }

    protected LinkEntity(Parcel in) {
        id = in.readString();
        title = in.readString();
        website = in.readString();
        isCollection = in.readByte() != 0;
    }

    public static final Creator<LinkEntity> CREATOR = new Creator<LinkEntity>() {
        @Override
        public LinkEntity createFromParcel(Parcel in) {
            return new LinkEntity(in);
        }

        @Override
        public LinkEntity[] newArray(int size) {
            return new LinkEntity[size];
        }
    };

    public boolean isCollection() {
        return isCollection;
    }

    public void setCollection(boolean collection) {
        isCollection = collection;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeString(website);
        parcel.writeByte((byte) (isCollection ? 1 : 0));
    }
}
