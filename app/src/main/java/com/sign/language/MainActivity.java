/* Activity which shows gridview of Alphabets/Numbers/Frequently used words based on choice */
package com.sign.language;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class MainActivity extends Activity {
    private GridView gridView;
    private GridViewAdapter customGridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final int choice = getIntent().getExtras().getInt("Userchoice");
        gridView = (GridView) findViewById(R.id.gridView);
        customGridAdapter = new GridViewAdapter(this, R.layout.row_grid, getData(choice));
        gridView.setAdapter(customGridAdapter);

        gridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                /* Call DisplayActivity with the choice and image position */
                Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
                intent.putExtra("Image Int", position);
                intent.putExtra("Choice", choice);
                startActivity(intent);
                finish();
            }

        });
    }

    private ArrayList<ImageItem> getData(int choice) {
        final ArrayList<ImageItem> imageItems = new ArrayList<ImageItem>();

        int[] arr_st = {R.array.image_ids,
                R.array.image_num,
                R.array.image_fuw};
        TypedArray imgs = getResources().obtainTypedArray(arr_st[choice - 1]);

        for (int i = 0; i < imgs.length(); i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),
                    imgs.getResourceId(i, -1));
            imageItems.add(new ImageItem(bitmap, " " + i + 1));
        }

        return imageItems;
    }
}
