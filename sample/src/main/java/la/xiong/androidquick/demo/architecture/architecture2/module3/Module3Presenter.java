package la.xiong.androidquick.demo.architecture.architecture2.module3;

import la.xiong.androidquick.demo.architecture.architecture2.mvp.BasePresenter;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class Module3Presenter extends BasePresenter<Module3> implements Module3Contract.Presenter {
    @Override
    public void tryToPrintSomething() {
        mView.toastSomething();
    }
}
