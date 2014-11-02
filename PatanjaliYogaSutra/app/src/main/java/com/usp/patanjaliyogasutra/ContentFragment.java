package com.usp.patanjaliyogasutra;

import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.usp.common.ListTagHandler;

public class ContentFragment extends SherlockFragment {

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		String message = getArguments().getString("message");
		View view = inflater.inflate(R.layout.content_fragment, null);
		TextView txtView = (TextView) view.findViewById(R.id.txtQuote);
		txtView.setText(Html.fromHtml(message, null, new ListTagHandler()));
		txtView.setMovementMethod(new ScrollingMovementMethod());
		return view;
	}
}
