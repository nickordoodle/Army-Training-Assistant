package com.example.machinegunrange;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<MachineGunner> expandableListMachineGunner;

    public CustomExpandableListAdapter(Context context,
                                       ArrayList<MachineGunner> expandableListMachineGunner) {
        this.context = context;
        this.expandableListMachineGunner = expandableListMachineGunner;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        MachineGunner current = this.expandableListMachineGunner.get(listPosition);

        switch(expandedListPosition)
        {
            // case statements
            // values must be of same type of expression
            case 0 :
                return current.getRank();

            case 1 :
                return current.getLastName();

            case 2 :
                return current.getFirstName();

            case 3 :
                return current.getBattalion();

            case 4 :
                return current.getCompany();

            case 5 :
                return current.getWeaponSystem();

            case 6 :
                return current.getScore();

            default :
                return null;
        }
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        MachineGunner currentGunner = this.expandableListMachineGunner.get(listPosition);
        String expandedListText;

        switch(expandedListPosition)
        {
            // case statements
            // values must be of same type of expression
            case 0 :
                String rank = "Rank: ";
                expandedListText = rank + currentGunner.getRank();
                break;
            case 1 :
                String lastName = "Last Name: ";
                expandedListText = lastName + currentGunner.getLastName();
                break;
            case 2 :
                String firstName = "First Name: ";
                expandedListText = firstName + currentGunner.getFirstName();
                break;
            case 3 :
                String battalion = "Battalion: ";
                expandedListText = battalion + currentGunner.getBattalion();
                break;
            case 4 :
                String company = "Company: ";
                expandedListText = company + currentGunner.getCompany();
                break;
            case 5 :
                String wpn = "Weapon System: ";
                expandedListText = wpn + currentGunner.getWeaponSystem();
                break;
            case 6 :
                String score = "Score: ";
                String scoretoText = Integer.toString(currentGunner.getScore());
                expandedListText = score + scoretoText;
                break;
            default:
                expandedListText = null;
                break;
        }

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item, null);
        }
        TextView expandedListTextView = (TextView) convertView
                .findViewById(R.id.expanded_list_item_textview);
        expandedListTextView.setText(expandedListText);
        Log.d("CHILD TAGS: ", expandedListText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return 7;
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.expandableListMachineGunner.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.expandableListMachineGunner.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String rank = this.expandableListMachineGunner
                .get(listPosition)
                .getRank();
        String lastName = this.expandableListMachineGunner
                .get(listPosition)
                .getLastName();
        int score = this.expandableListMachineGunner
                .get(listPosition)
                .getScore();
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, null);
        }
        //Set rank values
        TextView rankTextView = (TextView) convertView
                .findViewById(R.id.rank_textview);
        rankTextView.setTypeface(null, Typeface.BOLD);
        rankTextView.setText(rank);

        //Set last name values
        TextView lastNameTextView = (TextView) convertView
                .findViewById(R.id.last_name_textview);
        lastNameTextView.setTypeface(null, Typeface.BOLD);
        lastNameTextView.setText(lastName);

        //Set score values
        TextView scoreTextView = (TextView) convertView
                .findViewById(R.id.score_textview);
        scoreTextView.setTypeface(null, Typeface.BOLD);
        scoreTextView.setText(String.valueOf(score));

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}