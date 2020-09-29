package ru.onedr.earlzzz.testrentateamdagger;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ru.onedr.earlzzz.testrentateamdagger.pojo.Post;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<Post> mPosts;
    private ClickListener clickListener;

    @Inject
    public RecyclerViewAdapter(ClickListener clickListener) {
        this.clickListener = clickListener;
        mPosts = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_home, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Post post = mPosts.get(position);
        holder.bindCrime(post);
        (holder).mRLView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CardUserActivity.class);
                intent.putExtra("firstName", mPosts.get(position).getFirsName());
                intent.putExtra("lastName", mPosts.get(position).getLastName());
                intent.putExtra("email", mPosts.get(position).getUserEmail());
                intent.putExtra("avatar", mPosts.get(position).getAvatarSrc());
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;
        private TextView mTextView;
        private RelativeLayout mRLView;

        ViewHolder(View itemView) {
            super(itemView);

            mTextView = itemView.findViewById(R.id.textView_home);
            mImageView = itemView.findViewById(R.id.image_crime);
            mRLView = itemView.findViewById(R.id.rl_sitt);


        }
        void bindCrime(Post mPost) {
            mTextView.append(mPost.getFirsName()+" "+mPost.getLastName());
            Picasso.get().load(mPost.getAvatarSrc()).fit().centerInside().into(mImageView);
        }
    }

    public interface ClickListener {
        void launchIntent(String name);
    }

    public void setData(List<Post> data) {
        this.mPosts.addAll(data);
        notifyDataSetChanged();
    }
}

