package garrymckee.mellobit.com.teamworksample.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Garry on 18/06/2017.
 */

public class Person {

    @SerializedName("first-name")
    private
    String firstName;

    @SerializedName("last-name")
    private
    String lastName;

    @SerializedName("user-name")
    private
    String userName;

    @SerializedName("email-address")
    private
    String eMail;

    @SerializedName("phone-number-mobile")
    private
    String mobileNumber;

    @SerializedName("avatar-url")
    String avatarUrl;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String geteMail() {
        return eMail;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
