package com.androidwind.androidquick.demo.features.architecture.mvc;

import com.androidwind.androidquick.demo.constant.Constants;
import com.androidwind.androidquick.demo.features.CodeAndRunActivity;
import com.androidwind.androidquick.demo.features.module.network.retrofit.GankApis;
import com.androidwind.androidquick.demo.features.module.network.retrofit.GankRes;
import com.androidwind.androidquick.demo.features.module.network.retrofit.RetrofitManager;
import com.androidwind.androidquick.module.exception.ApiException;
import com.androidwind.androidquick.module.rxjava.BaseObserver;
import com.androidwind.androidquick.util.LogUtil;
import com.androidwind.annotation.annotation.BindTag;
import com.androidwind.annotation.annotation.TagInfo;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@BindTag(type = TagInfo.Type.ACTIVITY, tags = {"MVC"}, description = "Activity + MVC实例")
public class MVCActivity extends CodeAndRunActivity {

    @Override
    public String getMarkDownUrl() {
        return "MVCActivity";
    }

    @Override
    public String[] getItems() {
        return new String[]{"MVC", "点击一下"};
    }

    @Override
    public void clickItem(int position) {
        switch (position) {
            case 0:
                showOutput("\"I don't do anything, but this is a MVC show anyway. Click me!");
                break;
            case 1:
                RetrofitManager.INSTANCE.getRetrofit(Constants.GANK_API_URL).create(GankApis.class)
                        .getHistoryDate()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseObserver<GankRes<List<String>>>() {

                            @Override
                            public void onError(ApiException exception) {
                                LogUtil.e(TAG, "error:" + exception.getMessage());
                            }

                            @Override
                            public void onSuccess(GankRes<List<String>> listGankRes) {
                                LogUtil.i(TAG, listGankRes.getResults().toString());
                                if (!MVCActivity.this.isFinishing() && !MVCActivity.this.isDestroyed()) {
                                    showOutput("yes, I get it from net!");
                                }
                            }
                        });
                break;
        }
    }
}
