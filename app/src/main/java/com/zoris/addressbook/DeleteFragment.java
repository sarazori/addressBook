package com.zoris.addressbook;

import android.app.AlertDialog;
import android.app.Dialog;
import android.net.Uri;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;


// class DeleteFragment to confirm deletion and then delete a contact
public class DeleteFragment extends DialogFragment {

    private Uri contactUri; // Uri of selected contact
    // create an AlertDialog and return it
    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        Bundle arguments = getArguments();
        contactUri = arguments.getParcelable("CONTACT_URI");

        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.confirm_title);
        builder.setMessage(R.string.confirm_message);

        // provide an OK button that simply dismisses the dialog
        builder.setPositiveButton(R.string.button_delete,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(
                            DialogInterface dialog, int button) {

                        // use Activity's ContentResolver to invoke
                        // delete on the AddressBookContentProvider
                        getActivity().getContentResolver().delete(contactUri, null, null);
                        DetailFragment.listener.onContactDeleted(); // notify listener
                    }
                }
        );

        builder.setNegativeButton(R.string.button_cancel, null);
        return builder.create(); // return the AlertDialog
    }

 }
