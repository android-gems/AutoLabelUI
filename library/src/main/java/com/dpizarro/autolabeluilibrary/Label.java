package com.dpizarro.autolabeluilibrary;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/*
 * Copyright (C) 2015 David Pizarro
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class Label extends LinearLayout {

    private TextView mTextView;
    private OnClickCrossListener listenerOnCrossClick;
    private OnLabelClickListener listenerOnLabelClick;

    public Label(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Label(Context context, int textSize, int iconCross,
            boolean showCross, int textColor, int backgroundColor, boolean labelsClickables) {
        super(context);
        init(context, textSize, iconCross, showCross, textColor,
                backgroundColor, labelsClickables);
    }

    private void init(final Context context, int textSize, int iconCross,
            boolean showCross, int textColor, int backgroundColor, boolean labelsClickables) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.label_view, this, true);

        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.llLabel);
        linearLayout.setBackgroundColor(backgroundColor);
        if(labelsClickables){
            linearLayout.setClickable(true);
            linearLayout.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listenerOnLabelClick != null) {
                        listenerOnLabelClick.onClickLabel(view);
                    }
                }
            });
        }

        mTextView = (TextView) view.findViewById(R.id.tvLabel);
        mTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        mTextView.setTextColor(textColor);

        ImageView imageView = (ImageView) view.findViewById(R.id.ivCross);

        if (showCross) {
            imageView.setImageResource(iconCross);
            imageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listenerOnCrossClick != null) {
                        listenerOnCrossClick.onClickCross(view);
                    }
                }
            });
        } else {
            imageView.setVisibility(GONE);
        }

    }

    public String getText() {
        return mTextView.getText().toString();
    }

    public void setText(String text) {
        mTextView.setText(text);
    }

    /**
     * Set a callback listener when the cross icon is clicked.
     *
     * @param listener Callback instance.
     */
    public void setOnClickCrossListener(OnClickCrossListener listener) {
        this.listenerOnCrossClick = listener;
    }

    /**
     * Interface for a callback listener when the cross icon is clicked.
     */
    public interface OnClickCrossListener {

        /**
         * Call when the cross icon is clicked.
         */
        void onClickCross(View v);
    }

    /**
     * Set a callback listener when the {@link Label} is clicked.
     *
     * @param listener Callback instance.
     */
    public void setOnLabelClickListener(OnLabelClickListener listener) {
        this.listenerOnLabelClick = listener;
    }

    /**
     * Interface for a callback listener when the {@link Label} is clicked.
     * Container Activity/Fragment must implement this interface.
     */
    public interface OnLabelClickListener {

        /**
         * Call when the {@link Label} is clicked.
         */
        void onClickLabel(View v);
    }
}