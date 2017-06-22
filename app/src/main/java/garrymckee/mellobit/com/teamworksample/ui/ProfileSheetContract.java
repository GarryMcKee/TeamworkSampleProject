package garrymckee.mellobit.com.teamworksample.ui;

import android.content.Context;

/**
 * Created by Garry on 22/06/2017.
 */

public interface ProfileSheetContract {

    interface ProfileSheetPresenter extends BasePresenter{
        void callPerson(String number, Context context);
        void messagePerson(String number, Context context);
        void sendEmail(String[] emails, Context context);
    }

    interface ProfileSheetView extends BaseView{

    }
}
