package com.mx3.todo.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "todo_item")
public class ToDoItem implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private Long id;
    @ColumnInfo(name = "user_id")
    private Long userId;
    private String title;
    @SerializedName("completed")
    @ColumnInfo(name = "completed")
    private Boolean isCompleted;

    // Constructors

    public ToDoItem() {
    }

    @Ignore
    public ToDoItem(Long id, Long userId, String title, Boolean isCompleted) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.isCompleted = isCompleted;
    }

    // Helper Methods

    public void toggleCompleted() {
        this.isCompleted = !isCompleted;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }

    // Parcelable Implemented Methods

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeValue(this.userId);
        dest.writeString(this.title);
        dest.writeValue(this.isCompleted);
    }

    protected ToDoItem(Parcel in) {
        this.id = (Long) in.readValue(Integer.class.getClassLoader());
        this.userId = (Long) in.readValue(Integer.class.getClassLoader());
        this.title = in.readString();
        this.isCompleted = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public static final Parcelable.Creator<ToDoItem> CREATOR = new Parcelable.Creator<ToDoItem>() {
        @Override
        public ToDoItem createFromParcel(Parcel source) {
            return new ToDoItem(source);
        }

        @Override
        public ToDoItem[] newArray(int size) {
            return new ToDoItem[size];
        }
    };
}
