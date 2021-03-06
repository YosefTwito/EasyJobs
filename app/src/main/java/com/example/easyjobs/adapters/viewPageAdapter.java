package com.example.easyjobs.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import com.example.easyjobs.Objects.Picture;
import com.example.easyjobs.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class viewPageAdapter extends PagerAdapter
{
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<Picture> pages;
    private boolean job;
    private boolean deletable;

    public viewPageAdapter(Context context, ArrayList<Picture> pages, boolean job, boolean deletable)
    {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        this.pages = pages;
        this.job = job;
        this.deletable = deletable;
    }

    // Returns the number of pages to be displayed in the ViewPager.
    @Override
    public int getCount()
    {
        return pages.size();
    }

    // Returns true if a particular object (page) is from a particular page
    @Override
    public boolean isViewFromObject(View view, Object object)
    {
        return view == object;
    }

    // This method should create the page for the given position passed to it as an argument.
    @Override
    public Object instantiateItem(ViewGroup container, int position)
    {
        // Inflate the layout for the page
        View itemView = mLayoutInflater.inflate(R.layout.image_layout, container, false);
        // Find and populate data into the page (i.e set the image)
        ImageView imageView = (ImageView) itemView.findViewById(R.id.ImageLayout);
        File f = pages.get(position).getF();
        if (deletable)
        {
            Button b = itemView.findViewById(R.id.imageView_deleteButton);
            b.setVisibility(View.VISIBLE);
            b.setEnabled(true);
            b.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    System.out.println(pages.get(position).getName());
                    pages.remove(position);
                    notifyDataSetChanged();
                }
            });
        }
        Bitmap myBitmap = BitmapFactory.decodeFile(f.getAbsolutePath());
        imageView.setImageBitmap(myBitmap);

        // Add the page to the container
        container.addView(itemView);
        // Return the page
        return itemView;
    }

    // Removes the page from the container for the given position.
    @Override
    public void destroyItem(ViewGroup container, int position, Object object)
    {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(Object object)
    {
        return PagerAdapter.POSITION_NONE;
    }
}
