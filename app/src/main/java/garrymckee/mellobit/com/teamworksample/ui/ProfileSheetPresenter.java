package garrymckee.mellobit.com.teamworksample.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by Garry on 22/06/2017.
 */

public class ProfileSheetPresenter implements ProfileSheetContract.ProfileSheetPresenter {

    private ProfileSheetContract.ProfileSheetView mView;

    public ProfileSheetPresenter(ProfileSheetContract.ProfileSheetView view) {
        mView = view;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void callPerson(String number, Context context) {

    }

    @Override
    public void messagePerson(String number, Context context) {

    }

    @Override
    public void sendEmail(String[] emails, Context context) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, emails);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }

    }
}
