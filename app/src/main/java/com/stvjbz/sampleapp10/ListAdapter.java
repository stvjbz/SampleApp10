package com.stvjbz.sampleapp10;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by stvjbz on 16/05/18.
 */
public class ListAdapter extends CursorAdapter {

    public ListAdapter(Context context, Cursor c, int flag) {
        super(context, c, flag);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Cursorからデータを取り出す
        int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID));
        String data = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper
                .COLUMN_ELAPSEDTIME));
        double distance = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper
                .COLUMN_DISTANCE));
        double speed = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_SPEED));
        String address = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ADDRESS));

        TextView tv_id = (TextView) view.findViewById(R.id._id);
        TextView tv_date = (TextView) view.findViewById(R.id.date);
        TextView tv_elapsed_time = (TextView) view.findViewById(R.id.elapsed_time);
        TextView tv_distance = (TextView) view.findViewById(R.id.distance);
        TextView tv_speed = (TextView) view.findViewById(R.id.speed);
        TextView tv_place = (TextView) view.findViewById(R.id.address);

        tv_id.setText(String.valueOf(id));
        tv_date.setText(String.valueOf(date));
        tv_elapsed_time.setText(String.valueOf(elapsedTime));
        tv_distance.setText(String.valueOf(String.format("%.2f", distance/1000)));
        tv_speed.setText(String.valueOf("%.2f", speed));
        tv_place.setText(String.valueOf(address));
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context
                .LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.row, null);
        return  view;
    }
}
