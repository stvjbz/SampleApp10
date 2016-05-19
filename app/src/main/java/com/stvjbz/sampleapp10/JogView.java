package com.stvjbz.sampleapp10;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by stvjbz on 16/05/18.
 */
public class JogView extends ListActivity implements LoaderManager.LoaderCallbacks<Curosor> {
    private static final int CURSORLOADER_ID = 0;
    private ListAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view);

        Button btnView = (Button) this.findViewById(R.id.btnRet);
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                @Override
                public void onClick(View v) {
                    finish();
                }
            }
        });

        mAdapter = new ListAdapter(this, null, 0);
        setListAdapter(mAdapter);

        getLoaderManager().initLoader(CURSORLOADER_ID, null, this);
    }


    @Override
    public Loader<Curosor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, JogRecordContentProvider.CONTENT_URI, null, null, null, "_id DESC");
    }

    @Override
    public void onLoadFinished(Loader<Curosor> loader, Curosor cursor) {
        mAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Curosor> loader) {
        mAdapter.swapCursor(null);
    }
}
