package com.usp.patanjaliyogasutra;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.usp.common.AppConstants;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

public class PageAdapter extends FragmentStatePagerAdapter {
	private Context context;
	private int count;
	private Properties properties;

	public PageAdapter(FragmentManager fm, Context context) {
		super(fm);
		this.context = context;
		InputStream rawResource = context.getResources().openRawResource(R.raw.content1);
		properties = new Properties();
		try {
			properties.load(rawResource);
		} catch (IOException e) {
			Log.w(getClass().getName(), e.toString());
		}
		count = properties.size();
	}

	@Override
	public Fragment getItem(int position) {
		Fragment fragment = Fragment.instantiate(context, ContentFragment.class.getName());
		// Supply num input as an argument.
		Bundle args = new Bundle();
		String message = properties.getProperty(String.valueOf(position));
		args.putString(AppConstants.MESSAGE, message);
		fragment.setArguments(args);
		return fragment;
	}

	public int getCount() {
		return count;
	}

	public String getMessage(int id) {
		return properties.getProperty(String.valueOf(id));
	}
}
