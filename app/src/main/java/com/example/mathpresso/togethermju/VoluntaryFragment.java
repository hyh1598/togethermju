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

import net.cachapa.expandablelayout.ExpandableLinearLayout;


/**
 * Created by choijinjoo on 2016. 11. 9..
 */
public class VoluntaryFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_voluntary, container, false);
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
                    .inflate(R.layout.recycler_item_voluntary, parent, false);
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

        public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnFocusChangeListener {

            private ExpandableLinearLayout expandableLayout;
            private TextView expandButton;

            public ViewHolder(View itemView) {
                super(itemView);

                expandableLayout = (ExpandableLinearLayout) itemView.findViewById(R.id.expandable_layout);
                expandButton = (TextView) itemView.findViewById(R.id.expand_button);

                expandButton.setOnClickListener(this);
                expandButton.setFocusableInTouchMode(true);
                expandButton.setOnFocusChangeListener(this);
            }

            public void bind(int position) {
                expandButton.setText(position + ". Click to expand");
            }

            @Override
            public void onClick(View view) {
                if (expandButton.isFocused()) {
                    expandButton.clearFocus();
                } else {
                    expandButton.requestFocus();
                }
            }

            @Override
            public void onFocusChange(View view, boolean focused) {
                if (focused) {
                    expandableLayout.expand();
                } else {
                    expandableLayout.collapse();
                }
            }
        }
    }
}
