package com.jlt.pickpalette;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnItemClick;

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

// begin activity MainActivity
public class MainActivity extends Activity {

    /** CONSTANTS */

    /** VARIABLES */

    /** Floating Action Buttons */

    // Bind a field to the view for the specified ID
    @InjectView( R.id.am_fab ) FloatingActionButton floatingActionButton; // the fab

    /** Grid Views */

    @InjectView( R.id.am_gv ) GridView gridView; // the grid view to show the palette

    /* Image Views */

    @InjectView( R.id.am_iv ) ImageView imageView; // the image view to show the picture

    /* Primitives */

    private int numberOfPixels; // the pixels of the image

    /** Swatch Adapters */

    private SwatchAdapter swatchAdapter; // the swatch adapters

    /** Toolbars */

    @InjectView( R.id.am_tb ) Toolbar toolbar; // the main activity toolbar

    /** METHODS */

    /** Getters and Setters */

    /** Overrides */

    @Override
    // begin onCreate
    public void onCreate( Bundle savedInstanceState ) {

        super.onCreate( savedInstanceState );

        setContentView( R.layout.activity_main );

        // 0. inject this into the palette
        // 1. set the toolbar up

        // 0. inject this into the palette

        // inject annotated fields and methods in the specified Activity
        ButterKnife.inject( this );

        // 1. set the toolbar up

        toolbar.setTitle( R.string.app_name );

    } // end onCreate

    @Override
    // begin onCreateOptionsMenu
    public boolean onCreateOptionsMenu( Menu menu ) {

        // 0. inflate menu using the menu layout

        // 0. inflate menu using the menu layout

        getMenuInflater().inflate( R.menu.menu_main, menu );

        return true;

    } // end onCreateOptionsMenu

    // begin click
    @OnClick( R.id.am_fab ) // Bind a method to an OnClickListener on the view for each ID specified.
    public void click( View view ) {

        // 0. inform user of clicked fab
        // 1. start the picker fragment

        // 0. inform user of clicked fab

        Snackbar.make( findViewById( R.id.am_rl_fragment ), "Clicked FAB.", Snackbar.LENGTH_LONG ).show();

        // 1. start the picker fragment

        FragmentManager fragmentManager = getFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ColorPickerFragment colorPickerFragment = new ColorPickerFragment();

        colorPickerFragment.show( getFragmentManager(), "dialog" );

        fragmentTransaction.commit();

    } // end click

    // begin method onItemClick
    @OnItemClick( R.id.am_gv ) // Bind a method to an OnItemClickListener on the view for each ID specified
    public void onItemClick( int position ) {

        // 0. get the swatch at the passed position
        // 1. build a string that will show the
        // 1a. hex value of the color of the title
        // 1b. the population of the said color in the swatch
        // 2. show the string in a snackbar

        // 0. get the swatch at the passed position

        // in the map,
        // the key is a string, and
        // the value is a swatch
        Palette.Swatch swatch = ( ( Map.Entry< String, Palette.Swatch > ) gridView.getItemAtPosition( position ) ).getValue();

        // 1. build a string that will show the

        StringBuilder displayString =

        new StringBuilder()

        // 1a. hex value of the color of the title

        .append( "Title Text Color " )

        // swatch.getBodyTextColor() - Returns an appropriate color to use for any 'body' text which is displayed over this swatch's color
        .append( "#" + Integer.toHexString( swatch.getBodyTextColor() ).toString() )

        .append( "\n"  )

        // 1b. the population of the said color in the swatch

        .append( "Population " )

        // swatch.getPopulation - the number of pixels represented by this swatch
        .append( swatch.getPopulation() );

        // 2. show the string in a snackbar

        Snackbar.make( gridView, displayString.toString(), Snackbar.LENGTH_LONG ).show();

    } // end method onItemClick

    /** Other Methods */

