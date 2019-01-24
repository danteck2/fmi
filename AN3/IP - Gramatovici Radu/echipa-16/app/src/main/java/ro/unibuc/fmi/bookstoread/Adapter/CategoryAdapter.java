package ro.unibuc.fmi.bookstoread.Adapter;



import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ro.unibuc.fmi.bookstoread.Fragment.BookshelvesFragment;
import ro.unibuc.fmi.bookstoread.Fragment.DiscoverFragment;
import ro.unibuc.fmi.bookstoread.Fragment.ProfileFragment;
import ro.unibuc.fmi.bookstoread.R;

/**
 * Created by Gabi on 19/03/2018.
 */

public class CategoryAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public CategoryAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    /**
     * Return the {@link Fragment} that should be displayed for the given page number.
     */
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new ProfileFragment();
        } else if (position == 1) {
            return new BookshelvesFragment();
        } else {
            return new DiscoverFragment();
        }
    }

    /**
     * Return the total number of pages.
     */
    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.category_profile);
        } else if (position == 1) {
            return mContext.getString(R.string.category_bookshelves);
        } else {
            return mContext.getString(R.string.category_discover);
        }
    }
}
