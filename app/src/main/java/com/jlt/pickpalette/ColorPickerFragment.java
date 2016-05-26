package com.jlt.pickpalette;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 *  Pick Palette
 *
 *  Tutorial on how to use the Android Palette
 *
 *  Copyright (C) 2016 Kairu Joshua Wambugu
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 *
 */

// begin fragment ColorPickerFragment
public class ColorPickerFragment extends DialogFragment {

    /** CONSTANTS */

    /* Integers */

    private static final int

    INTENT_ID_PICK_PHOTO = 100,

    INTENT_ID_TAKE_PHOTO = 101;

    /* Strings */

    private static final String TAG = "PICKER_FRAGMENT"; // tag for debugging

    /** VARIABLES */

    /* Buttons */

    @InjectView( R.id.fp_b_pick_image ) Button pickImageButton; // button to pick an image
    @InjectView( R.id.fp_b_take_image ) Button takeImageButton; // button to take an image via cam

    /** METHODS */

    /** Getters and Setters */

    /**
     * Overrides
     */

    @Override
    // begin onCreateView
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {

        // 0. use the picker fragment layout
        // 1. inject annotated fields and methods into this fragment using the inflated view as the view root
        // 2. remove the title off the top of the screen
        // 3. return the inflated view

        // 0. use the picker fragment layout

        View rootView = inflater.inflate( R.layout.fragment_picker, container, false );

        // 1. inject annotated fields and methods into this fragment using the inflated view as the view root

        ButterKnife.inject( this, rootView );

        // 2. remove the title off the top of the screen

        getDialog().getWindow().requestFeature( Window.FEATURE_NO_TITLE );

        // 3. return the inflated view

        return rootView;

    } // end onCreateView

    /** Other Methods */

    @OnClick( R.id.fp_b_pick_image )
    // begin method onClickPickImageButton
    void onClickPickImageButton( View view ) {

        // 0. start an activity to let the user choose an image
        // 1. report the result back to the main activity

        // 0. start an activity to let the user choose an image

        // Intent.ACTION_PICK
        // Pick an item from the data, returning what was selected.
        // Output: The URI of the item that was picked.
        Intent photoPickerIntent = new Intent( Intent.ACTION_PICK );

        // intent to pick any kind of image
        photoPickerIntent.setType( "image/*" );

        // 1. report the result back to the main activity
        startActivityForResult( photoPickerIntent, INTENT_ID_PICK_PHOTO );

    } // end method onClickPickImageButton

    @OnClick( R.id.fp_b_take_image )
    // begin method onClickTakeImageButton
    void onClickTakeImageButton( View view ) {

        // 0. start an activity to let the user take an image from the cam
        // 1. if there exists a camera app in the device
        // 1a. report the taken image back to the main activity

        // 0. start an activity to let the user take an image from the cam

        // Intent.ACTION_PICK
        // Intent action that sent to have the cam capture an image and return it.
        // Output: The URI of the item that was picked.
        Intent cameraIntent = new Intent( MediaStore.ACTION_IMAGE_CAPTURE );

        // 1. if there exists a camera app in the device
        // 1a. report the taken image back to the main activity

        // cameraIntent.resolveActivity
        // - gets an activity that should be used to handle the cam intent
        if ( cameraIntent.resolveActivity( getActivity().getPackageManager() ) != null )
        { startActivityForResult( cameraIntent, INTENT_ID_TAKE_PHOTO ); }

    } // end method onClickTakeImageButton

    @Override
    // begin onActivityResult
    public void onActivityResult( int requestCode, int resultCode, Intent data ) {

        // 0. super things
        // 1. if we were picking a photo
        // 1a. if the picking went well
        // 1a1. log this
        // 1a2. get the uri of the image from the data
        // 1a3. create a palette using that image
        // 1a4. dismiss this dialog
        // 2. if we were taking a photo
        // 2a. if the photo session went well
        // 2a1. log this
        // 2a2. get the extras from the data
        // 2a3. get the image from the extras
        // 2a4. create a palette using the gotten image
        // 2a5. dismiss this dialog

        // 0. super things

        super.onActivityResult( requestCode, resultCode, data );

        // begin switch to know what to do
        switch ( requestCode ) {

            // 1. if we were picking a photo

            case INTENT_ID_PICK_PHOTO:

                // 1a. if the picking went well

                // begin if for if the result code is code OK
                if ( resultCode == Activity.RESULT_OK ) {

                    // 1a1. log this

                    // Log.d( TAG, "Picked a photo"  );

                    // 1a2. get the uri of the image from the data

                    Uri selectedImageUri = data.getData();

                    // 1a3. create a palette using that image

                    ( ( MainActivity ) getActivity() ).createPalette( selectedImageUri );

                    // 1a4. dismiss this dialog

                    getDialog().dismiss();

                } // end if for if the result code is code OK

                break;

            // 2. if we were taking a photo

            case INTENT_ID_TAKE_PHOTO:

                // 2a. if the photo session went well

                // begin if for if the result code is code OK
                if ( resultCode == Activity.RESULT_OK ) {

                    // 2a1. log this

                    // Log.d( TAG, "Kachick. Took a photo." );

                    // 2a2. get the extras from the data

                    Bundle extrasBundle = data.getExtras();

                    // 2a3. get the image from the extras

                    Bitmap imageBitmap = ( Bitmap ) extrasBundle.get( "data" );

                    // 2a4. create a palette using the gotten image

                    ( ( MainActivity ) getActivity() ).createPalette( imageBitmap );

                    // 2a5. dismiss this dialog

                    getDialog().dismiss();

                } // end if for if the result code is code OK

                break;

        } // end switch to know what to do

    } // end onActivityResult


} // end fragment ColorPickerFragment