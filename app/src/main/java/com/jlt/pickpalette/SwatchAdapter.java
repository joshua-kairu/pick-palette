package com.jlt.pickpalette;

import android.content.Context;
import android.support.v7.graphics.Palette;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Map;

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

// begin class SwatchAdapter
public class SwatchAdapter extends ArrayAdapter {

    /** CONSTANTS */

    /** VARIABLES */

    /* Main Activities */

    private MainActivity mainActivity; // the main activity

    /** Palettes */

    private Palette palette; // a palette

    /** CONSTRUCTOR */
    // begin constructor
    public SwatchAdapter( Context context, Object[] objects, MainActivity aMainActivity ) {

        // 0. super things
        // 1. initialize the main activity

        // 0. super things

        super( context, 0, objects );

        // 1. initialize the main activity

        mainActivity = aMainActivity;

    } // end constructor

    /** METHODS */

    /** Getters and Setters */

    /** Overrides */

    @Override
    // begin getView
    // get a View that displays the data at the specified position in the data set
    // position - The position of the item within the adapter's data set of the item whose view we want.
    // convertView - The old view to reuse, if possible. Note: You should check that this view is non-null and of an appropriate type before using. If it is not possible to convert this view to display the correct data, this method can create a new view.
    //               Heterogeneous lists can specify their number of view types, so that this View is always of the right type (see getViewTypeCount() and getItemViewType(int)).
    // parent - The parent that this view will eventually be attached to
    public View getView( int position, View convertView, ViewGroup parent ) {

        // 0. get the current item in the palette at the position specified using casting
        // 1. reuse the swatch view layout by inflating it
        // 2. get the views
        // 2a. get the color view
        // 2b. get the color title view
        // 3. set the color view's background to be the current palette item's color
        // 4. display the current palette's color number on the color view
        // 5. return the inflated layout

        // 0. get the current item in the palette at the position specified using casting

        // Palette.Swatch is a color swatch generated from an image's palette
        // this grid view holds a map that maps a title String to an color Swatch
        Map.Entry< String, Palette.Swatch > currentPaletteEntry = ( Map.Entry< String, Palette.Swatch > )getItem( position );

        // 1. reuse the swatch view layout by inflating it

        if ( convertView == null ) { convertView = LayoutInflater.from( getContext() ).inflate( R.layout.swatch_view, parent, false ); }

        // 2. get the views

        // 2a. get the color view

        View colorView = convertView.findViewById( R.id.sv_v_color_swatch );

        // 2b. get the color title view

        TextView colorTitleTextView = ( TextView ) convertView.findViewById( R.id.sv_tv_color_title );

        // 3. set the color view's background to be the current palette item's color

        int colorValue = currentPaletteEntry.getValue().getRgb();

        colorView.setBackgroundColor( colorValue );

        // colorView.setBackground( colorValue );  needs API 16

        // 4. display the current palette's color number on the color view

        // currentPaletteEntry.getKey() - the key is the title of the palette
        // currentPaletteEntry.getValue() - the value is the value of the color in the swatch
        colorTitleTextView.setText( mainActivity.getString( R.string.swatch_name_and_value, currentPaletteEntry.getKey(), Integer.toHexString( currentPaletteEntry.getValue().getRgb() ).toUpperCase() ) );

        // 5. return the inflated layout

        return convertView;

    } // end getView


    /** Other Methods */

} // end class SwatchAdapter