    // begin method createPalette
    // creates a palette from the image-related object passed in
    public void createPalette ( Object imageRelatedObject ) {

        Bitmap bitmap;

        // 0. if the object is an image URI
        // 0a. load this uri into the image view
        // 0b. open a stream to this image
        // 0c. initialize the bitmap from the stream
        // 1. else the object is a bitmap
        // 1a. set it to be in the image view
        // 2. generate a palette from the bitmap
        // 2a. get a map from the palette
        // 2b. use the map to initialize a swatch adapter.
        // 2c. use the swatch adapter to populate the grid view

        // begin try to try create the palette
        try {

            // 0. if the object is an image URI

            // begin if for if the object is a URI
            if ( imageRelatedObject instanceof Uri ) {

                // 0a. load this uri into the image view

                Uri imageUri = ( Uri ) imageRelatedObject;

                // 0b. open a stream to this image

                // Picasso.with - global default Picasso instance auto initialized with defaults suitable to most implementations.
                // load - Start an image request using the specified URI
                // into - Asynchronously fulfills the request (in this case the load request) into the specified ImageView.
                Picasso.with( this ).load( imageUri ).into( imageView );

                // getContentResolver -  gets a ContentResolver instance for this app's package.
                InputStream imageInputStream = getContentResolver().openInputStream( imageUri );

                // 0c. initialize the bitmap from the stream

                // decodeStream - Decode an input stream into a bitmap
                bitmap = BitmapFactory.decodeStream( imageInputStream );

            } // end if for if the object is a URI

            // 1. else the object is a bitmap

            // begin else for when the object is not a URI
            else {

                // 1a. set it to be in the image view

                bitmap = ( Bitmap ) imageRelatedObject;

                imageView.setImageBitmap( bitmap );

            } // end else for when the object is not a URI

            // 2. generate a palette from the bitmap

            // this generation is done asynchronously

            // begin method Palette.from( bitmap ).generate
            // Palette.from - Start generating a Palette with the returned Palette.Builder instance
            // generate - Generate the Palette asynchronously using the palette async listener's onGenerated
            Palette.from( bitmap ).generate(

                    // begin new Palette.PaletteAsyncListener
                    new Palette.PaletteAsyncListener() {

                        @Override
                        // begin onGenerated
                        public void onGenerated( Palette palette ) {

                            // 2a. get a map from the palette
                            // 2b. use the map to initialize a swatch adapter.
                            // 2c. use the swatch adapter to populate the grid view

                            // 2a. get a map from the palette

                            HashMap map = processPalette( palette );

                            // 2b. use the map to initialize a swatch adapter.

                            swatchAdapter = new SwatchAdapter( getApplicationContext(), map.entrySet().toArray(), MainActivity.this );

                            // 2c. use the swatch adapter to populate the grid view

                            gridView.setAdapter( swatchAdapter );

                        } // end onGenerated

                    } // end new Palette.PaletteAsyncListener

            ); // end method Palette.from( bitmap ).generate

        } // end try to try create the palette

        // catch issues

        catch ( Exception e ) { /* Log.e( "MainActivity", "error in creating palette" ); */ }

    } // end method createPalette

    // begin method processPalette
    private HashMap< String, Palette.Swatch > processPalette ( Palette aPalette ) {

        // 0. initialize a new hash map
        // 1. if the palette has a vibrant swatch
        // 1a. put an entry in the map for vibrant
        // 2. if the palette has a dark vibrant swatch
        // 2a. put an entry in the map for dark vibrant
        // 3. if the palette has a light vibrant swatch
        // 3a. put an entry in the map for light vibrant
        // 4. if the palette has a muted swatch
        // 4a. put an entry in the map for muted
        // 5. if the palette has a dark vibrant muted
        // 5a. put an entry in the map for dark muted
        // 6. if the palette has a light muted swatch
        // 6a. put an entry in the map for light muted
        // 7. return the map

        // 0. initialize a new hash map

        HashMap< String, Palette.Swatch > returnHashMap = new HashMap<>();

        // 1. if the palette has a vibrant swatch

        // 1a. put an entry in the map for vibrant

        if ( aPalette.getVibrantSwatch() != null ) { returnHashMap.put( "Vibrant", aPalette.getVibrantSwatch() ); }

        // 2. if the palette has a dark vibrant swatch

        // 2a. put an entry in the map for dark vibrant

        if ( aPalette.getDarkVibrantSwatch() != null ) { returnHashMap.put( "DarkVibrant", aPalette.getDarkVibrantSwatch() ); }

        // 3. if the palette has a light vibrant swatch

        // 3a. put an entry in the map for light vibrant

        if ( aPalette.getLightVibrantSwatch() != null ) { returnHashMap.put( "LightVibrant", aPalette.getLightVibrantSwatch() ); }

        // 4. if the palette has a muted swatch

        // 4a. put an entry in the map for muted

        if ( aPalette.getMutedSwatch() != null ) { returnHashMap.put( "Muted", aPalette.getMutedSwatch() ); }

        // 5. if the palette has a dark vibrant muted

        // 5a. put an entry in the map for dark muted

        if ( aPalette.getDarkMutedSwatch() != null ) { returnHashMap.put( "DarkMuted", aPalette.getDarkMutedSwatch() ); }

        // 6. if the palette has a light muted swatch

        // 6a. put an entry in the map for light muted

        if ( aPalette.getLightMutedSwatch() != null ) { returnHashMap.put( "LightMuted", aPalette.getLightMutedSwatch() ); }

        // 7. return the map

        return returnHashMap;

    } // end method processPalette

} // end activity MainActivity
