package com.appstacks.indiannaukribazaar.adapter;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.appstacks.indiannaukribazaar.R;
import com.appstacks.indiannaukribazaar.data.Constant;
import com.appstacks.indiannaukribazaar.model.News;
import com.appstacks.indiannaukribazaar.utils.TimeAgo;
import com.appstacks.indiannaukribazaar.utils.Tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HorizontalAdapterDetail extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<News> items = new ArrayList<>();

    private Context ctx;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, News obj, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.onItemClickListener = mItemClickListener;
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public HorizontalAdapterDetail(Context context, List<News> items) {
        this.items = items;
        ctx = context;

       /* Collections.sort(items, new Comparator<News>() {
            public int compare(News o1, News o2) {
                Long c1=o1.created_at;
                Long c2=o2.created_at;
                return c1.compareTo(c2);
            }
        });*/
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView title;
        public ImageView icon;
        public TextView timedate;
        public LinearLayout lyt_parent;

        public OriginalViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.title);
            icon = v.findViewById(R.id.icon);
            timedate = v.findViewById(R.id.timedate);
            lyt_parent = v.findViewById(R.id.lyt_parent);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontalitemlist, parent, false);
        vh = new HorizontalAdapterDetail.OriginalViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof OriginalViewHolder) {
            final News p = items.get(position);
            OriginalViewHolder v = (OriginalViewHolder) holder;
            v.title.setText(p.title);
            Log.e("date",p.created_at +"");
            v.timedate.setText(TimeAgo.get(ctx, p.created_at));
          //  Tools.displayImage(ctx, v.icon, Constant.getURLimgTopic(p.image));
            Tools.displayImageThumb(ctx, v.icon, Constant.getURLimgNews(p.image), 0.5f);
          //  v.lyt_color.setColorFilter(Color.parseColor(p.color), android.graphics.PorterDuff.Mode.SRC_IN);
            //v.icon.setColorFilter(Color.WHITE, android.graphics.PorterDuff.Mode.SRC_IN);
            v.lyt_parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(view, p, position);
                    }
                }
            });
        }
    }

    // Return the size of your data set (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return items.size();
    }

}