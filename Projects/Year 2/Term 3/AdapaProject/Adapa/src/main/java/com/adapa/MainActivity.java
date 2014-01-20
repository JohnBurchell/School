package com.adapa;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {

    final static String URL = "http://83.254.83.56:8001/";

    final static String URL_NASDAQ = "nasdaq_";
    final static String URL_DAX = "dax_";
    final static String URL_LSE = "lse_";

    final static String TAG_NASDAQ = "NASDAQ";
    final static String TAG_DAX = "DAX";
    final static String TAG_LSE = "LSE";

    final static String TEXT_COLOR_NASDAQ = "#2280b1";
    final static String TEXT_COLOR_DAX = "#a72d2c";
    final static String TEXT_COLOR_LSE = "#0f5418";

    final static int NASDAQ = 0;
    final static int DAX = 1;
    final static int LSE = 2;

    final static String SYMBOL = "symbols/";
    final static String STOCK = "stocks/";
    final static String USER = "users/";
    static String GET_SYMBOLS_URL = "_design/symbols/_view/get_company_data";

    final static String TEXT_COLOR_DARKER = "#3d3d3d";
    final static String TEXT_COLOR_DARK = "#444444";
    final static String TEXT_COLOR_LIGHT = "#f5f5f5";

    static Market nasdaq;
    static Market lse;
    static Market dax;
    int numMarketsLoaded = 0;


    final static String CHART_COLOR_GREEN = "#8eed5f";
    final static String CHART_COLOR_GREEN_2 = "#5caa16";
    final static String CHART_COLOR_RED = "#e52f2f";


    static ArrayList<Stock> stocks;
    static Stock activeStock = null;

    static String RSS_ECONOMY = "http://articlefeeds.nasdaq.com/nasdaq/categories?category=Economy";
    static String RSS_BUSINESS = "http://articlefeeds.nasdaq.com/nasdaq/categories?category=Business";
    static String RSS_TECHNOLOGY = "http://articlefeeds.nasdaq.com/nasdaq/categories?category=Technology";

    static String RSS_NASDAQ = "http://articlefeeds.nasdaq.com/nasdaq/categories?category=Technology";
    static String RSS_DAX = "http://feeds.finance.yahoo.com/rss/2.0/headline?s=%5EGDAXI&region=US&lang=en-US";
    static String RSS_LSE = "http://www.londonstockexchange.com/exchange/CompanyNewsRSS.html?newsSource=RNR";

    static String[] RSS_LINK = new String[]{RSS_ECONOMY, RSS_BUSINESS, RSS_TECHNOLOGY, RSS_NASDAQ, RSS_DAX, RSS_LSE};
    static String[] RSS_NAMES = new String[]{"Economy", "Business", "Technology"};
    static int RSS_INDEX = 0;

    static DrawerLayout mDrawerLayout;
    DrawerListAdapter adapter;
    ActionBarDrawerToggle mDrawerToggle;
    static ListView mDrawerList;
    static String[] menuTitleList;
    int[] menuIconList;

    static ProgressDialog pDialog;
    static boolean accessGranted = false;
    public static Boolean marketsLoaded = false;


    static User user;

    static CustomFragment activeFragment = null;
    private boolean dataCollected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pDialog = new ProgressDialog(this);

        promptLogin();
        getData();

        menuTitleList = getResources().getStringArray(R.array.menu_titles);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        menuIconList = new int[]{R.drawable.ic_icon_home, R.drawable.ic_icon_news, R.drawable.ic_icon_mystocks, R.drawable.ic_icon_about, R.drawable.ic_icon_logout};
        // Set the adapter for the list view
        ArrayList<DrawerItem> list = new ArrayList<DrawerItem>();
        for (int i = 0; i < menuTitleList.length; i++) {
            list.add(new DrawerItem(menuTitleList[i], menuIconList[i]));
        }
        adapter = new DrawerListAdapter(this, R.layout.drawer_list_item, list);

        mDrawerList.setAdapter(adapter);
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        if (savedInstanceState == null) {
            activeFragment = new PlaceholderFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content_frame, activeFragment)
                    .commit();
            activeFragment.setActionBar(this, getSupportActionBar());
        }

        selectItem(0, getSupportFragmentManager());
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_navigation_drawer,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                //getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void getData() {
        if (!dataCollected) {
            nasdaq = new Market();
            lse = new Market();
            dax = new Market();
            JSONListener listener = new JSONListener() {
                @Override
                public void onJSONAsyncComplete(Boolean validData) {
                    if(validData){
                        Log.d("MARKET_", "MARKET LOADED");
                        numMarketsLoaded++;
                        if(numMarketsLoaded >2)
                            activeFragment.marketList();
                    }else{
                        Log.d("MARKET_", "ERROR");
                    }
                }
            };
            JSONMarketAsync task1 = new JSONMarketAsync("https://script.google.com/macros/s/AKfycbxYQxUl4qTu-1Pz-rNxFOXfRbT87QG7R4lkaYkDe36fDX0LQ-o/exec?stock=IXIC", nasdaq);
            task1.execute();
            task1.setOnJSONAsyncComplete(listener);
            JSONMarketAsync task2 =new JSONMarketAsync("https://script.google.com/macros/s/AKfycbxYQxUl4qTu-1Pz-rNxFOXfRbT87QG7R4lkaYkDe36fDX0LQ-o/exec?stock=LSE.L", lse);
            task2.execute();
            task2.setOnJSONAsyncComplete(listener);
            JSONMarketAsync task3 =new JSONMarketAsync("https://script.google.com/macros/s/AKfycbxYQxUl4qTu-1Pz-rNxFOXfRbT87QG7R4lkaYkDe36fDX0LQ-o/exec?stock=DAX", dax);
            task3.execute();
            task3.setOnJSONAsyncComplete(listener);

            stocks = new ArrayList<Stock>();
            new JSONSymbolAsync(URL + URL_NASDAQ + SYMBOL + GET_SYMBOLS_URL, TAG_NASDAQ, URL_NASDAQ).execute();
            new JSONSymbolAsync(URL + URL_LSE + SYMBOL + GET_SYMBOLS_URL, TAG_LSE, URL_LSE).execute();
            new JSONSymbolAsync(URL + URL_DAX + SYMBOL + GET_SYMBOLS_URL, TAG_DAX, URL_DAX).execute();
            dataCollected = true;
        }
    }

    private void getUserSymbols(final String name) {
        user = User.logIn(name);
        showDialog();
        JSONUserAsync task = new JSONUserAsync(URL + USER + user.name);
        task.setOnJSONAsyncComplete(new JSONListener() {
            @Override
            public void onJSONAsyncComplete(Boolean validData) {
                dismissDialog();
                if (validData) {
                    Toast.makeText(MainActivity.this, "Welcome!", 500).show();
                } else {
                    Toast.makeText(MainActivity.this, "Login Failed.", 500).show();
                    logOut(false);
                }
            }
        });
        task.execute();
    }

    public void showDialog() {
        pDialog.setMessage("Logging a brother in...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    public static void dismissDialog() {
        pDialog.dismiss();
    }

    private void promptLogin() {
        if (user != null) {
            getUserSymbols(user.name);
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater factory = LayoutInflater.from(this);
        final View textEntryView = factory.inflate(R.layout.login_dialog, null);

        builder.setCancelable(false);
        // Set up the input
        final EditText userName = (EditText) textEntryView.findViewById(R.id.username);
        userName.setHint("Username");
        userName.setHintTextColor(Color.GRAY);
        userName.setInputType(InputType.TYPE_CLASS_TEXT);
        userName.setGravity(Gravity.LEFT);
        final EditText passWord = (EditText) textEntryView.findViewById(R.id.password);
        passWord.setHint("Password");
        passWord.setHintTextColor(Color.GRAY);
        passWord.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        passWord.setGravity(Gravity.LEFT);
        builder.setView(textEntryView);
// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getUserSymbols(userName.getText().toString());
            }
        });

        builder.show();
    }

    public static void getStockAndAdd(String name) {
        for (Stock s : stocks) {
            if (s.symbol.equals(name)) {
                s.added = true;
            }
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater menuInflater = getMenuInflater();
        activeFragment.createActionBar(menuInflater, menu);
        activeFragment.setActionBar(this, getSupportActionBar());

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        //menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        activeFragment.prepareActionBar(menu);

        return super.onPrepareOptionsMenu(menu);
    }

    public void refreshList(MenuItem item) {
        activeFragment.refreshList();
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends CustomFragment {
        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_home, container, false);
            return rootView;
        }
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position, getSupportFragmentManager());
        }
    }

    /**
     * Swaps fragments in the main content view
     */
    public void selectItem(int position, FragmentManager fragmentManager) {
        switch (position) {
            case 0:
                activeFragment = new HomeFragment();
                break;
            case 1:
                activeFragment = new NewsFragment();
                break;
            case 2:
                activeFragment = new MyStocksFragment();
                break;
            case 3:
                activeFragment = new AboutFragment();
                break;
            case 4:
                logOut(false);
                activeFragment = new HomeFragment();
                break;
            default:
                activeFragment = new HomeFragment();
                break;
        }

        getSupportActionBar().setIcon(menuIconList[position]);
        // Insert the fragment by replacing any existing fragment
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, activeFragment)
                .commit();

        // Highlight the selected item, update the title, and close the drawer
        mDrawerList.setItemChecked(position, true);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    public void addToMyStocks(View view) {
        TextView textView = (TextView) view.findViewById(R.id.textView1);
        String symbol = (String) textView.getText();

        for (Stock s : stocks) {
            if (s.symbol.equals(symbol)) {
                activeFragment.fetchSingleStockData(s);
                MyStocksFragment.popupWindow.dismiss();
            }
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Close?");
        builder.setMessage("Do you really wish to close?");
        builder.setCancelable(false);

        // Set up the buttons
        builder.setPositiveButton("Ya, I do", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                close();
            }
        });
        builder.setNegativeButton("No.", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ;
            }
        });
        builder.show();
    }

    private void logOut(boolean closing) {
        user = null;
        if (!closing)
            promptLogin();
    }

    private void close() {
        logOut(true);
        finish();
    }
}