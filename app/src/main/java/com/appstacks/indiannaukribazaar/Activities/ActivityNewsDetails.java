package com.appstacks.indiannaukribazaar.Activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.DrawableRes;

import com.appstacks.indiannaukribazaar.R;
import com.appstacks.indiannaukribazaar.Slider.ImageData;
import com.appstacks.indiannaukribazaar.adapter.HorizontalAdapter;
import com.google.android.material.snackbar.Snackbar;

import androidx.core.widget.NestedScrollView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.appstacks.indiannaukribazaar.connection.API;
import com.appstacks.indiannaukribazaar.connection.RestAdapter;
import com.appstacks.indiannaukribazaar.connection.response.ResponseNews;
import com.appstacks.indiannaukribazaar.connection.response.ResponseNewsDetails;
import com.appstacks.indiannaukribazaar.data.AppConfig;
import com.appstacks.indiannaukribazaar.data.Constant;
import com.appstacks.indiannaukribazaar.data.GDPR;
import com.appstacks.indiannaukribazaar.data.SharedPref;
import com.appstacks.indiannaukribazaar.data.ThisApp;
import com.appstacks.indiannaukribazaar.model.News;
import com.appstacks.indiannaukribazaar.model.SearchBody;
import com.appstacks.indiannaukribazaar.model.type.SourceType;
import com.appstacks.indiannaukribazaar.room.AppDatabase;
import com.appstacks.indiannaukribazaar.room.DAO;
import com.appstacks.indiannaukribazaar.room.table.NewsEntity;
import com.appstacks.indiannaukribazaar.utils.NetworkCheck;
import com.appstacks.indiannaukribazaar.utils.TimeAgo;
import com.appstacks.indiannaukribazaar.utils.Tools;
import com.appstacks.indiannaukribazaar.utils.ViewAnimation;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityNewsDetails extends AppCompatActivity {

    private static final String EXTRA_OBJECT = "key.EXTRA_OBJECT";

    // activity transition
    public static void navigate(Activity activity, News news) {
        Intent i = navigateBase(activity, news);
        activity.startActivity(i);
    }

    public static Intent navigateBase(Context context, News news) {
        Intent i = new Intent(context, ActivityNewsDetails.class);
        i.putExtra(EXTRA_OBJECT, news);
        return i;
    }

    // extra obj
    private boolean is_saved;
    boolean lyt_navigation_hide = false;

    private DAO dao;
    private News news = null;
    private List<String> topics = new ArrayList<>();
    private List<String> gallery = new ArrayList<>();

    private Call<ResponseNewsDetails> callbackCall = null;

    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    private NestedScrollView nested_scroll_view;
    private View parent_view;
    private ShimmerFrameLayout shimmer;
    private ImageView image, img_type;
    private View lyt_main_content, featured, lyt_bottom_bar, lyt_toolbar;
    private WebView web_view;
    private TextView date, type, total_view, total_comment;
    private MenuItem menu_refresh;
    private boolean is_running;
    private boolean is_activity_active = false;
    private Intent header_intent = null;
    ThisApp applicationHelper;
    String webLink;
    String imgLink;

    ImageView imageView2;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);


        imageView2=findViewById(R.id.imageView);
        textView=findViewById(R.id.textView);
        ImageData imageData=ImageData.getInstance();
        if(imageData!=null){
            imgLink=imageData.getImglink();
            webLink=imageData.getWebsiteLink();
            textView.setText(imageData.getText());

            Picasso.get().load(imgLink).into(imageView2);
        }

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = ""+webLink;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });


        news = (News) getIntent().getSerializableExtra(EXTRA_OBJECT);
        dao = AppDatabase.getDb(this).getDAO();

        iniComponent();
        initToolbar();
        if (news.source_type == SourceType.SAVED) {
            displayNewsData();
        } else {
            requestAction();
        }

        prepareBannerAds();
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                prepareIntersAds();
//            }
//        }, 1000 * AppConfig.ADS_INTERS_DETAILS_FIRST_INTERVAL);

        Tools.RTLMode(getWindow());
        ThisApp.get().saveClassLogEvent(getClass());
    }

    public void ShowInterstitial() {
        applicationHelper = (ThisApp) getApplicationContext();
        applicationHelper.ShowInterstitial();
     /*   if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdFailedToLoad(int i) {
                    super.onAdFailedToLoad(i);
                    mInterstitialAd.loadAd(new AdRequest.Builder().build());
                }
            });
            return;
        } else {
            if (mInterstitialAd != null && !mInterstitialAd.isLoading())
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
        }*/

    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorTextAction), PorterDuff.Mode.SRC_ATOP);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSmartSystemBar(this);
    }

    private RecyclerView recycler_view;

    private void iniComponent() {
        lyt_bottom_bar = findViewById(R.id.lyt_bottom_bar);
        lyt_toolbar = findViewById(R.id.lyt_toolbar);
        nested_scroll_view = findViewById(R.id.nested_scroll_view);
        image = findViewById(R.id.image);
        img_type = findViewById(R.id.img_type);
        total_view = findViewById(R.id.total_view);
        total_comment = findViewById(R.id.total_comment);
        shimmer = findViewById(R.id.shimmer);

        parent_view = findViewById(android.R.id.content);
        lyt_main_content = findViewById(R.id.lyt_main_content);
        featured = findViewById(R.id.featured);
        date = findViewById(R.id.date);
        type = findViewById(R.id.type);
        recycler_view = findViewById(R.id.recycler_view);

        featured.setVisibility(View.GONE);
        date.setText("");
        type.setText("");

        nested_scroll_view.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY >= oldScrollY) { // down
                    if (lyt_navigation_hide) return;
                    ViewAnimation.hideBottomBar(lyt_bottom_bar);
                    ViewAnimation.hideToolbar(lyt_toolbar);
                    lyt_navigation_hide = true;
                } else {
                    if (!lyt_navigation_hide) return;
                    ViewAnimation.showBottomBar(lyt_bottom_bar);
                    ViewAnimation.showToolbar(lyt_toolbar);
                    lyt_navigation_hide = false;
                }
            }
        });


        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        //recycler_view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        recycler_view.setLayoutManager(layoutManager);
        recycler_view.setHasFixedSize(true);
        //  layoutManager.scrollToPosition(0);
        //set data and list adapter
        mAdapter = new HorizontalAdapter(ActivityNewsDetails.this, recycler_view, Constant.NEWS_PER_REQUEST);
        recycler_view.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new HorizontalAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, News obj, int pos) {
                ActivityNewsDetails.navigate(ActivityNewsDetails.this, obj);
                ShowInterstitial();
            }
        });
        mAdapter.setOnLoadMoreListener(new HorizontalAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore(int current_page) {
                int item_count = mAdapter.getItemCount() - default_count;
                if (count_total > item_count) {
                    int next_page = (item_count / Constant.NEWS_PER_REQUEST) + 1;
                    requestAction1(next_page);
                } else {
                    // mAdapter.setLoaded();
                }
            }
        });
    }


    private void requestAction1(final int page_no) {
        showFailedView(false, "", R.drawable.img_failed);
        if (page_no == 0) {
            // swipeProgress(true);
        } else {
            mAdapter.setLoading();
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                requestHomeData(page_no);
            }
        }, 500);
    }

    private void requestAction() {
        if (is_running) return;
        showFailedView(false, "", R.drawable.img_failed);
        showLoading(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                requestVideoDetailsApi();
            }
        }, 1000);
    }

    private void onFailRequest() {
        showLoading(false);
        if (NetworkCheck.isConnect(this)) {
            showFailedView(true, getString(R.string.failed_text), R.drawable.img_failed);
        } else {
            showFailedView(true, getString(R.string.no_internet_text), R.drawable.img_no_internet);
        }
    }

    private void onFailRequest(int page_no) {
        showLoading(false);
        if (NetworkCheck.isConnect(this)) {
            showFailedView(true, getString(R.string.failed_text), R.drawable.img_failed);
        } else {
            showFailedView(true, getString(R.string.no_internet_text), R.drawable.img_no_internet);
        }
    }

    private void requestVideoDetailsApi() {
        int viewed = ThisApp.get().isEligibleViewed(news.id) ? 1 : 0;
        API api = RestAdapter.createAPI();
        callbackCall = api.getNewsDetails(news.id, viewed);
        callbackCall.enqueue(new Callback<ResponseNewsDetails>() {
            @Override
            public void onResponse(Call<ResponseNewsDetails> call, Response<ResponseNewsDetails> response) {
                ResponseNewsDetails resp = response.body();
                if (resp != null && resp.status.equals("success")) {
                    news = resp.news;
                    topics = resp.topics;
                    gallery = resp.gallery;

                    Log.e("news", resp.news.content.toString());


                    displayNewsData();
                    displayTopicData();
                    showLoading(false);
                } else {
                    onFailRequest();
                }
            }

            @Override
            public void onFailure(Call<ResponseNewsDetails> call, Throwable t) {
                Log.e("onFailure", t.getMessage());
                if (!call.isCanceled()) onFailRequest();
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void displayNewsData() {
        ((TextView) findViewById(R.id.title)).setText(news.title);
        web_view = findViewById(R.id.web_view);
        String html_data = "<style>img{max-width:100%;height:auto;} iframe{width:100%;}</style> ";
        if (new SharedPref(this).getSelectedTheme() == 1) {
            html_data += "<style>body{color: #f2f2f2;}</style> ";
        }
        html_data += news.content;

        web_view.getSettings().setJavaScriptEnabled(true);
        web_view.getSettings();
        web_view.getSettings().setBuiltInZoomControls(true);
        web_view.setBackgroundColor(Color.TRANSPARENT);
        web_view.setWebChromeClient(new WebChromeClient());


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            web_view.loadDataWithBaseURL(null, html_data, "text/html; charset=UTF-8", "utf-8", null);
        } else {
            web_view.loadData(html_data, "text/html; charset=UTF-8", null);
        }
        // disable scroll on touch
        web_view.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return (event.getAction() == MotionEvent.ACTION_MOVE);
            }
        });
        // override url direct
        web_view.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                if (url != null && (url.startsWith("http://") || url.startsWith("https://"))) {
                    view.getContext().startActivity(
                            new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                    return true;
                } else {
                    return false;
                }
            }
        });

        date.setText(TimeAgo.get(this, news.date));
        if (news.featured == 1) {
            featured.setVisibility(View.VISIBLE);
        }

        lyt_main_content.setVisibility(View.VISIBLE);

        if (news.type.equalsIgnoreCase("GALLERY")) {
            img_type.setImageResource(R.drawable.ic_type_gallery_large);
            type.setText(R.string.news_type_gallery);
            ArrayList<String> images = new ArrayList<>();
            images.add(Constant.getURLimgNews(news.image));
            if (gallery != null && gallery.size() > 0) {
                for (String i : gallery) images.add(Constant.getURLimgNews(i));
            }
            header_intent = ActivityGallery.navigateBase(this, images);

        } else if (news.type.equalsIgnoreCase("VIDEO")) {
            img_type.setImageResource(R.drawable.ic_type_video_large);
            type.setText(R.string.news_type_video);
            header_intent = ActivityWebView.navigateBase(this, news.url, false);
        } else {
            img_type.setVisibility(View.GONE);
            type.setText(R.string.news_type_article);
        }

        Tools.displayImage(this, image, Constant.getURLimgNews(news.image));

        total_view.setText(Tools.bigNumberFormat(news.total_view));
        total_comment.setText(Tools.bigNumberFormat(news.total_comment));

        (findViewById(R.id.lyt_image)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (header_intent == null) return;
                startActivity(header_intent);
            }
        });


        (findViewById(R.id.lyt_comment)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityComment.navigate(ActivityNewsDetails.this, news);
            }
        });


        (findViewById(R.id.btn_share)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.methodShare(ActivityNewsDetails.this, news);
            }
        });
        requestHomeData(1);
    }

    private void requestHomeData(final int page_no) {
        API api = RestAdapter.createAPI();
        Call<ResponseNews> callbackHome;

        SearchBody body = new SearchBody(page_no, Constant.NEWS_PER_REQUEST, 0);
        callbackHome = api.getListNewsAdv(body);
        callbackHome.enqueue(new Callback<ResponseNews>() {
            @Override
            public void onResponse(Call<ResponseNews> call, Response<ResponseNews> response) {
                ResponseNews resp = response.body();
                if (resp != null && resp.status.equals("success")) {
                    count_total = resp.count_total;
                    displayNewsData(resp.news);
                    // Log.e("data",resp.news);
                } else {
                    onFailRequest(page_no);
                }
            }

            @Override
            public void onFailure(Call<ResponseNews> call, Throwable t) {
                if (!call.isCanceled()) onFailRequest(page_no);
            }

        });


    }

    HorizontalAdapter mAdapter;
    private int count_total = 0;
    private int failed_page = 0;
    private int default_count = 0;

    private void displayNewsData(List<News> items) {
        mAdapter.insertData(items);
    }

    private void displayTopicData() {
        FlexboxLayout tags_flex_box = findViewById(R.id.topic_flex_box);
        tags_flex_box.removeAllViews();
        for (String t : topics) {
            TextView text = new TextView(this);
            text.setText(t);
            text.setTextColor(getResources().getColor(R.color.grey_40));
            text.setTextSize(10);
            text.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_rect_grey));
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            int margin = Tools.dpToPx(this, 2);
            int padding = Tools.dpToPx(this, 1);
            layoutParams.setMargins(margin, margin, margin, margin);
            text.setLayoutParams(layoutParams);
            text.setPadding(padding * 4, padding, padding * 4, padding * 2);
            tags_flex_box.addView(text);
        }
    }

    private void showFailedView(boolean show, String message, @DrawableRes int icon) {
        View lyt_failed = findViewById(R.id.lyt_failed);

        ((ImageView) findViewById(R.id.failed_icon)).setImageResource(icon);
        ((TextView) findViewById(R.id.failed_message)).setText(message);
        if (show) {
            lyt_main_content.setVisibility(View.INVISIBLE);
            lyt_failed.setVisibility(View.VISIBLE);
        } else {
            lyt_main_content.setVisibility(View.VISIBLE);
            lyt_failed.setVisibility(View.GONE);
        }
        (findViewById(R.id.failed_retry)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestAction();
            }
        });
    }

    private void showLoading(final boolean show) {
        is_running = show;
        if (menu_refresh != null) menu_refresh.setVisible(!show);
        if (!show) {
            shimmer.setVisibility(View.GONE);
            shimmer.stopShimmer();
            lyt_main_content.setVisibility(View.VISIBLE);
        } else {
            shimmer.setVisibility(View.VISIBLE);
            shimmer.startShimmer();
            lyt_main_content.setVisibility(View.INVISIBLE);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_news_details, menu);
        MenuItem menu_saved = menu.findItem(R.id.action_saved);
        menu_refresh = menu.findItem(R.id.action_refresh);

        NewsEntity ns = dao.getNews(news.id);
        is_saved = ns != null;
        if (is_saved) {
            menu_saved.setIcon(R.drawable.ic_bookmark);
            if (news.source_type == SourceType.SAVED) {
                topics = ns.getTopicsList();
                gallery = ns.getGalleryList();
                displayTopicData();
            }
        } else {
            menu_saved.setIcon(R.drawable.ic_bookmark_border);
        }
        Tools.changeMenuIconColor(menu, getResources().getColor(R.color.colorTextAction));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int item_id = item.getItemId();
        if (item_id == android.R.id.home) {
            finish();
        } else if (item_id == R.id.action_refresh) {
            requestAction();
        } else if (item_id == R.id.action_saved) {
            if (news.isDraft()) return true;
            String str;
            if (is_saved) {
                dao.deleteNews(news.id);
                str = getString(R.string.remove_from_saved);
            } else {
                NewsEntity entity = NewsEntity.entity(news);
                entity.setTopicsList(new ArrayList<>(topics));
                entity = setImageGallery(entity);
                dao.insertNews(entity);
                str = getString(R.string.added_to_saved);
            }
            invalidateOptionsMenu();
            Snackbar.make(parent_view, str, Snackbar.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle() + " clicked", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }


    private NewsEntity setImageGallery(NewsEntity entity) {
        if (news.type.equalsIgnoreCase("GALLERY") && gallery != null && gallery.size() > 0) {
            entity.setGalleryList(new ArrayList<>(gallery));
        }
        return entity;
    }

    @Override
    public void onDestroy() {
        if (callbackCall != null && !callbackCall.isCanceled()) callbackCall.cancel();
        shimmer.stopShimmer();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mAdView != null) mAdView.resume();
        is_activity_active = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mAdView != null) mAdView.pause();
        is_activity_active = false;
    }

    private void prepareBannerAds() {
        if (!AppConfig.ADS_DETAILS_ALL || !NetworkCheck.isConnect(getApplicationContext())) return;

        // banner

        mAdView = (AdView) findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                // Check the LogCat to get your test device ID
                .build();

        mAdView.loadAd(adRequest);

    }

    @Override
    public void onBackPressed() {
        finish();
        /*mInterstitialAd = new InterstitialAd(getApplicationContext());
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        AdRequest adRequest = new AdRequest.Builder().addNetworkExtrasBundle(AdMobAdapter.class, GDPR.getBundleAd(this)).build();
        mInterstitialAd.loadAd(adRequest);
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                mInterstitialAd.show();
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();

            }
        });*/
    }

    private void prepareIntersAds() {
        if (!AppConfig.ADS_DETAILS_ALL || !NetworkCheck.isConnect(getApplicationContext())) return;

        // interstitial
        mInterstitialAd = new InterstitialAd(getApplicationContext());
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        AdRequest adRequest = new AdRequest.Builder().addNetworkExtrasBundle(AdMobAdapter.class, GDPR.getBundleAd(this)).build();
        if (AppConfig.ADS_DETAILS_INTERS)
            mInterstitialAd.loadAd(adRequest);
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                if (!is_activity_active) return;
                mInterstitialAd.show();
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();

            }
        });
    }

}
