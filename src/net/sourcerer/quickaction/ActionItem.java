package net.sourcerer.quickaction;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Button class for QuickActionBar, will be added to a bar by addActionItem(final ActionItem action).
 * ActionItem can change there depended View object by using the setters. Be careful this also means
 * an ActionItem can only be connected to one QuickActionBar.
 *
 * The used android layout for the buttons need a imageView with id 'qa_icon' and a TextView with id 'qa_title'.
 *
 * @author Sourcerer
 *
 */
public class ActionItem {

    private Drawable icon;
    private String title;

    private boolean selected = false;
    private boolean sticky = false;

    private Integer minWidth = null;

    private QuickActionOnClickListner onClickListner;
    private QuickActionOnOpenListner onOnOpenListner;

    private View button;
    private ImageView imageView;
    private TextView textView;

    public ActionItem(String title, Drawable icon) {
        this.title = title;
        this.icon = icon;
    }

    protected void init(final View button) {
        this.button = button;

        // fetching correct id for ImageView and TextView
        Resources res = button.getResources();
        int imageViewId = res.getIdentifier("qa_icon", "id", button.getContext().getPackageName());
        int textViewId = res.getIdentifier("qa_title", "id", button.getContext().getPackageName());

        imageView = (ImageView) button.findViewById(imageViewId);
        textView = (TextView) button.findViewById(textViewId);

        setTitle(title);
        setIcon(icon);

        button.setSelected(selected);
        button.setFocusable(true);
        button.setClickable(true);

        if (minWidth != null) {
            button.setMinimumWidth(minWidth);
        }
    }

    /**
     * If the ActionItem is already attached to QuickActionBar the button will be changed too.
     * Null will hide the title.
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;

        if (textView != null) {
            textView.setText(title);

            if (title == null) {
                textView.setVisibility(View.GONE);
            } else {
                textView.setVisibility(View.VISIBLE);
            }
        }
    }

    public String getTitle() {
        return this.title;
    }

    /**
     * If the ActionItem is already attached to QuickActionBar the button will be changed too.
     * Null will hide the icon.
     * @param title
     */
    public void setIcon(Drawable icon) {
        this.icon = icon;

        if (imageView != null) {
            imageView.setImageDrawable(icon);

            if (icon == null) {
                imageView.setVisibility(View.GONE);
            } else {
                imageView.setVisibility(View.VISIBLE);
            }
        }
    }

    public Drawable getIcon() {
        return this.icon;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;

        if (button != null) {
            button.setSelected(selected);
        }
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setSticky(boolean sticky) {
        this.sticky = sticky;
    }

    public boolean isSticky() {
        return sticky;
    }

    public void setMinWidth(Integer width) {
        minWidth = width;

        if (button != null) {
            button.setMinimumWidth(minWidth);
        }
    }

    public Integer getMinWidth() {
        return minWidth;
    }

    public void setQuickActionClickListner(QuickActionOnClickListner onClickListner) {
        this.onClickListner = onClickListner;
    }

    public QuickActionOnClickListner getQuickActionClickListner() {
        return onClickListner;
    }

    public boolean hasOnClickListner() {
        return onClickListner != null;
    }

    public void setQuickActionOnOpenListner(QuickActionOnOpenListner onOpenListner) {
        this.onOnOpenListner = onOpenListner;
    }

    public QuickActionOnOpenListner getQuickActionOnOpenListner() {
        return onOnOpenListner;
    }

    public boolean hasOnOpenListner() {
        return onOnOpenListner != null;
    }

    /**
     * Can only return isEnabled after beeing added to a QuickActionBar
     */
    public void setEnabled(boolean value) {
        button.setEnabled(value);
    }

    /**
     * Can only return isEnabled after beeing added to a QuickActionBar
     * @return
     */
    public boolean isEnabled() {
        return button.isEnabled();
    }

    /**
     * Can only return isEnabled after beeing added to a QuickActionBar
     */
    public void setVisibility(int value) {
        button.setVisibility(value);
    }

    protected View getView() {
        return button;
    }

}
