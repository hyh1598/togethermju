package com.example.mathpresso.togethermju;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Created by choijinjoo on 2016. 11. 9..
 */
public class ContestFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contest, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new SimpleAdapter(recyclerView));
        return rootView;
    }
    private static class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.ViewHolder> {
        private static final int UNSELECTED = -1;

        private RecyclerView recyclerView;
        private int selectedItem = UNSELECTED;

        public SimpleAdapter(RecyclerView recyclerView) {
            this.recyclerView = recyclerView;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycler_item_contest, parent, false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.bind(position);
        }

        @Override
        public int getItemCount() {
            return 100;
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private TextView expandButton;
            private int position;

            public ViewHolder(View itemView) {
                super(itemView);

                expandButton = (TextView) itemView.findViewById(R.id.expand_button);

                expandButton.setOnClickListener(this);
            }

            public void bind(int position) {
                this.position = position;

                expandButton.setText(position + ". Tap to expand");

                expandButton.setSelected(false);
            }

            @Override
            public void onClick(View view) {
                ViewHolder holder = (ViewHolder) recyclerView.findViewHolderForAdapterPosition(selectedItem);
                if (holder != null) {
                    holder.expandButton.setSelected(false);
                }

                if (position == selectedItem) {
                    selectedItem = UNSELECTED;
                } else {
                    expandButton.setSelected(true);
                    selectedItem = position;
                }
            }
        }
    }

}
