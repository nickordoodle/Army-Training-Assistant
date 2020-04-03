package com.example.armytrainingassistant;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;


    private ArrayList<Trainee> soldierTrainingList;

    public CustomExpandableListAdapter(Context context) {
        this.context = context;
        this.soldierTrainingList = new ArrayList<>();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CustomExpandableListAdapter{");
        sb.append("context=").append(context);
        sb.append(", expandableListMachineGunner=").append(soldierTrainingList);
        sb.append('}');
        return sb.toString();
    }

    public void addItem(final Trainee item) {
        soldierTrainingList.add(item);
        this.notifyDataSetChanged();
    }

    public void removeItem(final Trainee item) {
        soldierTrainingList.remove(item);
        this.notifyDataSetChanged();
    }

    public void replaceData(ArrayList<Trainee> newList){
        this.soldierTrainingList.clear();
        soldierTrainingList.addAll(newList);
    }

    public ArrayList<Trainee> getSoldierTrainingList() {
        return soldierTrainingList;
    }

    public void deleteAllData(){
        this.soldierTrainingList.clear();
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        Trainee current = this.soldierTrainingList.get(listPosition);

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
        Trainee currentGunner = this.soldierTrainingList.get(listPosition);
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
        return this.soldierTrainingList.get(listPosition);
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void notifyDataSetInvalidated() {
        super.notifyDataSetInvalidated();
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return this.soldierTrainingList.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String rank = this.soldierTrainingList
                .get(listPosition)
                .getRank();
        String lastName = this.soldierTrainingList
                .get(listPosition)
                .getLastName();
        int score = this.soldierTrainingList
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
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int getChildType(int groupPosition, int childPosition) {
        return super.getChildType(groupPosition, childPosition);
    }

    @Override
    public int getChildTypeCount() {
        return super.getChildTypeCount();
    }

    @Override
    public int getGroupType(int groupPosition) {
        return super.getGroupType(groupPosition);
    }

    @Override
    public int getGroupTypeCount() {
        return super.getGroupTypeCount();
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }


}