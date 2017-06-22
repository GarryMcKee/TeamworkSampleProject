package garrymckee.mellobit.com.teamworksample.profilesheet;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import garrymckee.mellobit.com.teamworksample.R;
import garrymckee.mellobit.com.teamworksample.model.Person;

import static android.view.View.GONE;

/**
 * Created by Garry on 22/06/2017.
 */

public class ProfileSheetFragment extends DialogFragment implements ProfileSheetContract.ProfileSheetView {

    public static final String ARG_NAME = "argName";
    public static final String ARG_EMAIL = "argEmail";
    public static final String ARG_NUMBER = "argNumber";
    public static final String ARG_AVATAR_URL = "argAvatarUrl";

    private ProfileSheetContract.ProfileSheetPresenter mPresenter;

    @BindView(R.id.profile_avatar_view)
    SimpleDraweeView profileAvatarView;

    @BindView(R.id.profile_person_name_text_view)
    TextView personNameTextview;

    @BindView(R.id.call_button_icon)
    ImageButton callButton;

    @BindView(R.id.message_button_icon)
    ImageButton messageButton;

    @BindView(R.id.email_person_icon)
    ImageButton emailButton;

    public static ProfileSheetFragment newInstance(Person person) {

        Bundle args = new Bundle();
        args.putString(ARG_NAME, person.getFullName());
        args.putString(ARG_EMAIL, person.geteMail());
        args.putString(ARG_NUMBER, person.getMobileNumber());
        args.putString(ARG_AVATAR_URL, person.getAvatarUrl());

        ProfileSheetFragment fragment = new ProfileSheetFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_profile_sheet, null);
        ButterKnife.bind(this, v);
        setupUi();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(getArguments().getString(ARG_NAME))
                .setView(v)
                .setNegativeButton(R.string.close_profile_sheet, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ProfileSheetFragment.this.dismiss();
                    }
                });
        return builder.create();

    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter = new ProfileSheetPresenter(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.detachView();
        mPresenter = null;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.d("CHECKUI", "SETTING UI");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void setupUi() {
        profileAvatarView.setImageURI(getArguments().getString(ARG_AVATAR_URL));
        personNameTextview.setText(getArguments().getString(ARG_NAME));

        final String number = getArguments().getString(ARG_NUMBER)  != null ? getArguments().getString(ARG_NUMBER) : "";
        final String email = getArguments().getString(ARG_EMAIL) != null ? getArguments().getString(ARG_EMAIL) : "";

        if(!number.trim().isEmpty()) {
            callButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPresenter.callPerson(number, getActivity());
                }
            });

            messageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPresenter.messagePerson(number, getActivity());
                }
            });
        } else {
            Log.d("CHECKBADNUMBER", "NUMBER IS EMPTY");
            callButton.setEnabled(false);
            callButton.setVisibility(GONE);
            messageButton.setEnabled(false);
            messageButton.setVisibility(GONE);
        }

        if(!email.trim().isEmpty()) {
            emailButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPresenter.sendEmail(new String[]{email}, getActivity());
                }
            });
        } else {
            emailButton.setEnabled(false);
            emailButton.setVisibility(GONE);
        }

    }

    @Override
    public void showGeneralError() {
        Toast.makeText(getActivity(), R.string.general_error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showFeatureNotAvailableError() {
        Toast.makeText(getActivity(), R.string.feature_not_available_error, Toast.LENGTH_LONG).show();
    }
}
