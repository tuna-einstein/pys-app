package com.usp.patanjaliyogasutra;

import java.util.Random;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.text.Html;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar.TabListener;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

public class ViewPagerFragmentActivity extends SherlockFragmentActivity
implements TabListener {
	private static String TYPE = "text/plain";
	private PageAdapter mPagerAdapter;
	private ViewPager pager;
	Random r=new Random();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.viewpager_layout);
		final ActionBar actionBar = getSupportActionBar();
	    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	    this.initialisePaging();
	    // add tabs
	   
	    actionBar.addTab(actionBar.newTab()
	              .setText(R.string.chapter1)
	              .setTabListener(this));
	    actionBar.addTab(actionBar.newTab()
	              .setText(R.string.chapter2)
	              .setTabListener(this));
	    actionBar.addTab(actionBar.newTab()
	              .setText(R.string.chapter3)
	              .setTabListener(this));
	    actionBar.addTab(actionBar.newTab()
	              .setText(R.string.chapter4)
	              .setTabListener(this));

//	    Tab tab2 = actionBar.newTab()
//	           .setText("TabTitle2")
//	           .setTabListener(null);
//	    actionBar.addTab(tab2);

//	    // check if there is a saved state to select active tab
//	    if( savedInstanceState != null ){
//	      getSupportActionBar().setSelectedNavigationItem(
//	                  savedInstanceState.getInt(ACTIVE_TAB));
//	    }
		//setupActionBar();
		
	}

	private void initialisePaging() {
		this.mPagerAdapter  = new PageAdapter(super.getSupportFragmentManager(), this);
		pager = (ViewPager)super.findViewById(R.id.viewpager);
		pager.setAdapter(this.mPagerAdapter);


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater menuInflater = getSupportMenuInflater();
		menuInflater.inflate(R.menu.content_page_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{

		if (item.getItemId() == R.id.menu_random) {
			pager.setCurrentItem(r.nextInt(mPagerAdapter.getCount()), true);
			return true;
		} else if (item.getItemId() ==  R.id.menu_next) {
			if (pager.getCurrentItem() < mPagerAdapter.getCount() - 1) {
				pager.setCurrentItem(pager.getCurrentItem() + 1);
			}
			return true;
		} else if (item.getItemId() ==  R.id.menu_previous) {
			if (pager.getCurrentItem() > 0) {
				pager.setCurrentItem(pager.getCurrentItem() - 1);
			}
			return true;
		} else if (item.getItemId() ==  R.id.menu_share) {
			int currentItem = pager.getCurrentItem();
			String message = mPagerAdapter.getMessage(currentItem);
			showShareOptions(message);
			return true;
		} else if (item.getItemId() == R.id.menu_info) {
			Intent myIntent = new Intent(this, InfoActivity.class);
			startActivity(myIntent);
			return true;
		} else if (item.getItemId() == R.id.menu_rate_me) {
			Intent marketLaunch = new Intent(Intent.ACTION_VIEW);
			marketLaunch.setData(Uri.parse("market://search?q=com.usp.patanjaliyogasutra"));
			startActivity(marketLaunch);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void showShareOptions(String message) {
		Intent i=new Intent(android.content.Intent.ACTION_SEND);
		i.setType(TYPE);
		i.putExtra(android.content.Intent.EXTRA_SUBJECT,
				getResources().getString(R.string.share_subject));
		i.putExtra(android.content.Intent.EXTRA_TEXT,  Html.fromHtml(message).toString());
		startActivity(Intent.createChooser(i,
				getResources().getString(R.string.share_title)));
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
	if (tab.getText().equals(getText(R.string.chapter1))) {
		pager.setCurrentItem(0);
	}
	if (tab.getText().equals(getText(R.string.chapter2))) {
		pager.setCurrentItem(52);
	}
	if (tab.getText().equals(getText(R.string.chapter3))) {
		pager.setCurrentItem(108);
	}
	if (tab.getText().equals(getText(R.string.chapter4))) {
		pager.setCurrentItem(165);
	}
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
}
